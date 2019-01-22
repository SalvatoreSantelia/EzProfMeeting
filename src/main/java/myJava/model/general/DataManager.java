package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.*;


import myJava.model.professore.ReceivementManager;
import myJava.model.studente.BookingManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {


    public User doLogin(String mail, String password) throws SQLException {
        if (mail == null || mail.equals("") || password == null || password.equals(""))
            return null;
        else return ac.doLogin(mail, password);

    }

    public boolean creaRicevimento(Ricevimento ricevimento) throws SQLException {
        if (ricevimento != null)
            return rm.creaRicevimento(ricevimento);
        else
            return false;
    }

    public boolean eliminaRicevimento(Ricevimento ricevimento) throws SQLException {
        if (ricevimento != null)
            return rm.eliminaRicevimento(ricevimento);
        else return false;
    }

    public boolean modificaRicevimento(Ricevimento ricevimento) throws SQLException {
        if (ricevimento != null)
            return rm.modificaRicevimento(ricevimento);
        else return false;
    }

    public Ricevimento visualizzaRicevimento(String oraInizio, String oraFine, String data) throws SQLException {


        if (!oraInizio.equals("") && oraInizio != null && !oraFine.equals("") && oraFine != null && data != null)
            return rm.visualizzaRicevimento(oraInizio, oraFine, data);
        else return null;
    }

    public boolean inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {
        if (prenotazione != null && prenotazione.getIdPrenotazione() != 0 && prenotazione.getIdRicevimento() != 0 && prenotazione.getIdStudente() != 0)
            return m.inserisciPrenotazione(prenotazione);
        else return false;
    }

    public List<Studente> visualizzaStudenti(Ricevimento ricevimento) throws SQLException {

        return null;
    }

    public List<Prenotazione> visualizzaPrenotazioni(int idStudente) throws SQLException {

        return m.visualizzaPrenotazioni(idStudente);
    }


    public boolean inviaMessaggio(int idMittente, int idDestinatario, String messaggio) {
        return true;

    }

    public Messaggio visualizzaMessagio(int idDestinatario) {

        return null;
    }


    public boolean eliminaPrenotazione(Prenotazione prenotazione) throws SQLException {

        if (prenotazione != null)
            return m.eliminaPrenotazione(prenotazione);
        else return false;
    }

    public boolean registraPresenza(String presenzaAssenza, int idStudente) throws SQLException {

        if (presenzaAssenza.equals("assente"))
            return rm.registraAssenza(idStudente);
        else if (presenzaAssenza.equals("presente"))
            return rm.registraPresenza(idStudente);
        else
            return false;
    }


    BookingManager m = new BookingManager();
    AccessManager ac = new AccessManager();
    ReceivementManager rm = new ReceivementManager();


// Metodi di supporto

    //visualizza tutti i prof
    public List<Professore> visualizzaProfessori() throws SQLException {

        Connection connection = null;

        List<Professore> professori = new ArrayList<>();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from professore");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Professore professore = new Professore();
                professore.setIdProfessore(rs.getInt(1));
                professore.setNomeProfessore(rs.getString(2));
                professore.setCognomeProfessore(rs.getString(3));
                professore.setEmailProfessore(rs.getString(4));
                professore.setTelefonoProfessore(rs.getString(5));
                professore.setUfficioProfessore(rs.getString(6));
                professori.add(professore);
            }
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return professori;
    }


    //get LastData Messaggio
    public Messaggio getLastDataMessaggio(int idProfessore, int idStudente) {
        Connection connection = null;
        Messaggio messaggio = new Messaggio();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT *  from messaggio where idProfessore = ? AND idStudente = ? ORDER BY dataMessaggio DESC");
            statement.setInt(1, idProfessore);
            statement.setInt(2, idStudente);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                messaggio.setIdMessaggio(rs.getInt("idMessaggio"));
                messaggio.setDataMessaggio(rs.getDate("dataMessaggio"));
                messaggio.setTestoMessaggio(rs.getString("testoMessaggio"));
                messaggio.setIdProfessore(rs.getInt("idProfessore"));
                messaggio.setIdStudente(rs.getInt("idStudente"));
                connection.close();
                return messaggio;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return messaggio;
    }

}


