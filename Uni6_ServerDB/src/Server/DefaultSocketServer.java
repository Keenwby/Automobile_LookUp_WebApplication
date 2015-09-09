package Server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import Model.Automobile;
import Scale.EditThread;
import Adapter.BuildAuto;

public class DefaultSocketServer extends Thread implements SocketClientInterface,
SocketClientConstants{
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
    private ServerSocket serversock;
    private Socket sock;
    private String strHost;
    private int iPort;
    
    private AutoServer buildauto;

    public DefaultSocketServer(String strHost, int iPort, AutoServer buildauto) { 
    	this.strHost = strHost;
    	this.iPort = iPort;
    	this.buildauto = buildauto;
    	
    }//constructor

    public void run(){
       if (openConnection()){
          handleSession();
          closeSession();
       }
    }//run
    
    public boolean openConnection(){    	
    	   //create a new socket
    	   try {
    	     serversock = new ServerSocket(iPort);                    
    	   }
    	   catch(IOException socketError){
    	     if (DEBUG) System.err.println
    	        ("Unable to listen to Port: " + iPort);
    	     return false;
    	   }
    	   //ServerSocket accept
    	   serverAccept();
    	   
    	   try {
               oos = new ObjectOutputStream(sock.getOutputStream());
               ois = new ObjectInputStream(sock.getInputStream());
           } catch (IOException e) {
               e.printStackTrace();
           }
    	   
    	  return serversock!=null ? true : false;
    	}
    
    public boolean serverAccept(){
    	try {
    		sock = serversock.accept(); 
    		} catch (IOException e) {
    		System.err.println("Accept failed.");
    		System.exit(1); 
    		}
    	return sock!=null ? true : false;
    }
    
    //Server get an object from Client
    public Object getObject() throws IOException{
    	Object propobject = null;
       	try {
			propobject = ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return propobject;
    }
    //Server get a command from Client
    public int getCommand() throws IOException{
    	String get = null;
    	get = (String) getObject();
    	
    	return Integer.valueOf(get);
    }
    //Build an auto from properties file
    public void buildAuto() throws IOException{
    	
    	Object propobject = null;
    	propobject = getObject();
    	buildauto.buildAutofromProperties(propobject);
    }
    //Server send an object to Client
    public void sendObject(Object propsobject) throws IOException{
    	if (propsobject == null)
    		return;
		oos.writeObject(propsobject);
		oos.flush();
    }
    //Server send a command to Client
    public void sendCommand(int command) throws IOException{
    	
    	sendObject(Integer.toString(command));
    	System.out.println("Command " + command + " sent!");
    	oos.flush();
    }
    //Close Session
    public void closeSession(){
		try {
			ois = null;
			oos = null;
			sock.close();
		}
		catch (IOException e){
			if (DEBUG) System.err.println
			("Error closing socket to " + strHost);
   }       
}
    
    public void handleSession(){

    	  if (DEBUG) System.out.println ("Server: handling session with "
    	            + strHost + ":" + iPort);
    	  try {
    		  
    	    while(true){
    	    	//Get an command
    	    	System.out.println("Server: Waiting...");
    	    	int command = getCommand();
    	    	
    	    	switch(command){
    	    	
    	    	case STOP://Stop the server
    	    		System.out.println("Command " + command + " received!");
    	    		return;
    	    	
    	    	case SER_SAVE_FILE://receive an object and build an auto
    	    		System.out.println("Command " + command + " received!");
    	    		buildAuto();
    	    		System.out.println("Object built successful!");
    	    		sendCommand(SER_SAVE_FILE_SUCC);
    	    		break;
    	    		
    	    	case CLI_ALL_MODELS://receive the command of providing the list of models
    	    		System.out.println("Command " + command + " received!");
    	    		String list = buildauto.getAllModels();
    	    		sendObject(list);//send the list to Server
    	    		System.out.println("List of Model names sent!");
    	    		break;
    	    	
    	    	case CLI_ONE_MODEL://receive the command of providing a specific model
    	    		String name = null;
    	    		name = (String) getObject();
    	    		System.out.println("Command" + command + "received!");
    	    		Automobile auto = buildauto.getModel(name);
    	    		//System.out.println(buildauto.getAllModels());
    	    		
    	    		if(auto != null){
    	    			sendObject(auto);//Send the model to Client
    	    			System.out.println("Model: " + name + " " + auto.getBasePrice() + " sent!");
    	    		}
    	    		else
    	    			System.out.println("Model not found!");
    	    		
    	    }
    	    	}
    	 
    	  }
    	  catch (IOException e){
    		  e.printStackTrace();
    	}       
    }
}
