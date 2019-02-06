package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.*;


import myJava.model.professore.ReceivementManager;
import myJava.model.studente.BookingManager;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che svolge il ruolo di interfaccia al model
 */
public class DataManager {


    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param mail
     * @param password
     * @return
     * @throws SQLException
     */
    public User doLogin(String mail, String password) throws SQLException {
        if (mail == null || mail.equals("") || password == null || password.equals(""))
            return null;
        else{
            System.out.println("Invio richiesta di caricamento...");
            return ac.doLogin(mail, password);
        }

    }

    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param ricevimento
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    public boolean creaRicevimento(Ricevimento ricevimento) throws SQLException, ParseException {
        if (ricevimento != null)
            return rm.creaRicevimento(ricevimento);
        else
            return false;
    }

    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param ricevimento
     * @return
     * @throws SQLException
     */
    public boolean eliminaRicevimento(Ricevimento ricevimento) throws SQLException {
        if (ricevimento != null)
            return rm.eliminaRicevimento(ricevimento);
        else return false;
    }

    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param ricevimento
     * @return
     * @throws SQLException
     */
    public boolean modificaRicevimento(Ricevimento ricevimento) throws SQLException {
        if (ricevimento != null)
            return rm.modificaRicevimento(ricevimento);
        else return false;
    }


    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param oraInizio
     * @param oraFine
     * @param data
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    public Ricevimento visualizzaRicevimento(String oraInizio, String oraFine, String data) throws SQLException ,ParseException{


        if (!oraInizio.equals("") && oraInizio != null && !oraFine.equals("") && oraFine != null && data != null)
            return rm.visualizzaRicevimento(oraInizio, oraFine, data);
        else return null;
    }

    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param prenotazione
     * @return
     * @throws SQLException
     */
    public boolean inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {
        if (prenotazione != null && prenotazione.getIdRicevimento() != 0 && prenotazione.getIdStudente() != 0)
            return m.inserisciPrenotazione(prenotazione);
        else return false;
    }


    /**
     * Richiede all'opportuno manager l'elenco delle prenotazioni effettuate
     * @param idStudente
     * @return
     * @throws SQLException
     */
    public List<Prenotazione> visualizzaPrenotazioni(int idStudente) throws SQLException {

        return m.visualizzaPrenotazioni(idStudente);
    }


    /**
     * Delega all'opportuno manager l'invio dei messaggi
     * @param idMittente
     * @param idDestinatario
     * @param messaggio
     * @param lato
     * @return
     */
    public boolean inviaMessaggio(int idMittente, int idDestinatario, String messaggio,String lato) {
        mm.inviaMessaggio(idMittente,idDestinatario,messaggio,lato);
        return true;
    }


    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param prenotazione
     * @return
     * @throws SQLException
     */
    public boolean eliminaPrenotazione(Prenotazione prenotazione) throws SQLException {

        if (prenotazione != null)
            return m.eliminaPrenotazione(prenotazione);
        else return false;
    }

    /**
     * Verifica la validità dei parametri e delega la richiesta all'opportuno manager
     * @param presenzaAssenza
     * @param idStudente
     * @return
     * @throws SQLException
     */
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


    /**
     * Restituisce l'elenco di tutti i professori
     * @return
     * @throws SQLException
     */
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


    /**
     * Richiede all'opportuno manager l'elenco degli studenti prenotati a un certo ricevimento
     * @param ricevimento
     * @return
     * @throws SQLException
     */
    public List<Studente> visualizzaStudenti(Ricevimento ricevimento) throws SQLException{
        return rm.visualizzaStudenti(ricevimento);
    }

    /**
     * Richiede all'opportuno manager i dati relativi a un certo ricevimento
     * @param idRicevimento
     * @return
     */
    public Ricevimento getRicevimentoById(int idRicevimento){
        return rm.getRicevimentoById(idRicevimento);
    }


    /**
     * Restituisce le informazioni di un certo professore tramite id
     * @param idProf
     * @return
     * @throws SQLException
     */
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


    /**
     * Restituisce le informazioni di un certo studente tramite id
     * @param idStudente
     * @return
     * @throws SQLException
     */
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


    /**
     * Richiede all'opportuno manager l'ultimo messaggio inviato in una certa conversazione
     * @param idStudente
     * @param idProfessore
     * @return
     */
    public Messaggio getLastDataMessaggio(int idStudente, int idProfessore){
        return mm.getLastDataMessaggio(idStudente,idProfessore);
    }


    /**
     * Richiede all'opportuno manager le informazioni relative a un certo professore tramite email
     * @param email
     * @return
     */
    public Professore getProfessoreByEmail(String email) {
        return   ac.getProfessoreByEmail(email);
    }

    /**
     * Richiede all'opportuno manager le informazioni relative a un certo studente tramite email
     * @param email
     * @return
     */
    public Studente getStudenteByEmail(String email) {
        return   ac.getStudenteByEmail(email);
    }

    /**
     * Richiede all'opportuno manager l'elenco dei ricevimenti di un certo professore
     * @param prof
     * @return
     */
    public ArrayList<Ricevimento> getRicevimentiByProf(Professore prof)
    {
        return rm.getRicevimentiByProf(prof);
    }

    /**
     * Richiede all'opportuno manager l'elenco degli studenti che hanno contattato un certo professore
     * @param idProf
     * @return
     * @throws SQLException
     */
    public ArrayList<Studente> getStudentiContattati(int idProf) throws SQLException {
        return mm.getStudentiContattati(idProf);
    }


    /**
     * Richiede all'opportuno manager le informazioni relative a una certa prenotazione tramite id
     * @param idPrenotazione
     * @return
     * @throws SQLException
     */
    public Prenotazione getPrenotazioneById(int idPrenotazione)throws SQLException{
        return m.getPrenotazioneById(idPrenotazione);
    }


    /**
     * Richiede all'opportuno manager la conversazione tra un certo professore e un certo studente
     * @param idStudente
     * @param idProfessore
     * @return
     */
    public ArrayList<Messaggio> getArrayListMessaggio(int idStudente, int idProfessore){
        return mm.getArrayListMessaggio(idStudente,idProfessore);
    }


    /**
     * Richiede all'opportuno manager la lista degli studenti prenotati a un certo ricevimento
     * @param idRicevimento
     * @return
     * @throws SQLException
     */
    public List<Prenotazione> visualizzaPrenotazioniByIdRicevimento(int idRicevimento) throws SQLException {
        return rm.visualizzaPrenotazioniByIdRicevimento(idRicevimento);
    }
}


