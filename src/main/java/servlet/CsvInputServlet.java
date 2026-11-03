package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import form.MenuCsvInputForm;
import model.GlobalConst;
import model.MenuCSV;
import service.CsvFileListService;
import service.CsvFileListServiceImpl;
import service.CsvInputService;
import service.CsvInputServiceImpl;

/**
 * Servlet implementation class
 */
public class CsvInputServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CsvInputServlet() {
    super();
  }

  MenuCsvInputForm menuCsvInputForm;

  /**
   * 初期表示（INDEX → menu_input.jsp）
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 初期化
    request.setAttribute("message", "");
    // 1. CSVリスト取得（SharedService）
    CsvFileListService fileService = new CsvFileListServiceImpl();
    List<String> csvFiles = fileService.execute();
    request.setAttribute("csvFiles", csvFiles);

    // 2. 初期表示へ forward
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(GlobalConst.JspInputUrl);
    dispatcher.forward(request, response);
  }

  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. inputCheck
    String total = request.getParameter("total");

    if (total == null || total.trim().isEmpty()) {

      request.setAttribute("message", "金額を入力してください");

      RequestDispatcher dispatcher =
          request.getRequestDispatcher(GlobalConst.JspInputUrl);
      dispatcher.forward(request, response);
      return;
    } else if (!total.matches("[0-9]+")) {
      // message
      request.setAttribute("message", "金額を数値で入力してください");
      RequestDispatcher dispatcher =
          request.getRequestDispatcher(GlobalConst.JspInputUrl);
      dispatcher.forward(request, response);
      return;

    }

    // 1. parameter set
    menuCsvInputForm = new MenuCsvInputForm(
      (String) request.getParameter("date"),
      (String) request.getParameter("csv_name"),
      (String) request.getParameter("total"),
      (String) request.getParameter("total_condition"),
      ""
      );

    try {
      // 3. サーバー側でしか判定できないチェック

      // 3. service execute
      CsvInputService service = new CsvInputServiceImpl();
      List<MenuCSV> retList = service.execute(menuCsvInputForm);

      // 4. requestSetAttribute
      request.setAttribute("resultList", retList);
      request.setAttribute("message", "検索が完了しました");
      // 5. forward
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
      dispatcher.forward(request, response);
    } catch (Exception e) {
      // ★ 例外時は入力画面に戻す（ここが重要）
      request.setAttribute("error", "入力値が不正です：" + e.getMessage());

      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspInputUrl);
      dispatcher.forward(request, response);
    }
  }

}
