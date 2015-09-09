package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadSchema {
	//Read Statements from properties file
	private String[] stat = new String[6];
	
	public void ReadFile(String filename){
		if (filename == null)
			return;
		
		Properties props = new Properties();
		FileInputStream in = null;
		
		try {
            in = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
		
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i = 0;
		setStat(props.getProperty("drop"), i++);
		setStat(props.getProperty("create"), i++);
		setStat(props.getProperty("use"), i++);
		setStat(props.getProperty("automobile"), i++);
		setStat(props.getProperty("optionset"), i++);
		setStat(props.getProperty("option"), i++);
	}
	
	public void setStat(String str, int i){
		this.stat[i] = str;
	}
	
	public String[] getStat(){
		return stat;
	}
}
