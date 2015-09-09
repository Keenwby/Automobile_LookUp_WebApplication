package Exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class AutoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorno = 0;
	private String errormsg = null;
	
	//Constructors
	public AutoException() {
		super();
	}
	public AutoException(int errorno){
		super();
		this.errorno = errorno;
	}
	
	public AutoException(Error e) {
		super();
		this.errorno = e.getErrorno();
		this.errormsg = e.getErrormsg();
		printproblem();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, kk:mm:ss",Locale.US);
		String timeStr = sdf.format(new Date());
		writelog("Error " + " " + errorno + ": " + errormsg + timeStr + "\n");
	}
	
	//Getters & Setters
	public int getErrorno() {
		return errorno;
	}
	
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	
	public String getErrormsg() {
		return errormsg;
	}
	
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	//Printers
	public void printproblem() {
		System.out.println("Exception: [errorno=" + errorno + ", errormsg=" + errormsg); 
	}
	public void printString(String str){
		System.out.println(str);
	}
	
	//Fixers
	public void fix(int errno) {
		
		if(errno <= 100){
			Fix1to100 f1 = new Fix1to100(); 
			switch(errno){
			case 1: f1.fix1();	break;
			case 2: f1.fix2();	break;
			/*case 3: f1.fix3();	break;
			case 4: f1.fix4();	break;
			case 5: f1.fix5();	break;*/
			}
		}
		
		if(errno > 100){
			Fix101to200 f2 = new Fix101to200(); 
			printString("Sorry, any mistakes in input file will terminate this program.");
			switch(errno){
			case 101: f2.fix101();	break;
			case 102: f2.fix102();	break;
			case 103: f2.fix103();	break;
			case 104: f2.fix104();	break;
			case 105: f2.fix105();	break;
			case 106: f2.fix106();	break;
			}
		}
	}
	public void writelog(String str){  
	    try {  
	    	FileWriter fw = new FileWriter("log.txt", true);  
	        BufferedWriter bw = new BufferedWriter(fw);  
	        bw.append(str); 
	        bw.close();  
	        fw.close();  
	    }catch (Exception e) {  
        // TODO Auto-generated catch block  
	         e.printStackTrace();  
	        }  
	   } 
}
