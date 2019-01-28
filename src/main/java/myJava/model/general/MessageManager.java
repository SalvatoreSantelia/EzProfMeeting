package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Messaggio;
import myJava.model.beans.Studente;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageManager {

    public void inviaMessaggio(int idStudente, int idProfessore, String testo, String lato){
        Connection connection = null;
        ArrayList<Studente> studenti = new ArrayList<Studente>();
        DataManager dm = new DataManager();
        PreparedStatement preparedStatement = null;
        System.out.println(idStudente+idProfessore+testo+lato+"a");
        String insertSQL = "insert into " + "messaggio"
                + " (dataMessaggio, testoMessaggio,idProfessore ,idStudente,lato, orarioMessaggio) values (?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            SimpleDateFormat formatterr = new SimpleDateFormat("HH:mm:ss");

            preparedStatement.setDate(1, java.sql.Date.valueOf(formatter.format(date)));
            preparedStatement.setString(2, testo);
            preparedStatement.setInt(3, idProfessore);
            preparedStatement.setInt(4, idStudente);
            preparedStatement.setString(5, lato);
            preparedStatement.setString(6,formatterr.format(System.currentTimeMillis()));
            preparedStatement.executeUpdate();

            connection.commit();
            preparedStatement.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }



    public ArrayList<Studente> getStudentiContattati(int idProf) throws SQLException {
        Connection connection = null;
        ArrayList<Studente> studenti = new ArrayList<Studente>();
        DataManager dm = new DataManager();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT *  from messaggio where idProfessore = ? GROUP BY idStudente ORDER BY dataMessaggio,orarioMessaggio DESC");
            statement.setInt(1,idProf);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Messaggio messaggio = new Messaggio();
                messaggio.setIdMessaggio(rs.getInt("idMessaggio"));
                messaggio.setIdStudente(rs.getInt("idStudente"));
                Studente studente = dm.getStudenteById(messaggio.getIdStudente());
                studenti.add(studente);
            }
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return studenti;
    }












    //get Last Messaggio
    public Messaggio getLastDataMessaggio(int idStudente, int idProfessore){
        Connection connection = null;
        Messaggio messaggio = new Messaggio();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from messaggio where idProfessore = ? and idStudente = ? ORDER BY dataMessaggio DESC, orarioMessaggio DESC ");
            statement.setInt(1,idProfessore);
            statement.setInt(2,idStudente);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                messaggio.setIdMessaggio(rs.getInt("idMessaggio"));
                messaggio.setDataMessaggio(rs.getDate("dataMessaggio"));
                messaggio.setTestoMessaggio(rs.getString("testoMessaggio"));
                messaggio.setIdStudente(rs.getInt("idStudente"));
                messaggio.setIdProfessore(rs.getInt("idProfessore"));
                messaggio.setLato(rs.getString("lato"));
                messaggio.setOrarioMessaggio(rs.getTime("orarioMessaggio"));

                connection.close();
                return messaggio;
            }
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return messaggio;
    }



    //get ArrayList di messaggi
    public ArrayList<Messaggio> getArrayListMessaggio(int idStudente, int idProfessore){
        Connection connection = null;
        ArrayList<Messaggio> messaggi = new ArrayList<Messaggio>();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from messaggio where idProfessore = ? and idStudente = ? ORDER BY dataMessaggio");
            statement.setInt(1,idProfessore);
            statement.setInt(2,idStudente);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Messaggio messaggio = new Messaggio();
                messaggio.setIdMessaggio(rs.getInt("idMessaggio"));
                messaggio.setDataMessaggio(rs.getDate("dataMessaggio"));
                messaggio.setOrarioMessaggio(rs.getTime("orarioMessaggio"));
                messaggio.setTestoMessaggio(rs.getString("testoMessaggio"));
                messaggio.setIdStudente(rs.getInt("idStudente"));
                messaggio.setIdProfessore(rs.getInt("idProfessore"));
                messaggio.setLato(rs.getString("lato"));
                messaggi.add(messaggio);
            }
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return messaggi;
    }
}
