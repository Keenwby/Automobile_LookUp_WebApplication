package Server;

import Model.Automobile;

public interface AutoServer {
	
	public void buildAutofromProperties(Object propobject);
	
	public Automobile getModel(String modelname);
	
	public String getAllModels();
}
