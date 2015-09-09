package Util;

/* Project_1_Unit_4
 * Name: Baiyang(Keen) Wang
 * andrew id: baiyangw
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import Model.Automobile;
import Exception.*;
import Exception.Error;

public class ReadResource {
	private int linenum = 0;
	//Variables to create a new automotive
	protected Automobile a; 
	private String[] opts = new String[2];
	//Method to create an Automotive object
		public Automobile buildAutoObject(Automobile a){
			return a;
		}
		
		//Method to read file and populate the Automotive object
		public Automobile Read(String filename) throws AutoException{
			
			try {
				FileReader file = new FileReader(filename);
				BufferedReader buff = new BufferedReader(file);
				boolean eof = false;
				a = buildAutoObject(new Automobile(filename));
				
				while(!eof){
					String line = buff.readLine();
					linenum++;
					if(line == null){
						eof = true;
						}
					else{
						//Set baseprice
						if(linenum == 1){
							//Check whether an exception of getting base price happens
							try{
								a.setBasePrice(Float.valueOf(line));
							}catch(NumberFormatException ne){
								Error e = null;
								throw new AutoException(e.NO102);
							}
							 continue;
						}
						//Set Optionsets
						if(linenum == 2){
							StringTokenizer str = new StringTokenizer(line, ",");
							int optsetsize = str.countTokens();
							String[] optsets = new String[optsetsize];
							int i = 0;
							while(str.hasMoreTokens()){
								optsets[i] = str.nextToken().trim();
								i++;
								}
							a.setOptionset(optsets);
							continue;
						}
						
						opts = line.split(":");
						//Get Option Names
						StringTokenizer strname = new StringTokenizer(opts[0], ",");
						String[] optnames = new String[strname.countTokens()];
						int i = 0;
						while(strname.hasMoreTokens()){
							optnames[i] = strname.nextToken().trim();
							i++;
						}
						//Get Option Prices
						StringTokenizer strprice = new StringTokenizer(opts[1], ",");
						float[] optprices = new float[strprice.countTokens()];
						i = 0;
						while(strprice.hasMoreTokens()){
							optprices[i] = Float.valueOf(strprice.nextToken().trim());
							i++;
						}
						
						//Set Option names
						a.setOptionName(linenum - 3, optnames);
						
					//Check whether any optnames or optprices are missing
						if(optnames.length == optprices.length)
							//Set Option prices
							a.setOptionPrice(linenum - 3, optprices);
						else{
							//throw exceptions
							if(optnames.length > optprices.length){
								Error e =null;
								throw new AutoException(e.NO104);	
							}
							else{
								Error e = null;
								throw new AutoException(e.NO103);
							}
						}
						}
					}
				buff.close();
			}
			catch(FileNotFoundException f){
				Error e = null;
				throw new AutoException(e.NO101);
			}
			catch(IOException e){
				throw new AutoException(106);
			}			
			return a;
		}
		
}
