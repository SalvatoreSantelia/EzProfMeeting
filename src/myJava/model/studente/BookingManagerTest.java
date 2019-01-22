package myJava.model.studente;

import myJava.model.beans.Prenotazione;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookingManagerTest {
    @Mock
    private Ricevimento mockedRicevimento;

    @Mock
    private Studente mockedStudente;

    @Test
    public void testInserisciPrenotazione()throws SQLException {


     mockedRicevimento=mock(Ricevimento.class);
     mockedStudente=mock(Studente.class);
     when(mockedRicevimento.getIdRicevimento()).thenReturn(2);
     when(mockedStudente.getIdStudente()).thenReturn(1);
     Prenotazione prenotazione=new Prenotazione(4,"Rocco Aliberti","nesuna","11:12:36",mockedRicevimento.getIdRicevimento(),mockedStudente.getIdStudente(),false);

     BookingManager bm=new BookingManager();
     Assert.assertEquals(true,bm.inserisciPrenotazione(prenotazione));
    }


@Test
public void testVisualizzaPrenotazioni() throws SQLException{
   List<Prenotazione> listaPrenotazione=new ArrayList<Prenotazione>();
   Prenotazione p=new Prenotazione(1,"Salvatore Santelia","Sono Stupido","11:59:59",1,1,true);
   listaPrenotazione.add(p);
   BookingManager bm=new BookingManager();
   Assert.assertEquals(listaPrenotazione.get(0).getIdPrenotazione(),bm.visualizzaPrenotazioni(1).get(0).getIdPrenotazione());


}

@Test
    public void testEliminaPrenotazione()throws SQLException{
    Prenotazione p=new Prenotazione(4,"Salvatore Santelia","Sono Stupido","11:59:59",1,1,true);
    BookingManager bm=new BookingManager();
    Assert.assertEquals(true,bm.eliminaPrenotazione(p));


}


}