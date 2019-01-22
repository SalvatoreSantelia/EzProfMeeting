package myJava.model.professore;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Ricevimento;
import java.sql.*;

public class ReceivementManager {


    public boolean creaRicevimento(Ricevimento ricevimento)throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "insert into " + "fit.ordine"
                + " (idRicevimento,orarioInizio,orarioFine,luogo,dataR,idProfessore) values (?, ?, ?, ?, ?, ?)";

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


    public boolean eliminaRicevimento(Ricevimento ricevimento)throws SQLException{

        Connection conn;

        conn=DriverManagerConnectionPool.getConnection();

        PreparedStatement preparedStatement= conn.prepareStatement("delete from ricevimento where idRicevimento=?");
preparedStatement.setInt(1,ricevimento.getIdRicevimento());
        preparedStatement.execute();

        conn.close();
        return true;


    }

    public boolean modificaRicevimento(Ricevimento ricevimento)throws SQLException{


        Connection conn;

        conn=DriverManagerConnectionPool.getConnection();

        PreparedStatement preparedStatement= conn.prepareStatement("update ricevimento where idRicevimento=?");
        preparedStatement.setInt(1,ricevimento.getIdRicevimento());
        preparedStatement.executeUpdate();

        conn.close();
        return true;

    }

    public Ricevimento visualizzaRicevimento( Time orarioInizio, Time orarioFine, Date dataR)throws SQLException{


        Connection conn=null;
        PreparedStatement preparedStatement =null;
try {
    conn = DriverManagerConnectionPool.getConnection();

    preparedStatement = conn.prepareStatement("select * from ricevimento r where r.orarioInizio=? and r.orarioFine=? and r.dataR=?");
    preparedStatement.setTime(1, orarioInizio);
    preparedStatement.setTime(2, orarioFine);
    preparedStatement.setDate(3, dataR);

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


}finally {
    try {
        if (preparedStatement != null)
            preparedStatement.close();
    } finally {
        DriverManagerConnectionPool.releaseConnection(conn);

    }
}

    }

    public boolean registraPresenza(int idStudente)throws SQLException{

        Connection conn=null;
        PreparedStatement preparedStatement=null;

        conn=DriverManagerConnectionPool.getConnection();
        preparedStatement=conn.prepareStatement("update prenotazione  set prenotazione.presenza=1 where prenotazione.idStudente=?");

        preparedStatement.setInt(1,idStudente);
        preparedStatement.executeUpdate();

        conn.close();
        return true;




    }

    public boolean registraAssenza(int idStudente)throws SQLException{

        Connection conn=null;
        PreparedStatement preparedStatement=null;

        conn=DriverManagerConnectionPool.getConnection();
        preparedStatement=conn.prepareStatement("update prenotazione  set prenotazione.presenza=0 where prenotazione.idStudente=?");
        preparedStatement.setInt(1,idStudente);
        preparedStatement.executeUpdate();

        preparedStatement=conn.prepareStatement("update studente set studente.numAssenza where studente.idStudente=?");
        preparedStatement.setInt(1,idStudente);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conn.close();
        return true;



    }


}
