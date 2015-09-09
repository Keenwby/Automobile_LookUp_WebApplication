package DB;

import java.sql.*;

import Util.ReadSchema;

public class MysqlCreateTable extends MysqlConnection{
	
	public void createAutoTable() throws SQLException{
		Connection conn = null;
		Statement st = null;
		
		//Register a JDBC Driver
		
		try {
			Class.forName(JDBC);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Get a connection
		conn = getLHConnection(LHURL);
		st = conn.createStatement();
		//Read Statements from properties file
		ReadSchema rs = new ReadSchema();
		rs.ReadFile("Schema.properties");
		String str[] = rs.getStat();
		//Execute the statements
		for(int i = 0; i < str.length; i++){
			//System.out.println(str[i]);
			st.executeUpdate(str[i]);
		}
		//Close Connection
		closeStat(st);
		closeConnection(conn);
		System.out.println("Table created sucessfully!");
	}
}
