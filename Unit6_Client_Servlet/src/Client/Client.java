package Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {

	public void run(){
		System.out.println("Client starts!");
		
		 String strLocalHost = "";
		 
		  try{
		      strLocalHost = 
		        InetAddress.getLocalHost().getHostName();
		  }
		 catch (UnknownHostException e){
		      System.err.println ("Unable to find local host");
		 }
		  ClientDefaultSocketClient d = new ClientDefaultSocketClient(strLocalHost, 9008);
		  d.start();
		 }
	public static void main(String[] args){
		System.out.println("Client starts!");
		
		 String strLocalHost = "";
		 
		  try{
		      strLocalHost = 
		        InetAddress.getLocalHost().getHostName();
		  }
		 catch (UnknownHostException e){
		      System.err.println ("Unable to find local host");
		 }
		  ClientDefaultSocketClient d = new ClientDefaultSocketClient(strLocalHost, 9008);
		  d.start();
		 }
}
	
