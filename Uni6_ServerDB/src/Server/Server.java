package Server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import Adapter.BuildAuto;
import DB.MysqlCreateTable;
import DB.MysqlEditAuto;
import Model.Automobile;

public class Server {

	public static void main (String args[]){
		   /* debug main; does daytime on local host */
		  String strLocalHost = "";
		  try{
		      strLocalHost = 
		        InetAddress.getLocalHost().getHostName();
		  }
		 catch (UnknownHostException e){
		      System.err.println ("Unable to find local host");
		 }
		 //Create a table 
		 MysqlCreateTable ct = new MysqlCreateTable();
			try {
				ct.createAutoTable();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AutoServer buildauto = null;
			
			if(args[0].equals("1")){
				buildauto = new BuildAuto();
			}
			if(args[0].equals("2")){
				buildauto = new MysqlEditAuto();
			}
			if(buildauto != null){
				DefaultSocketServer d = new DefaultSocketServer(strLocalHost, 9008, buildauto);
				d.start();
			}
		 }
}
