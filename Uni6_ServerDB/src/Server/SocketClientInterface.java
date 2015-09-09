package Server;

public interface SocketClientInterface {
	
	public boolean openConnection();
    
	public void handleSession();
	
    public void closeSession();
}
