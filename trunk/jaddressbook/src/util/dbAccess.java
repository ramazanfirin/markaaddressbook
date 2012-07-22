/********************************************************************************
 *
 * jAddressBook - advanced address book
 *
 * Copyright (C) 2006 jAddressBook development team
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details ( see the LICENSE file ).
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 ********************************************************************************/

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * DataBase Access for jAddressBook
 * @author binary
 */
public class dbAccess {
    private Properties dbSettings;
    private Connection dbConnection;
    private boolean haveDriver;
    
    /** Creates a new instance of dbAccess */
    public  dbAccess(Properties prop) {
        dbSettings=prop;
        //Try to Load JDBC Driver
        try { 
        Class.forName(dbSettings.getProperty("JDBCDriver")).newInstance();
        } catch (Exception ex) {
                ex.printStackTrace();
                haveDriver=false;
        }
        haveDriver=true;
    }
    
    /*
     * Get Driver Data
     * @return Return if have driver or not
     */
     public boolean getDriverData()
     {
         return this.haveDriver;
     }
     
    /*
     * Connect to DataBase
     * @return true on success false on failed
     */
    public String dbConnect(){
        try {
        	System.out.println("jdbc:"+dbSettings.getProperty("dbType")+"://"+dbSettings.getProperty("dbHost")+"/"+dbSettings.getProperty("dbLogin")+dbSettings.getProperty("dbPassword"));;
        dbConnection = DriverManager.getConnection("jdbc:"+dbSettings.getProperty("dbType")+"://"+dbSettings.getProperty("dbHost")+"/",dbSettings.getProperty("dbLogin"),dbSettings.getProperty("dbPassword"));
        dbConnection.createStatement().executeUpdate("use "+dbSettings.getProperty("dbName")+" ; ");
        }catch(SQLException e) {
            e.printStackTrace();
            return e.getMessage();
            
        }
     return null;
    }

    /*
     * Select DB to work
     * @return 0 on success -1 if failed
     */
    public void selectDB() throws SQLException{
    	dbConnection.createStatement().executeUpdate("use "+dbSettings.getProperty("dbName")+" ; ");
    }
    
    /*
     * Initialize DB
     * @return 0 on success -1 on error
     */
    public int initDB(){
    	try {
    		//Drop Database
    		dbConnection.createStatement().executeUpdate("drop database "+dbSettings.getProperty("dbName")+" ;");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	try {
    		//Create Database
    		dbConnection.createStatement().executeUpdate("create database "+dbSettings.getProperty("dbName")+" ;");
    		dbConnection.createStatement().executeUpdate("use "+dbSettings.getProperty("dbName")+" ;");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	try {
    		//Create tables 
    		dbConnection.createStatement().executeUpdate(
    				"create table addressbook ("+
    				"        ID int  unsigned  not null primary key auto_increment,"+
    				"	owner int unsigned not null references addressbook(ID),"+
    				"        name varchar(200),"+
    				"        comments varchar(200)"+
    				"	);\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table address ("+
    				"	ID int  unsigned  not null primary key auto_increment,"+
    				"	owner int unsigned not null references person(ID),"+
    				"	address varchar(200),"+
    				"	comments varchar(200),"+
    				"	first int unsigned"+
    				"	);\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table email ("+
    				"        ID int  unsigned  not null primary key auto_increment,"+
    				"        owner int unsigned not null references person(ID),"+
    				"        email varchar(50),"+
    				"        comments varchar(200),"+
    				"        first int unsigned"+
    				"        );\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table fax ("+
    				"        ID int  unsigned  not null primary key auto_increment,"+
    				"        owner int unsigned not null references person(ID),"+
    				"        fax varchar(50),"+
    				"        comments varchar(200),"+
    				"        first int unsigned"+
    				"        );\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table foto ("+
    				"        ID int  unsigned  not null primary key auto_increment,"+
    				"        owner int unsigned not null references person(ID),"+
    				"        foto longblob ,"+
    				"        comments varchar(200)"+
    				"        );\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table person ("+
    				"        ID int  unsigned  not null primary key auto_increment,"+
    				"	addressbook int unsigned not null references addressbook(ID),"+
    				"	surname varchar(200),"+
    				"	name varchar(200),"+
    				"	nickname varchar(200),"+
    				"	comments longtext"+
    				"	);\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table telmob ("+
    				"        ID int  unsigned  not null primary key auto_increment,"+
    				"        owner int unsigned not null references person(ID),"+
    				"        telmob varchar(50),"+
    				"        comments varchar(200),"+
    				"        first int unsigned"+
    				"        );\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table tel ("+
    				"	ID int  unsigned  not null primary key auto_increment,"+
    				"	owner int unsigned not null references person(ID),"+
    				"	tel varchar(50),"+
    				"	comments varchar(200),"+
    				"	first int unsigned"+
    				"	);\n");
    				dbConnection.createStatement().executeUpdate(
    				"create table web ("+
    				"        ID int  unsigned  not null primary key auto_increment,"+
    				"        owner int unsigned not null references person(ID),"+
    				"        web varchar(200),"+
    				"        comments varchar(200),"+
    				"        first int unsigned"+
    				"        );\n");
    				
    		return 0;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return -1;
    	}
    }
    /*
     * Work with AddressBook 
     */
    
    /*
     * Add new Address Book
     * @param ownerID ID of owner AddressBook (0 if no parent)
     * @param name Name of Address Book
     * @param comments comments for addressbook
     */
    public void addAddressBook(String ownerID,String name,String comments) {
        try {
            dbConnection.createStatement().executeUpdate("insert into addressbook values(NULL,'"+ownerID+"','"+name+"','"+comments+"' );");
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Remove Address Book
     * @param ID ID of Address Book 
     */
    public void removeAddressBook(String ID) {
        try {
            dbConnection.createStatement().executeUpdate("delete from addressbook where ID='"+ID+"'");
    }catch(SQLException e) {
            e.printStackTrace();
    }
    }
    
    /*
     * Get Address Book Data
     * @param ID Address Book ID
     */
    public String[] getAddressBook(String ID) {
    	try {
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from addressbook where ID='"+ID+"' ;");
    		String data[]=new String[4];
    		
    		result.next();
    		data[0]=result.getString("ID");
            data[1]=result.getString("owner");
            data[2]=result.getString("name");
            data[3]=result.getString("comments");
            
            return data;
    	}catch(SQLException e) {
    		return null;
    	}
    }
    
    /*
     * Get Address Book list
     * @return Address Book List
     */
    public String[][] getAddressBooks() {
    try {
        ResultSet result=dbConnection.createStatement().executeQuery("select * from addressbook order by ID asc;");
        
        result.last();
        int length=result.getRow();
        if (length==0)
        	return null;
        String data[][]=new String[length][4];
        int i=0;
        result.first();
        
        do {
           data[i][0]=result.getString("ID");
           data[i][1]=result.getString("owner");
           data[i][2]=result.getString("name");
           data[i][3]=result.getString("comments");
           //data[i][4]=i+"";
           i++;
        }while(result.next());
        return data;
    }catch(SQLException e){
        e.printStackTrace();
        return null;
    }
    }
    
    /*
     * Update Address Book Data
     * @param ID Address Book ID
     * @param owner Owner of Address Book
     * @param name Name of Address Book
     * @param comments Comments for Address Book
     */
    public void updateAddressBook(String ID,String owner,String name,String comments) {
    	try {
    		dbConnection.createStatement().executeUpdate("update addressbook set owner='"+owner+"', name='"+name+"', comments='"+comments+"' where ID='"+ID+"' ;");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /*
     * ========================================
     * Work With persons
     * ========================================
     */
    
    /*
     * Add person in "addressBookID" 
     * @param addressBookID ID of Address Book
     * @return ID of person
     */
    public String addPerson(String addressBookID) {
    	try {
    		dbConnection.createStatement().executeUpdate("insert into person values(NULL,'"+addressBookID+"','','','',''); ");
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from person order by ID desc ; ");
    		result.next();
    		return result.getString("ID");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return -1+"";
    	}
    }
    
   
    /*
     * Get Raw person Data
     * @param ownerID ID of owner
     * @param tableName Name of Table
     */
    public ResultSet getRawData(String ownerID,String tableName) {
    	try {
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from "+tableName+" where owner='"+ownerID+"' order by ID asc ;");
    		return result;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*
     * Set person personal data
     * @param personID ID of person
     * @param data Data to set
     */
    public void setPersonPersonalData(String personID,String data[]) {
    	try {
    		dbConnection.createStatement().executeUpdate("update person set addressbook='"+data[0]+"', surname='"+data[1]+"', name='"+data[2]+"',  nickname='"+data[3]+"', comments='"+data[4]+"' where ID='"+personID+"' ;");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /*
     * Get person personal data
     * @parap personID ID of person
     */
    public String[] getPersonPersonalData(String personID) {
    	try {
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from person where ID='"+personID+"' ");
    		String data[]=new String[6];
    		
    		result.next();
    		
    		data[0]=result.getString(1);
    		data[1]=result.getString(2);
    		data[2]=result.getString(3);
    		data[3]=result.getString(4);
    		data[4]=result.getString(5);
    		data[5]=result.getString(6);
    		
    		return data;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*
     * Set user's foto data
     * @param userID ID of user
     * @param foto Foto stream
     * @param length Length o foto stream
     */
    public void setPersonFoto(String userID,InputStream foto,long length) {
    	try {
    		PreparedStatement ps=dbConnection.prepareStatement("update foto set foto=? where owner='"+userID+"' ;");
    		ps.setBinaryStream(1, foto, (int)length);
    		ps.execute();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /*
     * Get user's foto
     * @param userID ID of user
     */
    public InputStream getPersonFoto(String userID) {
    	try {
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from foto where owner='"+userID+"' ;");
    		result.next();
    		return result.getBinaryStream("foto");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*
     * Set person's foto comments
     * @param personID ID of person
     * @param comments comments for foto
     */
    public void setPersonFotoComments(String personID,String comments) {
    	try {
    		dbConnection.createStatement().executeUpdate("update foto set comments='"+comments+"' where owner='"+personID+"' ;");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /*
     * Get person's foto comments
     * @param personID ID of person
     */
    public String getPersonFotoComments(String presonID) {
    	try {
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from foto where owner='"+presonID+"' ;");
    		result.next();
    		return result.getString("comments");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*
     * Set person's data
     * @param personID ID of person
     * @param pTable Person data table
     * @param tableName name of table to store data
     */
    public void setPersonData(String personID,Table pTable,String tableName) {
    	try {
    		dbConnection.createStatement().executeUpdate("delete from "+tableName+" where owner='"+personID+"'");
    		for(int i=0;i<pTable.getItemCount();i++) {
    			dbConnection.createStatement().executeUpdate("insert into "+tableName+" values(NULL,'"+personID+"','"+pTable.getItem(i).getText(0)+"','"+pTable.getItem(i).getText(1)+"','"+(pTable.getItem(i).getText(2).equals("*") ? "1" : "0" )+"' );");
    		}
    		
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }
    
    /*
     * Get persons data
     * @param abooks Address Books Array
     */
    public String[][] getPersonPrimaryData(String[] abooks) {
    	try {
    		String query="";
    		if (abooks.length==0)
    			return null;
    		for(int i=0;i<abooks.length;i++)
    				if (abooks[i].equals("0")) {
    					query="select * from person ";
    					break;
    				}
    		if (query.length()==0) {
    			query="select * from person where ";
    			for(int i=0;i<abooks.length;i++)
    				if (i<abooks.length-1)
    				query=query+"addressbook='"+abooks[i]+"' OR  ";
    				else
    					query=query+"addressbook='"+abooks[i]+"' ";
    		}
    		//Setup direction
    		query=query+" order by ID asc ; ";
    		
    		
    		ResultSet result=dbConnection.createStatement().executeQuery(query);
    		
    		result.last();
            int length=result.getRow();

            if (length==0)
            		return null;
            
    		String data[][]=new String[length][11];
    		int id=0;
    		result.first();
    		String ID;
    		do {
    			for(int i=0;i<data[id].length;i++)
    				data[id][i]="";
    			data[id][0]=result.getString("ID");
    			/*
    			 * Get Address Book Name
    			 */
    			ResultSet result2=dbConnection.createStatement().executeQuery("select * from addressbook where ID='"+result.getString("addressbook")+"' ;");
    			result2.next();
    			data[id][1]=result2.getString("name");
    			data[id][2]=result.getString("surname");
    			data[id][3]=result.getString("name");
    			data[id][4]=result.getString("nickname");
    			//Extract Primary Data
    			ID=result.getString("ID");
    			data[id][5]=getPrimaryData(ID,"address","address");
    			data[id][6]=getPrimaryData(ID,"tel","tel");
    			data[id][7]=getPrimaryData(ID,"telmob","telmob");
    			data[id][8]=getPrimaryData(ID,"fax","fax");
    			data[id][9]=getPrimaryData(ID,"web","web");
    			data[id][10]=getPrimaryData(ID,"email","email");
    			id++;
    		}while(result.next());
    		return data;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*
     * Get Primary Data about person from table
     * @param personID ID of person
     * @param table Table to select data
     * @param col Column with data
     */
    private String getPrimaryData(String personID,String table,String col) {
    	try {
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from "+table+" where owner='"+personID+"' and first='1' ;");
    		result.next();
    		return result.getString(col);
    	}catch(SQLException e) {

    		return "";
    	}
    }
    
    /*
     * Delete person from DB
     * @param personID delete person from DB
     */
    public void deletePerson(String personID) {
    	try {
    		dbConnection.createStatement().executeUpdate("delete from person where ID='"+personID+"' ;");
    		dbConnection.createStatement().executeUpdate("delete from address where owner='"+personID+"' ;");
    		dbConnection.createStatement().executeUpdate("delete from tel where owner='"+personID+"' ;");
    		dbConnection.createStatement().executeUpdate("delete from telmob where owner='"+personID+"' ;");
    		dbConnection.createStatement().executeUpdate("delete from fax where owner='"+personID+"' ;");
    		dbConnection.createStatement().executeUpdate("delete from web where owner='"+personID+"' ;");
    		dbConnection.createStatement().executeUpdate("delete from email where owner='"+personID+"' ;");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    /*
     * Register new Person
     * @param aBookID Address Book ID
     * @return person ID
     */
    public String registerPerson(String aBookID) {
    	try {
    		dbConnection.createStatement().executeUpdate("insert into person values(NULL,'"+aBookID+"','','','','') ;");
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from person order by ID desc");
    		result.next();
    		String personID=result.getString("ID");
    		
    		//Setup Default foto
    		File foto=new File("./img/nofoto.png");
    		PreparedStatement ps=dbConnection.prepareStatement("insert into foto values(NULL,'"+personID+"',?,'' );");
    		try {
				ps.setBinaryStream(1, new FileInputStream(foto), (int)foto.length());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		ps.execute();
    		
    		return personID;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*
     * Get person's primary data
     * @param personID ID of person
     * @return Array with data about person
     */
    public String[] getPersonPrimaryData(String personID) {
    	try {
    		ResultSet result=dbConnection.createStatement().executeQuery("select * from person where ID='"+personID+"' ;");
    		result.next();
    		String data[]=new String[11];
    		
    		data[0]=result.getString("ID");
    		
    		ResultSet result2=dbConnection.createStatement().executeQuery("select * from addressbook where ID='"+result.getString("addressbook")+"' ;");
			result2.next();
			data[1]=result2.getString("name");
			data[2]=result.getString("surname");
			data[3]=result.getString("name");
			data[4]=result.getString("nickname");
			//Extract Primary Data
			String ID=result.getString("ID");
			data[5]=getPrimaryData(ID,"address","address");
			data[6]=getPrimaryData(ID,"tel","tel");
			data[7]=getPrimaryData(ID,"telmob","telmob");
			data[8]=getPrimaryData(ID,"fax","fax");
			data[9]=getPrimaryData(ID,"web","web");
			data[10]=getPrimaryData(ID,"email","email");
    		
    		return data;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    /*
     * Exec Search Query
     * @param abooks Address Books Array
     * @param svalues Search Masc
     * @param switches AND/OR switches
     */
    public String[][] ExecSearchQuery(String[] abooks,Text[] svalues,Button[] switches) {
    	try {
    		int type=0;
    		String query="";
    		for(int i=0;i<abooks.length;i++)
    				if (abooks[i].equals("0")) {
    					query="select ID from person ";
    					break;
    				}
    		if (query.length()==0) {
    			type=1;
    			query="select ID from person where ";
    			for(int i=0;i<abooks.length;i++)
    				if (i<abooks.length-1)
    				query=query+"addressbook='"+abooks[i]+"' OR  ";
    				else
    					query=query+"addressbook='"+abooks[i]+"' ";
    		}
    		
    		/*
    		 * Setup 'LIKE' 
    		 */
    		if (type==0)
    			query=query+" where ";
    			else
    				query=query+" AND ";
    		int first=0;
    		String[] Secfields=new String[]{"address","tel","telmob","fax","email","web"};
    		for(int i=0;i<Secfields.length;i++) 
    			if (svalues[i+3].getText().length()!=0) {
    				if (first!=0) 
    				 {
    					if (switches[i+3].getText().equals(" OR ")) 
    							query=query+" OR ";
    					else
    						query=query+" AND ";
    				} else 
    					first=1;
    			query=query+"  ID in (select owner from "+Secfields[i]+" where "+Secfields[i]+" like '"+svalues[i+3].getText()+"') ";
    		}
    		
    		String[] fields=new String[]{"surname","name","nickname"};
    		
    		for(int i=0;i<fields.length;i++) 
    			if (svalues[i].getText().length()!=0) {
    				if (first!=0) 
    				 {
    					if (switches[i].getText().equals(" OR ")) 
    							query=query+" OR ";
    					else
    						query=query+" AND ";
    				} else 
    					first=1;
    			query=query+"  "+fields[i]+" like '"+svalues[i].getText()+"' ";
    		}
    		//Setup direction
    		query=query+" order by ID asc ; ";
    		
    		//System.out.println("Query : "+query);
    		ResultSet result=dbConnection.createStatement().executeQuery(query);
    		
    		result.last();
            int length=result.getRow();

    		String data[][]=new String[length][11];
    		int id=0;
    		result.first();
    		
    		do {
    			String pID=result.getString("ID");
    			data[id]=getPersonPrimaryData(pID);
    			id++;
    		}while(result.next());
    		return data;
    	}catch(SQLException e) {
    		//e.printStackTrace();
    		return null;
    	}
    }
    
}
