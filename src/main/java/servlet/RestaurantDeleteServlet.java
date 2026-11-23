package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.RestaurantDeleteForm;
import form.RestaurantSearchForm;
import model.GlobalConst;
import service.RestrnDeleteService;
import service.RestrnDeleteServiceImpl;
import service.RestrnRegistServiceResult;

/**
 * Servlet.
 */
public class RestaurantDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private RestrnDeleteService service;

  /**
   * コンストラクタ.
   */
  public RestaurantDeleteServlet() {
    super();
    service = new RestrnDeleteServiceImpl();
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

    // String fromScreen = (String) request.getParameter("from_screen");

    // 1. parameter set
    HttpSession session = request.getSession();
    String userName = (String) session.getAttribute("userName");
    RestaurantDeleteForm form = new RestaurantDeleteForm(
        (String) request.getParameter("selected_row"),
        "");

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      // reload
      RestaurantSearchForm searchForm = new RestaurantSearchForm(
            (String) request.getParameter("restaurant_name"),
            (String) request.getParameter("type"),
            ""
            );
      request.setAttribute("form", searchForm);
      request.setAttribute("message", message);

      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.RestaurantSearch);
      dispatcher.forward(request, response);
      return;
    }

    // 入力チェック不要
    // 3. サーバー側のチェック
    try {
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
