package server;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {
	  private Client() {
	  }
	 
	  public static void main(String[] args) throws Exception{
	    //create WebService client proxy factory
	    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	    //register WebService interface
	    factory.setServiceClass(IHelloWorld.class);
	    //set webservice publish address to factory.
	    factory.setAddress("http://localhost:9000/HelloWorld");
	    
	    IHelloWorld iHelloWorld = (IHelloWorld) factory.create();
	    System.out.println("invoke webservice...");
	    System.out.println("message context is:" + iHelloWorld.loadAllDriver2());
	    
	    
	    
	    
	    System.exit(0);
	  }
	}
