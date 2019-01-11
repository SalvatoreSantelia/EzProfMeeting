package myJava.model.studente;

import myJava.model.beans.Prenotazione;
import myJava.model.general.DataManager;
import org.junit.Assert;
import org.junit.Test;

import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BookingManagerTest {

    @Test
    public void testVisualizzaStudenti() throws ParseException, SQLException {

        List<Studente> students = new ArrayList<>();



        Ricevimento ricevimento=new Ricevimento(01,"15:00:00","16:00:00","luogo","2019-01-08",12);

        Prenotazione p=new Prenotazione(11,null,"motivazione","15:40:50",1,01,true);
        DataManager dm=new DataManager();
        dm.inserisciPrenotazione(p);
        Studente stud=new Studente(1,"Salvatore","Santelia","0512104519","s.santelia1@studenti.unisa.it","1234",0);

        students.add(stud);
        Assert.assertEquals(dm.visualizzaStudenti(ricevimento),students);

    }


}