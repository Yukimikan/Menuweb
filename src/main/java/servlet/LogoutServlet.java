package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet.
 */
public class LogoutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public LogoutServlet() {
    super();
  }

  /**
   * ログアウト
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. 画面情報取得（なし）
    // Session が存在しなければ新規作成しない。
    HttpSession session = request.getSession(false);

    if (session != null) {
      session.invalidate();
    }
    response.sendRedirect("login");
  }

}
