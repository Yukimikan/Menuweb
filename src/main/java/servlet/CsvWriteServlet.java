package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import form.MenuCsvRegistForm;
import model.GlobalConst;
import model.MenuCSV;
import service.CsvFileListService;
import service.CsvFileListServiceImpl;
import service.CsvWriteService;
import service.CsvWriteServiceImpl;

/**
 * Servlet implementation class HelloServlet.
 */
public class CsvWriteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CsvWriteServlet() {
    super();
  }

  MenuCsvRegistForm menuCsvRegistform;

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
        request.getRequestDispatcher(GlobalConst.JspRegistUrl);
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
    menuCsvRegistform =
      new MenuCsvRegistForm(
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
    if (!menuCsvRegistform.inputCheck()) {
      request.setAttribute("message", "CSV名が不正です：");
      // CSVリストを再取得（入力画面に戻るため）
      CsvFileListService fileService = new CsvFileListServiceImpl();
      List<String> csvFiles = fileService.execute();
      request.setAttribute("csvFiles", csvFiles);
      // reload
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspRegistUrl);
      dispatcher.forward(request, response);
      return;
    }
    try {
      // 3. サーバー側のチェック

      // 3. service execute
      CsvWriteService service = new CsvWriteServiceImpl();
      List<MenuCSV> retList = service.execute(menuCsvRegistform);

      // 4. requestSetAttribute
      request.setAttribute("resultList", retList);
      // 5. forward
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
      dispatcher.forward(request, response);
    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
      dispatcher.forward(request, response);
    }

  }

}
