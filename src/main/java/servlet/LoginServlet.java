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

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. parameter set
    String user = request.getParameter("user");
    String password = request.getParameter("password");
    LoginForm loginForm = new LoginForm(
    	user,
    	password);

    try {
      // 2. inputCheck(フロントで実行)
      if(!loginForm.inputCheck()) {
    	  return;
      }
      // 3. サーバー側でしか判定できないチェック

      // 3. service execute
      LoginService service = new LoginServiceImpl();
	  String userName = service.execute(loginForm);
      // 4. requestSetAttribute
      request.setAttribute("userName", userName);
	  // 5. forward
      if (userName == null || userName.isEmpty()) {
        String errMessage = MessageUtil.getProperty("msg_w01_login");
        System.out.print(errMessage);
        //request.setAttribute("error", "入力値が不正です：");
   	    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuLoginUrl);
  	    dispatcher.forward(request, response);
      } else {
        // 5. forward
	    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuIndexUrl);
        dispatcher.forward(request, response);
      }

    } catch (Exception e) {
      // ★ 例外時はエラー画面に遷移（ここが重要）
      request.setAttribute("error", "入力値が不正です：" + e.getMessage());
      System.out.print(e.getMessage());

      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
      dispatcher.forward(request, response);
    }

  }

}
