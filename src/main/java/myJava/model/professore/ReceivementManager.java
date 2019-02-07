package myJava.model.professore;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Prenotazione;
import myJava.model.beans.Professore;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe per la gestione dei ricevimenti
 */
public class ReceivementManager {

  /**
   * Crea un nuovo ricevimento nel database
   *
   * @param ricevimento ricevimento da dover inserire nel database
   * @return un booleano che rappresenta l'esito dell'operazione
   * @throws SQLException   in caso di errori con il database
   * @throws ParseException riscontrabile nella conversione da Date a String e viceversa durante la convalida della data
   */
  public boolean creaRicevimento(Ricevimento ricevimento) throws SQLException, ParseException {


    if (!checkData(ricevimento.getData()) || !checkOrario(ricevimento.getOrarioFine() + ":00") || !checkOrario(ricevimento.getOrarioInizio() + ":00") || !checkLuogo(ricevimento.getLuogo())) {
      System.out.println("Test Ricevimento non passato");
      return false;
    }
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "insert into " + "ricevimento"
        + " (idRicevimento,orarioInizio,orarioFine,luogo,data, postiTotali, postiDisponibili ,idProfessore) values (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setInt(1, ricevimento.getIdRicevimento());
      preparedStatement.setString(2, ricevimento.getOrarioInizio());
      preparedStatement.setString(3, ricevimento.getOrarioFine());
      preparedStatement.setString(4, ricevimento.getLuogo());
      preparedStatement.setString(5, ricevimento.getData());
      preparedStatement.setInt(6, ricevimento.getPostiTotali());
      preparedStatement.setInt(7, ricevimento.getPostiTotali());
      preparedStatement.setInt(8, ricevimento.getIdProfessore());
      preparedStatement.executeUpdate();

      connection.commit();
      System.out.println("Ricevimento Inserito");
      return true;
    } catch (Exception e) {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
      return false;
    }


  }


  /**
   * Elimina dal database un certo ricevimento
   *
   * @param ricevimento ricevimento da eliminare
   * @return un booleano che rappresenta l'esito dell'operazione
   * @throws SQLException in caso di errori con il database
   */
  public boolean eliminaRicevimento(Ricevimento ricevimento) throws SQLException {

    if (ricevimento == null || ricevimento.getIdRicevimento() == 0) {
      System.out.println("No ricevimento");
      return false;
    }
    Connection conn;
    conn = DriverManagerConnectionPool.getConnection();
    try {
      PreparedStatement preparedStatement = conn.prepareStatement("delete from ricevimento where idRicevimento=?");
      preparedStatement.setInt(1, ricevimento.getIdRicevimento());
      System.out.println("Eliminazione: " + ricevimento.getIdRicevimento());
      if (preparedStatement.executeUpdate() == 0) {//non è stato cancellato alcun ricevimento
        throw new Exception();

      }
      conn.commit();
      System.out.println("Operazione completata");
      return true;
    } catch (Exception e) {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
      return false;

    }
  }

  /**
   * Modifica le informazioni relative a un certo ricevimento
   *
   * @param ricevimento ricevimento di cui aggiornare i dati nel database
   * @return un booleano che rappresenta l'esito dell'operazione
   * @throws SQLException in caso di errori con il database
   */
  public boolean modificaRicevimento(Ricevimento ricevimento) throws SQLException {


    Connection conn;

    conn = DriverManagerConnectionPool.getConnection();
    try {
      PreparedStatement preparedStatement = conn.prepareStatement("update ricevimento set idRicevimento=?,orarioInizio=?,orarioFine=?,luogo=?,data=?,idProfessore=?, postiDisponibili=? where idRicevimento=?");
      preparedStatement.setInt(1, ricevimento.getIdRicevimento());
      preparedStatement.setString(2, ricevimento.getOrarioInizio());
      preparedStatement.setString(3, ricevimento.getOrarioFine());
      preparedStatement.setString(4, ricevimento.getLuogo());
      preparedStatement.setString(5, ricevimento.getData());
      preparedStatement.setInt(6, ricevimento.getIdProfessore());
      preparedStatement.setInt(7, ricevimento.getPostiDisponibili()); //TODO da testare
      preparedStatement.setInt(8, ricevimento.getIdRicevimento());
      if (preparedStatement.executeUpdate() == 0) {
        throw new Exception();
      }
      conn.commit();
      preparedStatement.close();
      conn.close();
      return true;
    } catch (Exception e) {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
      conn.close();
      return false;
    }
  }

  /**
   * Visualizza le informazioni relative a un certo ricevimento date le informazioni di data e ora
   *
   * @param orarioInizio l'orario di inizio del ricevimento
   * @param orarioFine   l'orario in cui finisce il ricevimento
   * @param dataR        il giorno in cui vi è il ricevimento
   * @return il ricevimento desiderato
   * @throws SQLException   in caso di errori con il database
   * @throws ParseException riscontrabile nella conversione da Date a String e viceversa durante la convalida della data/ora
   */
  public Ricevimento visualizzaRicevimento(String orarioInizio, String orarioFine, String dataR) throws SQLException, ParseException {

    if (!checkOrario(orarioInizio) || !checkOrario(orarioFine) || !checkData(dataR)) {

      return null;
    }
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
      conn = DriverManagerConnectionPool.getConnection();

      preparedStatement = conn.prepareStatement("select * from ricevimento r where r.orarioInizio=? and r.orarioFine=? and r.data=?");
      preparedStatement.setString(1, orarioInizio);
      preparedStatement.setString(2, orarioFine);
      preparedStatement.setString(3, dataR);

      ResultSet rs = preparedStatement.executeQuery();
      Ricevimento r = new Ricevimento();
      while (rs.next()) {
        r.setIdRicevimento(rs.getInt(1));
        r.setOrarioInizio(rs.getString(2));
        r.setOrarioFine(rs.getString(3));
        r.setLuogo(rs.getString(4));
        r.setData(rs.getString(5));
        r.setPostiTotali(rs.getInt(6));
        r.setPostiDisponibili(rs.getInt(7));
        r.setIdProfessore(rs.getInt(8));
      }
      return r;


    } catch (Exception e) {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
      return null;
    }

  }

  /**
   * Registra nel database la presenza di un certo studente
   *
   * @param idStudente l'identificativo dello studente nel database
   * @return un booleano che rappresenta l'esito dell'operazione
   * @throws SQLException in caso di errori con il database
   */
  public boolean registraPresenza(int idStudente) throws SQLException {
    if (idStudente == 0) {
      return false;
    }
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
      conn = DriverManagerConnectionPool.getConnection();
      preparedStatement = conn.prepareStatement("update prenotazione  set prenotazione.presenza=1 where prenotazione.idStudente=?");

      preparedStatement.setInt(1, idStudente);
      if (preparedStatement.executeUpdate() == 0) {

        throw new Exception();
      }
      conn.commit();
      return true;
    } catch (Exception e) {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());

      return false;


    }
  }

  /**
   * Registra nel database l'assenza di un certo studente
   *
   * @param idStudente l'identificativo dello studente nel database
   * @return un booleano che rappresenta l'esito dell'operazione
   * @throws SQLException in caso di errori con il database
   */
  public boolean registraAssenza(int idStudente) throws SQLException {
    if (idStudente == 0) {
      return false;
    }
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    try {
      conn = DriverManagerConnectionPool.getConnection();

      preparedStatement = conn.prepareStatement("update studente set numAssenza=numAssenza+1 where idStudente=?");
      preparedStatement.setInt(1, idStudente);
      if (preparedStatement.executeUpdate() == 0) {

        throw new Exception();
      }
      conn.commit();

      return true;
    } catch (Exception e) {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());

      return false;

    }


  }

  /**
   * Estrae dal database la lista degli studenti prenotati a un certo ricevimento
   *
   * @param ricevimento il ricevimento  dal quale si vuole ricavare l'elenco delle prenotazioni degli studenti
   * @return la lista degli studenti prenotati
   * @throws SQLException in caso di errori con il database
   */
  public List<Studente> visualizzaStudenti(Ricevimento ricevimento) throws SQLException {
    if (ricevimento == null || ricevimento.getIdRicevimento() == 0) {


      return null;

    }
    Connection connection = null;

    List<Studente> students = new ArrayList<>();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT *  from studente s inner join prenotazione p on s.idStudente=p.idStudente WHERE p.idRicevimento = ?");
      //setting the parameters
      statement.setInt(1, ricevimento.getIdRicevimento());
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Studente student = new Studente();
        student.setIdStudente(rs.getInt(1));
        student.setNomeStudente(rs.getString(2));
        student.setCognomeStudente(rs.getString(3));
        student.setMatricola(rs.getString(4));
        student.setEmailStudente(rs.getString(5));
        student.setTelefonoStudente(rs.getString(6));
        student.setNumAssenza(rs.getInt(7));
        students.add(student);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return students;


  }

  /**
   * Estrae dal database le informazioni relative a un certo ricevimento tramite id
   *
   * @param idRicevimento l'identificativo del ricevimento desiderato
   * @return il ricevimento desiderato con tutte le sue informazioni
   */
  public Ricevimento getRicevimentoById(int idRicevimento) {
    if (idRicevimento == 0) {

      return null;
    }
    Connection connection = null;
    Ricevimento ricevimento = new Ricevimento();
    DateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement(
          "SELECT *  from ricevimento where idRicevimento = ? ");
      statement.setInt(1, idRicevimento);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        ricevimento.setIdRicevimento(rs.getInt("idRicevimento"));
        Time start = rs.getTime("orarioInizio");
        ricevimento.setOrarioInizio(simpleDateFormat.format(start.getTime()));
        Time fine = rs.getTime("orarioFine");
        ricevimento.setOrarioFine(simpleDateFormat.format(fine.getTime()));
        ricevimento.setLuogo(rs.getString("luogo"));
        ricevimento.setData(rs.getString("data"));
        ricevimento.setPostiDisponibili(rs.getInt("postiDisponibili"));
        ricevimento.setPostiTotali(rs.getInt("postiTotali"));
        ricevimento.setIdProfessore(rs.getInt("idProfessore"));
      }
      connection.close();
    } catch (Exception e) {

      e.printStackTrace();
    }
    return ricevimento;
  }

  /**
   * Estrae dal database l'elenco dei ricevimenti di un certo professore
   *
   * @param prof il professore del quale si vuole ricavare i ricevimenti
   * @return la lista dei ricevimenti creati dal professore passato come parametro
   */
  public ArrayList<Ricevimento> getRicevimentiByProf(Professore prof) {
    if (prof == null) {

      return null;
    }
    Connection connection = null;
    ArrayList<Ricevimento> lista = new ArrayList<>();
    DateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement(
          "SELECT *  from ricevimento where idProfessore = ? ");
      statement.setInt(1, prof.getIdProfessore());
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Ricevimento ricevimento = new Ricevimento();
        ricevimento.setIdRicevimento(rs.getInt("idRicevimento"));
        Time start = rs.getTime("orarioInizio");
        ricevimento.setOrarioInizio(simpleDateFormat.format(start.getTime()));
        Time fine = rs.getTime("orarioFine");
        ricevimento.setOrarioFine(simpleDateFormat.format(fine.getTime()));
        ricevimento.setLuogo(rs.getString("luogo"));
        ricevimento.setData(rs.getString("data"));
        ricevimento.setPostiDisponibili(rs.getInt("postiDisponibili"));
        ricevimento.setPostiTotali(rs.getInt("postiTotali"));
        ricevimento.setIdProfessore(rs.getInt("idProfessore"));
        lista.add(ricevimento);
      }
      connection.close();
    } catch (Exception e) {

      e.printStackTrace();
    }
    return lista;
  }

  /**
   * Verifica il formato della data
   *
   * @param data la data di cui bisogna effettuare la verifica
   * @return booleano che rappresenta l'esito della verifica
   */
  private boolean checkData(String data) {
    if (data.matches("\\d{4}-\\d{2}-\\d{2}")) {

      return true;
    } else {
      return false;
    }
  }

  /**
   * Verifica il formato dell'orario e se rispetta i limiti imposti
   *
   * @param orario l'orario di cui bisogna effettuare la verifica
   * @return booleano che rappresenta l'esito della verifica
   * @throws ParseException possibile errore che si può avere nel parsing da Date a String e viceversa
   */
  private boolean checkOrario(String orario) throws ParseException {

    if (orario.matches("\\d{2}:\\d{2}:\\d{2}")) {
      String dataFittizzia = "2019-12-12 ";
      System.out.println(orario);
      String dataCompleta = dataFittizzia + orario;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date parsedDate = format.parse(dataCompleta);
      Timestamp timeStamp = new java.sql.Timestamp(parsedDate.getTime());
      parsedDate = format.parse(dataFittizzia + "20:00:00");
      Timestamp orarioLimiteInferiore = new java.sql.Timestamp(parsedDate.getTime());
      parsedDate = format.parse(dataFittizzia + "08:30:00");
      Timestamp orarioLimiteSuperiore = new Timestamp(parsedDate.getTime());
      System.out.println("check  " + timeStamp + " - " + orarioLimiteInferiore + " - " + orarioLimiteSuperiore);

      if (timeStamp.before(orarioLimiteInferiore) && timeStamp.after(orarioLimiteSuperiore)) {

        return true;

      }
      return false;
    } else {
      return false;
    }
  }

  /**
   * Verifica la presenza o meno di un luogo del ricevimento
   *
   * @param luogo Stringa su cui effettuare il controllo
   * @return booleano che rappresenta l'esito della verifica

   */
  private boolean checkLuogo(String luogo) {

    if (!luogo.trim().equals("")) {

      return true;
    }
    return false;
  }


  /**
   * Estrae dal database l'elenco delle prenotazioni di un certo ricevimento
   *
   * @param idRicevimento l'identificativo di un ricevimento
   * @return la lista delle prenotazioni per il ricevimento specificato
   * @throws SQLException in caso di errori con il database
   */
  public List<Prenotazione> visualizzaPrenotazioniByIdRicevimento(int idRicevimento) throws SQLException {
    if (idRicevimento == 0) {


      return null;

    }
    Connection connection = null;

    List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT *  from prenotazione WHERE prenotazione.idRicevimento = ?");
      //setting the parameters
      statement.setInt(1, idRicevimento);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Prenotazione prenotation = new Prenotazione();
        prenotation.setIdPrenotazione(rs.getInt(1));
        prenotation.setListaStudenti(rs.getString(2));
        prenotation.setMotivazione(rs.getString(3));
        prenotation.setOrario(rs.getString(4));
        prenotation.setIdRicevimento(rs.getInt(5));
        prenotation.setIdStudente(rs.getInt(6));
        prenotation.setPresenza(rs.getBoolean(7));
        prenotazioni.add(prenotation);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return prenotazioni;


  }

}
