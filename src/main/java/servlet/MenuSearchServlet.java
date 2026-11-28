package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.MenuSearchForm;
import model.WebConst;
import service.MenuSearchService;
import service.MenuSearchServiceImpl;
import service.MenuSearchServiceResult;

/**
 * Servlet.
 */
public class MenuSearchServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private MenuSearchService service;

  /**
   * コンストラクタ.
   */
  public MenuSearchServlet() {
    super();
    service = new MenuSearchServiceImpl();
  }

  /**
   * 初期表示.
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. 画面情報取得（なし）

    // 2. 初期表示へ forward
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(WebConst.MenuSearch);
    dispatcher.forward(request, response);
  }

  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. 画面情報取得
    HttpSession session = request.getSession();
    // 1. parameter set
    MenuSearchForm form = new MenuSearchForm(
        (String) request.getParameter("date"),
        "0",
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("category"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("menu_name"),
        (String) request.getParameter("eat_in_price"),
        (String) request.getParameter("takeout_price"),
        (String) request.getParameter("eat_in_condition"),
        (String) request.getParameter("takeout_condition"),
        (String) request.getParameter("total_condition"),
        (String) session.getAttribute("userName"),
        ""
        );

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      //reload
      request.setAttribute("form", form);
      request.setAttribute("message", message);
      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.MenuSearch);
      dispatcher.forward(request, response);
      return;
    }

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      MenuSearchServiceResult result = service.execute(form);
      if (result.isSuccess()) {
        // 4. requestSetAttribute
        request.setAttribute("form", form);
        request.setAttribute("menuList", result.getMenuList());
        request.setAttribute("count", result.getMenuList().size());
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.MenuSearch);
        dispatcher.forward(request, response);
      } else {
        // 4. requestSetAttribute
        request.setAttribute("form", form);
        request.setAttribute("message", result.getMessage());
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.MenuSearch);
        dispatcher.forward(request, response);
        return;
      }
    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.Error);
      dispatcher.forward(request, response);
    }
  }

}
