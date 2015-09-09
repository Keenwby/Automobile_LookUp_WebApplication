package Model;

/* Project_1_Unit_4
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import Exception.*;
import Exception.Error;

public class Automobile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * generate an ID
	 */
	//private static final long serialVersionUID = -5726040483074594356L;
	//Properties:
	private String make;
	private String model;
	private float baseprice;
	private ArrayList<Optionset> optsets;
	
	//Constructors
	public Automobile(String name){
		setModel(name);
		setBasePrice(0);
		this.optsets = new ArrayList<Optionset>(0);
	}
	
	public Automobile(String name, float baseprice){
		setModel(name);
		setBasePrice(baseprice);
		this.optsets = new ArrayList<Optionset>(0);
	}
	
	//setters
	public synchronized void setModel(String name){
		this.model = name;
	}
	public synchronized void setBasePrice(float baseprice){
		this.baseprice = baseprice;
	}
	public synchronized void setMake(String make){
		this.make = make;
	}
	//SetOptionsets
	public synchronized void setOptionset(String[] name){
		for(int i = 0; i < name.length; i++){
			optsets.add(new Optionset(name[i]));
		}
	}
	//SetOptions
	public synchronized void setOption(int optsetIndex, String[] name, float[] price){
			optsets.get(optsetIndex).setOption(name, price);
	}
	public synchronized void setOptionName(int optsetIndex,String[] name){
		optsets.get(optsetIndex).setOptionName(name);
	}
	public synchronized void setOptionPrice(int optsetIndex,float[] price){
		optsets.get(optsetIndex).setOptionPrice(price);
	}
	//Set Option Choice
	public synchronized void setOptionChoice(String optsetname, String optname) throws AutoException{
		int index = findOptionset(optsetname);
		if (index != -1){
			optsets.get(index).setOptChoice(optname);
		}
		else{
			Error e = null;
			throw new AutoException(e.NO1);
		}
	}
	
	//getters
	public synchronized String getModel(){
		return model;
	}
	public synchronized String getMake(){
		return this.make;
	}
	public synchronized float getBasePrice(){
		return baseprice;
	}
	//Get Option Choice
	public synchronized String getOptionChoice(String optsetname) throws AutoException{
		int index = findOptionset(optsetname);
		if (index != -1){
			return optsets.get(index).getOptChoice().toString();
		}
		else{
			Error e = null;
			throw new AutoException(e.NO2);
		}
	}

	//Get total price
	public synchronized float getTotalPrice(String optsetname){
		int index = findOptionset(optsetname);
		if (index != -1){
			return optsets.get(index).getOptChoice().getPrice() + baseprice;
		}
		//Error Later
		else return -10000;
	}
	
	//Finders
	//Find the Optionset with name
	public synchronized int findOptionset(String name){
		int index = -1;
		for(int i = 0; i < optsets.size(); i++){
			if(optsets.get(i) != null && (optsets.get(i).getName()).equals(name)){
				index = i;
				break;
			}
			else continue;
		}
			return index;
	}
	//Find the Option with names
	public synchronized int[] findOption(String optsetname, String optname){
		int[] index = {findOptionset(optsetname), -1};
		if(index[0] != -1)
			index[1] = optsets.get(index[0]).findOption(optname);
			return index;
			
	}
	
	//Delete
	//Delete an Optionset
	public synchronized void deleteOptionset(String name){
		int index = findOptionset(name);
		if(index != -1){
			optsets.remove(index);
		}
	}
	//Delete an Option
	public synchronized void deleteOption(String optsetname, String optname){
		int index[] = findOption(optsetname, optname);
		optsets.get(index[0]).deleteOption(optname);
	}
	
	
	//Update
	//Update Optionset name
	public synchronized void updateOptionsetName(String optsetname, String newname) throws AutoException{
		int index = findOptionset(optsetname);
		if(index != -1){
			optsets.get(index).setName(newname);
		}
		else{
			Error e = null;
			throw new AutoException(e.NO1);
		}
	}
	//Update all prices of Options in an Optionset
	public synchronized void updateOptionsetPrices(String optsetname, float[] newvals) throws AutoException{
		int index = findOptionset(optsetname);
		if(index != -1){
			for(int i = 0; i < optsets.get(index).getOptSize(); i++){
				if(optsets.get(index).getOption(i) != null)
					optsets.get(index).getOption(i).setPrice(newvals[i]);
			}
		}
		else{
			Error e = null;
			throw new AutoException(e.NO1);
		}
	}
	
	//Update an price of Option with name
	public synchronized void updateOptionPrice(String optsetname, String optname, float newval) throws AutoException{
		int[] index = findOption(optsetname, optname); 
		if(index[1] != -1){
			optsets.get(index[0]).getOption(index[1]).setPrice(newval);
		}
		else{
			Error e = null;
			throw new AutoException(e.NO2);
		}
	}
	
	//Print
	//print Optionset
	public synchronized void printOptionset(String optsetname){
		int index = findOptionset(optsetname);
		for(int i = 0; i < optsets.get(index).getOptSize(); i++){
			optsets.get(index).printOption(i);
		}
	}
	//print an Option
	public synchronized void printOption(String optsetname, String optname){
		int[] index = findOption(optsetname, optname);
		optsets.get(index[0]).printOption(index[1]);
	}
	//print all optionsets
	public synchronized void printAllOptsets(){
		System.out.println("Model Infomation: ");
		System.out.print("Model Name: " + getModel() + " ");
		System.out.println("Base Price: " + getBasePrice());
		System.out.print("Optionsets: ");
		for(int i = 0; i < optsets.size(); i++){
			if(optsets.get(i)!=null){
				System.out.print(optsets.get(i).getName() + "  ");
			}
			System.out.println();
		}
	}
	//print options in an optset
	public synchronized void printAllOpts(String optsetname){
		int index = findOptionset(optsetname);
		optsets.get(index).printAllOptions();
	}
	//print optionchoice
	public synchronized void printoptchoice(String optsetname){
		int index = findOptionset(optsetname);
		optsets.get(index).printOptionChoice();
	}
	//print a string
	public synchronized void printaString(String str){
		System.out.println(str);
	}
	
	@Override
	public synchronized String toString(){
		return "Modelname: " + model + " Base Price: " + baseprice;
	}
	//Unit 5
	public ArrayList<String> getOptsetlist(){
		ArrayList<String> optsetnames = new ArrayList<String>();
		for(int i = 0; i < optsets.size(); i++){
			optsetnames.add(optsets.get(i).getName());
		}
		return optsetnames;
	}
	
	public ArrayList<String> getOptlist(String optsetname){
		int index = findOptionset(optsetname);
		if(index == -1)
			return null;
		else
			return optsets.get(index).getOptlist();
	}
	
}
