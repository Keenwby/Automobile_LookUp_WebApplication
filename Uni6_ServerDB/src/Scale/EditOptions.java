package Scale;
/* Project_1_Unit_4
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */
import Adapter.*;
import Exception.AutoException;

public class EditOptions extends Thread{
	
	//@param id is the id of current thread
	//@param choice is the id 
	private String id;
	private int choice;
	
	//Constructor
	public EditOptions(String id, int choice){
		super(id);
		this.id = id;
		this.choice = choice;
	}
	
	//Set the option choice
	private void threadsetOptionChoice(String modelname, String optsetname, String optname){
			UpdateAuto ua = new BuildAuto();
			ua.updateOptionChoice(modelname, optsetname, optname);
	}

	//Get the option choice
	private String threadgetOptionChoiceName(String modelname, String optsetname){
		BuildAuto ba = new BuildAuto();
		return ba.getOptionChoice(modelname, optsetname);
	}
	
	//Printer
	public void print(String str){
		System.out.println(str);
	}
	//Fixer
	public void fix(AutoException ae, int errno) {
		// TODO Auto-generated method stub
		ae.fix(errno);
	}
	
	//Runners
	//Set an optionchoice
	private void setOption(){
		print(id + ": " + "begins: set option choice.");
		print(id + ": " + "Select Optionset 'Color' and option 'Liquid Grey Clearcoat Metallic'.");
		threadsetOptionChoice("FordZTW", "Color", "Liquid Grey Clearcoat Metallic");
		print(id + ": " + "Select Done!");
	}
	//Get an optionchoice after sleeping for 500 ms
	private void getOption(){
		print(id + ": " + "begins: get option choice selected in thread 1.");
		print(id + ": " + "waits for 500ms");
		try{
			Thread.sleep(500);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		print(id + ": " + "waiting completes");
		print(id + ": " + "Get Optionchoice in  Optionset 'Color':");
		print(id + ": " + threadgetOptionChoiceName("FordZTW", "Color").toString());
		print(id + ": " + "Get Done!");
	}
	//Set and then get an optionchoice
	private void setandgetOption(){
		print(id + ": " + "begins: set and get option choice.");
		print(id + ": " + "Select Optionset 'Color' and option 'Cloud 9 White Clearcoat'.");
		threadsetOptionChoice("FordZTW", "Color", "Cloud 9 White Clearcoat");
		print(id + ": " + "Select Done!");
		print(id + ": " + "Get Optionchoice in  Optionset 'Color':");
		print(id + ": " + threadgetOptionChoiceName("FordZTW", "Color").toString());
		print(id + ": " + "Get Done!");
	}
	
	//Run
	public void run(){
		switch(choice){
		case 1:
			setOption();
			break;
		case 2:
			getOption();
			break;
		case 3:
			setandgetOption();
			break;
		default:
			;
		}
	}
}
