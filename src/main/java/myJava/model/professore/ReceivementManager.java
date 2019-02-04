package myJava.model.professore;

import myJava.control.connection.DriverManagerConnectionPool;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;


import java.security.spec.ECField;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;


public class ReceivementManager {


    public boolean creaRicevimento(Ricevimento ricevimento)throws SQLException ,ParseException{


        if(ricevimento.getIdRicevimento()==0||!checkData(ricevimento.getData())||!checkOrario(ricevimento.getOrarioFine())||!checkOrario(ricevimento.getOrarioInizio())||!checkLuogo(ricevimento.getLuogo())){

            return false;
        }
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
            if(preparedStatement.executeUpdate()==0){

                throw new Exception();
            }

            connection.commit();

            return true;
        } catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }


    }


    public boolean eliminaRicevimento(Ricevimento ricevimento)throws SQLException {

if(ricevimento.getIdRicevimento()==0){
    return false;
}
        Connection conn;
        conn = DriverManagerConnectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from ricevimento where idRicevimento=?");
            preparedStatement.setInt(1, ricevimento.getIdRicevimento());
            if(preparedStatement.executeUpdate()==0){//non è stato cancellato alcun ricevimento
                throw new Exception();

            }
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
    if(preparedStatement.executeUpdate()==0){
        throw new Exception();
    }
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

    public Ricevimento visualizzaRicevimento( String orarioInizio, String orarioFine, String dataR)throws SQLException,ParseException{

if(!checkOrario(orarioInizio)||!checkOrario(orarioFine)||!checkData(dataR)){

    return null;
}
        Connection conn=null;
        PreparedStatement preparedStatement =null;
try {
    conn = DriverManagerConnectionPool.getConnection();

    preparedStatement = conn.prepareStatement("select * from ricevimento r where r.orarioInizio=? and r.orarioFine=? and r.data=?");
    preparedStatement.setString(1, orarioInizio);
    preparedStatement.setString(2, orarioFine);
    preparedStatement.setString(3, dataR);

    ResultSet rs=preparedStatement.executeQuery();
    if(rs.next()){
        throw new Exception();

    }
    rs.previous();
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
if(idStudente==0){

    return false;
}
        Connection conn=null;
        PreparedStatement preparedStatement=null;
try {
    conn = DriverManagerConnectionPool.getConnection();
    preparedStatement = conn.prepareStatement("update prenotazione  set prenotazione.presenza=1 where prenotazione.idStudente=?");

    preparedStatement.setInt(1, idStudente);
   if(preparedStatement.executeUpdate()==0){

       throw new Exception();
   }
    conn.commit();
    return true;
}catch (Exception e){
    System.err.println("Got an exception! ");
    System.err.println(e.getMessage());

        return false;




    }}

    public boolean registraAssenza(int idStudente)throws SQLException{
        if(idStudente==0){

            return false;
        }
        Connection conn=null;
        PreparedStatement preparedStatement=null;
try {
    conn = DriverManagerConnectionPool.getConnection();

    preparedStatement = conn.prepareStatement("update studente set numAssenza=numAssenza+1 where idStudente=?");
    preparedStatement.setInt(1, idStudente);
    if (preparedStatement.executeUpdate() == 0) {

        throw new Exception();
    }
    conn.commit();

    return true;
}catch (Exception e){
    System.err.println("Got an exception! ");
    System.err.println(e.getMessage());

    return false;

}




    }
    public List<Studente> visualizzaStudenti(Ricevimento ricevimento) throws SQLException{
if(ricevimento == null || ricevimento.getIdRicevimento()==0){


    return null;

}
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

    //get Ricevimento byId
    public Ricevimento getRicevimentoById(int idRicevimento){
        if(idRicevimento==0){

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
                ricevimento.setIdProfessore(rs.getInt("idProfessore"));
            }
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return ricevimento;
    }

    private boolean checkData(String data){
        if(data.matches("\\d{4}-\\d{2}-\\d{2}")){

            return true;
        }
        else return false;
    }

    private boolean checkOrario(String orario)throws ParseException {
        if(orario.matches("\\d{2}:\\d{2}:\\d{2}")){
                String dataFittizzia="2019-12-12 ";
       String dataCompleta= dataFittizzia + orario;
SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 Date parsedDate=format.parse(dataCompleta);
 Timestamp timeStamp=new java.sql.Timestamp( parsedDate.getTime());
 parsedDate=format.parse(dataFittizzia+"20:00:00");
 Timestamp orarioLimiteInferiore=new java.sql.Timestamp( parsedDate.getTime());
 parsedDate=format.parse(dataFittizzia+"09:00:00");
 Timestamp orarioLimiteSuperiore=new Timestamp(parsedDate.getTime());

 if(timeStamp.before(orarioLimiteInferiore)&&timeStamp.after(orarioLimiteSuperiore)){
            return true;

        }
 return false;
        }
else
    return false;
    }

    private boolean checkLuogo(String luogo){

        if(!luogo.equals("")){

            return true;
        }
        return false;
    }

}
