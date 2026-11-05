package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import common.MessageUtil;
import form.LoginForm;
import form.LoginResult;
import model.GlobalConst;
import service.LoginService;
import service.LoginServiceImpl;

/**
 * Servlet.
 */
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LoginServlet() {
    super();
  }

  LoginForm loginForm;

  /**
   * 初期表示
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 初期化
    request.setAttribute("message", "");
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
    loginForm = new LoginForm(
    	(String) request.getParameter("user"),
    	(String) request.getParameter("password")
    	);

    // 2. inputCheck(フロントで実行)
    if(!loginForm.inputCheck()) {
      //reload
      request.setAttribute("message","未入力項目があります");
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspLoginUrl);
  	  dispatcher.forward(request, response);
  	  return;
    }

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      LoginService service = new LoginServiceImpl();
	  LoginResult result = service.execute(loginForm);
	  if(result.isSuccess()) {
	    // 4. requestSetAttribute
	    request.setAttribute("userName", result.getUserName());
        // 5. forward
	    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuIndexUrl);
	    dispatcher.forward(request, response);
	  } else {
	    // 5. forward
        request.setAttribute("message",MessageUtil.getProperty("msg_w01_login"));
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
