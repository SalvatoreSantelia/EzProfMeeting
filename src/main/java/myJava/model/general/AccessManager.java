package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Professore;
import myJava.model.beans.Studente;
import myJava.model.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessManager {

    public User doLogin(String mail, String password) throws SQLException {

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

            while (rs.next()) {

                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setTipo(rs.getString("ruolo"));


            }
            System.out.println(utente.getEmail());

        } catch (Exception e) {

            e.printStackTrace();
        }
        return utente;

    }


    public Studente getUserStudente(String email, String password) {
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

            while (rs.next()) {
                studente.setIdStudente(rs.getInt("idStudente"));
                studente.setNomeStudente(rs.getString("nomeStudente"));
                studente.setCognomeStudente(rs.getString("cognomeStudente"));
                studente.setMatricola(rs.getString("matricola"));
                studente.setEmailStudente(rs.getString("emailStudente"));
                studente.setTelefonoStudente(rs.getString("telefonoStudente"));
                studente.setNumAssenza(rs.getInt("numAssenza"));
            }

            System.out.println(studente);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return studente;
    }


    //ottenere il prof in base all'user
    public Professore getUserProfessore(String email, String password) {
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
        }
        return professore;
    }



    //get professore by email
    public Professore getProfessoreByEmail(String email) {
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
        }
        return professore;
    }

}