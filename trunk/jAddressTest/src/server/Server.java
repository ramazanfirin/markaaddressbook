package server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import util.Util;

public class Server {
	public Server() {
	  IHelloWorld helloWorld = new HelloWorldImpl();
	  //create WebService service factory
	  JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
	  //register WebService interface
	  factory.setServiceClass(IHelloWorld.class);
	  //publish the interface
	  factory.setAddress("http://localhost:"+Util.getParameter("localServerPort")+"/OtobusFihrist");
	  factory.setServiceBean(helloWorld);
	  //create WebService instance
	  factory.create();
	}
	 
	public static void main(String[] args) throws InterruptedException {
	  //now start the webservice server
	  new Server();
	  System.out.println("Server ready...");
	  Thread.sleep(1000 * 600);
	  System.out.println("Server exit...");
	  System.exit(0);
	}
	}