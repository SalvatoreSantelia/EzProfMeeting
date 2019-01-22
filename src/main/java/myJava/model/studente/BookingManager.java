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

public class BookingManager {

    public BookingManager(){}

    public boolean inserisciPrenotazione(Prenotazione prenotazione) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

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
        } catch(Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            connection.close();

            return false;
        }

    }
    //metodo da spostare in ReceivementManager

    public List<Prenotazione> visualizzaPrenotazioni(int idStudente)throws SQLException{


        Connection connection = null;

        List<Prenotazione> bookings =new ArrayList<>();
        try
        {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from prenotazione p inner join studente s on s.idStudente=p.idStudente WHERE s.idStudente = ?");
            //setting the parameters
            statement.setInt(1,idStudente);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Prenotazione prenotation=new Prenotazione();
                prenotation.setIdPrenotazione(rs.getInt(1));
                prenotation.setListaStudenti(rs.getString(2));
                prenotation.setMotivazione(rs.getString(3));
                prenotation.setOrario(rs.getString(4));
                prenotation.setIdRicevimento(rs.getInt(5));
                prenotation.setIdStudente(rs.getInt(6));
                prenotation.setPresenza(rs.getBoolean(7));
                bookings.add(prenotation);
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return bookings;

    }

    public boolean eliminaPrenotazione(Prenotazione prenotazione)throws SQLException{

        Connection connection=null;

        connection=DriverManagerConnectionPool.getConnection();
try {
    PreparedStatement preparedStmt = connection.prepareStatement("delete from prenotazione where idPrenotazione=?");
    preparedStmt.setInt(1, prenotazione.getIdPrenotazione());

    preparedStmt.execute();
    connection.commit();

}catch(Exception e) {
    e.printStackTrace();
    return false;
}
       return true;
    }
}
