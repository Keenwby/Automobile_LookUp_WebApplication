package Exception;
//This Class provides fix methods for exceptions happen in Model Package
public class Fix1to100{
	
	//Error 1: Optionset not found!
		public void fix1(){
			printsolution("Optionset not found! "
					+ "Please check the optionset name you entered!");
		}
			
		//Error 2: Option not found!
		public void fix2(){
			printsolution("Option not found! "
					+ "Please check the option name you entered!");
		}
		//Printer
		public void printsolution(String str){
			System.out.println(str);
		}
}
