package DB;

import java.sql.*;

public class MysqlConnection {
	
	static final String JDBC = "com.mysql.jdbc.Driver";
	static final String LHURL = "jdbc:mysql://localhost:3306/";
	static final String URL = LHURL + "modeldb";
	static final String USER = "root";
	static final String PASSWORD = "";
	
	//Open a Connection from local host
	public Connection getLHConnection(String host) throws SQLException{
		Connection conn = DriverManager.getConnection(host, USER, PASSWORD);
		return conn!=null ? conn : null;
	}
	//Get a Connection of the modeldb database
	public Connection getConnection(String modeldb) throws SQLException{
		return getLHConnection(modeldb);
	}
	//Close Connection
	public void closeConnection(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
		else
			return;
	}
	//Close Statement
	public void closeStat(Statement st) throws SQLException{
		st.close();
	}
}
