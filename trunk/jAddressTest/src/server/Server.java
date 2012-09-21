package server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.DestinationFactory;

import util.Util;

public class Server {
	public Server() {
	  IHelloWorld helloWorld = new HelloWorldImpl();
	  //create WebService service factory
	  JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
	  //register WebService interface
	  factory.setServiceClass(IHelloWorld.class);
	  //publish the interface
	  factory.setAddress("http://0.0.0.0:"+Util.getParameter("localServerPort")+"/OtobusFihrist");
	 // factory.setAddress("http://127.0.0.1:"+Util.getParameter("localServerPort")+"/OtobusFihrist");
	 // factory.setAddress("/"+Util.getParameter("localServerPort")+"/OtobusFihrist");
	  factory.setServiceBean(helloWorld);
	  //create WebService instance
	  org.apache.cxf.endpoint.Server s = factory.create();
	  
	  s.getDestination();
	 
	
	  System.out.println("dfsdf");
	  
//	  factory.setDestinationFactory(destinationFactory);
	

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