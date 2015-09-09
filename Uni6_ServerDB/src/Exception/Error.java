package Exception;

public enum Error {
		NO1(1, "Wrong OptionsetName"),
		NO2(2, "Wrong OptionName"),
		NO101(101, "Wrong Filename"),
		NO102(102, "Missing Baseprice"),
		NO103(103, "Missing Option"),
		NO104(104, "Missiong Option Price"),
		NO105(105, "Empty Optionset"),
		NO106(106, "Other IOException");
		
		private final int errorno;
		private final String errormsg;
		private Error(int errorno, String errormsg){
			this.errorno = errorno;
			this.errormsg = errormsg;
		}
		public int getErrorno(){
			return errorno;
		}
		public String getErrormsg(){
			return errormsg;
		}
		public void enumeration(){
			for(Error e : Error.values()){
				System.out.println(e.getErrorno() + ": " + e.getErrormsg());
			}
		}
}
