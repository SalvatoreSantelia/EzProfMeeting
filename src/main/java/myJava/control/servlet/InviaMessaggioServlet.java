package myJava.control.servlet;

import myJava.model.beans.Messaggio;
import myJava.model.general.DataManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet per la gestione del traffico dei messsaggi
 */
@WebServlet(name = "InviaMessaggioServlet")
public class InviaMessaggioServlet extends HttpServlet {
  DataManager dm = new DataManager();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doGet(request, response);
  }

  /**
   * Registra messaggio nel database e aggiorna la pagina client della conversazione
   *
   * @param request la richiesta http con le informazioni del messaggio
   * @param response la risposta http per aggiornare la pagina
   * @throws IOException in caso di problemi con il writer
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String testo = request.getParameter("testo");
    int id = Integer.parseInt(request.getParameter("idStudente"));
    int idProf = Integer.parseInt(request.getParameter("idProfessore"));
    String lato = request.getParameter("lato");
    DataManager dm = new DataManager();
    dm.inviaMessaggio(id, idProf, testo, lato);
    ArrayList<Messaggio> messaggi = dm.getArrayListMessaggio(id, idProf);
    String risposta = "[";
    int i = 0;
    for (; i < messaggi.size() - 1; i++) {
      Messaggio a = messaggi.get(i);
      risposta = risposta + "{"
          + "\"testo\": \"" + a.getTestoMessaggio() + "\","
          + "\"lato\": \"" + a.getLato() + "\","
          + "\"data\": \"" + a.getDataMessaggio().toString() + "\","
          + "\"orario\": \"" + a.getOrarioMessaggio().toString() + "\"},";
    }
    Messaggio a = messaggi.get(i);
    risposta = risposta + "{"
        + "\"testo\": \"" + a.getTestoMessaggio() + "\","
        + "\"lato\": \"" + a.getLato() + "\","
        + "\"data\": \"" + a.getDataMessaggio().toString() + "\","
        + "\"orario\": \"" + a.getOrarioMessaggio().toString() + "\"}";
    risposta = risposta + "]";
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.write(risposta);
  }
}
