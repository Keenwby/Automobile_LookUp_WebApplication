package Exception;

//This Class provides fix methods for exceptions happen in Util Package
public class Fix101to200{

	//Error 1: File not found or file name incorrect
	public void fix101(){
		printsolution("File not found! Please check your input file name!");
		System.exit(-1);
	}
		
	//Error 2: Missing baseprice
	public void fix102(){
		printsolution("Please make sure the first line of the input file contains and only "
				+ "contains a float number");
		System.exit(-1);
	}
	
	//Error 3: Missing option
	public void fix103(){
		printsolution("An option might be missing! "
				+ "Please check the input file if one option corresponds one price!");
		System.exit(-1);
	}
		
	//Error 4: Missing price
	public void fix104(){
		printsolution("A price might be missing! "
				+ "Please check the input file if one option corresponds one price!");
		System.exit(-1);
	}
	//Error 5: Empty Optionset
	public void fix105(){
		printsolution("An empty optionset is found! "
					+ "Please check the input file if corresponding options are missing!");
		System.exit(-1);
		}
	//Error 6: Other IOException
	public void fix106(){
		printsolution("Other IOException happens!"
				+ "Please try again!");
		System.exit(-1);
		}
	//Printer
	public void printsolution(String str){
		System.out.println(str);
	}	
}
