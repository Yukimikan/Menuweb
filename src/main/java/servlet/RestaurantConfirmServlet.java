package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.RestaurantConfirmForm;
import model.GlobalConst;
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
   * 初期表示.
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
    RestaurantConfirmForm form = new RestaurantConfirmForm(
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("type"),
        "");

    // 2. inputCheck(フロントで実行)

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      RestrnRegistServiceResult result = service.execute(form, userName);
      if (result.isSuccess()) {
        // 4. requestSetAttribute

        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.RestaurantRegistFinish);
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.Error);
      dispatcher.forward(request, response);
    }

  }

}
