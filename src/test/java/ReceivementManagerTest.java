import com.sun.imageio.spi.RAFImageInputStreamSpi;
import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;
import myJava.model.professore.ReceivementManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

//import static org.mockito.Mockito.*;


public class ReceivementManagerTest {
private Ricevimento dammiRicevimento(){
    return    new Ricevimento(5, "11:12:38", "11:31:40", "stecca F", "2018-12-12", 1);
}
private Ricevimento dammiRicevimentoModificato(){

    return    new Ricevimento(3, "11:12:38", "11:31:40", "stecca F", "2018-12-12", 2);
}
    @Test
    public void testCreaRicevimento() throws SQLException {

        ReceivementManager receivementManager = new ReceivementManager();

        Assert.assertEquals(true, receivementManager.creaRicevimento(dammiRicevimento()));

    }


    @Test
    public void testEliminRicevimento() throws SQLException {

    ReceivementManager rm=new ReceivementManager();
    Assert.assertEquals(true,rm.eliminaRicevimento(dammiRicevimento()));


    }
    @Test
public void testModificaRicevimento() throws SQLException{
    ReceivementManager r=new ReceivementManager();
    Assert.assertEquals(true,r.modificaRicevimento(dammiRicevimentoModificato()));


}
@Test

    public void testVisualizzaRicevimento() throws SQLException{

    ReceivementManager receivementManager=new ReceivementManager();
    Assert.assertEquals(4,receivementManager.visualizzaRicevimento("11:12:38", "11:31:40","2018-12-12").getIdRicevimento());


}
@Test
    public void testRegistraPresenza()throws SQLException{

    ReceivementManager receivementManager=new ReceivementManager();
    Assert.assertEquals(true,receivementManager.registraPresenza(1));


}
@Test
    public void testRegistraAssenza()throws SQLException{

    ReceivementManager receivementManager=new ReceivementManager();
    
    Assert.assertEquals(true,receivementManager.registraAssenza(1));
}

@Test
public void testVisualizzaStudenti()throws SQLException{
    List<Studente> studenteList =new ArrayList<>();
    Ricevimento ricevimento=new Ricevimento(2,"","","","",1);
    Studente studente=new Studente(1,"","","","","",1);
    studenteList.add(studente);
    ReceivementManager rm=new ReceivementManager();
    Assert.assertEquals(studenteList.get(0).getIdStudente(),rm.visualizzaStudenti(ricevimento).get(0).getIdStudente());

}
@Test
    public void testGetRicevimentoByID()throws  SQLException{
    Ricevimento ricevimento =new Ricevimento(2,"12:00","12:30","stecca F","2019-02-16",2);
    ReceivementManager rm=new ReceivementManager();
    Assert.assertEquals(rm.getRicevimentoById(2).getIdRicevimento(),ricevimento.getIdRicevimento());
    Assert.assertEquals(rm.getRicevimentoById(2).getIdProfessore(),ricevimento.getIdProfessore());
    Assert.assertEquals(rm.getRicevimentoById(2).getData(),ricevimento.getData());
    Assert.assertEquals(rm.getRicevimentoById(2).getLuogo(),ricevimento.getLuogo());
    Assert.assertEquals(rm.getRicevimentoById(2).getOrarioFine(),ricevimento.getOrarioFine());
    Assert.assertEquals(rm.getRicevimentoById(2).getOrarioInizio(),ricevimento.getOrarioInizio());
}


}