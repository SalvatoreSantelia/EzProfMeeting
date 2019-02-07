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
import java.util.ArrayList;

/**
 * Servlet per il caricamento delle conversazioni
 */
@WebServlet(name = "MessageServlet")
public class MessageServlet extends HttpServlet {
  DataManager dm = new DataManager();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  /**
   * Carica una conversazione tra studente e professore, e aggiorna la pagina client
   *
   * @param request  la richiesta http del client, con i parametri necessari
   * @param response la risposta http da inviare al client
   * @throws IOException in caso di errori con la risposta http
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int idStudente = Integer.parseInt(request.getParameter("idStudente"));
    int idProfessore = Integer.parseInt(request.getParameter("idProfessore"));
    String lato = request.getParameter("lato");
    ArrayList<Messaggio> messaggi = dm.getArrayListMessaggio(idStudente, idProfessore);
    String risposta = "";
    if (messaggi.size() != 0) {
      risposta = "[";
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
    }
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.write(risposta);
  }
}
