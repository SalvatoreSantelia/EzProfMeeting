package myJava.control.servlet;

import myJava.model.beans.Prenotazione;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;
import myJava.model.general.DataManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BookingServlet")
public class BookingServlet extends HttpServlet {
  DataManager dm = new DataManager();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String action = request.getParameter("action");
    if (action == null) {
      return;
    }

    if (action.equals("rimuoviPrenotazione")) {
      eliminaPrenotazione(request, response);
    } else if (action.equals("inserisciPrenotazione")) {

      aggiungiPrenotazione(request, response);

    }

  }

  private void eliminaPrenotazione(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
    System.out.println(idPrenotazione);

    try {
      Prenotazione prenotazione = dm.getPranotazioneById(idPrenotazione);
      dm.eliminaPrenotazione(prenotazione);
      Ricevimento r = dm.getRicevimentoById(prenotazione.getIdRicevimento());
      r.setPostiDisponibili(r.getPostiDisponibili() + 1);
      dm.modificaRicevimento(r);

    } catch (SQLException e) {
      response.getWriter().println("FAILURE");
      e.printStackTrace();
    }
  }

  private void aggiungiPrenotazione(HttpServletRequest request, HttpServletResponse response) {
    Prenotazione prenotazione = new Prenotazione();
    Integer idR, idStud;
    String motivazione, altriStudenti, orario;
    Studente studente;

    orario = request.getParameter("orario");
    idR = Integer.parseInt(request.getParameter("idRicevimento").trim());
    studente = (Studente) request.getSession().getAttribute("user");
    idStud = studente.getIdStudente();
    motivazione = request.getParameter("motivazione");
    altriStudenti = request.getParameter("studenti");

    prenotazione.setIdRicevimento(idR);
    prenotazione.setIdStudente(idStud);
    prenotazione.setListaStudenti(altriStudenti);
    prenotazione.setOrario(orario);
    prenotazione.setMotivazione(motivazione);

    try {
      if (dm.inserisciPrenotazione(prenotazione)) {
        Ricevimento r = dm.getRicevimentoById(prenotazione.getIdRicevimento());
        r.setPostiDisponibili(r.getPostiDisponibili() - 1);
        dm.modificaRicevimento(r);
        response.getWriter().println("SUCCESS");
      } else {
        response.getWriter().println("FAILURE");
      }
    } catch (SQLException | IOException e) {
      e.printStackTrace();

    }

  }
}
