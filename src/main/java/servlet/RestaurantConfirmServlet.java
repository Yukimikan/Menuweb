package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.RestrnRegistForm;
import model.WebConst;
import service.RestrnRegistService;
import service.RestrnRegistServiceImpl;
import service.RestrnRegistServiceResult;

/**
 * Servlet.
 */
public class RestaurantConfirmServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private RestrnRegistService service;

  /**
   * コンストラクタ.
   */
  public RestaurantConfirmServlet() {
    super();
    service = new RestrnRegistServiceImpl();
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
    RestrnRegistForm form = new RestrnRegistForm(
        null,
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("type"),
        (String) session.getAttribute("userName"),
        "");

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      // error
      request.setAttribute("form", form);
      request.setAttribute("message", message);

      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.Error);
      dispatcher.forward(request, response);
      return;
    }
    try {
      // 3. サーバー側のチェック

      // 3. service execute
      RestrnRegistServiceResult result = service.execute(form);
      if (result.isSuccess()) {
        // 4. requestSetAttribute

        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.RestaurantRegistFinish);
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.Error);
      dispatcher.forward(request, response);
    }

  }

}
