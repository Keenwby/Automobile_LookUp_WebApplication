package Adapter;

import Model.Automobile;

public interface EditAutoDB {

	public void addModel(Automobile auto);
	
	public void deleteModel(String modelname);
	
	public void displayAllModels();
	
	public void displayOptsets(String modelname);
	
	public void displayOpts(String modelname, String optsetname);
	
	public void updateBasePrice(String modelname, float newprice);
	
	public void updateOptsetName(String modelname, String optsetname, String newprice);
	
	public void updateOptPrice(String modelname, String optsetname,
			   String optname, float newprice);
	
}
