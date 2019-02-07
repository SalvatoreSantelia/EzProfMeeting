package myJava.control.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import myJava.model.beans.User;
import myJava.model.general.DataManager;


/**
 * Servlet per la verifica delle credenziali
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {


  private DataManager DM = new DataManager();


  /**
   * Verifica le credenziali utente passate nella richiesta
   *
   * @param request la richiesta http con le credenziali
   * @param response la risposta http per indicare l'esito
   * @throws IOException in caso di problemi con il writer
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");


    User utente = new User();
    try {

      utente = DM.doLogin(username, password);
    } catch (Exception e) {
    }
    if (utente == null || utente.getEmail() == null) {
      System.out.println("utente non caricato");

      response.getWriter().write("FAILURE");
    } else {

      response.getWriter().write(utente.getEmail());

    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    doGet(request, response);


  }
}