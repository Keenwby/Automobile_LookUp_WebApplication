package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import Util.ReadPropertiesfromFile;

public class CarModelOptionsIO implements SocketClientConstance{

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket sock;

	public CarModelOptionsIO(Socket sock, ObjectOutputStream oos, 
							 ObjectInputStream ois) throws IOException{
        this.oos = oos;
        this.ois = ois;
        this.sock = sock;
    }//constructor

	//Read data from a properties file and upload to server
	public boolean uploadProperties(String filename) throws IOException{
    	
    	if(filename == null)
    		return false;
    	ReadPropertiesfromFile read = new ReadPropertiesfromFile();
		Properties prop = read.Read(filename);	
		if(prop == null)
			return false;
		sendObject(prop);
		return true;
    }
	
	//Send an object
	public void sendObject(Object propsobject) throws IOException{
    	if (propsobject == null)
    		return;
		oos.writeObject(propsobject);
		oos.flush();
    }
	//Send a command    
	public void sendCommand(int command) throws IOException{
		sendObject(Integer.toString(command));
		System.out.println("Command " + command + " sent!");
	}
	
	//Get an object
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
	
	//Get a command 
	public int getCommand() throws IOException{
	    String get = null;
	    get = (String) getObject();
	    	
	    return Integer.valueOf(get);
	}	
	
	public String getSocket(){
		return sock.toString();
	}
}
