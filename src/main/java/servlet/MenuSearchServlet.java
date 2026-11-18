package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.MenuSearchForm;
import model.GlobalConst;
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
        request.getRequestDispatcher(GlobalConst.MenuSearch);
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
    HttpSession session = request.getSession();
    String userName = (String) session.getAttribute("userName");
    MenuSearchForm form = new MenuSearchForm(
        (String) request.getParameter("date"),
        (String) request.getParameter("type"),
        (String) request.getParameter("restaurant_name"),
        "0",
        "",
        "0",
        "0",
        (String) request.getParameter("total"),
        (String) request.getParameter("total_condition"),
        ""
        );

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      //reload
      request.setAttribute("form", form);
      request.setAttribute("message", message);
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuSearch);
      dispatcher.forward(request, response);
      return;
    }

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      MenuSearchServiceResult result = service.execute(form, userName);
      if (result.isSuccess()) {
        // 4. requestSetAttribute
        request.setAttribute("resultList", result.getRetList());
        //  request.setAttribute("message", "検索が完了しました");
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuSearchResult);
        dispatcher.forward(request, response);
      } else {
        // 5. forward
        request.setAttribute("form", form);
        request.setAttribute("message", result.getMessage());
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.MenuSearch);
        dispatcher.forward(request, response);
        return;
      }
    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.Error);
      dispatcher.forward(request, response);
    }
  }

}
