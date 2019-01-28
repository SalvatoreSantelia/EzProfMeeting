package myJava.control.servlet;

import myJava.model.beans.Prenotazione;
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
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equals("rimuoviPrenotazione")) {
            int idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
            System.out.println(idPrenotazione);

            try {
                Prenotazione prenotazioen = dm.getPranotazioneById(idPrenotazione);
                dm.eliminaPrenotazione(prenotazioen);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
