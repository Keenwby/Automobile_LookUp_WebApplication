package Adapter;
/* Project_1_Unit_6
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */

import DB.MysqlEditAuto;
import Exception.AutoException;
import Model.*;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;

public abstract class proxyAutomobile {
	
	private static Map<String, Automobile> map = new LinkedHashMap<String, Automobile>();
	
	//1. Methods in BuildAuto
	int filetype;
	//Build an auto
	public void BuildAuto(String filename, int filetype){
		Automobile auto = null;
		if(filetype == 1){//ReadResource	
			try {
				auto = new Util.ReadResource().read(filename);
				} catch (AutoException ae) {
			// TODO Auto-generated catch block
					fix(ae, ae.getErrorno());
				}
			}
		if(filetype == 2){//ReadProperties
			auto = new Util.ReadPropertiesFile().readProperties(filename);
			}
		if(auto!=null){
			//Add new auto into LinkedHashMap
			map.put(filename, auto);
			//Add new auto into DB
			EditAutoDB edb = new MysqlEditAuto();
			edb.addModel(auto);
			}
		}
	
	//Print all the info of this auto
	public void printAuto(String modelname){
		if(map.get(modelname)!=null)
			map.get(modelname).printAll();
		else
			return;
	}
	//2. Methods in UpdateAuto
	//update optionsetname
	public void updateOptionSetName(String modelname, String optsetname, 
		    String newname){
		try{
			if(map.get(modelname)!=null)
				map.get(modelname).updateOptionsetName(optsetname, newname);
			else
				return;
		}catch(AutoException ae){
			fix(ae, ae.getErrorno());
		}
	}
	//update optionprice
	public void updateOptionPrice(String modelname, String optsetname, 
		  String optname, float newprice){
		//Update in the LinkedHashMap
		try{
			if(map.get(modelname)!=null)
				map.get(modelname).updateOptionPrice(optsetname, optname, newprice);
			else
				return;
		}catch(AutoException ae){
			fix(ae, ae.getErrorno());
		}
		//Update the DB
		EditAutoDB edb = new MysqlEditAuto();
		edb.updateOptPrice(modelname, optsetname, optname, newprice);
	}

	//Unit 2: 3. Methods in FixAuto
	//Fix exceptions
	public void fix(AutoException ae, int errno) {
		// TODO Auto-generated method stub
		ae.fix(errno);
	}
	
	//Unit 3: 4. Methods in MultiEditAuto
	public Automobile getModel(String modelname){
		return map.get(modelname)!=null ? map.get(modelname) : null;
	}
	
	//Unit 4: Build auto from properties file
	public void buildAutofromProperties(Object propobject) {
		// TODO Auto-generated method stub
		
        Automobile newAuto = null;
        newAuto = new Util.ReadPropertiesFile().readObject(propobject);
        
        if (newAuto != null) {
            // add the new automobile model to the hash table.
            map.put(newAuto.getModel(), newAuto);
        }
	}
	
	//Unit 4: return all model names
    public String getAllModels(){
        Set<String> keyset = map.keySet();
        return keyset.toString();
    }
    
    public void updateOptionChoice(String modelname, String optionset, String optionname){
		try {
			if(map.get(modelname)!= null)
				map.get(modelname).setOptionChoice(optionset,optionname);
			else
				return;
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public String getOptionChoice(String modelname, String optionset){
		String str = null;
		try {
			if(map.get(modelname)!=null)
				str = map.get(modelname).getOptionChoice(optionset).toString();
			else
				return null;
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
    //Unit 6: deleteAuto
    public void deleteAuto(String name){
    	//Delete the auto in LinkedHashMap
    	map.remove(name);
   		//Delete the auto in DB
    	EditAutoDB edb = new MysqlEditAuto();
    	edb.deleteModel(name);
    }

}
