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
		String user = (String) request.getAttribute("user");
		String password = (String) request.getAttribute("password");

		System.out.println(user);
		//	doGet(request, response);
		try {
		   UserDaoImpl daoImpl = new UserDaoImpl();
     	   String userName = daoImpl.SelectSQLGetUser(user, password);
     	   if (userName.equals("")) {
		       // 5. forward
		       RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuIndexUrl);
		       dispatcher.forward(request, response);
     	   }else {
     		  //error
     		  request.setAttribute("user","error");
     	      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuLoginUrl);
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
