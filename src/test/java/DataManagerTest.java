import myJava.model.beans.*;
import myJava.model.general.AccessManager;
import myJava.model.general.DataManager;
import myJava.model.general.MessageManager;
import myJava.model.professore.ReceivementManager;
import myJava.model.studente.BookingManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

//TODO testing di integrazione
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataManagerTest {
    BookingManager bm;
    ReceivementManager rm;
    MessageManager mm;
    AccessManager am;
    DataManager dm;
@Before
public void instanziaOggetti(){
    rm=new ReceivementManager();
    mm=new MessageManager();
    bm=new BookingManager();
    am=new AccessManager();
    dm=new DataManager();
}
    @Test     
    public void testDoLogin()throws SQLException {
    String mail,password;
        User utente =new User("mia@email.it","miapassword2","");
        Assert.assertNull(dm.doLogin("","miapassword")); //mail= ""
        Assert.assertNull(dm.doLogin("miaemail",""));  //password= ""
        Assert.assertEquals(utente.getEmail(),dm.doLogin("mia@email.it","miapassword2").getEmail());
    }
    @Test
    public void testCreaRicevimento()throws SQLException, ParseException {
        Ricevimento ricevimento=null;
        Ricevimento ricevimento1= new Ricevimento(6, "12:21", "14:37", "stecca F", "2018-12-12", 5, 1, 1);
        Assert.assertFalse(dm.creaRicevimento(ricevimento));
        Assert.assertTrue(dm.creaRicevimento(ricevimento1));
    }
    @Test
    public void testEliminaRicevimento() throws SQLException{
        Ricevimento ricevimento=null;
        Ricevimento ricevimento1= new Ricevimento(6, "12:21", "14:37", "stecca F", "2018-12-12", 5, 1, 1);
        Assert.assertTrue(dm.eliminaRicevimento(ricevimento1));
        Assert.assertFalse(dm.eliminaRicevimento(ricevimento));

    }
    @Test
    public void testModificaRicevimento() throws SQLException{
        Ricevimento ricevimento=null;
        Assert.assertFalse(dm.modificaRicevimento(ricevimento));
        Assert.assertTrue(dm.modificaRicevimento(new Ricevimento(3, "11:12:38", "11:31:40", "stecca F", "2018-12-12", 5, 1, 2)));


    }
    @Test
    public void testVisualizzaRicevimento() throws SQLException,ParseException{
    Ricevimento ricevimento=new Ricevimento();
        Assert.assertEquals(3,dm.visualizzaRicevimento("11:12:38", "11:31:40","2018-12-12").getIdRicevimento());
    }
    @Test
    public void testCreaPrenotazione() throws SQLException{
        Prenotazione prenotazione=new Prenotazione(5,"","nesuna","11:12:36",2,2,false);
        Assert.assertTrue(dm.inserisciPrenotazione(prenotazione));

    }
    @Test
    public void testVisualizzaPrenotazioni() throws SQLException{
        List<Prenotazione> listaPrenotazione=new ArrayList<Prenotazione>();
        Prenotazione p=new Prenotazione(1,"Salvatore Santelia","Sono Stupido","11:59:59",2,1,true);
        listaPrenotazione.add(p);
        Assert.assertEquals(listaPrenotazione.get(0).getIdPrenotazione(),dm.visualizzaPrenotazioni(1).get(0).getIdPrenotazione());
    }
    @Test
    public void testInviaMessaggio() throws SQLException{

        Assert.assertTrue(dm.inviaMessaggio(1,1,"CIAOPROF","studente"));

    }
    @Test
    public void testEliminaPrenotazione() throws SQLException{
        Prenotazione prenotazione=new Prenotazione(5,"","nesuna","11:12:36",2,2,false);
        Assert.assertTrue(dm.eliminaPrenotazione(prenotazione));

    }
    @Test
    public void testRegistraPresenza()throws SQLException{
    String assenza= "assente",presenza="presente";
    Assert.assertTrue(dm.registraPresenza(assenza,1));
    Assert.assertTrue(dm.registraPresenza(presenza,1));
    Assert.assertFalse(dm.registraPresenza("",1));

    }
    @Test
    public void testVisualizzaProfessori()throws SQLException{

    Assert.assertEquals("Ferrucci",dm.visualizzaProfessori().get(0).getCognomeProfessore());
    }
    @Test
    public void testVisualizzaStudenti()throws SQLException{

        List<Studente> studenteList =new ArrayList<>();
        Ricevimento ricevimento=new Ricevimento(2,"","","","", 0, 1, 1);
        Studente studente=new Studente(1,"","","","","",1);
        studenteList.add(studente);
        ReceivementManager rm=new ReceivementManager();

        Assert.assertEquals(studenteList.get(0).getIdStudente(),dm.visualizzaStudenti(ricevimento).get(0).getIdStudente());
    }
    @Test
    public void testGetRicevimentoByID()throws SQLException{
        Ricevimento ricevimento =new Ricevimento(2,"12:00","12:30","stecca F","2019-02-16",5, 10, 2);
        Assert.assertEquals(dm.getRicevimentoById(2).getIdRicevimento(),ricevimento.getIdRicevimento());
        Assert.assertEquals(dm.getRicevimentoById(2).getIdProfessore(),ricevimento.getIdProfessore());
        Assert.assertEquals(dm.getRicevimentoById(2).getData(),ricevimento.getData());
        Assert.assertEquals(dm.getRicevimentoById(2).getLuogo(),ricevimento.getLuogo());
        Assert.assertEquals(dm.getRicevimentoById(2).getOrarioFine(),ricevimento.getOrarioFine());
        Assert.assertEquals(dm.getRicevimentoById(2).getOrarioInizio(),ricevimento.getOrarioInizio());
    }
    @Test
    public void testGetProfById()throws SQLException{

    Assert.assertEquals("Carmine",dm.getProfById(2).getNomeProfessore());
        Assert.assertEquals("Gravino",dm.getProfById(2).getCognomeProfessore());
    Assert.assertEquals(null,dm.getProfById(18));

    }
    @Test
    public void testGetStudenteById() throws SQLException{

        Assert.assertEquals("Rocco",dm.getStudenteById(2).getNomeStudente());
        Assert.assertEquals("Aliberti",dm.getStudenteById(2).getCognomeStudente());
        Assert.assertEquals(null,dm.getStudenteById(15));


    }
    @Test
    public void testGetLastDataMessaggio() throws SQLException{


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        SimpleDateFormat formatterr = new SimpleDateFormat("HH:mm:ss");
        Time timestampOra=new Time(System.currentTimeMillis());


        Messaggio m=new Messaggio("studente",2,timestampOra,date,"CIAOPROF",1,1);

        Assert.assertEquals(m.getIdMessaggio(),dm.getLastDataMessaggio(1,1).getIdMessaggio());
        Assert.assertNull(dm.getLastDataMessaggio(0,0));
        Assert.assertNull(dm.getLastDataMessaggio(31212312,23131));


    }
    @Test
    public void testGetProfessoreByEmail() throws SQLException{

    Assert.assertEquals(1,dm.getProfessoreByEmail("fferrucci@unisa.it").getIdProfessore());

    }
    @Test
    public void testGetStudenteByEmail() throws SQLException{

    Assert.assertEquals(1,dm.getStudenteByEmail("s.santelia1@studenti.unisa.it").getIdStudente());

    }
    @Test
    public void testGetRicevimentoByProf() throws SQLException{
        Assert.assertEquals(1,dm.getRicevimentiByProf(new Professore(1,"","","","","")).get(0).getIdRicevimento());

    }
    @Test
    public void testGetStudentiContattati() throws SQLException{
        List<Studente> listaStudenti=new ArrayList<Studente>();
        Studente studente1=new Studente(1,"Salvatore","Santelia","0512104001","s.santelia1@studenti.unisa.it","3334455678",5),
                studente2=new Studente(2,"Rocco","Aliberti","0512104627","r.aliberti18@studenti.unisa.it","3334455678",1);

        listaStudenti.add(studente1);
        listaStudenti.add(studente2);

        Assert.assertEquals(dm.getStudentiContattati(1).get(0).getIdStudente(),listaStudenti.get(1).getIdStudente());


        Assert.assertEquals(dm.getStudentiContattati(1).get(1).getIdStudente(),listaStudenti.get(0).getIdStudente());
    }
    @Test
    public void testGetPrenotazioneById() throws SQLException{


        Prenotazione prenotazione=new Prenotazione(1,"","","",2,1,false);
        Assert.assertEquals(null,dm.getPrenotazioneById(0));
        Assert.assertEquals(prenotazione.getIdPrenotazione(),dm.getPrenotazioneById(1).getIdPrenotazione());
    }
    @Test
    public void testGetArrayListMessaggio() throws SQLException{

    Assert.assertEquals(2,dm.getArrayListMessaggio(1,1).get(0).getIdMessaggio());

    }

}
