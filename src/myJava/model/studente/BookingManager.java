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

        String insertSQL = "insert into " + "fit.ordine"
                + " (idPrenotazione, listaStudenti,motivazione ,orario,idRicevimento,idStudente,presenza,) values (?, ?, ?, ?, ?, ?)";

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
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
                return true;
            }
        }

    }

    public List<Studente> visualizzaStudenti(Ricevimento ricevimento) throws SQLException{

        Connection connection = null;

        List<Studente> students =new ArrayList<>();
        try
        {
            connection = DriverManagerConnectionPool.getConnection();
            //creating prepared statement for our required query
            PreparedStatement statement = connection.prepareStatement("SELECT *  from studente s inner join prenotazione p on s.idStudente=p.idStudente WHERE p.idRicevimento = ?");
            //setting the parameters
            statement.setInt(1,ricevimento.getIdRicevimento());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Studente student=new Studente();
                student.setIdStudente(rs.getInt(1));
                student.setNomeStudente(rs.getString(2));
                student.setCognomeStudente(rs.getString(3));
                student.setMatricola(rs.getString(4));
                student.setEmailStudente(rs.getString(5));
                student.setTelefonoStudente(rs.getString(6));
                student.setNumAssenza(rs.getInt(7));
                students.add(student);

                connection.close();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return students;


    }

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
        }
        return bookings;

    }

    public boolean eliminaPrenotazione(Prenotazione prenotazione)throws SQLException{

        Connection connection=null;

        connection=DriverManagerConnectionPool.getConnection();

        PreparedStatement preparedStmt = connection.prepareStatement("delete from prenotazione where =?");
        preparedStmt.setInt(1,prenotazione.getIdPrenotazione());

        preparedStmt.execute();

        connection.close();

       return true;
    }
}
