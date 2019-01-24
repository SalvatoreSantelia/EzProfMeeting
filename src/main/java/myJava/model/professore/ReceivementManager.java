package myJava.model.professore;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;

import java.security.spec.ECField;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceivementManager {


    public boolean creaRicevimento(Ricevimento ricevimento)throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "insert into " + "ricevimento"
                + " (idRicevimento,orarioInizio,orarioFine,luogo,data,idProfessore) values (?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, ricevimento.getIdRicevimento());
            preparedStatement.setString(2, ricevimento.getOrarioInizio());
            preparedStatement.setString(3, ricevimento.getOrarioFine());
            preparedStatement.setString(4, ricevimento.getLuogo());
            preparedStatement.setString(5, ricevimento.getData());
            preparedStatement.setInt(6, ricevimento.getIdProfessore());
            preparedStatement.executeUpdate();

            connection.commit();
            System.out.println("ho commitatto");
            return true;
        } catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }


    }


    public boolean eliminaRicevimento(Ricevimento ricevimento)throws SQLException {

        Connection conn;

        conn = DriverManagerConnectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from ricevimento where idRicevimento=?");
            preparedStatement.setInt(1, ricevimento.getIdRicevimento());
            preparedStatement.executeUpdate();
            conn.commit();

            return true;
        } catch(Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;

        }
    }
    public boolean modificaRicevimento(Ricevimento ricevimento)throws SQLException{


        Connection conn;

        conn=DriverManagerConnectionPool.getConnection();
try {
    PreparedStatement preparedStatement = conn.prepareStatement("update ricevimento set idRicevimento=?,orarioInizio=?,orarioFine=?,luogo=?,data=?,idProfessore=? where idRicevimento=?");
    preparedStatement.setInt(1,ricevimento.getIdRicevimento());
    preparedStatement.setString(2,ricevimento.getOrarioInizio());
    preparedStatement.setString(3,ricevimento.getOrarioFine());
    preparedStatement.setString(4,ricevimento.getLuogo());
    preparedStatement.setString(5,ricevimento.getData());
    preparedStatement.setInt(6,ricevimento.getIdProfessore());
    preparedStatement.setInt(7, ricevimento.getIdRicevimento());
    preparedStatement.executeUpdate();
    conn.commit();
    preparedStatement.close();
    conn.close();
    return true;
}catch(Exception e) {
    System.err.println("Got an exception! ");
    System.err.println(e.getMessage());
    conn.close();
    return false;
}
    }

    public Ricevimento visualizzaRicevimento( String orarioInizio, String orarioFine, String dataR)throws SQLException{


        Connection conn=null;
        PreparedStatement preparedStatement =null;
try {
    conn = DriverManagerConnectionPool.getConnection();

    preparedStatement = conn.prepareStatement("select * from ricevimento r where r.orarioInizio=? and r.orarioFine=? and r.data=?");
    preparedStatement.setString(1, orarioInizio);
    preparedStatement.setString(2, orarioFine);
    preparedStatement.setString(3, dataR);

    ResultSet rs=preparedStatement.executeQuery();
    Ricevimento r=new Ricevimento();
    while(rs.next()){
        r.setIdRicevimento(rs.getInt(1));
        r.setOrarioInizio(rs.getString(2));
        r.setOrarioFine(rs.getString(3));
        r.setLuogo(rs.getString(4));
        r.setData(rs.getString(5));
        r.setIdProfessore(rs.getInt(6));
    }
    return r;


}catch(Exception e) {
    System.err.println("Got an exception! ");
    System.err.println(e.getMessage());
    return null;
}

    }

    public boolean registraPresenza(int idStudente)throws SQLException{

        Connection conn=null;
        PreparedStatement preparedStatement=null;
try {
    conn = DriverManagerConnectionPool.getConnection();
    preparedStatement = conn.prepareStatement("update prenotazione  set prenotazione.presenza=1 where prenotazione.idStudente=?");

    preparedStatement.setInt(1, idStudente);
    preparedStatement.executeUpdate();
    conn.commit();
    return true;
}catch (Exception e){
    System.err.println("Got an exception! ");
    System.err.println(e.getMessage());

        return false;




    }}

    public boolean registraAssenza(int idStudente)throws SQLException{

        Connection conn=null;
        PreparedStatement preparedStatement=null;

        conn=DriverManagerConnectionPool.getConnection();

        preparedStatement=conn.prepareStatement("update studente set numAssenza=numAssenza+1 where idStudente=?");
        preparedStatement.setInt(1,idStudente);
        preparedStatement.executeUpdate();
        conn.commit();
        preparedStatement.close();
        conn.close();
        return true;



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


            }
        } catch (Exception e) {





            e.printStackTrace();
            return null;
        }
        return students;


    }


}
