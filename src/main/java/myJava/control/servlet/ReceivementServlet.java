package myJava.control.servlet;

import jdk.nashorn.internal.parser.JSONParser;
import myJava.model.beans.Professore;
import myJava.model.beans.Ricevimento;
import myJava.model.general.DataManager;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ReceivementServlet")
public class ReceivementServlet extends HttpServlet {
  DataManager dm = new DataManager();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    if (request.getParameter("operazione").equals("inserimento")) {
      inserisciRicevimento(request, response);
      return;
    }

    if (request.getParameter("operazione").equals("modifica")) {
      modificaRicevimento(request, response);
      return;
    }

    if (request.getParameter("operazione").equals("elimina")) {
      System.out.println("Richiesta di eliminazione");
      eliminaRicevimento(request, response);
    }

  }

  private void eliminaRicevimento(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println("Id ricevuto: " + id);
    Ricevimento r = new Ricevimento();
    r.setIdRicevimento(id);
    try {

      if (dm.eliminaRicevimento(r)) {
        response.getWriter().println("SUCCESS");
      } else {
        response.getWriter().println("FAILURE");
      }
    } catch (SQLException e) {
      try {
        response.getWriter().println("FAILURE");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void modificaRicevimento(HttpServletRequest request, HttpServletResponse response) {


    String startFirstReceivement, endLastReceivement, luogo, giorno;
    String oraInizio, oraFine;
    int id;
    HttpSession session = request.getSession();
    Professore prof = (Professore) session.getAttribute("user");
    id = Integer.parseInt(request.getParameter("id"));
    startFirstReceivement = request.getParameter("inizio");
    endLastReceivement = request.getParameter("fine");
    luogo = request.getParameter("luogo");

    System.out.println(startFirstReceivement + "\n" + endLastReceivement + "\n" + luogo + "\n");

    giorno = startFirstReceivement.substring(0, 10).trim();
    oraInizio = startFirstReceivement.substring(10).trim();
    oraFine = endLastReceivement.substring(10).trim();
    System.out.println(
        giorno + "\n" + oraInizio + "\n" + oraFine
    );

    Ricevimento r = new Ricevimento();
    r.setIdRicevimento(id);
    r.setData(giorno);
    r.setOrarioFine(oraFine);
    r.setOrarioInizio(oraInizio);
    r.setLuogo(luogo);
    r.setIdProfessore(prof.getIdProfessore());


    try {

      if (dm.modificaRicevimento(r)) {
        response.getWriter().println("SUCCESS");
      } else {
        response.getWriter().println("FAILURE");
      }

    } catch (SQLException | IOException ex) {
      ex.printStackTrace();
      try {
        response.getWriter().println("FAILURE");
      } catch (IOException ioex) {
        ioex.printStackTrace();
      }
    }
  }

  private void inserisciRicevimento(HttpServletRequest request, HttpServletResponse response) {

    String startFirstReceivement, endLastReceivement, luogo, giorno;
    String oraInizio, oraFine;
    int postiTotali;
    HttpSession session = request.getSession();
    Professore prof = (Professore) session.getAttribute("user");
    startFirstReceivement = request.getParameter("inizio");
    endLastReceivement = request.getParameter("fine");
    luogo = request.getParameter("luogo");

    postiTotali = Integer.parseInt(request.getParameter("posti"));
    System.out.println(startFirstReceivement + "\n" + endLastReceivement + "\n" + luogo + "\n" + postiTotali);

    giorno = startFirstReceivement.substring(0, 10).trim();
    oraInizio = startFirstReceivement.substring(10).trim();
    oraFine = endLastReceivement.substring(10).trim();
    System.out.println(
        giorno + "\n" + oraInizio + "\n" + oraFine
    );

    String temp = oraInizio;

    while (!temp.equals(oraFine)) {
      int hh, mm;
      Ricevimento r = new Ricevimento();
      r.setData(giorno);
      r.setLuogo(luogo);
      r.setOrarioInizio(temp);
      r.setPostiTotali(postiTotali);
      r.setPostiDisponibili(postiTotali);

      hh = Integer.parseInt(temp.substring(0, 2));
      mm = Integer.parseInt(temp.substring(3, 5));

      if (mm == 0) {
        temp = hh + ":30";
      } else {
        hh++;
        temp = hh + ":00";
      }

      if (hh == 9) {
        temp = "0" + temp;
      }

      r.setOrarioFine(temp);
      r.setIdProfessore(prof.getIdProfessore());

      System.out.println(r);

      try {

        if (dm.creaRicevimento(r)) {
          response.getWriter().println("SUCCESS");
        } else {
          response.getWriter().println("FAILURE");
        }

      } catch (SQLException | IOException ex) {
        ex.printStackTrace();
        try {
          response.getWriter().println("FAILURE");
        } catch (IOException ioex) {
          ioex.printStackTrace();
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }


  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
