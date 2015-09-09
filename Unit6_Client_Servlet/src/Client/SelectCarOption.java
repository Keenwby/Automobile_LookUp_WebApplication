package Client;

import java.io.IOException;
import java.util.Scanner;

import Exception.AutoException;
import Model.Automobile;

public class SelectCarOption {

	private Automobile auto;
	
	public SelectCarOption(){
	}//constructor
	//Ask the user to select an option choice
	public String selectModel(Scanner sc) throws IOException{
	    System.out.println("Please enter the model name: ");
	   	return sc.nextLine();
	}
	
	public void makeChoice(Automobile auto, Scanner sc){
		this.auto = auto;
		String optsetname;
		String optname;
		auto.printAllOptsets();
		System.out.println("Please enter the optionset name: ");
		optsetname = sc.nextLine();
		auto.printAllOpts(optsetname);
		System.out.println("Please enter the option name: ");
		optname = sc.nextLine();
		if(optsetname != null && optname != null){
			try {
				auto.setOptionChoice(optsetname, optname);
			} catch (AutoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Display option choice
			auto.printoptchoice(optsetname);
		}
	}
}
