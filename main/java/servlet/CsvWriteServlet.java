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

  public CsvWriteServlet() {
    super();
  }

  CsvWriteServDto csvWriteServDto;


  /**
   *  ポスト処理.
   *
   *  @param HttpServletRequest request, HttpServletResponse response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // 1. parameter set
    String[] cols = {
      "", // No（新規登録なので空）
      request.getParameter("type"),
      request.getParameter("restaurant_name"),
      request.getParameter("singlemenu_flg"),
      request.getParameter("menu"),
      request.getParameter("price"),
      request.getParameter("tax"),
      request.getParameter("total")
    };

    // 2. MenuCSV に渡す(初期化)
    MenuCSV rec = new MenuCSV(cols);
    csvWriteServDto = new CsvWriteServDto(rec);

    // 2. inputCheck(フロントで実行)
    /* nothing */

    try {
      // 3. service execute
      CsvWriteService service = new CsvWriteServiceImpl();
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
