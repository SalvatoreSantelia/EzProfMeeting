package myJava.model.general;

import myJava.model.beans.Messaggio;
import myJava.model.beans.Prenotazione;



import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;
import myJava.model.professore.ReceivementManager;
import myJava.model.studente.BookingManager;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.List;

public class DataManager {


    public boolean login(String mail, String password)throws SQLException{
        if(mail==null || mail.equals("") || password==null || password.equals(""))
        return false;
        else return ac.login(mail,password);

    }
    public boolean creaRicevimento(Ricevimento ricevimento)throws SQLException{
        if(ricevimento!=null)
            return  rm.creaRicevimento(ricevimento);
            else
                return false;
    }
    public boolean eliminaRicevimento(Ricevimento ricevimento)throws SQLException{
        if(ricevimento!=null)
            return rm.eliminaRicevimento(ricevimento);
        else return false;
    }
    public boolean modificaRicevimento(Ricevimento ricevimento)throws SQLException{
        if(ricevimento!=null)
           return rm.modificaRicevimento(ricevimento);
           else return false;
    }
    public Ricevimento visualizzaRicevimento(Time oraInizio, Time oraFine, Date data)throws SQLException{


        if(!oraInizio.equals("") && oraInizio!=null && !oraFine.equals("") && oraFine!=null && data!=null )
            return rm.visualizzaRicevimento(oraInizio,oraFine,data);
       else return null;
    }
    public boolean inserisciPrenotazione(Prenotazione prenotazione)throws SQLException{
        if(prenotazione!=null && prenotazione.getIdPrenotazione()!=0 && prenotazione.getIdRicevimento()!=0 && prenotazione.getIdStudente()!=0)
        return m.inserisciPrenotazione(prenotazione);
        else return false;
    }
    public List<Studente> visualizzaStudenti(Ricevimento ricevimento)throws SQLException{

        if(ricevimento!=null)
          return  m.visualizzaStudenti(ricevimento);
        else return  null;
    }
    public List<Prenotazione> visualizzaPrenotazioni (int idStudente) throws SQLException {

        return m.visualizzaPrenotazioni(idStudente);
    }
    public boolean inviaMessaggio(int idMittente,int idDestinatario, String messaggio){
return true;

    }
public Messaggio visualizzaMessagio(int idDestinatario){

        return null;
}

public boolean eliminaPrenotazione(Prenotazione prenotazione)throws SQLException{

        if(prenotazione!=null)
           return m.eliminaPrenotazione(prenotazione);
       else return false;
}

public boolean registraPresenza(String presenzaAssenza,int idStudente)throws SQLException{

        if(presenzaAssenza.equals("assente"))
           return rm.registraAssenza(idStudente);
        else if(presenzaAssenza.equals("presente"))
            return rm.registraPresenza(idStudente);
        else
            return false;
}


    BookingManager m=new BookingManager();
    AccessManager ac=new AccessManager();
    ReceivementManager rm=new ReceivementManager();

}
