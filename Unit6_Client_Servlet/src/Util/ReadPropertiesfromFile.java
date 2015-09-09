package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesfromFile {
	
		public Properties Read(String filename){
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
			
			return props;
		}
}
