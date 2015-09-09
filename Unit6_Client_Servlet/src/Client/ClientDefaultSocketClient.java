package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import Model.Automobile;

public class ClientDefaultSocketClient extends Thread implements 
								ClientConstance, SocketClientConstance{
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
 
    private Socket sock;
    private String strHost;
    private int iPort;
    private int state = WAIT_USR_MENUCHOISE;
    
    public static String modellist;
    
    private CarModelOptionsIO cm = null;
    
    public ClientDefaultSocketClient(String strHost, int iPort) { 
    	this.strHost = strHost;
    	this.iPort = iPort;
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
    		   sock = new Socket(strHost, iPort);                    
    	   }
    	   catch(IOException socketError){
    	     if (DEBUG) System.err.println
    	        ("Unable to connect to Host: " + strHost);
    	     return false;
    	   }
    	   
    	   try {
               oos = new ObjectOutputStream(sock.getOutputStream());
               ois = new ObjectInputStream(sock.getInputStream());
               oos.flush();
           } catch (IOException e) {
               e.printStackTrace();
           }
    	   
    	  return sock!=null ? true : false;
    	}
    
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

    	  if (DEBUG) System.out.println ("Client: handling session with"
    	            + strHost + ":" + iPort);	  
    	  try {
    		  cm = new CarModelOptionsIO(sock, oos, ois);
    	  	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
    	  	}
    	  if(cm == null){
    		  System.out.println("CarModelOptionsIO creates failed!");
    		  return;
    	  }
    	  Scanner sc;
    	  
    	  try {
    		  
    	    while(true){
    	    	 sc = new Scanner(System.in);
    	    	try{	
    	    		Thread.sleep(100);
    	    	}catch (InterruptedException e){
    	    		e.printStackTrace();
    	    	}
    	    	
    	    	if(state == WAIT_USR_MENUCHOISE){
    	    		//User decides to upload a model or configure a car
    	    		int mainchoice = userMainmenu(sc);
    	    		if(mainchoice == 1){//Upload a model
    	    			cm.sendCommand(SER_SAVE_FILE);
    	    			System.out.println("Please enter the filename: ");//Enter model name
    	    			String fn = sc.nextLine();
    	    			System.out.println(fn);
    	    			if(cm.uploadProperties(fn)){//send the properties using objectoutputstream
    	    				System.out.println("Properties file sent!");
    	    				state = WAIT_SER_COMMAND;//Waiting for response from Server
    	    				int command = cm.getCommand();
    	    				if(command == SER_SAVE_FILE_SUCC)//Confirmation
    	    					System.out.println("Command " + SER_SAVE_FILE_SUCC + "received!");
    	    				else
    	    					System.out.println("Command " + SER_SAVE_FILE_SUCC + 
    	    										" not received. Maybe sth. is wrong.");
    	    				state = WAIT_USR_MENUCHOISE;
    	    			}
    	    		}
    	    		else if(mainchoice == 2){//Get all available models
    	    			state = WAIT_USR_OPTCHIOCE;
    	    			cm.sendCommand(CLI_ALL_MODELS);
    					System.out.println("Command: " + CLI_ALL_MODELS + "sent!");
    					state = WAIT_SER_OBJECT;
    					ClientDefaultSocketClient.modellist = (String) cm.getObject();//Get the modelnames
    					if(ClientDefaultSocketClient.modellist != null){
    						System.out.println(ClientDefaultSocketClient.modellist + " received! "
    										+ "Please reload the page!");//Display modelnames
    						state = WAIT_USR_MENUCHOISE;
    					}
    	    		}
    	    		else if(mainchoice == 3){//Configure a car
    	    			state = WAIT_USR_OPTCHIOCE;
    	    			while(state != WAIT_USR_MENUCHOISE){
    	    				int choice = userCarConfigmenu(sc);
        	    			switch(choice){
        	    				case 1://Ask for one model
        	    					cm.sendCommand(CLI_ONE_MODEL);
        	    					System.out.println("Command: " + CLI_ONE_MODEL + "sent!");
        	    					SelectCarOption sco = new SelectCarOption();
        	    					String modelname = sco.selectModel(sc);//Get modelname from user
        	    					cm.sendObject(modelname);//Send the name to Server
        	    					
        	    					System.out.println("Waiting for " + modelname + "!");
        	    					Object obj = cm.getObject();//Get the object
        	    					Automobile auto = (Automobile) obj;
        	    					
        	    					if(auto == null){
        	    						System.out.println("No Automobile received!");
        	    					}else{
        	    						//SelectCarOption sco = new SelectCarOption(auto);
        	    						//Pass the object of automobile to SelectCarOption
        	    						state = WAIT_USR_OPTCHIOCE;
        	    						sco.makeChoice(auto, sc);//Ask user to set an option choice
        	    					}    	    						
        	    					break;
        	    				case 0://Back to last menu
        	    					state = WAIT_USR_MENUCHOISE;
        	    					break;
        	    			}
    	    			}
    	    		}else{
    	    			System.out.println("Exit the System!");
    	    			state = STOP;
    	    		}
    	    	}else if(state == STOP){
    	    		cm.sendCommand(STOP);//Ask Server to terminate
    	    		return;//Terminate Client
    	    	}
    	    }
    	     
    	  }
    	  catch (IOException e){
    		  e.printStackTrace();
    	}       
    }
    //Ask the user to select to upload model or configure a car
    public int userMainmenu(Scanner sc) throws IOException{
    	System.out.println("Please enter: 1 for uploading a new model"
    			+ "; 2 for getting all available models' list" +  "; 3 for Configuring a car" + "; 0 for exiting");
    	return Integer.valueOf(sc.nextLine());
    			
    }
    //Ask the user to select to display all available models or a specific model
    public int userCarConfigmenu(Scanner sc) throws IOException{
    	System.out.println("Please enter: " + "1 for select a model" + "; 0 for exiting to uppermenu");
    	return Integer.valueOf(sc.nextLine());
    } 

    public String getModellist(){
    	return ClientDefaultSocketClient.modellist;
    }
    
    public CarModelOptionsIO getCarModelOptionsIO(){
    	return cm;
    }
}
