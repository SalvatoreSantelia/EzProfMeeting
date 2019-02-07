package myJava.control.servlet;

import myJava.model.beans.Professore;
import myJava.model.beans.Studente;
import myJava.model.beans.User;
import myJava.model.general.DataManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet per gestire l'accesso di un utente alla piattaforma
 */
@WebServlet(name = "AccessServlet")
public class AccessServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    doGet(request, response);
  }

  /**
   * Crea la sessione per un utente le cui credenziali sono già state verificate
   *
   * @param request  richiesta http con l'email dell'utente da caricare ed inserire nella sessione
   * @param response risposta http
   * @throws ServletException in caso di problemi con il dispatcher
   * @throws IOException in caso di problemi con il writer
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DataManager model = new DataManager();
    User utente = null;
    try {
      utente = model.doLogin(request.getParameter("email"), request.getParameter("pass"));
    } catch (SQLException e) {
      e.printStackTrace();

    }

    DataManager dataManager = new DataManager();
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(-1);
    if (utente.getTipo().equals("studente")) {
      Studente studente = dataManager.getStudenteByEmail(utente.getEmail());
      session.setAttribute("user", studente);
      request.getServletContext().getRequestDispatcher("/View/Studente/HomeStudente.jsp").forward(request, response);
    }
    if (utente.getTipo().equals("professore")) {
      Professore professore = dataManager.getProfessoreByEmail(utente.getEmail());
      session.setAttribute("user", professore);
      request.getServletContext().getRequestDispatcher("/View/Professore/HomeProfessore.jsp").forward(request, response);
    }


  }
}
