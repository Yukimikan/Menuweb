package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.MenuCSV;
import service.CSVControlServiceImpl;

/**
 * Servlet implementation class HelloServlet
 */
public class CSVInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String inputUrl = "./jsp/menu_csv_input.jsp";
	private final String resultUrl = "./jsp/menu_csv_result.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CSVInputServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// String date = (String) request.getParameter("date");
		String csv_name = (String) request.getParameter("csv_name");
		String err_message = "";
		
		try {
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.readCSV(csv_name);
			
			// 終了条件を判定
			if (retList != null) {
				// requestSetAttribute
				request.setAttribute("retList", retList);
				//forward
				RequestDispatcher dispatcher = request.getRequestDispatcher(resultUrl);
				dispatcher.forward(request, response);
			}else {
				//forward
				RequestDispatcher dispatcher = request.getRequestDispatcher(inputUrl);
				dispatcher.forward(request, response);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
