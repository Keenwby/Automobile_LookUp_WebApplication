package Model;

/* Project_1_Unit_6
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
	//private static final long serialVersionUID = -5726040483074594356L;
	//Properties:
	private String make;
	private String model;
	private float baseprice;
	private List<Optionset> optsets;
	
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
	//SetAllOptionsets
	public synchronized void setAllOptionset(String[] name){
		for(int i = 0; i < name.length; i++){
			optsets.add(new Optionset(name[i]));
		}
	}
	//SetOptionset
	public synchronized void setOptionset(String name){
		optsets.add(new Optionset(name));
	}
	//SetAllOptions
	public synchronized void setOptionNames(int optsetIndex,String[] name){
		if(checkIndexIsValid(optsetIndex))
			optsets.get(optsetIndex).setAllOptionName(name);
		else{
			System.out.println("Invalid input index!");
		}
			return;
	}
	
	public synchronized void setOptionPrices(int optsetIndex,float[] price){
		if(checkIndexIsValid(optsetIndex))
			optsets.get(optsetIndex).setAllOptionPrice(price);
			else{
				System.out.println("Invalid input index!");
			}
				return;
	}
	//SetOption
	public synchronized void setOption(int optsetIndex,String name, float price){
		if(checkIndexIsValid(optsetIndex))
			optsets.get(optsetIndex).setOption(name, price);
		else{
			System.out.println("Invalid input index!");
		}
			return;
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
			optsets.get(index).getOption(i).toString();
		}
	}
	//print an Option
	public synchronized void printOption(String optsetname, String optname){
		int[] index = findOption(optsetname, optname);
		optsets.get(index[0]).getOption(index[1]).toString();
	}
	//print all
	public synchronized void printAll(){
		System.out.println("Model Name: " + getModel());
		System.out.println("Base Price: " + getBasePrice());
		for(int i = 0; i < optsets.size(); i++){
			if(optsets.get(i)!=null){
				System.out.println("Optionset name: " + optsets.get(i).getName() + ": ");
				for(int j = 0; j < optsets.get(i).getOptSize(); j++){
					if(optsets.get(i).getOption(j) != null)
						System.out.println(optsets.get(i).getOption(j).toString());
				}
			}
		}
	}
	
	//Unit 6
	//Get an Optionset by index
	
	public String getOptsetName(int index){
		if(checkIndexIsValid(index))
			return optsets.get(index).getName();	
		else{
			System.out.println("Invalid input index!");
			return null;
		}
	}
	//Get an Optionname by index
	
	public String getOptName(int optsetindex, int optindex){
		if(checkIndexIsValid(optsetindex))
			if(optsets.get(optsetindex).checkIndexIsValid(optindex))
				return optsets.get(optsetindex).getOptName(optindex);
			else{
				System.out.println("Invalid input index!");
				return null;
			}
		else{
			System.out.println("Invalid input index!");
			return null;
		}
	}
	//Get an Optionname by index
	
	public float getOptPrice(int optsetindex, int optindex){
		if(checkIndexIsValid(optsetindex))
			if(optsets.get(optsetindex).checkIndexIsValid(optindex))
				return optsets.get(optsetindex).getOptPrice(optindex);
			else{
				System.out.println("Invalid input index!");
				return -9999;
			}
		else{
			System.out.println("Invalid input index!");
			return -9999;
		}
	}
	//Get optionset size
		//Get optsize

	public int getOptSize(int index){
		if(optsets.get(index)!= null)
			return optsets.get(index).getOptSize();
		else
			return -1;
	}

	//Add for feedback of Unit 1
	public boolean checkIndexIsValid(int index){
		return index < getOptsetSize() ? true : false;
	}
	
	public int getOptsetSize(){
		return optsets.size();
	}
	
	public synchronized String toString(){
		return "Modelname: " + model + " Base Price: " + baseprice;
	}
}