import myJava.model.beans.Prenotazione;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;
import myJava.model.studente.BookingManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import javax.persistence.SqlResultSetMapping;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookingManagerTest {

    public BookingManager creaBooking() {
        return new BookingManager();
    }
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
     Prenotazione pNulla=new Prenotazione();
     BookingManager bm=new BookingManager();
     Assert.assertEquals(true,bm.inserisciPrenotazione(prenotazione));
     Assert.assertFalse(bm.inserisciPrenotazione(pNulla));


    }

@Test
public void testVisualizzaPrenotazioni() throws SQLException{
   List<Prenotazione> listaPrenotazione=new ArrayList<Prenotazione>();
   Prenotazione p=new Prenotazione(1,"Salvatore Santelia","Sono Stupido","11:59:59",2,1,true);
   listaPrenotazione.add(p);
   BookingManager bm=new BookingManager();

  Assert.assertEquals(listaPrenotazione.get(0).getIdPrenotazione(),bm.visualizzaPrenotazioni(1).get(0).getIdPrenotazione());
   Assert.assertNull(bm.visualizzaPrenotazioni(0));
   Assert.assertNull(bm.visualizzaPrenotazioni(12423));

}

@Test
    public void testEliminaPrenotazione()throws SQLException{
    Prenotazione p=new Prenotazione(4,"Salvatore Santelia","Sono Stupido","11:59:59",1,1,true);
    BookingManager bm=new BookingManager();
    Assert.assertEquals(true,bm.eliminaPrenotazione(p));
    Assert.assertFalse(bm.eliminaPrenotazione(new Prenotazione()));
}

@Test
    public void testCheckMotivoRicevimento() throws SQLException,NoSuchMethodException, InvocationTargetException,IllegalAccessException {

        BookingManager bm=creaBooking();

    Method method=bm.getClass().getDeclaredMethod("checkMotivoRicevimento", String.class);
    method.setAccessible(true);

    Assert.assertTrue((boolean)method.invoke(bm,"Prove Intercorso"));
  Assert.assertFalse((boolean)method.invoke(bm,""));
    Assert.assertFalse((boolean)method.invoke(bm,"\n" +
            "dhasbdhasbdhjabdjahdahjdhasdaahdbajsdvbadasddsaddasdadadadadadadaadadadadjdvbajdbajdbajdbddsdsdsfsfsdfsfdsfsdfsajhbdahjdahjd\n"));

}

@Test
    public void testCheckNomiAltriStudenti()throws SQLException,NoSuchMethodException, InvocationTargetException,IllegalAccessException{

      BookingManager bm=creaBooking();

    Method method=bm.getClass().getDeclaredMethod("checkNomiAltriStudenti", String.class);
    method.setAccessible(true);
    String string=null;
    for(int i=0;i< 67000;i++)
    {
        string=string+"a";
    }
    Assert.assertFalse((boolean) method.invoke(bm, string));
    Assert.assertTrue((boolean) method.invoke(bm,"Santelia Salvatore,Postiglione Luca"));
}
}