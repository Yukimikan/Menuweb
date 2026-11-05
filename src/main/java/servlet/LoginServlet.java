package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import common.MessageUtil;
import form.LoginForm;
import model.GlobalConst;
import service.LoginService;
import service.LoginServiceImpl;

/**
 * Servlet implementation class HelloServlet.
 */
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public LoginServlet() {
    super();
  }

  LoginForm loginForm;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. parameter set
    loginForm = new LoginForm(
    	(String) request.getParameter("user"),
    	(String) request.getParameter("password")
    	);

    // 2. inputCheck(フロントで実行)
    if(!loginForm.inputCheck()) {
      //reload
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuLoginUrl);
  	  dispatcher.forward(request, response);
  	  return;
    }

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      LoginService service = new LoginServiceImpl();
	  String userName = service.execute(loginForm);
      // 4. requestSetAttribute
      request.setAttribute("userName", userName);
	  // 5. forward
      if (userName == null || userName.isEmpty()) {
        String errMessage = MessageUtil.getProperty("msg_w01_login");
        System.out.print(errMessage);
   	    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuLoginUrl);
  	    dispatcher.forward(request, response);
      } else {
        // 5. forward
	    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuIndexUrl);
        dispatcher.forward(request, response);
      }

    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
      dispatcher.forward(request, response);
    }

  }

}
