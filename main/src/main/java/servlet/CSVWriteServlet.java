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
public class CSVWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String forwardScreenUrl = "./jsp/menu_csv_result.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CSVWriteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String csv_name = "menu.csv";

		// データ移送
		MenuCSV rec = new MenuCSV();
		
//		rec.setNo((String) request.getParameter("type"));
		rec.setType((String) request.getParameter("type"));
		rec.setRestaurant_name((String) request.getParameter("restaurant_name"));
		rec.setSinglemenu_flg((String) request.getParameter("singlemenu_flg"));
		rec.setMenu((String) request.getParameter("menu"));
		rec.setPrice((String) request.getParameter("price"));
		rec.setTax((String) request.getParameter("tax"));
		rec.setTotal((String) request.getParameter("total"));
		
		try {
			// service
			CSVControlServiceImpl service = new CSVControlServiceImpl();
			List<MenuCSV> retList = service.writeCSV(rec, csv_name);		
			// requestSetAttribute
			request.setAttribute("retList" ,retList);
			// forward
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardScreenUrl);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
