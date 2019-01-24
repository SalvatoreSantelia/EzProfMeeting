package myJava.model.general;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Messaggio;
import myJava.model.beans.Professore;
import myJava.model.beans.Studente;
import myJava.model.professore.ReceivementManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    public ArrayList<Studente> getStudentiContattati(int idProf) throws SQLException {
        Connection connection = null;
        ArrayList<Studente> studenti = new ArrayList<Studente>();
        DataManager dm = new DataManager();
        try {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from messaggio where idProfessore = ?");
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
            PreparedStatement statement = connection.prepareStatement("SELECT *  from messaggio where idProfessore = ? and idStudente = ? ORDER BY dataMessaggio DESC");
            statement.setInt(1,idProfessore);
            statement.setInt(2,idStudente);
            ResultSet rs = statement.executeQuery();

            rs.next();
                messaggio.setIdMessaggio(rs.getInt("idMessaggio"));
                messaggio.setDataMessaggio(rs.getString("dataMessaggio"));
                messaggio.setTestoMessaggio(rs.getString("testoMessaggio"));
                messaggio.setIdStudente(rs.getInt("idStudente"));
                messaggio.setIdProfessore(rs.getInt("idProfessore"));

            System.out.println("CIAO "+idProfessore+" "+idStudente+" "+messaggio.getIdMessaggio()+""+messaggio.getTestoMessaggio());
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return messaggio;
    }
}
