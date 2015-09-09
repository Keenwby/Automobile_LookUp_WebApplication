package DB;

import java.sql.*;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;

import Server.AutoServer;
import Util.ReadInstructions;
import Adapter.EditAutoDB;
import Model.Automobile;

public class MysqlEditAuto extends MysqlConnection implements EditAutoDB, AutoServer{

	ReadInstructions ri = new ReadInstructions();
	Properties props = ri.ReadFile("Instructions.properties");
	String sqlcommand = null;
	
	public void addModel(Automobile auto){
		//Get connection
		try {
			Connection conn = getConnection(URL);
			Statement st = conn.createStatement();
			//Format Query
			String sqlcommand = formatQuery("addModel", auto, -1, -1, -1, null, -9999, null);
			st.executeUpdate(sqlcommand, Statement.RETURN_GENERATED_KEYS);
			//Get the auto-generated auto ID
			int autoIncKey = getGenerateKey(st);
			//Insert optionsets
			for(int i = 0; i < auto.getOptsetSize();i++){
				sqlcommand = formatQuery("addOptset", auto, i, -1, autoIncKey, null, -9999, null);
				st.executeUpdate(sqlcommand, Statement.RETURN_GENERATED_KEYS);
				//Get the auto-generated optset ID
				int optsetIncKey = getGenerateKey(st);
				//Insert opts
				for(int j = 0; j < auto.getOptSize(i);j++){
					sqlcommand = formatQuery("addOpt", auto, i, j, optsetIncKey, null, -9999, null);
					st.executeUpdate(sqlcommand, Statement.RETURN_GENERATED_KEYS);
				}
				
			}
			//Close
			closeStat(st);
			closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteModel(String modelname){

		//Get connection
		try {
			Connection conn = getConnection(URL);
			Statement st = conn.createStatement();
			//Format Query
			String sqlcommand = formatQuery("delModel", null, -1, -1, -1, modelname, -9999, null);
			//Execute Query
			st.executeUpdate(sqlcommand);
			//Close
			closeStat(st);
			closeConnection(conn);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBasePrice(String modelname, float newprice){
		if(modelname == null)
			return ;
		//Get connection
		try {
			Connection conn = getConnection(URL);
			Statement st = conn.createStatement();
			//Format Query 
			String sqlcommand = formatQuery("updateBasePrice", null, -1, -1, -1, modelname, newprice, null);
			st.executeUpdate(sqlcommand);
			//Close
			closeStat(st);
			closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateOptsetName(String modelname, String optsetname, String newname){
		if(modelname == null || optsetname == null)
			return ;
		//Get connection
		try {
			Connection conn = getConnection(URL);
			Statement st = conn.createStatement();
			//Format Query to find targer model
			String sqlcommand = formatQuery("findModel", null, -1, -1, -1, modelname, -9999, null);
			ResultSet res = st.executeQuery(sqlcommand);
			//Get autoid
			int autoid = -1;
			while(res.next()){
				autoid = res.getInt("id");
			}
			if(autoid == -1){
				System.out.println("Wrong autoid found!");
				return;
			}
			//Format Query
			sqlcommand = formatQuery("updateOptsetName", null, -1, -1, autoid, optsetname, -9999, newname);
			st.executeUpdate(sqlcommand);
			//Close
			closeStat(st);
			closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateOptPrice(String modelname, String optsetname,
		       String optname, float newprice) {
			if(modelname == null || optsetname == null || optname == null)
				return ;
			//Get connection
			try {
				Connection conn = getConnection(URL);
				Statement st = conn.createStatement();
				//Format Query to find targer model
				String sqlcommand = formatQuery("findModel", null, -1, -1, -1, modelname, -9999, null);
				ResultSet res = st.executeQuery(sqlcommand);
				//Get autoid
				int autoid = -1;
				while(res.next()){
					autoid = res.getInt("id");
				}
				if(autoid == -1){
					System.out.println("Wrong autoid found!");
					return;
				}
				//Format Query to find targer optionset
				sqlcommand = formatQuery("findOptset", null, -1, -1, autoid, optsetname, -9999, null);
				res = st.executeQuery(sqlcommand);
				//Get autoid
				int optsetid = -1;
				while(res.next()){
					optsetid = res.getInt("id");
				}
				if(optsetid == -1){
					System.out.println("Wrong optsetid found!");
					return;
				}
				//Update the targer option price
				//Format Query to find targer model
				sqlcommand = formatQuery("updatePrice", null, -1, -1, optsetid, optname, newprice, null);
				st.executeUpdate(sqlcommand);
				//Close
				closeStat(st);
				closeConnection(conn);
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void displayAllModels(){
		
		//Get connection
		try {
			Connection conn = getConnection(URL);
			//Execute Query
			Statement st = conn.createStatement();
			String sqlcommand = props.getProperty("displayModel");
			ResultSet res = st.executeQuery(sqlcommand);
			//Display results
			while(res.next()){
				System.out.println("ModelName: " + res.getString("name"));
				System.out.println("ModelId: " + res.getInt("id"));
				System.out.println("Model: " + res.getString("model"));
				System.out.println("Make: " + res.getString("make"));
				System.out.println("Baseprice: " + res.getFloat("baseprice"));
			}
			res.close();
			//Close
			closeStat(st);
			closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Format query
	
	public void displayOptsets(String modelname){
		if(modelname == null)
			return ;
		//Get connection
		try {
			Connection conn = getConnection(URL);
			Statement st = conn.createStatement();
			//Format Query to find targer model
			String sqlcommand = formatQuery("findModel", null, -1, -1, -1, modelname, -9999, null);
			ResultSet res = st.executeQuery(sqlcommand);
			//Get autoid
			int autoid = -1;
			while(res.next()){
				autoid = res.getInt("id");
			}
			if(autoid == -1){
				System.out.println("Wrong autoid found!");
				return;
			}
			//Get all optsetnames
			sqlcommand = formatQuery("displayOptset", null, -1, -1, autoid, null, -9999, null);
			res = st.executeQuery(sqlcommand);
			//Display results
			while(res.next()){
				System.out.println("OptsetName: " + res.getString("name"));
				System.out.println("OptsetId: " + res.getInt("id"));
			}
			res.close();
			//Close
			closeStat(st);
			closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayOpts(String modelname, String optsetname){
		if(modelname == null || optsetname == null)
			return ;
		//Get connection
		try {
			Connection conn = getConnection(URL);
			Statement st = conn.createStatement();
			//Format Query to find targer model
			String sqlcommand = formatQuery("findModel", null, -1, -1, -1, modelname, -9999, null);
			ResultSet res = st.executeQuery(sqlcommand);
			//Get autoid
			int autoid = -1;
			while(res.next()){
				autoid = res.getInt("id");
			}
			if(autoid == -1){
				System.out.println("Wrong autoid found!");
				return;
			}
			//Format Query to find targer optset
			sqlcommand = formatQuery("findOptset", null, -1, -1, autoid, optsetname, -9999, null);
			res = st.executeQuery(sqlcommand);
			//Get optid
			int optsetid = -1;
			while(res.next()){
				optsetid = res.getInt("id");
			}
			if(optsetid == -1){
				System.out.println("Wrong autoid found!");
				return;
			}
			//Get all optnames
			sqlcommand = formatQuery("displayOpt", null, -1, -1, optsetid, null, -9999, null);
			res = st.executeQuery(sqlcommand);
			//Display results
			while(res.next()){
				System.out.println("OptName: " + res.getString("name"));
				System.out.println("OptPrice " + res.getString("price"));
				System.out.println("OptId: " + res.getInt("id"));
			}
			res.close();
			//Close
			closeStat(st);
			closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String formatQuery(String query, Automobile auto, int optsetindex, 
							  int optindex, int id, String name, float newprice, String newname){
		int choice = 0;
		if (query.equalsIgnoreCase("addModel"))
			choice = 1;
		else if(query.equalsIgnoreCase("addOptset"))
			choice = 2;
		else if(query.equalsIgnoreCase("addOpt"))
			choice = 3;
		else if(query.equalsIgnoreCase("delModel"))
			choice = 4;
		else if(query.equalsIgnoreCase("findModel"))
			choice = 6;
		else if(query.equalsIgnoreCase("displayOptset"))
			choice = 7;
		else if(query.equalsIgnoreCase("findOptset"))
			choice = 8;
		else if(query.equalsIgnoreCase("displayOpt"))
			choice = 9;
		else if(query.equalsIgnoreCase("updateBasePrice"))
			choice = 10;
		else if(query.equalsIgnoreCase("updateOptsetName"))
			choice = 11;
		else if(query.equalsIgnoreCase("updatePrice"))
			choice = 12;
	
		StringBuilder sb = new StringBuilder();
		Formatter fmter = new Formatter(sb, Locale.US);
		String fmt = props.getProperty(query);
		if(fmt!=null){
			switch(choice){
				case 1:
					fmter.format(fmt, auto.getModel(), auto.getMake(), auto.getModel(), auto.getBasePrice());
					break;
				case 2:
					fmter.format(fmt, auto.getOptsetName(optsetindex), id);
					break;
				case 3:
					fmter.format(fmt, auto.getOptName(optsetindex, optindex), 
									  auto.getOptPrice(optsetindex, optindex), id);
					break;
				case 4:
					fmter.format(fmt, name);
					break;
				case 6:
					fmter.format(fmt, name);
					break;
				case 7:
					fmter.format(fmt, id);
					break;
				case 8:
					fmter.format(fmt, id, name);
					break;
				case 9:
					fmter.format(fmt, id);
					break;
				case 10:
					fmter.format(fmt, newprice, name);
					break;
				case 11:
					fmter.format(fmt, newname, id, name);
					break;	
				case 12:
					fmter.format(fmt, newprice, id, name);
					break;
				default:
					;
			}
		}
		fmter.close();
		return sb.toString();
	}
	//Get the auto-generated key	
	
	public int getGenerateKey(Statement st){
		int id = -1;
		try {
			ResultSet res = st.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);
			}
			if(id==-1)
				System.out.println("Fail to get autoGenerateKey!");
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void buildAutofromProperties(Object propobject) {		
	        
		    Automobile newAuto = null;
	        newAuto = new Util.ReadPropertiesFile().readObject(propobject);
	        
	        if (newAuto != null) {
	            // add the new automobile model to the hash table.
	        	addModel(newAuto);
	        }
	}

	@Override
	public Automobile getModel(String modelname) {
		
		Automobile auto = null;
		int autoid = -1;
		int[] optsetid = new int[5];
		String[] optsetname = new String[5];
		//1. Model
		try {
			Connection conn = getConnection(URL);
			//Execute Query
			Statement st = conn.createStatement();
			String sqlcommand = props.getProperty("displayModel");
			ResultSet res = st.executeQuery(sqlcommand);
			//Display results
			while(res.next()){
				if(modelname.equals(res.getString("name"))){
					auto = new Automobile(res.getString("name"), res.getFloat("baseprice"));
					autoid = res.getInt("id");
				}
			}
			//2.Optsets
			if(autoid == -1){
				System.out.println("Wrong autoid found!");
				return null;
			}
			if(auto != null){
				//Format Query
				sqlcommand = formatQuery("displayOptset", null, -1, -1, autoid, null, -9999, null);
				res = st.executeQuery(sqlcommand);
				
				int i = 0;
				while(res.next()){
					optsetname[i] = res.getString("name");
					auto.setOptionset(optsetname[i]);
					optsetid[i++] = res.getInt("id");
				}
				//3. Opts
				for(i = 0; i < 5; i++){
					sqlcommand = formatQuery("displayOpt", null, -1, -1, optsetid[i], null, -9999, null);
					res = st.executeQuery(sqlcommand);
					while(res.next()){
						auto.setOption(i, res.getString("name"), res.getFloat("price"));
					}
				}
			}

			res.close();
			//Close
			closeStat(st);
			closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auto;
	}
	@Override
	
	public String getAllModels() {
		StringBuilder list = new StringBuilder();
		list.append(":");
		try {
			Connection conn = getConnection(URL);
			//Execute Query
			Statement st = conn.createStatement();
			String sqlcommand = props.getProperty("displayModel");
			ResultSet res = st.executeQuery(sqlcommand);
			//Display results
			while(res.next()){
				list.append(res.getString("name") + ",");
			}
			res.close();
			//Close
			closeStat(st);
			closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list.toString();
	}
	
}

	

	
