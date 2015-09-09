package Adapter;
/* Project_1_Unit_4
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */
import Exception.AutoException;
import Model.*;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;

public abstract class proxyAutomobile {
	
	private static Map<String, Automobile> map = new LinkedHashMap<String, Automobile>();
	//Definition of methods in interfaces
	
	//1. Methods in BuildAuto
	//Build an auto
	public void BuildAuto(String filename){	
			try {
				map.put(filename, new Util.ReadResource().Read(filename));
				} catch (AutoException ae) {
			// TODO Auto-generated catch block
					fix(ae, ae.getErrorno());
				}
		}
	
	//Print all the info of this auto
	public void printAuto(String modelname){
		//Not printing the whole auto just all optionsets in the auto
		map.get(modelname).printAllOptsets();
	}
	//2. Methods in UpdateAuto
	//update optionsetname
	public void updateOptionSetName(String modelname, String optsetname, 
		    String newname){
		try{
			map.get(modelname).updateOptionsetName(optsetname, newname);
		}catch(AutoException ae){
			fix(ae, ae.getErrorno());
		}
	}
	//update optionprice
	public void updateOptionPrice(String modelname, String optsetname, 
		  String optname, float newprice){
		try{
			map.get(modelname).updateOptionPrice(optsetname, optname, newprice);
		}catch(AutoException ae){
			fix(ae, ae.getErrorno());
		}
	}
	//Unit 2: 3. Methods in FixAuto
	//Fix exceptions
	public void fix(AutoException ae, int errno) {
		// TODO Auto-generated method stub
		ae.fix(errno);
	}
	
	//Print all modelnames
	public void printAllModels(){
	for (Iterator it =  map.keySet().iterator();it.hasNext();)
	   {
	    Object key = it.next();
	    map.get(key).printaString(key+"="+ map.get(key));
		}
	}
	
	//Unit 3 Modified: 4. Methods in EditOptions
	public Automobile getModel(String modelname){
		return map.get(modelname);
	}
	
	public void updateOptionChoice(String modelname, String optionset, String optionname){
		try {
			map.get(modelname).setOptionChoice(optionset,optionname);
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getOptionChoice(String modelname, String optionset){
		String str = null;
		try {
			str = map.get(modelname).getOptionChoice(optionset).toString();
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
