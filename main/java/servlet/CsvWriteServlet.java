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
import service.CsvWriteServiceImpl;

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

    // データ移送
    MenuCSV rec = new MenuCSV();

    // 1. parameter set
    // rec.setNo((String) request.getParameter("type"));
    rec.setType((String) request.getParameter("type"));
    rec.setRestaurantName((String) request.getParameter("restaurant_name"));
    rec.setSinglemenuFlg((String) request.getParameter("singlemenu_flg"));
    rec.setMenu((String) request.getParameter("menu"));
    rec.setPrice((String) request.getParameter("price"));
    rec.setTax((String) request.getParameter("tax"));
    rec.setTotal((String) request.getParameter("total"));

    // 2. inputCheck(フロントで実行)
    CsvWriteServiceImpl cwService = new CsvWriteServiceImpl();
    if (cwService.formatCheck(rec) == false) {
      // 中断
      return;
    }

    try {
      // 3. service execute
      CsvUtilServiceImpl cuService = new CsvUtilServiceImpl();
      cuService.write(rec, GlobalConst.CsvName);
      // 再読み込み
      List<MenuCSV> retList = cuService.read(GlobalConst.CsvName);
      // requestSetAttribute
      request.setAttribute("retList", retList);
      // 4. forward
      RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspResultUrl);
      dispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
