package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Professore;
import myJava.model.beans.Studente;
import myJava.model.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe che implementa la parte di login del sistema
 * @version 1.0
 * */
public class AccessManager {

  /**
   * metodo che si occupa del login controllando le credenziali nel database
   * @param mail è la stringa che rappresenta la mail dell'utente e non deve essere vuota
   * @param password è la stringa che rappresenta la password dell'utente e, oltre a non essere vuota, deve avere almeno 8 caratteri
   * */
  public User doLogin(String mail, String password) throws SQLException {
    if(!mailSyntaxCheck(mail)|| password.length()<8){
      return null;
    }
    Connection connection = null;

    User utente = new User();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT * from login WHERE email = ? AND password=?");
      //setting the parameters

      statement.setString(1, mail);
      statement.setString(2, password);
      //executing the prepared statement, which returns a ResultSet
      ResultSet rs = statement.executeQuery();
      if(!rs.next()){
        throw new Exception();

      }
      rs.previous();
      while (rs.next()) {

        utente.setEmail(rs.getString("email"));
        utente.setPassword(rs.getString("password"));
        utente.setTipo(rs.getString("ruolo"));


      }
      // System.out.println(utente.getEmail());

    } catch (Exception e) {

      e.printStackTrace();
      return null;
    }
    return utente;

  }
  /**
   * @return un oggetto professore data la sua e-mail
   * @param email non deve essere vuota
   * */
  public Professore getProfessoreByEmail(String email) {
    if(!mailSyntaxCheck(email)){
      return null;

    }
    Connection connection = null;

    Professore professore = new Professore();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT * from professore WHERE emailProfessore = ?");
      //setting the parameters

      statement.setString(1, email);
      //executing the prepared statement, which returns a ResultSet
      ResultSet rs = statement.executeQuery();
      if(!rs.next()){

        throw new Exception();
      }
      rs.previous();
      while (rs.next()) {
        professore.setIdProfessore(rs.getInt("idProfessore"));
        professore.setNomeProfessore(rs.getString("nomeProfessore"));
        professore.setCognomeProfessore(rs.getString("cognomeProfessore"));
        professore.setEmailProfessore(rs.getString("emailProfessore"));
        professore.setTelefonoProfessore(rs.getString("telefonoProfessore"));
        professore.setUfficioProfessore(rs.getString("ufficioProfessore"));
      }


    } catch (Exception e) {

      e.printStackTrace();
      return null;
    }
    return professore;
  }



  /**@return un oggetto studente data la sua e-mail
   * @param email non deve essere vuota
   * */
  public Studente getStudenteByEmail(String email) {
    if(!mailSyntaxCheck(email)){

      return null;
    }
    Connection connection = null;

    Studente studente = new Studente();
    try {
      connection = DriverManagerConnectionPool.getConnection();
      //creating prepared statement for our required query
      PreparedStatement statement = connection.prepareStatement("SELECT * from studente WHERE emailStudente = ?");
      //setting the parameters

      statement.setString(1, email);
      //executing the prepared statement, which returns a ResultSet
      ResultSet rs = statement.executeQuery();
      if(!rs.next()){

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


    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return studente;
  }
/**
 *metodo che controlla la sintassi della mail
 * */
  private boolean mailSyntaxCheck(String email)
  {
    // Create the Pattern using the regex
    Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

    // Match the given string with the pattern
    Matcher m = p.matcher(email);

    // check whether match is found
    boolean matchFound = m.matches();

    StringTokenizer st = new StringTokenizer(email, ".");
    String lastToken = null;
    while (st.hasMoreTokens()) {
      lastToken = st.nextToken();
    }

    // validate the country code
    if (matchFound && lastToken.length() >= 2
        && email.length() - 1 != lastToken.length()) {

      return true;
    } else {
      return false;
    }

  }
}