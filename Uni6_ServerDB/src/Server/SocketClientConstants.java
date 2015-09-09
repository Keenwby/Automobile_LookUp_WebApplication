package Server;

public interface SocketClientConstants {
	int iECHO_PORT = 7;
    int iDAYTIME_PORT = 13;
    int iSMTP_PORT = 25;
    boolean DEBUG = true;
    
    //Server receives file and creates auto
    int SER_SAVE_FILE = 1;
    //Server creates auto successfully
    int SER_SAVE_FILE_SUCC = 2;
    //Server sends file 
    int SER_SEND_FILE = 3;
    //Client asks server to provide all models name
    int CLI_ALL_MODELS = 4;
    //Client asks server to provide a model
    int CLI_ONE_MODEL = 5;
    
    //Stop the socket
    int STOP = 0;
}
