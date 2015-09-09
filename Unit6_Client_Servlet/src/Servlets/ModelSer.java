package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Client.ClientDefaultSocketClient;

/**
 * Servlet implementation class ModelSer
 */
public class ModelSer extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static ClientDefaultSocketClient client;
	
    public ModelSer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String strLocalHost = "";
		 
		  try{
		      strLocalHost = 
		        InetAddress.getLocalHost().getHostName();
		  }
		 catch (UnknownHostException e){
		      System.err.println ("Unable to find local host");
		 }
		  client = new ClientDefaultSocketClient(strLocalHost, 9008);
		  client.start();
		 
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("Please upload the files");
		out.println("FilePath:/Users/wangbaiyang/Documents/workspace_kapler/Unit5_Client/A3Wagon.properties");
		out.println("FilePath:/Users/wangbaiyang/Documents/workspace_kapler/Unit5_Client/Qirui.properties");
		if(client.getModellist() == null){
			out.println("Please reload the page after uploading.");
		}
		else{
			request.getSession().setAttribute("Modelist", client.getModellist());
			request.getRequestDispatcher("/MainPage.jsp").forward(request, response);
		}
	}

}
