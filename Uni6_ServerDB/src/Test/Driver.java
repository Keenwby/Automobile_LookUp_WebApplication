package Test;

import java.sql.SQLException;

import Adapter.*;
import DB.*;
import Model.Automobile;
import Scale.EditThread;

public class Driver {

	public static void main(String args[]){
	
	//1. Create Table
	/*System.out.println("~~~~~Create Table:~~~~~~~");
	MysqlCreateTable ct = new MysqlCreateTable();
		try {
			ct.createAutoTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
	//2. Build an Automobile from File
	System.out.println("~~~~~Build an Auto:~~~~~~");
	CreateAuto ca = new BuildAuto();
	ca.BuildAuto("FordZTW", 1);
	//Display a Model
	EditAutoDB edb = new MysqlEditAuto();
	edb.displayAllModels();
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
	//3. Add another Automobile From properties file
	System.out.println("~~~~~Build another Auto:~~~~~~");
	ca.BuildAuto("A3Wagon.properties", 2);
	edb.displayAllModels();
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
	//4. Delete an Automobile
	System.out.println("~~~~~After deleting an Auto:~~~");
	edb.deleteModel("FordZTW");
	edb.displayAllModels();
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
	//5. Display all optsets in an auto
	System.out.println("~~~~~All Optsets in the Auto:~~~");
	edb.displayOptsets("Audi A3 Wagon");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
	//Display all opts in an optset
	System.out.println("~~~~~All Opts in the Optset:~~~");
	edb.displayOpts("Audi A3 Wagon","Transmission");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Please press y when you are ready to update.");
	sc.nextLine();
	*/
	
	EditAutoDB edb = new MysqlEditAuto();
	//5. Update baseprice
	System.out.println("~~~~~After updating the baseprice to 20000:~~~");
	edb.updateBasePrice("Audi A3 Wagon", 20000);
	edb.displayAllModels();
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
	
	//6. Update an optionset name
	System.out.println("~~~~~After updating the optsetname 'Transmission' to 'Trans':~~~");
	edb.updateOptsetName("Audi A3 Wagon", "Transmission", "Trans");
	edb.displayOpts("Audi A3 Wagon","Trans");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
	//6. Update an option price
	System.out.println("~~~~~After updating the price of 'Trans' 'Automatic' to 1000:~~~");
	edb.updateOptPrice("Audi A3 Wagon", "Trans", "Automatic", 1000);
	edb.displayOpts("Audi A3 Wagon","Trans");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
        
	}
}
