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
import service.CsvFileListService;
import service.CsvFileListServiceImpl;

/**
 * Servlet implementation class HelloServlet.
 */
public class CsvInputGetServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CsvInputGetServlet() {
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
    try {
      // 2. inputCheck(フロントで実行)
      // 入力チェック
      // 3. service execute
      CsvFileListService service = new CsvFileListServiceImpl();
      List<String> csvList = service.execute();

      // 終了条件を判定
      if (csvList != null) {
        // requestSetAttribute
        request.setAttribute("csvList", csvList);
        //4. forward
        if ("csv_input".equals(request.getParameter("screen_id"))) {
          RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspInputUrl);
          dispatcher.forward(request, response);
        } else if ("csv_regist".equals(request.getParameter("screen_id"))) {
          RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspRegistUrl);
          dispatcher.forward(request, response);
        }
      } else {
        //4. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspInputUrl);
        dispatcher.forward(request, response);
      }
    } catch (IOException e) {

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
