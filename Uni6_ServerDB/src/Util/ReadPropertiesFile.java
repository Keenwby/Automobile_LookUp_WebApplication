package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import Model.Automobile;

public class ReadPropertiesFile {
	
	public Automobile readProperties(String filename) {
			
		    Automobile auto = null;
		
			if (filename == null)
				return null;
			
			Properties props = new Properties();
			FileInputStream in = null;
			
			try {
	            in = new FileInputStream(filename);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return null;
	        }
			
			try {
				props.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String carmake = props.getProperty("CarMake");
			if(!carmake.equals(null)){
				//Get the model name
				String modelname = props.getProperty("CarMake") + " " +
								   props.getProperty("CarModel");
				//Build a new automobile
				auto = new Automobile(modelname);
				//Set baseprice
				String baseprice = props.getProperty("BasePrice");
				if(!baseprice.equals(null)){
						auto.setBasePrice(Float.valueOf(baseprice));
				}
				/*else{
					Error e = null;
					throw new AutoException(e.NO102);
				}*/
				//Set optionset
				int optsetindex = 1;
				String optset;
				
				while(props.getProperty("Option" + optsetindex) != null){
					//Create an optset
					optset = props.getProperty("Option" + optsetindex);
					auto.setOptionset(optset);
					//Get option names & prices
					char optindex = 'a';
					String optname;
					String optprice;
					
					while(props.getProperty("OptionValue" + optsetindex + optindex) != null){
						optname = props.getProperty("OptionValue" + optsetindex + optindex);
						optprice = props.getProperty("OptionPrice" + optsetindex + optindex);
						//Create an option
						auto.setOption(optsetindex - 1, optname, Float.valueOf(optprice));
						
						optindex++;
					}
					
					optsetindex++;
				}
			}
			return auto;
		}

	public Automobile readObject(Object propobject){
		// Receive a serilized object and create a new Automobile
			
			Properties props = (Properties) propobject;
		
			Automobile auto = null;
			
			String carmake = props.getProperty("CarMake");
			if(!carmake.equals(null)){
				//Get the model name
				String modelname = props.getProperty("CarMake") + " " +
								   props.getProperty("CarModel");
				//Build a new automobile
				auto = new Automobile(modelname);
				//Set baseprice
				String baseprice = props.getProperty("BasePrice");
				if(!baseprice.equals(null)){
						auto.setBasePrice(Float.valueOf(baseprice));
				}
				/*else{
					Error e = null;
					throw new AutoException(e.NO102);
				}*/
				//Set optionset
				int optsetindex = 1;
				String optset;
				
				while(props.getProperty("Option" + optsetindex) != null){
					//Create an optset
					optset = props.getProperty("Option" + optsetindex);
					auto.setOptionset(optset);
					//Get option names & prices
					char optindex = 'a';
					String optname;
					String optprice;
					
					while(props.getProperty("OptionValue" + optsetindex + optindex) != null){
						optname = props.getProperty("OptionValue" + optsetindex + optindex);
						optprice = props.getProperty("OptionPrice" + optsetindex + optindex);
						//Create an option
						auto.setOption(optsetindex - 1, optname, Float.valueOf(optprice));
						
						optindex++;
					}
					
					optsetindex++;
				}
			}
			return auto;
		}

}
