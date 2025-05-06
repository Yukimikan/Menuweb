package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import model.MenuCSV;
import service.CsvUtilServiceImpl;


/**
 * Servlet implementation class HelloServlet.
 */
public class CsvInputServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String inputUrl = "./jsp/menu_csv_input.jsp";
  private static final String resultUrl = "./jsp/menu_csv_result.jsp";

  public CsvInputServlet() {
    super();
  }

  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 1. parameter set
    String date = (String) request.getParameter("date");
    String csvName = (String) request.getParameter("csv_name");
    String errMessage = "";

    // 2. inputCheck
    if (StringUtils.isEmpty(date) || StringUtils.isBlank(date)) {
      //中断
      errMessage = "日付を指定して下さい";
      return;
    } else if (StringUtils.isEmpty(csvName) || StringUtils.isBlank(csvName)) {
      //中断
      errMessage = "CSV名を指定して下さい";
      return;
    }

    try {
      // 3. service execute
      CsvUtilServiceImpl service = new CsvUtilServiceImpl();
      List<MenuCSV> retList = service.read(csvName);

      // 終了条件を判定
      if (retList != null) {
        // requestSetAttribute
        request.setAttribute("retList", retList);
        //4. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(resultUrl);
        dispatcher.forward(request, response);
      } else {
        //4. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(inputUrl);
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
