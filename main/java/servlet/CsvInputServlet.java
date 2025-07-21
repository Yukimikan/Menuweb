package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

import model.GlobalConst;
import model.MenuCSV;
import service.CsvUtilServiceImpl;


/**
 * Servlet implementation class HelloServlet.
 */
public class CsvInputServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

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
    //中断
    if (StringUtils.isEmpty(date) || StringUtils.isBlank(date)) {
      errMessage = GlobalConst.MSG_W_DATE_ERROR;
      request.setAttribute("message", errMessage);
    } else if (StringUtils.isEmpty(csvName) || StringUtils.isBlank(csvName)) {
      errMessage = GlobalConst.MSG_W_CSVNAME_ERROR;
      request.setAttribute("message", errMessage);
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
