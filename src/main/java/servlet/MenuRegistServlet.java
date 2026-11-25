package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import form.MenuRegistForm;
import model.WebConst;
import service.MenuRegistService;
import service.MenuRegistServiceImpl;
import service.MenuRegistServiceResult;

/**
 * Servlet.
 */
public class MenuRegistServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private MenuRegistService service;

  /**
   * コンストラクタ.
   */
  public MenuRegistServlet() {
    super();
    service = new MenuRegistServiceImpl();
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
        request.getRequestDispatcher(WebConst.MenuRegist);
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
    HttpSession session = request.getSession();
    String userName = (String) session.getAttribute("userName");
    // 戻る押下した場合
    if ("confirm".equals(fromScreen)) {
      // 遷移元（確認画面）
      // 1. parameter set
      MenuRegistForm form = new MenuRegistForm(
          "1",
          (String) request.getParameter("restaurant_name"),
          (String) request.getParameter("menu_name"),
          (String) request.getParameter("category"),
          (String) request.getParameter("singlemenu_flg"),
          (String) request.getParameter("eat_in_price"),
          (String) request.getParameter("takeout_price"),
          userName,
          "");
      request.setAttribute("form", form);
      // 2. 初期表示へ forward
      RequestDispatcher dispatcher =
          request.getRequestDispatcher(WebConst.MenuRegist);
      dispatcher.forward(request, response);

      return;
    }
    // チェックボックスの選択された値を取得
    // String[] confirm_flg = request.getParameterValues("confirm_flg");
    String confirmFlg = request.getParameter("confirm_flg");
    boolean checked = confirmFlg != null;

    // 入力チェック不要
    if (checked) {
      regist(request, response);
    } else {
      confirm(request, response);
    }

  }

  /**
   *  登録用.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  private void regist(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // 1. parameter set
    HttpSession session = request.getSession();

    String userName = (String) session.getAttribute("userName");
    // 1. parameter set
    MenuRegistForm form = new MenuRegistForm(
        (String) request.getParameter("confirm_flg"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("menu_name"),
        (String) request.getParameter("category"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("eat_in_price"),
        (String) request.getParameter("takeout_price"),
        userName,
        "");

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      // reload
      request.setAttribute("form", form);
      request.setAttribute("message", message);

      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.MenuRegist);
      dispatcher.forward(request, response);
      return;
    }

    // 3. サーバー側のチェック
    try {
      // 3. service execute
      MenuRegistServiceResult result = service.execute(form);
      if (result.isSuccess()) {
        // 4. requestSetAttribute

        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.MenuRegistFinish);
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.Error);
      dispatcher.forward(request, response);
    }
  }

  /**
   *  確認画面遷移.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  private void confirm(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // 1. parameter set
    HttpSession session = request.getSession();

    String userName = (String) session.getAttribute("userName");
    // 1. parameter set
    MenuRegistForm form = new MenuRegistForm(
        (String) request.getParameter("confirm_flg"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("menu_name"),
        (String) request.getParameter("category"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("eat_in_price"),
        (String) request.getParameter("takeout_price"),
        userName,
        "");

    // 2. inputCheck(フロントで実行)
    String message = form.inputCheck();
    if (!message.isEmpty()) {
      // reload
      request.setAttribute("form", form);
      request.setAttribute("message", message);

      RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.MenuRegist);
      dispatcher.forward(request, response);
      return;
    }
    // 3. サーバー側のチェック

    // 3. service execute

    // 4. requestSetAttribute
    request.setAttribute("form", form);
    // 5. forward
    RequestDispatcher dispatcher = request.getRequestDispatcher(WebConst.MenuConfirm);
    dispatcher.forward(request, response);
  }

}
