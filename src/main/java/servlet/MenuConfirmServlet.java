package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.MenuConfirmForm;
import model.GlobalConst;
import service.MenuRegistService;
import service.MenuRegistServiceImpl;
import service.MenuRegistServiceResult;

/**
 * Servlet.
 */
public class MenuConfirmServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private MenuRegistService service;

  public MenuConfirmServlet() {
    super();
    service = new MenuRegistServiceImpl();
  }


  /**
   * 初期表示
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // エラー
  }

  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // 1. parameter set
	HttpSession session = request.getSession();
	String userName = (String) session.getAttribute("userName");
	MenuConfirmForm menuConfirmform = new MenuConfirmForm(
        (String) request.getParameter("type"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("menu"),
        (String) request.getParameter("price"),
        (String) request.getParameter("tax"),
        (String) request.getParameter("total"),
        "");

    // 2. inputCheck(フロントで実行)

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      MenuRegistServiceResult result = service.execute(menuConfirmform, userName);
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

  }

}
