package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import dao.UserDaoImpl;
import model.GlobalConst;

/**
 * Servlet implementation class HelloServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");

		System.out.println(user);
		//	doGet(request, response);
		try {
		   UserDaoImpl daoImpl = new UserDaoImpl();
     	   String userName = daoImpl.SelectSQLGetUser(user, password);
     	   if ("".equals(userName)) {
         	   request.setAttribute("error", "入力値が不正です：");
       	       RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuLoginUrl);
      	       dispatcher.forward(request, response);
     	   }else {
		       // 5. forward
		       RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuIndexUrl);
		       dispatcher.forward(request, response);
     	   }

	    } catch (Exception e) {
	      // ★ 例外時は入力画面に戻す（ここが重要）
	      request.setAttribute("error", "入力値が不正です：" + e.getMessage());

	      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuLoginUrl);
	      dispatcher.forward(request, response);
	    }

	}

}
