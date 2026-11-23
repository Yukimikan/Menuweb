package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.RestrnSearchForm;
import model.WebConst;
import service.RestrnDeleteService;
import service.RestrnDeleteServiceImpl;
import service.RestrnDeleteServiceResult;
import service.RestrnSearchService;
import service.RestrnSearchServiceImpl;
import service.RestrnSearchServiceResult;

/**
 * Servlet.
 */
public class RestaurantSearchServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private RestrnSearchService service;
  private RestrnDeleteService deleteService;

  /**
   * コンストラクタ.
   */
  public RestaurantSearchServlet() {
    super();
    service = new RestrnSearchServiceImpl();
    deleteService = new RestrnDeleteServiceImpl();
  }

  /**
   * 初期表示.
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. 画面情報取得
    // 1. parameter set
    HttpSession session = request.getSession();
    String userName = (String) session.getAttribute("userName");

    // 2. inputCheck(フロントで実行)

    // 3. service execute
    RestrnSearchServiceResult result = service.initSearch(userName);
    // 4. requestSetAttribute
    request.setAttribute("restaurantList", result.getRestaurantList());
    request.setAttribute("count", result.getRestaurantList().size());
    // 5. 初期表示
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(WebConst.RestaurantSearch);
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
    String action = (String) request.getParameter("action");
    switch(action) {
      case WebConst.Action_検索:
        this.search(request, response);
        break;
      case WebConst.Action_削除:
        this.delete(request, response);
        break;
      default :
    }
  }

  /**
   *  検索用.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  private void search(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

    // 1. parameter set
    HttpSession session = request.getSession();
    String userName = (String) session.getAttribute("userName");
    RestrnSearchForm form = new RestrnSearchForm(
        (String) request.getParameter("action"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("type"),
        null,
        userName,
        ""
        );

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      //reload
      request.setAttribute("form", form);
      request.setAttribute("message", message);
      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.RestaurantSearch);
      dispatcher.forward(request, response);
      return;
    }

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      RestrnSearchServiceResult result = service.execute(form);
      if (result.isSuccess()) {
        // 4. requestSetAttribute
        request.setAttribute("form", form);
        request.setAttribute("restaurantList", result.getRestaurantList());
        request.setAttribute("count", result.getRestaurantList().size());
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.RestaurantSearch);
        dispatcher.forward(request, response);
      } else {
        // 4. requestSetAttribute
        request.setAttribute("form", form);
        request.setAttribute("message", result.getMessage());
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.RestaurantSearch);
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

  /**
   *  削除用.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  private void delete(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // 1. parameter set
    HttpSession session = request.getSession();
    String userName = (String) session.getAttribute("userName");
    RestrnSearchForm form = new RestrnSearchForm(
        (String) request.getParameter("action"),
        "",
        "",
        (String) request.getParameter("selected_row"),
        userName,
        "");

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      // reload
      request.setAttribute("form", form);
      request.setAttribute("message", message);

      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.RestaurantSearch);
      dispatcher.forward(request, response);
      return;
    }

    // 入力チェック不要
    // 3. サーバー側のチェック
    try {
      // 3. service execute
      RestrnDeleteServiceResult result = deleteService.execute(form, userName);
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
