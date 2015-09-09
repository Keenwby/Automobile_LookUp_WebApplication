package Model;

/* Project_1_Unit_4
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Optionset implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//properties
	private String name = new String();
	private List<Option> opts;
	private Option choice;
	//
	protected Optionset(){
		this.setName(null);
		this.opts = new ArrayList<Option>(0);
	}
	protected Optionset(String name){
		this.setName(name);
		this.opts = new ArrayList<Option>(0);
	}
	//setters
	protected void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	//set the option value with name
	protected void setOption(String[] name, float[] price){
		for(int i = 0; i < name.length; i++){
			opts.add(new Option(name[i], price[i]));
		}
	}
	//set option name
	protected void setOptionName(String[] name){
		for(int i = 0; i < name.length; i++){
			opts.add(new Option(name[i]));
		}
	}
	//set option price
	protected void setOptionPrice(float[] price){
		for(int i = 0; i < price.length; i++){
			opts.get(i).setPrice(price[i]);
		}
	}
	//set option choice
	protected void setOptChoice(String name){
		int index = findOption(name);
		if(index != -1)
			this.choice = opts.get(index);
	}
	
	//getters
	//get name
	protected String getName(){
		return name;
	}
	//get an option
	protected Option getOption(int index){
		return opts.get(index);
	}
	//get opton size
	protected int getOptSize(){
		return opts.size();
	}
	//get option choice
	protected Option getOptChoice(){
		return choice;
	}
	
	//Finders
	//Find the Option with name
	protected int findOption(String name){
			int index = -1;
			
			for(int i = 0; i < opts.size(); i++){
				if(opts.get(i) != null && (opts.get(i).getName()).equals(name)){
					index = i;
					break;
				}
				else continue;
			}
			return index;
		}
	
	//Delete an Option
	protected void deleteOption(String name){
		int index = findOption(name);
		if(index != -1){
			opts.remove(index);
		}
	}
	//Print an Option
	protected void printOption(int index){
		if(opts.get(index) != null){
			System.out.print("Option name: " + opts.get(index).getName() + ", ");
			System.out.println("Option price: " + "$" + opts.get(index).getPrice() + " ");
		}
	}
	//Print Optionchoice
	protected void printOptionChoice(){
		if(choice == null){
			System.out.println("No choice has been set!");
			return;
		}
		else{
			System.out.println(choice.toString());
		}
	}
	
	@Override
	public String toString(){
		return "Optionset name: " + name;
	}
	
	//Inner Class Option
	class Option implements Serializable{
		//properties
		private String name;
		private float price;
		//constructors
		protected Option(String name){
			this.setName(name);
			this.setPrice(0);
		}
		protected Option(String name, float price){
			this.setName(name);
			this.setPrice(price);
		}
		//setters
		protected void setPrice(float price) {
			// TODO Auto-generated method stub
			this.price = price;
		}
		protected void setName(String name) {
			// TODO Auto-generated method stub
			this.name = name;
		}
		//getters
		protected String getName(){
			return name;
		}
		protected float getPrice(){
			return price;
		}
		@Override
		public String toString(){
			return "[Optionname] " + name + " [Price] " + price;
		}
	}

	public void printAllOptions() {
		// TODO Auto-generated method stub
		for(int i = 0; i < opts.size(); i++){
			if(opts.get(i)!=null){
				System.out.print(opts.get(i).getName() + "  ");
			}
			System.out.println();
			}
		}
	//Unit 5
	protected ArrayList<String> getOptlist(){
		ArrayList<String> optnames = new ArrayList<String>();
		for(int i = 0; i < opts.size(); i++){
			optnames.add(opts.get(i).getName());
		}
		return optnames;
	}
}
