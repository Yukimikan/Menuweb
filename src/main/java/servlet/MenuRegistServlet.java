package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import form.MenuRegistForm;
import model.GlobalConst;
import service.MenuRegistService;
import service.MenuRegistServiceImpl;
import service.MenuRegistServiceResult;

/**
 * Servlet.
 */
public class MenuRegistServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private MenuRegistService service;

  public MenuRegistServlet() {
    super();
    service = new MenuRegistServiceImpl();
  }


  /**
   * 初期表示
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 初期化

	// 1. CSVリスト取得（SharedService）

    // 2. 初期表示へ forward
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(GlobalConst.JspMenuRegistUrl);
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
	String userName = (String) request.getParameter("user_name");
	MenuRegistForm menuRegistform = new MenuRegistForm(
        (String) request.getParameter("confirm_flg"),
        (String) request.getParameter("type"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("menu"),
        (String) request.getParameter("price"),
        (String) request.getParameter("tax"),
        (String) request.getParameter("total"),
        userName,
        "");
	// チェックボックスの選択された値を取得
	// String[] confirm_flg = request.getParameterValues("confirm_flg");
	String confirmFlg = request.getParameter("confirm_flg");
	boolean checked = confirmFlg != null;
    // 2. inputCheck(フロントで実行)
	String message = menuRegistform.inputCheck();
    if (!message.isEmpty()) {
      request.setAttribute("message", message);
      // CSVリストを再取得（入力画面に戻るため）

      // reload
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspMenuRegistUrl);
      dispatcher.forward(request, response);
      return;
    }

    // 入力チェック不要
	if (checked) {
      // 3. サーバー側のチェック
      try {
        // 3. service execute
    	MenuRegistServiceResult result = service.execute(menuRegistform, userName);
        if (result.isSuccess()) {
          // 4. requestSetAttribute

          // 5. forward
          RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspMenuResultUrl);
          dispatcher.forward(request, response);
        }
	  } catch (Exception e) {
        // エラー画面に遷移
        System.out.print(e.getMessage());
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
        dispatcher.forward(request, response);
	    }
      } else {
        // 3. サーバー側のチェック

        // 3. service execute

    	// 4. requestSetAttribute
    	request.setAttribute("user_name", userName);
    	request.setAttribute("form", menuRegistform);
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspMenuConfirmUrl);
        dispatcher.forward(request, response);
    }
  }
}
