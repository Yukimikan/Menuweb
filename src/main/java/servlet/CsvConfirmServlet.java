package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import form.CsvConfirmForm;
import model.GlobalConst;
import service.CsvWriteService;
import service.CsvWriteServiceImpl;
import service.CsvWriteServiceResult;

/**
 * Servlet.
 */
public class CsvConfirmServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private CsvWriteService service;

  /**
   * コンストラクタ.
   */
  public CsvConfirmServlet() {
    super();
    service = new CsvWriteServiceImpl();
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
    CsvConfirmForm form = new CsvConfirmForm(
        (String) request.getParameter("csv_name"),
        (String) request.getParameter("type"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("menu"),
        (String) request.getParameter("price"),
        (String) request.getParameter("tax"),
        (String) request.getParameter("total"),
        "");

    // 2. inputCheck(フロントで実行)

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      CsvWriteServiceResult result = service.execute(form);
      if (result.isSuccess()) {
        // 4. requestSetAttribute

        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.CsvWriteFinish);
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
