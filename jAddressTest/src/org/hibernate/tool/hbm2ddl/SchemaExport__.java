package org.hibernate.tool.hbm2ddl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.cfg.Settings;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.ConnectionProviderFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.pretty.DDLFormatter;
import org.hibernate.util.ConfigHelper;
import org.hibernate.util.JDBCExceptionReporter;
import org.hibernate.util.PropertiesHelper;
import org.hibernate.util.ReflectHelper;

public class SchemaExport__
{
  private static final Log log = LogFactory.getLog(SchemaExport__.class);
  private ConnectionHelper connectionHelper;
  private String[] dropSQL;
  private String[] createSQL;
  private String outputFile = null;
  private String importFile = "/import.sql";
  private Dialect dialect;
  private String delimiter;
  private final List exceptions = new ArrayList();
  private boolean haltOnError = false;
  private boolean format = true;

  public SchemaExport__(Configuration cfg)
    throws HibernateException
  {
    this(cfg, cfg.getProperties());
  }

  public SchemaExport__(Configuration cfg, Settings settings)
    throws HibernateException
  {
    this.dialect = settings.getDialect();
    this.connectionHelper = new SuppliedConnectionProviderConnectionHelper(settings.getConnectionProvider());

    this.dropSQL = cfg.generateDropSchemaScript(this.dialect);
    this.createSQL = cfg.generateSchemaCreationScript(this.dialect);
    this.format = settings.isFormatSqlEnabled();
  }

  /** @deprecated */
  public SchemaExport__(Configuration cfg, Properties properties)
    throws HibernateException
  {
    this.dialect = Dialect.getDialect(properties);

    Properties props = new Properties();
    props.putAll(this.dialect.getDefaultProperties());
    props.putAll(properties);

    this.connectionHelper = new ProviderConnectionHelper(props);
    this.dropSQL = cfg.generateDropSchemaScript(this.dialect);
    this.createSQL = cfg.generateSchemaCreationScript(this.dialect);
    this.format = PropertiesHelper.getBoolean("hibernate.format_sql", props);
  }

  public SchemaExport__(Configuration cfg, Connection connection) {
    this.connectionHelper = new SuppliedConnectionHelper(connection);
    this.dialect = Dialect.getDialect(cfg.getProperties());
    this.dropSQL = cfg.generateDropSchemaScript(this.dialect);
    this.createSQL = cfg.generateSchemaCreationScript(this.dialect);
  }

  public SchemaExport__ setOutputFile(String filename)
  {
    this.outputFile = filename;
    return this;
  }

  public SchemaExport__ setImportFile(String filename) {
    this.importFile = filename;
    return this;
  }

  public SchemaExport__ setDelimiter(String delimiter)
  {
    this.delimiter = delimiter;
    return this;
  }

  public void create(boolean script, boolean export)
  {
    execute(script, export, false, false);
  }

  public void drop(boolean script, boolean export)
  {
    execute(script, export, true, false);
  }

  private String format(String sql) {
    return this.format ? new DDLFormatter(sql).format() : sql;
  }

  public void execute(boolean script, boolean export, boolean justDrop, boolean justCreate)
  {
    log.info("Running hbm2ddl schema export");

    Connection connection = null;
    Writer outputFileWriter = null;
    Reader importFileReader = null;
    Statement statement = null;

    this.exceptions.clear();
    try
    {
      try
      {
        InputStream stream = ConfigHelper.getResourceAsStream(this.importFile);
        importFileReader = new InputStreamReader(stream,"UTF-8");
      }
      catch (HibernateException e)
      {
        log.debug("import file not found: " + this.importFile);
      }

      if (this.outputFile != null) {
        log.info("writing generated schema to file: " + this.outputFile);
        outputFileWriter = new FileWriter(this.outputFile);
      }

      if (export) {
        log.info("exporting generated schema to database");
        connection = this.connectionHelper.getConnection();
        if (!connection.getAutoCommit()) {
          connection.commit();
          connection.setAutoCommit(true);
        }
        statement = connection.createStatement();
      }

      if (!justCreate) {
        drop(script, export, outputFileWriter, statement);
      }

      if (!justDrop) {
        create(script, export, outputFileWriter, statement);
        if ((export) && (importFileReader != null)) {
          importScript(importFileReader, statement);
        }
      }

      log.info("schema export complete");
    }
    catch (Exception e)
    {
      this.exceptions.add(e);
      log.error("schema export unsuccessful", e);
    }
    finally
    {
      try
      {
        if (statement != null) statement.close();
        if (connection != null) this.connectionHelper.release(); 
      }
      catch (Exception e)
      {
        this.exceptions.add(e);
        log.error("Could not close connection", e);
      }
      try
      {
        if (outputFileWriter != null) outputFileWriter.close();
        if (importFileReader != null) importFileReader.close(); 
      }
      catch (IOException ioe)
      {
        this.exceptions.add(ioe);
        log.error("Error closing output file: " + this.outputFile, ioe);
      }
    }
  }

  private void importScript(Reader importFileReader, Statement statement)
    throws IOException
  {
    log.info("Executing import script: " + this.importFile);
    BufferedReader reader = new BufferedReader(importFileReader);
    for (String sql = reader.readLine(); sql != null; sql = reader.readLine())
      try {
        String trimmedSql = sql.trim();
        if ((trimmedSql.length() != 0) && (!trimmedSql.startsWith("--")) && (!trimmedSql.startsWith("//")) && (trimmedSql.startsWith("/*")))
        {
          continue;
        }

        if (trimmedSql.endsWith(";")) {
          trimmedSql = trimmedSql.substring(0, trimmedSql.length() - 1);
        }
        log.debug(trimmedSql);
        System.out.println(trimmedSql);
        statement.execute(trimmedSql);
      }
      catch (SQLException e)
      {
        throw new JDBCException("Error during import script execution", e);
      }
  }

  private void create(boolean script, boolean export, Writer fileOutput, Statement statement)
    throws IOException
  {
    for (int j = 0; j < this.createSQL.length; j++)
      try {
        execute(script, export, fileOutput, statement, this.createSQL[j]);
      }
      catch (SQLException e) {
        if (this.haltOnError) {
          throw new JDBCException("Error during DDL export", e);
        }
        this.exceptions.add(e);
        log.error("Unsuccessful: " + this.createSQL[j]);
        log.error(e.getMessage());
      }
  }

  private void drop(boolean script, boolean export, Writer fileOutput, Statement statement)
    throws IOException
  {
    for (int i = 0; i < this.dropSQL.length; i++)
      try {
        execute(script, export, fileOutput, statement, this.dropSQL[i]);
      }
      catch (SQLException e) {
        this.exceptions.add(e);
        log.debug("Unsuccessful: " + this.dropSQL[i]);
        log.debug(e.getMessage());
      }
  }

  private void execute(boolean script, boolean export, Writer fileOutput, Statement statement, String sql)
    throws IOException, SQLException
  {
    String formatted = format(sql);
    if (this.delimiter != null) formatted = formatted + this.delimiter;
    if (script) System.out.println(formatted);
    log.debug(formatted);
    if (this.outputFile != null) fileOutput.write(formatted + "\n");
    if (export) statement.executeUpdate(sql); 
  }

  public static void main(String[] args)
  {
    try {
      Configuration cfg = new Configuration();

      boolean script = true;
      boolean drop = false;
      boolean create = false;
      boolean halt = false;
      boolean export = true;
      String outFile = null;
      String importFile = "/import.sql";
      String propFile = null;
      boolean format = false;
      String delim = null;

      for (int i = 0; i < args.length; i++) {
        if (args[i].startsWith("--")) {
          if (args[i].equals("--quiet")) {
            script = false;
          }
          else if (args[i].equals("--drop")) {
            drop = true;
          }
          else if (args[i].equals("--create")) {
            create = true;
          }
          else if (args[i].equals("--haltonerror")) {
            halt = true;
          }
          else if (args[i].equals("--text")) {
            export = false;
          }
          else if (args[i].startsWith("--output=")) {
            outFile = args[i].substring(9);
          }
          else if (args[i].startsWith("--import=")) {
            importFile = args[i].substring(9);
          }
          else if (args[i].startsWith("--properties=")) {
            propFile = args[i].substring(13);
          }
          else if (args[i].equals("--format")) {
            format = true;
          }
          else if (args[i].startsWith("--delimiter=")) {
            delim = args[i].substring(12);
          }
          else if (args[i].startsWith("--config=")) {
            cfg.configure(args[i].substring(9));
          }
          else if (args[i].startsWith("--naming=")) {
            cfg.setNamingStrategy((NamingStrategy)ReflectHelper.classForName(args[i].substring(9)).newInstance());
          }

        }
        else
        {
          String filename = args[i];
          if (filename.endsWith(".jar")) {
            cfg.addJar(new File(filename));
          }
          else {
            cfg.addFile(filename);
          }
        }

      }

      if (propFile != null) {
        Properties props = new Properties();
        props.putAll(cfg.getProperties());
        props.load(new FileInputStream(propFile));
        cfg.setProperties(props);
      }

      SchemaExport__ se = new SchemaExport__(cfg).setHaltOnError(halt).setOutputFile(outFile).setImportFile(importFile).setDelimiter(delim);

      if (format) se.setFormat(true);
      se.execute(script, export, drop, create);
    }
    catch (Exception e)
    {
      log.error("Error creating schema ", e);
      e.printStackTrace();
    }
  }

  public List getExceptions()
  {
    return this.exceptions;
  }

  public SchemaExport__ setFormat(boolean format)
  {
    this.format = format;
    return this;
  }

  public SchemaExport__ setHaltOnError(boolean haltOnError)
  {
    this.haltOnError = haltOnError;
    return this;
  }

  private class ProviderConnectionHelper
    implements SchemaExport__.ConnectionHelper
  {
    private Properties cfgProperties;
    private ConnectionProvider connectionProvider;
    private Connection connection;

    public ProviderConnectionHelper(Properties cfgProperties)
    {
      this.cfgProperties = cfgProperties;
    }

    public Connection getConnection() throws SQLException {
      if (this.connection == null) {
        this.connectionProvider = ConnectionProviderFactory.newConnectionProvider(this.cfgProperties);
        this.connection = this.connectionProvider.getConnection();
        if (!this.connection.getAutoCommit()) {
          this.connection.commit();
          this.connection.setAutoCommit(true);
        }
      }
      return this.connection;
    }

    public void release() throws SQLException {
      if (this.connection != null) {
        JDBCExceptionReporter.logAndClearWarnings(this.connection);
        this.connectionProvider.closeConnection(this.connection);
        this.connectionProvider.close();
      }
      this.connection = null;
    }
  }

  private class SuppliedConnectionProviderConnectionHelper
    implements SchemaExport__.ConnectionHelper
  {
    private ConnectionProvider provider;
    private Connection connection;

    public SuppliedConnectionProviderConnectionHelper(ConnectionProvider provider)
    {
      this.provider = provider;
    }

    public Connection getConnection() throws SQLException {
      if (this.connection == null) {
        this.connection = this.provider.getConnection();
        if (!this.connection.getAutoCommit()) {
          this.connection.commit();
          this.connection.setAutoCommit(true);
        }
      }
      return this.connection;
    }

    public void release() throws SQLException {
      if (this.connection != null) {
        JDBCExceptionReporter.logAndClearWarnings(this.connection);
        this.provider.closeConnection(this.connection);
        this.connection = null;
      }
    }
  }

  private class SuppliedConnectionHelper
    implements SchemaExport__.ConnectionHelper
  {
    private Connection connection;

    public SuppliedConnectionHelper(Connection connection)
    {
      this.connection = connection;
    }

    public Connection getConnection() {
      return this.connection;
    }

    public void release() {
      JDBCExceptionReporter.logAndClearWarnings(this.connection);
      this.connection = null;
    }
  }

  private static abstract interface ConnectionHelper
  {
    public abstract Connection getConnection()
      throws SQLException;

    public abstract void release()
      throws SQLException;
  }
}