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
import service.CsvInputService;
import service.CsvInputServiceImpl;

/**
 * Servlet implementation class HelloServlet.
 */
public class CsvInputServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CsvInputServlet() {
    super();
  }

  MenuCsvInputForm csvInputServForm;
  //  CsvInputServOutDto csvInputServOutDto;

  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. parameter set
    csvInputServForm = new MenuCsvInputForm(
        (String) request.getParameter("date"),
        (String) request.getParameter("csv_name"),
        "");

    try {
      // 2. inputCheck(フロントで実行)
      // 入力チェック
      if (!csvInputServForm.commonCheck(csvInputServForm.getCsvName())) {
        throw new IOException();
      }
      // 3. service execute
      CsvInputService service = new CsvInputServiceImpl();
      List<MenuCSV> retList = service.execute(csvInputServForm);

      // 終了条件を判定
      if (retList != null) {
        // requestSetAttribute
        request.setAttribute("retList", retList);
        //4. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
        dispatcher.forward(request, response);
      } else {
        //4. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspInputUrl);
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
