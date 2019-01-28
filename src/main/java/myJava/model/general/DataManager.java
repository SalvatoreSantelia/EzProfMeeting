package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.*;


import myJava.model.professore.ReceivementManager;
import myJava.model.studente.BookingManager;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {


    public User doLogin(String mail, String password) throws SQLException {
        if (mail == null || mail.equals("") || password == null || password.equals(""))
            return null;
        else return ac.doLogin(mail, password);

    }

    public boolean creaRicevimento(Ricevimento ricevimento) throws SQLException, ParseException {
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

    public Ricevimento visualizzaRicevimento(String oraInizio, String oraFine, String data) throws SQLException ,ParseException{


        if (!oraInizio.equals("") && oraInizio != null && !oraFine.equals("") && oraFine != null && data != null)
            return rm.visualizzaRicevimento(oraInizio, oraFine, data);
        else return null;
    }

    public boolean inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {
        if (prenotazione != null && prenotazione.getIdPrenotazione() != 0 && prenotazione.getIdRicevimento() != 0 && prenotazione.getIdStudente() != 0)
            return m.inserisciPrenotazione(prenotazione);
        else return false;
    }


    public List<Prenotazione> visualizzaPrenotazioni(int idStudente) throws SQLException {

        return m.visualizzaPrenotazioni(idStudente);
    }


    public boolean inviaMessaggio(int idMittente, int idDestinatario, String messaggio,String lato) {
        mm.inviaMessaggio(idMittente,idDestinatario,messaggio,lato);
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
    MessageManager mm = new MessageManager();


// Metodi di supporto

    //visualizza tutti i prof
    public List<Professore> visualizzaProfessori() throws SQLException {

        Connection connection = null;

        List<Professore> professori = new ArrayList<>();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from professore ORDER BY cognomeProfessore");
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


    public List<Studente> visualizzaStudenti(Ricevimento ricevimento) throws SQLException{
        return rm.visualizzaStudenti(ricevimento);
    }

    public Ricevimento getRicevimentoById(int idRicevimento){
        return rm.getRicevimentoById(idRicevimento);
    }




    //get prof By Id
    public Professore getProfById(int idProf) throws SQLException {
        Connection connection = null;
        Professore professore = new Professore();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from professore where idProfessore = ?");
            statement.setInt(1,idProf);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                professore.setIdProfessore(rs.getInt(1));
                professore.setNomeProfessore(rs.getString(2));
                professore.setCognomeProfessore(rs.getString(3));
                professore.setEmailProfessore(rs.getString(4));
                professore.setTelefonoProfessore(rs.getString(5));
                professore.setUfficioProfessore(rs.getString(6));
            }
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return professore;
    }



    //get studente By Id
    public Studente getStudenteById(int idStudente) throws SQLException {
        Connection connection = null;
        Studente studente = new Studente();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from studente where idStudente = ?");
            statement.setInt(1,idStudente);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                studente.setIdStudente(rs.getInt("idStudente"));
                studente.setNomeStudente(rs.getString("nomeStudente"));
                studente.setCognomeStudente(rs.getString("cognomeStudente"));
                studente.setMatricola(rs.getString("matricola"));
                studente.setEmailStudente(rs.getString("emailStudente"));
                studente.setTelefonoStudente(rs.getString("telefonoStudente"));
                studente.setNumAssenza(rs.getInt("numAssenza"));
            }
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return studente;
    }




    //get Last Messaggio
    public Messaggio getLastDataMessaggio(int idStudente, int idProfessore){
        return mm.getLastDataMessaggio(idStudente,idProfessore);
    }



    public Professore getProfessoreByEmail(String email) {
        return   ac.getProfessoreByEmail(email);
    }
    public Studente getStudenteByEmail(String email) {
        return   ac.getStudenteByEmail(email);
    }


   /** public ArrayList<Ricevimento> getRicevimentiByProf(Professore prof)
    {
        return rm.getRicevimentiByProf(prof);
    }*/

    public ArrayList<Studente> getStudentiContattati(int idProf) throws SQLException {
        return mm.getStudentiContattati(idProf);
    }


    //

    public Prenotazione getPranotazioneById(int idPrenotazione)throws SQLException{
        return m.getPranotazioneById(idPrenotazione);
    }


    public ArrayList<Messaggio> getArrayListMessaggio(int idStudente, int idProfessore){
        return mm.getArrayListMessaggio(idStudente,idProfessore);
    }
}


