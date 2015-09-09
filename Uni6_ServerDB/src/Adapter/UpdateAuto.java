package Adapter;
/* Project_1_Unit_4
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */
public interface UpdateAuto {
	
	public void updateOptionSetName(String Modelname, String OptionSetname, 
								    String newName);
	
	public void updateOptionPrice(String Modelname, String Optionname, 
								  String Option, float newprice);
	public void updateOptionChoice(String Modelname, String Optionset, String Optionname);
}
