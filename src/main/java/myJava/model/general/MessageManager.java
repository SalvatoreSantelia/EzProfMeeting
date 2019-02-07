package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Messaggio;
import myJava.model.beans.Studente;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe per la gestione del traffico dei messaggi
 */
public class MessageManager {



    /* * Registra il messaggio nel database
     * @param idStudente
     * @param idProfessore
     * @param testo
     * @param lato
     * @return
     */
    public boolean inviaMessaggio(int idStudente, int idProfessore, String testo, String lato)throws SQLException {
        if(idStudente==0|| idProfessore==0||testo.equals("")||lato.equals("")){

            return false;
        }
        Connection connection = null;
        ArrayList<Studente> studenti = new ArrayList<Studente>();
        DataManager dm = new DataManager();
        PreparedStatement preparedStatement = null;
        //System.out.println(idStudente+idProfessore+testo+lato+"a");
        String insertSQL = "insert into " + "messaggio"
                + " (dataMessaggio, testoMessaggio,idProfessore ,idStudente,lato, orarioMessaggio) values (?, ?, ?, ?, ?, ?)";


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
        preparedStatement.setString(6, formatterr.format(System.currentTimeMillis()));
        preparedStatement.executeUpdate();


        connection.commit();
        preparedStatement.close();
        return true;


    }

    /**
     * Estrae dal database l'elenco degli studenti che hanno contattato un certo professore
     * @param idProf
     * @return
     * @throws SQLException
     */
    public ArrayList<Studente> getStudentiContattati(int idProf) throws SQLException {
        if(idProf==0){
            return null;
        }
        Connection connection = null;
        ArrayList<Studente> studenti = new ArrayList<Studente>();
        DataManager dm = new DataManager();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT *  from messaggio where idProfessore = ? GROUP BY idStudente ORDER BY dataMessaggio,orarioMessaggio DESC");
            statement.setInt(1,idProf);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){

                throw new Exception();
            }
            rs.previous();
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
            return null;
        }
        return studenti;
    }


    /**
     * Estrae dal database l'ultimo messaggio inviato in una certa conversazione
     * @param idStudente
     * @param idProfessore
     * @return
     */
    public Messaggio getLastDataMessaggio(int idStudente, int idProfessore){
        if(idProfessore==0||idStudente==0){

            return null;
        }
        Connection connection = null;
        Messaggio messaggio = new Messaggio();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from messaggio where idProfessore = ? and idStudente = ? ORDER BY dataMessaggio DESC, orarioMessaggio DESC ");
            statement.setInt(1,idProfessore);
            statement.setInt(2,idStudente);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){

                throw new Exception();
            }
            rs.previous();
            rs.next();
                messaggio.setIdMessaggio(rs.getInt("idMessaggio"));
                messaggio.setDataMessaggio(rs.getDate("dataMessaggio"));
                messaggio.setTestoMessaggio(rs.getString("testoMessaggio"));
                messaggio.setIdStudente(rs.getInt("idStudente"));
                messaggio.setIdProfessore(rs.getInt("idProfessore"));
                messaggio.setLato(rs.getString("lato"));
                messaggio.setOrarioMessaggio(rs.getTime("orarioMessaggio"));




        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        System.out.println(messaggio.getIdMessaggio()+ " ID MESSAGGIO");
        return messaggio;
    }


    /**
     * Estrae dal database la conversazione tra un certo professore e un certo studente
     * @param idStudente
     * @param idProfessore
     * @return
     */
    //todo CHIEDI A VITTORIO
    public ArrayList<Messaggio> getArrayListMessaggio(int idStudente, int idProfessore){
        if(idProfessore==0||idStudente==0) {

            return null;
        }
        Connection connection = null;
        ArrayList<Messaggio> messaggi = new ArrayList<Messaggio>();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from messaggio where idProfessore = ? and idStudente = ? ORDER BY dataMessaggio DESC,orarioMessaggio desc ");
            statement.setInt(1,idProfessore);
            statement.setInt(2,idStudente);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                throw new Exception();
            }
             rs.previous();
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
            return null;

        }
        return messaggi;
    }
}
