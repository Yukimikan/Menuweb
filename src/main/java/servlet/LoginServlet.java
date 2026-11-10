package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import form.LoginForm;
import model.GlobalConst;
import service.LoginService;
import service.LoginServiceImpl;
import service.LoginServiceResult;

/**
 * Servlet.
 */
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private LoginService service;

  public LoginServlet() {
    super();
    service = new LoginServiceImpl();
  }


  /**
   * 初期表示
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. 画面情報取得（なし）

    // 2. 初期表示へ forward
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(GlobalConst.JspLoginUrl);
    dispatcher.forward(request, response);
  }

  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. parameter set
	LoginForm loginForm = new LoginForm(
    	(String) request.getParameter("user"),
    	(String) request.getParameter("password"),
    	""
    	);

    // 2. inputCheck(フロントで実行)
	String message = loginForm.inputCheck();
    if(!message.isEmpty()) {
      //reload
      request.setAttribute("message", message);
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspLoginUrl);
  	  dispatcher.forward(request, response);
  	  return;
    }

    try {
      // 3. サーバー側のチェック

      // 3. service execute
	  LoginServiceResult result = service.execute(loginForm);
	  if (result.isSuccess()) {
	    // 4. requestSetAttribute
	    request.setAttribute("userName", result.getUserName());
        // 5. forward
	    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuIndexUrl);
	    dispatcher.forward(request, response);
	  } else {
	    // 5. forward
        request.setAttribute("message",result.getMessage());
   	    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspLoginUrl);
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
