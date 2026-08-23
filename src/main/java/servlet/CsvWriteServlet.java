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
    String csvName = (String) request.getParameter("csv_name");
    String[] cols = {
      "99", // No（新規登録なので空）
      request.getParameter("type"),
      request.getParameter("restaurant_name"),
      request.getParameter("singlemenu_flg"),
      request.getParameter("menu"),
      request.getParameter("price"),
      request.getParameter("tax"),
      request.getParameter("total")
    };

    // MenuCSV に渡す(初期化)
    MenuCSV rec = MenuCSV.fromInput(cols);
    menuCsvRegistform = new MenuCsvRegistForm(csvName, rec, "");

    try {
      // 2. inputCheck(フロントで実行)
      // サーバー側チェック（必要最小限）
      if (!menuCsvRegistform.commonCheck(menuCsvRegistform.getCsv_name())) {
        throw new IOException("CSV名が不正です");
      }
      // 3. service execute
      CsvWriteService service = new CsvWriteServiceImpl();
      List<MenuCSV> retList = service.execute(menuCsvRegistform);

      // 4. requestSetAttribute
      request.setAttribute("resultList", retList);
      // 5. forward
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
      dispatcher.forward(request, response);
    } catch (Exception e) {
      // ★ 例外系 → 入力画面へ戻す
      request.setAttribute("error", "入力値が不正です：" + e.getMessage());
      // CSVリストを再取得（入力画面に戻るため）
      CsvFileListService fileService = new CsvFileListServiceImpl();
      List<String> csvFiles = fileService.execute();
      request.setAttribute("csvFiles", csvFiles);
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspRegistUrl);
      dispatcher.forward(request, response);
    }

  }

}
