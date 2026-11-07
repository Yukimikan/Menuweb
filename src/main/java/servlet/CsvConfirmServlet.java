package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import form.MenuCsvConfirmForm;
import model.GlobalConst;
import service.CsvFileListService;
import service.CsvFileListServiceImpl;
import service.CsvWriteService;
import service.CsvWriteServiceImpl;
import service.CsvWriteServiceResult;

/**
 * Servlet.
 */
public class CsvConfirmServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private CsvWriteService service;
  private CsvFileListService fileService;

  public CsvConfirmServlet() {
    super();
    service = new CsvWriteServiceImpl();
    fileService = new CsvFileListServiceImpl();
  }


  /**
   * 初期表示
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
	MenuCsvConfirmForm menuCsvConfirmform = new MenuCsvConfirmForm(
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
    if (!menuCsvConfirmform.inputCheck()) {
      // error deadlogic
      request.setAttribute("message", "入力エラー");
      // error
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
      dispatcher.forward(request, response);
      throw new RuntimeException();
    }

    try {
      // 3. サーバー側のチェック

      // 3. service execute
      CsvWriteServiceResult result = service.execute(menuCsvConfirmform);

      if (result.isSuccess()) {
        // 4. requestSetAttribute
        request.setAttribute("resultList", result.getRetList());
        // 5. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
        dispatcher.forward(request, response);
      } else {
  	    // 5. forward
    	// error
        request.setAttribute("message","何らかのエラー発生");
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspErrorUrl);
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
