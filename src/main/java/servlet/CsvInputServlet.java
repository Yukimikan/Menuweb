package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import form.MenuCsvInputForm;
import form.MenuCsvInputResult;
import model.GlobalConst;
import service.CsvFileListService;
import service.CsvFileListServiceImpl;
import service.CsvInputService;
import service.CsvInputServiceImpl;

/**
 * Servlet.
 */
public class CsvInputServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CsvInputServlet() {
    super();
  }

  MenuCsvInputForm menuCsvInputForm;

  /**
   * 初期表示
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

    // 1. parameter set
    menuCsvInputForm = new MenuCsvInputForm(
      (String) request.getParameter("date"),
      (String) request.getParameter("csv_name"),
      (String) request.getParameter("total"),
      (String) request.getParameter("total_condition"),
      ""
      );

    // 2. inputCheck(フロントで実行)
    if(!menuCsvInputForm.inputCheck()) {
    	//reload
        request.setAttribute("message","未入力項目があります");
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspInputUrl);
        dispatcher.forward(request, response);
        return;
    };

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      CsvInputService service = new CsvInputServiceImpl();
      MenuCsvInputResult result = service.execute(menuCsvInputForm);
      if (result.isSuccess()) {
        // 4. requestSetAttribute
        request.setAttribute("resultList", result.getRetList());
        //  request.setAttribute("message", "検索が完了しました");
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
        dispatcher.forward(request, response);
      } else {
  	    // 5. forward
        request.setAttribute("message","何らかのエラー発生");
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspInputUrl);
        dispatcher.forward(request, response);
    	return;
      }
    } catch (Exception e) {
      // エラー画面に遷移
      System.out.print(e.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
      dispatcher.forward(request, response);
    }
  }

}
