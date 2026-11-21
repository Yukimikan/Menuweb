package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.RestaurantRegistForm;
import model.GlobalConst;
import service.RestaurantRegistService;
import service.RestaurantRegistServiceImpl;
import service.RestaurantRegistServiceResult;

/**
 * Servlet.
 */
public class RestaurantRegistServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private RestaurantRegistService service;

  /**
   * コンストラクタ.
   */
  public RestaurantRegistServlet() {
    super();
    service = new RestaurantRegistServiceImpl();
  }


  /**
   * 初期表示.
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 初期化

    // 1. CSVリスト取得（SharedService）

    // 2. 初期表示へ forward
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(GlobalConst.RestaurantRegist);
    dispatcher.forward(request, response);
  }

  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String fromScreen = (String) request.getParameter("from_screen");
    // 戻る押下した場合
    if ("confirm".equals(fromScreen)) {
      // 遷移元（確認画面）
      // 1. parameter set
      // HttpSession session = request.getSession();
      RestaurantRegistForm form = new RestaurantRegistForm(
          "1",
          (String) request.getParameter("restaurant_id"),
          (String) request.getParameter("restaurant_name"),
          (String) request.getParameter("type"),
          "");
      request.setAttribute("form", form);
      // 2. 初期表示へ forward
      RequestDispatcher dispatcher =
          request.getRequestDispatcher(GlobalConst.RestaurantRegist);
      dispatcher.forward(request, response);

      return;
    }

    // 1. parameter set
    HttpSession session = request.getSession();
    String userName = (String) session.getAttribute("userName");
    RestaurantRegistForm form = new RestaurantRegistForm(
        (String) request.getParameter("confirm_flg"),
        (String) request.getParameter("restaurant_id"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("type"),
        "");

    // チェックボックスの選択された値を取得
    // String[] confirm_flg = request.getParameterValues("confirm_flg");
    String confirmFlg = request.getParameter("confirm_flg");
    boolean checked = confirmFlg != null;

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      // reload
      request.setAttribute("form", form);
      request.setAttribute("message", message);

      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.RestaurantRegist);
      dispatcher.forward(request, response);
      return;
    }

    // 入力チェック不要
    if (checked) {
      // 3. サーバー側のチェック
      try {
        // 3. service execute
    	  RestaurantRegistServiceResult result = service.execute(form, userName);
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
    } else {
      // 3. サーバー側のチェック

      // 3. service execute

      // 4. requestSetAttribute
      request.setAttribute("user_name", userName);
      request.setAttribute("form", form);
      // 5. forward
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.RestaurantConfirm);
      dispatcher.forward(request, response);
    }
  }

}
