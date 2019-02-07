package myJava.control.servlet;

import myJava.model.beans.User;
import myJava.model.general.DataManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet per disconnettere un utente precedentemente connesso
 */
@WebServlet(name = "LoginServlet")

public class LogoutServlet extends HttpServlet {


  private DataManager DM = new DataManager();

  /**
   * Rimuove la sessione e reindirizza alla pagina del login
   *
   * @param request  la richiesta http da cui ricavare la sessione
   * @param response la risposta http
   * @throws IOException in caso di errori con la risposta http
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    session.invalidate();
    response.sendRedirect(request.getContextPath() + "/View/General/Login.jsp");
  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    doGet(request, response);


  }
}