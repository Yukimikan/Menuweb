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
import service.CsvUtilServiceImpl;

/**
 * Servlet implementation class HelloServlet.
 */
public class CsvWriteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

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

    String csvName = "menu.csv";

    // データ移送
    MenuCSV rec = new MenuCSV();

    // rec.setNo((String) request.getParameter("type"));
    rec.setType((String) request.getParameter("type"));
    rec.setRestaurantName((String) request.getParameter("restaurant_name"));
    rec.setSinglemenuFlg((String) request.getParameter("singlemenu_flg"));
    rec.setMenu((String) request.getParameter("menu"));
    rec.setPrice((String) request.getParameter("price"));
    rec.setTax((String) request.getParameter("tax"));
    rec.setTotal((String) request.getParameter("total"));

    try {
      // service
      CsvUtilServiceImpl service = new CsvUtilServiceImpl();
      List<MenuCSV> retList = service.write(rec, csvName);
      // requestSetAttribute
      request.setAttribute("retList", retList);
      // forward
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
      dispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
