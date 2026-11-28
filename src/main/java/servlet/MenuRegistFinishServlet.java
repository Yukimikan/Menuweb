package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import model.WebConst;

/**
 * Servlet.
 */
public class MenuRegistFinishServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ.
   */
  public MenuRegistFinishServlet() {
    super();
  }


  /**
   * 初期表示.
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. 画面情報取得（なし）

    // 2. 初期表示へ forward
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(WebConst.MenuRegistFinish);
    dispatcher.forward(request, response);
  }

}
