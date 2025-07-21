package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import model.GlobalConst;

/**
 * Servlet implementation class HelloServlet.
 */
public class SearchMenuServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public SearchMenuServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }


  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String date = (String) request.getParameter("date");
    String total = (String) request.getParameter("total");
    RequestDispatcher dispatcher = request.getRequestDispatcher(GlobalConst.JspMenuResultUrl);
    dispatcher.forward(request, response);
  }

}
