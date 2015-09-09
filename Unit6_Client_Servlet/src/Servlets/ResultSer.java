package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.AutoException;
import Model.Automobile;

/**
 * Servlet implementation class ResultSer
 */
public class ResultSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultSer() {
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
		Automobile auto = (Automobile) request.getSession().getAttribute("auto");
		PrintWriter out = response.getWriter();
		String name = auto.getModel();
		request.getSession().setAttribute("autoname", name);
		float baseprice = auto.getBasePrice();
		request.getSession().setAttribute("baseprice", baseprice);
		if(auto!=null){
			ArrayList<String> optsetlist = auto.getOptsetlist();
			int length = optsetlist.size();
			request.getSession().setAttribute("size", length);
			for(int i = 0; i < length; i++){
				String optsetname = optsetlist.get(i);
				String optname = request.getParameter(optsetname);
				String choice = null;
				try {
					 auto.setOptionChoice(optsetname, optname);
					 choice = auto.getOptionChoice(optsetname);
				} catch (AutoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(choice != null)
					out.println(choice);
					request.getSession().setAttribute("Optset" + i, optsetname);
					request.getSession().setAttribute("Choice" + i, choice);
			}
			request.getRequestDispatcher("Result.jsp").forward(request, response);
		}
	}
}

