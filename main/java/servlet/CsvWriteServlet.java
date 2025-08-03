package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import model.GlobalConst;
import model.MenuCSV;
import service.CsvWriteService;
import service.CsvWriteServiceImpl;

/**
 * Servlet implementation class HelloServlet.
 */
public class CsvWriteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  CsvWriteService service = new CsvWriteServiceImpl();
  CsvWriteServDto csvWriteServDto;

  public CsvWriteServlet() {
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
    MenuCSV rec = new MenuCSV(
        "", //setNo
        (String) request.getParameter("type"),
        (String) request.getParameter("restaurant_name"),
        (String) request.getParameter("singlemenu_flg"),
        (String) request.getParameter("menu"),
        (String) request.getParameter("price"),
        (String) request.getParameter("tax"),
        (String) request.getParameter("total"));
    csvWriteServDto = new CsvWriteServDto(rec);

    // 2. inputCheck(フロントで実行)
    /* nothing */

    try {
      // 3. service execute
      List<MenuCSV> retList = service.execute(csvWriteServDto);

      // 終了条件を判定
      if (retList != null) {
        // requestSetAttribute
        request.setAttribute("retList", retList);
        // 4. forward
        RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
        dispatcher.forward(request, response);
      } else {
        // 中断
        return;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
