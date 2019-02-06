package myJava.model.studente;

import myJava.model.beans.Prenotazione;
import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la gestione delle prenotazioni
 */
public class BookingManager {

  /**
   * Crea una nuova prenotazione nel database
   * @param prenotazione
   * @return
   * @throws SQLException
   */
  public boolean inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    if (prenotazione.getListaStudenti() == null || !checkNomiAltriStudenti(prenotazione.getListaStudenti()) || !checkMotivoRicevimento(prenotazione.getMotivazione())) {
      return false;
    }
    String insertSQL = "insert into " + "prenotazione"
        + " (idPrenotazione, listaStudenti,motivazione ,orario,idRicevimento,idStudente,presenza) values (?, ?, ?, ?, ?, ?,?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setInt(1, prenotazione.getIdPrenotazione());

      preparedStatement.setString(2, prenotazione.getListaStudenti());
      preparedStatement.setString(3, prenotazione.getMotivazione());
      preparedStatement.setString(4, prenotazione.getOrario());
      preparedStatement.setInt(5, prenotazione.getIdRicevimento());
      preparedStatement.setInt(6, prenotazione.getIdStudente());
      preparedStatement.setBoolean(7, prenotazione.isPresenza());
      preparedStatement.executeUpdate();

      connection.commit();
      preparedStatement.close();
      return true;
    } catch (Exception e) {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
      connection.close();

      return false;
    }

  }


  /**
   * Estrae dal database l'elenco delle prenotazioni di un certo studente
   * @param idStudente
   * @return
   * @throws SQLException
   */
  public List<Prenotazione> visualizzaPrenotazioni(int idStudente) throws SQLException {

    if (idStudente == 0) {
      return null;
    }
    Connection connection = null;

    List<Prenotazione> bookings = new ArrayList<>();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT *  from prenotazione p inner join studente s on s.idStudente=p.idStudente WHERE s.idStudente = ?");
      //setting the parameters
      statement.setInt(1, idStudente);
      ResultSet rs = statement.executeQuery();
      if (!rs.next()) {

        throw new Exception();
      }
      rs.previous();
      while (rs.next()) {
        Prenotazione prenotation = new Prenotazione();
        prenotation.setIdPrenotazione(rs.getInt(1));
        prenotation.setListaStudenti(rs.getString(2));
        prenotation.setMotivazione(rs.getString(3));
        prenotation.setOrario(rs.getString(4));
        prenotation.setIdRicevimento(rs.getInt(5));
        prenotation.setIdStudente(rs.getInt(6));
        prenotation.setPresenza(rs.getBoolean(7));
        System.out.println(prenotation.getIdPrenotazione());
        bookings.add(prenotation);
      }
    } catch (Exception e) {

      e.printStackTrace();
      return null;
    }

    return bookings;

  }

  /**
   * Elimina dal database una certa prenotazioni
   * @param prenotazione
   * @return
   * @throws SQLException
   */
  public boolean eliminaPrenotazione(Prenotazione prenotazione) throws SQLException {
    if (prenotazione==null ||  prenotazione.getIdPrenotazione() == 0) {
      return false;

    }
    Connection connection = null;
    System.out.println("Eliminazione in corso");
    connection = DriverManagerConnectionPool.getConnection();
    try {
      PreparedStatement preparedStmt = connection.prepareStatement("delete from prenotazione where idPrenotazione=?");
      preparedStmt.setInt(1, prenotazione.getIdPrenotazione());

      preparedStmt.execute();
      connection.commit();

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifica la validità della motivazione
   * @param motivazione
   * @return
   */
  private boolean checkMotivoRicevimento(String motivazione) {
    if (motivazione.equals("") || motivazione.length() > 60) {

      return false;
    }
    return true;

  }

  /**
   * Verifica la validità di altri studenti
   * @param nomiAltriStudenti
   * @return
   */
  private boolean checkNomiAltriStudenti(String nomiAltriStudenti) {

    if (nomiAltriStudenti.length() > 65000) {
      return false;

    } else {
      return true;
    }


  }

  /**
   * Estrae dal database una certa prenotazione tramite id
   * @param idPrenotazione
   * @return
   * @throws SQLException
   */
  public Prenotazione getPrenotazioneById(int idPrenotazione) throws SQLException {
    if (idPrenotazione == 0)
      return null;
    Prenotazione p = new Prenotazione();
    Connection connection = null;
    connection = DriverManagerConnectionPool.getConnection();
    try {
      PreparedStatement preparedStmt = connection.prepareStatement("select* from prenotazione where idPrenotazione=?");
      preparedStmt.setInt(1,idPrenotazione);
      ResultSet rs = preparedStmt.executeQuery();
      if(!rs.next())
      {
        return null;
      }

      p.setIdPrenotazione(rs.getInt(1));
      p.setListaStudenti(rs.getString(2));
      p.setMotivazione(rs.getString(3));
      p.setOrario(rs.getString(4));
      p.setIdRicevimento(rs.getInt(5));
      p.setIdStudente(rs.getInt(6));
      p.setPresenza(rs.getBoolean(7));

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return p;
  }
}
