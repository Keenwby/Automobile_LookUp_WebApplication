package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Client.*;
import Model.Automobile;

/**
 * Servlet implementation class OptionsetSer
 */
public class OptionsetSer extends HttpServlet implements ClientConstance, SocketClientConstance{
	private static final long serialVersionUID = 1L;
       
	private Automobile auto;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OptionsetSer() {
        super();
        // TODO Auto-generated constructor stub
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
		String str = request.getParameter("Choice");
		
		out.println(str);
		
		ClientDefaultSocketClient client = ModelSer.client;
		CarModelOptionsIO cm = client.getCarModelOptionsIO();
		
		out.println(cm.getSocket());
		cm.sendCommand(CLI_ONE_MODEL);
		
		//SelectCarOption sco = new SelectCarOption();
		cm.sendObject(str);//Send the name to Server
		
		auto = (Automobile) cm.getObject();//Get the selected Auto
		
		//Send the Auto to CarConfiguratoin.jsp
		if(auto != null){
			out.println(auto.toString());
			request.getSession().setAttribute("auto", auto);
			request.getRequestDispatcher("CarConfiguration.jsp").forward(request, response);
			}
		}
}

