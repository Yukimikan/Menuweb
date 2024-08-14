package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class SearchMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String forwardScreenUrl = "./jsp/menu_result.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String date = (String) request.getParameter("date");
		String total = (String) request.getParameter("total");
		System.out.println(date);
		System.out.println(total);
		//"./menu_result.jsp"
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardScreenUrl);
		dispatcher.forward(request, response);
	}

}
