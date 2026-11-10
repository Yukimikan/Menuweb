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
import service.CsvFileListService;
import service.CsvFileListServiceImpl;
import service.CsvWriteService;
import service.CsvWriteServiceImpl;
import service.CsvWriteServiceResult;

/**
 * Servlet.
 */
public class CsvWriteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private CsvWriteService service;
  private CsvFileListService fileService;

  public CsvWriteServlet() {
    super();
    service = new CsvWriteServiceImpl();
    fileService = new CsvFileListServiceImpl();
  }


  /**
   * 初期表示
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 初期化
    request.setAttribute("message", "");

    // 1. CSVリスト取得（SharedService）
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
	MenuCsvRegistForm menuCsvRegistform = new MenuCsvRegistForm(
        (String) request.getParameter("csv_name"),
        (String) request.getParameter("confirm_flg"),
        (String) request.getParameter("type"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("menu"),
        (String) request.getParameter("price"),
        (String) request.getParameter("tax"),
        (String) request.getParameter("total"),
        "");
	// チェックボックスの選択された値を取得
	// String[] confirm_flg = request.getParameterValues("confirm_flg");
	String confirmFlg = request.getParameter("confirm_flg");
	boolean checked = confirmFlg != null;
    // 2. inputCheck(フロントで実行)
    if (!menuCsvRegistform.inputCheck()) {
      request.setAttribute("message", "未入力項目があります");
      // CSVリストを再取得（入力画面に戻るため）
      List<String> csvFiles = fileService.execute();
      request.setAttribute("csvFiles", csvFiles);
      // reload
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspRegistUrl);
      dispatcher.forward(request, response);
      return;
    }

    // 入力チェック不要
	try {
  	  if (checked) {
        // 3. サーバー側のチェック
        // 3. service execute
        CsvWriteServiceResult result = service.execute(menuCsvRegistform);
	    if (result.isSuccess()) {
          // 4. requestSetAttribute
          request.setAttribute("resultList", result.getRetList());
          // 5. forward
          RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
          dispatcher.forward(request, response);
	    } else {
          // 4. requestSetAttribute
          request.setAttribute("resultList", result.getRetList());
          // 5. forward
          RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspRegistUrl);
          dispatcher.forward(request, response);
        }
	  } else {
        // 3. サーバー側のチェック
        // 3. service execute
  	    // CsvWriteServiceResult result = service.execute(menuCsvRegistform);
  	    // 4. requestSetAttribute
	    request.setAttribute("csv_name", menuCsvRegistform.getCsvName());
	    request.setAttribute("type", menuCsvRegistform.getType());
	    request.setAttribute("restaurant_name", menuCsvRegistform.getRestaurantName());
	    request.setAttribute("singlemenu_flg", menuCsvRegistform.getSinglemenuFlg());
	    request.setAttribute("menu", menuCsvRegistform.getMenu());
	    request.setAttribute("price", menuCsvRegistform.getPrice());
	    request.setAttribute("tax", menuCsvRegistform.getTax());
	    request.setAttribute("total", menuCsvRegistform.getTotal());
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspConfirmUrl);
        dispatcher.forward(request, response);
      }
  	} catch (Exception e) {
        // エラー画面に遷移
        System.out.print(e.getMessage());
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
        dispatcher.forward(request, response);
      }
  }
}
