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

    BookingManager m = new BookingManager();
    AccessManager ac = new AccessManager();
    ReceivementManager rm = new ReceivementManager();
    MessageManager mm = new MessageManager();
  /**
   * Verifica il contenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param mail     l'email per identificare l'account con cui accedere
   * @param password la password per accedere ad uno specifico account
   * @return un account
   * @throws SQLException in caso di errori con il db
   */
  public User doLogin(String mail, String password) throws SQLException {
    if (mail == null || mail.equals("") || password == null || password.equals("")) {
      return null;
    } else {
      System.out.println("Invio richiesta di caricamento...");
      return ac.doLogin(mail, password);
    }

    }

  /**
   * Verifica il contenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param ricevimento ricevimento da creare
   * @return l'esito dell'operazione del manager
   * @throws SQLException   in caso di errori con il db
   * @throws ParseException in caso di errore nei parsing
   */
  public boolean creaRicevimento(Ricevimento ricevimento) throws SQLException, ParseException {
    if (ricevimento != null) {
      return rm.creaRicevimento(ricevimento);
    } else {
      return false;
    }
  }

  /**
   * Verifica il contenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param ricevimento il ricevimento da eliminare
   * @return l'esito dell'operazione del manager
   * @throws SQLException in caso di errori con il db
   */
  public boolean eliminaRicevimento(Ricevimento ricevimento) throws SQLException {
    if (ricevimento != null) {
      return rm.eliminaRicevimento(ricevimento);
    } else {
      return false;
    }}

  /**
   * Verifica il contenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param ricevimento il ricevimento da modificare
   * @return l'esito dell'operazione del manager
   * @throws SQLException in caso di errori con il db
   */
  public boolean modificaRicevimento(Ricevimento ricevimento) throws SQLException {
    if (ricevimento != null) {
      return rm.modificaRicevimento(ricevimento);
    } else {
      return false;
    }
  }


  /**
   * Verifica il contenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param oraInizio l'orario id inizio del ricevimento
   * @param oraFine   l'orario di fine del ricevimento
   * @param data      la data del ricevimento
   * @return il ricevimento desiderato caricato dal manager
   * @throws SQLException   in caso di errori con il db
   * @throws ParseException in caso di errori nelle operazioni di parsing
   */
  public Ricevimento visualizzaRicevimento(String oraInizio, String oraFine, String data) throws SQLException, ParseException {


        if (!oraInizio.equals("") && oraInizio != null && !oraFine.equals("") && oraFine != null && data != null)
            return rm.visualizzaRicevimento(oraInizio, oraFine, data);
        else return null;
    }

  /**
   * Verifica il contenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param prenotazione prenotazione di uno studente da registrare
   * @return l'esito dell'operazione del manager
   * @throws SQLException in caso di errori con il db
   */
  public boolean inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {
    if (prenotazione != null && prenotazione.getIdRicevimento() != 0 && prenotazione.getIdStudente() != 0) {
      return m.inserisciPrenotazione(prenotazione);
    } else {
      return false;
    }
  }


  /**
   * Richiede all'opportuno manager l'elenco delle prenotazioni effettuate
   *
   * @param idStudente l'identificativo dello studente di cui si vuole visualizzare le prenotazioni
   * @return la lista delle prenotazioni ricavate dal manager
   * @throws SQLException in caso di problemi con il db
   */
  public List<Prenotazione> visualizzaPrenotazioni(int idStudente) throws SQLException {

        return m.visualizzaPrenotazioni(idStudente);
    }


  /**
   * Delega all'opportuno manager l'invio dei messaggi
   *
   * @param idStudente   l'identificativo dello studente
   * @param idProfessore l'identificativo del professore
   * @param messaggio    il contenuto del messaggio
   * @param lato         chi ha inviato il messggio, se uno studente o professore
   * @return l'esito dell'operazione del manager
   */
  public boolean inviaMessaggio(int idStudente, int idProfessore, String messaggio, String lato) {
    mm.inviaMessaggio(idStudente, idProfessore, messaggio, lato);
    return true;
  }


  /**
   * Verifica ilcontenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param prenotazione la prenotazione da eliminare
   * @return l'esito dell'operazione del manager
   * @throws SQLException in caso di errori con il db
   */
  public boolean eliminaPrenotazione(Prenotazione prenotazione) throws SQLException {

        if (prenotazione != null)
            return m.eliminaPrenotazione(prenotazione);
        else return false;
    }

  /**
   * Verifica il contenuto dei parametri e delega la richiesta all'opportuno manager
   *
   * @param presenzaAssenza stringa contenente il valore da registrare, se una presenza o un'assenza
   * @param idStudente      lo studente di cui registrare la presenza/assenza
   * @return l'esito dell'operazione del manager
   * @throws SQLException in caso di errori con il db
   */
  public boolean registraPresenza(String presenzaAssenza, int idStudente) throws SQLException {

        if (presenzaAssenza.equals("assente"))
            return rm.registraAssenza(idStudente);
        else if (presenzaAssenza.equals("presente"))
            return rm.registraPresenza(idStudente);
        else
            return false;
    }






  /**
   * Restituisce l'elenco di tutti i professori
   *
   * @return l'elenco di tutti i docenti
   * @throws SQLException in caso di problemi con il db
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
                return null;
            }
            return professori;
        }


  /**
   * Richiede all'opportuno manager l'elenco degli studenti prenotati a un certo ricevimento
   *
   * @param ricevimento ricevimento di cui si vuole visualizzare gli studenti prenotati
   * @return la lista degli studenti prenotati
   * @throws SQLException in caso di errori con il db
   */
  public List<Studente> visualizzaStudenti(Ricevimento ricevimento) throws SQLException {
    return rm.visualizzaStudenti(ricevimento);
  }

  /**
   * Richiede all'opportuno manager i dati relativi a un certo ricevimento
   *
   * @param idRicevimento l'identificativo del ricevimento di cui si vuole ottenere un riferimento
   * @return il ricevimento caricato dal manager
   */
  public Ricevimento getRicevimentoById(int idRicevimento) {
    return rm.getRicevimentoById(idRicevimento);
  }


  /**
   * Restituisce le informazioni di un certo professore tramite id
   *
   * @param idProf l'identificativo del professore
   * @return le informazioni del professore desiderato
   * @throws SQLException in caso di problemi con il db
   */
  public Professore getProfById(int idProf) throws SQLException {
    Connection connection = null;
    Professore professore = new Professore();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT *  from professore where idProfessore = ?");
      statement.setInt(1, idProf);
      ResultSet rs = statement.executeQuery();
        if(!rs.next()){
            throw new Exception();
        }
        rs.previous();
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
                return null;
            }
            return professore;
        }


  /**
   * Restituisce le informazioni di un certo studente tramite id
   *
   * @param idStudente identificativo dello studente
   * @return le informazioni dello studente desiderato
   * @throws SQLException in caso di problemi con il db
   */
  public Studente getStudenteById(int idStudente) throws SQLException {
    Connection connection = null;
    Studente studente = new Studente();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT *  from studente where idStudente = ?");
      statement.setInt(1, idStudente);
      ResultSet rs = statement.executeQuery();
        if (!rs.next()) {
            throw new Exception();
        }
        rs.previous();
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
            return null;
        }
        return studente;
    }


  /**
   * Richiede all'opportuno manager l'ultimo messaggio inviato in una certa conversazione
   *
   * @param idStudente   l'identificativo dello studente
   * @param idProfessore l'identificativo del professore
   * @return l'ultimo messaggio della conversazione, caricato dal manager
   */
  public Messaggio getLastDataMessaggio(int idStudente, int idProfessore) {
    return mm.getLastDataMessaggio(idStudente, idProfessore);
  }


  /**
   * Richiede all'opportuno manager le informazioni relative a un certo professore tramite email
   *
   * @param email l'emeil del docente
   * @return le informazioni del docente con quella email
   */
  public Professore getProfessoreByEmail(String email) {
    return ac.getProfessoreByEmail(email);
  }

  /**
   * Richiede all'opportuno manager le informazioni relative a un certo studente tramite email
   *
   * @param email l'emeil dello studente
   * @return le informazioni dello studente con quella email
   */
  public Studente getStudenteByEmail(String email) {
    return ac.getStudenteByEmail(email);
  }

  /**
   * Richiede all'opportuno manager l'elenco dei ricevimenti di un certo professore
   *
   * @param prof il professore di cui caricare i ricevimenti
   * @return la lista dei ricevimenti caricata dal manager
   */
  public ArrayList<Ricevimento> getRicevimentiByProf(Professore prof) {
    return rm.getRicevimentiByProf(prof);
  }

  /**
   * Richiede all'opportuno manager l'elenco degli studenti che hanno contattato un certo professore
   *
   * @param idProf l'identificativo del professore
   * @return l'elenco degli studenti che hanno contattato il professore
   * @throws SQLException in caso di problemi con il db
   */
  public ArrayList<Studente> getStudentiContattati(int idProf) throws SQLException {
    return mm.getStudentiContattati(idProf);
  }


  /**
   * Richiede all'opportuno manager le informazioni relative a una certa prenotazione tramite id
   *
   * @param idPrenotazione l'identificativo della prenotazione
   * @return la prenotazione caricata dal manager
   * @throws SQLException in caso di problemi con il db
   */
  public Prenotazione getPrenotazioneById(int idPrenotazione) throws SQLException {
    return m.getPrenotazioneById(idPrenotazione);
  }


  /**
   * Richiede all'opportuno manager la conversazione tra un certo professore e un certo studente
   *
   * @param idStudente   l'identificativo dello studente
   * @param idProfessore l'identificativo del professore
   * @return la conversazione tra il docente e il professore
   */
  public ArrayList<Messaggio> getArrayListMessaggio(int idStudente, int idProfessore) {
    return mm.getArrayListMessaggio(idStudente, idProfessore);
  }


  /**
   * Richiede all'opportuno manager la lista degli studenti prenotati a un certo ricevimento
   *
   * @param idRicevimento l'identificativo del ricevimento
   * @return restituisce la lista delle prenotazioni per il ricevimento desiderato
   * @throws SQLException in caso di problemi con il db
   */
  public List<Prenotazione> visualizzaPrenotazioniByIdRicevimento(int idRicevimento) throws SQLException {
    return rm.visualizzaPrenotazioniByIdRicevimento(idRicevimento);
  }
}


