import myJava.model.beans.Ricevimento;
import myJava.model.beans.Studente;
import myJava.model.professore.ReceivementManager;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
//import static org.mockito.Mockito.*;
public class ReceivementManagerTest {
private Ricevimento dammiRicevimento(){
    return    new Ricevimento(5, "11:12:38", "11:31:40", "stecca F", "2018-12-12", 1);
}
private Ricevimento dammiRicevimentoModificato(){

    return    new Ricevimento(3, "11:12:38", "11:31:40", "stecca F", "2018-12-12", 2);
}
    @Test
    public void testCreaRicevimento() throws SQLException , ParseException {

        ReceivementManager receivementManager = new ReceivementManager();

        Assert.assertTrue( receivementManager.creaRicevimento(dammiRicevimento()));
        Assert.assertFalse(receivementManager.creaRicevimento(new Ricevimento(6,"122435","21331231","stecca G","2019-11-07",1)));
        Assert.assertFalse(receivementManager.creaRicevimento(new Ricevimento(6,"12:24:35","21:33:12","stecca G","2019-11-07",1)));
        Assert.assertFalse(receivementManager.creaRicevimento(new Ricevimento(6,"12:24:35","21:33:12","stecca G","20191107",1)));
        Assert.assertFalse(receivementManager.creaRicevimento(new Ricevimento(6,"07:24:35","21:33:12","stecca G","2019-11-07",1)));
        Assert.assertFalse(receivementManager.creaRicevimento(new Ricevimento()));
    }


    @Test
    public void testEliminRicevimento() throws SQLException {

    ReceivementManager rm=new ReceivementManager();
    Assert.assertEquals(true,rm.eliminaRicevimento(dammiRicevimento()));
    Assert.assertFalse(rm.eliminaRicevimento(new Ricevimento()));
    Assert.assertFalse(rm.eliminaRicevimento(new Ricevimento(233, "11:12:38", "11:31:40", "stecca F", "2018-12-12", 2)));


    }
    @Test
public void testModificaRicevimento() throws SQLException{
    ReceivementManager r=new ReceivementManager();
    Assert.assertEquals(true,r.modificaRicevimento(dammiRicevimentoModificato()));
    Assert.assertFalse(r.modificaRicevimento(new Ricevimento(233,"11:12:38", "11:31:40", "stecca F", "2018-12-12", 2)));

}
@Test

    public void testVisualizzaRicevimento() throws SQLException,ParseException{

    ReceivementManager receivementManager=new ReceivementManager();
    Assert.assertEquals(4,receivementManager.visualizzaRicevimento("11:12:38", "11:31:40","2018-12-12").getIdRicevimento());
Assert.assertEquals(null,receivementManager.visualizzaRicevimento("112234","312312","2018-12-12"));

}
@Test
    public void testRegistraPresenza()throws SQLException{

    ReceivementManager receivementManager=new ReceivementManager();
    Assert.assertEquals(true,receivementManager.registraPresenza(1));
    Assert.assertFalse(receivementManager.registraPresenza(0));
    Assert.assertFalse(receivementManager.registraPresenza(1122333444));

}
@Test
    public void testRegistraAssenza()throws SQLException{

    ReceivementManager receivementManager=new ReceivementManager();
    
    Assert.assertEquals(true,receivementManager.registraAssenza(1));
    Assert.assertFalse(receivementManager.registraPresenza(0));
    Assert.assertFalse(receivementManager.registraPresenza(1122333444));

}

@Test
public void testVisualizzaStudenti()throws SQLException{
    List<Studente> studenteList =new ArrayList<>();
    Ricevimento ricevimento=new Ricevimento(2,"","","","",1);
    Studente studente=new Studente(1,"","","","","",1);
    studenteList.add(studente);
    ReceivementManager rm=new ReceivementManager();

    Assert.assertEquals(studenteList.get(0).getIdStudente(),rm.visualizzaStudenti(ricevimento).get(0).getIdStudente());

    Assert.assertNull(rm.visualizzaStudenti(new Ricevimento()));
 //   Assert.assertNull(rm.visualizzaStudenti(new Ricevimento(121212313,"","","","",1)));


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
    Assert.assertNull(rm.getRicevimentoById(0));
}
@Test
    public void testCheckOrario()throws NoSuchMethodException,IllegalAccessException, InvocationTargetException {
ReceivementManager rm=new ReceivementManager();
    Method method= rm.getClass().getDeclaredMethod("checkOrario", String.class);
    method.setAccessible(true);

    Assert.assertTrue((boolean)method.invoke(rm,"14:00:00"));
    Assert.assertFalse((boolean)method.invoke(rm,"08:00:00"));
    Assert.assertFalse((boolean)method.invoke(rm,"21:00:00"));
    Assert.assertFalse((boolean)method.invoke(rm,"080000"));



}
@Test
    public void testCheckData() throws NoSuchMethodException,IllegalAccessException,InvocationTargetException{

    ReceivementManager rm=new ReceivementManager();
    Method method= rm.getClass().getDeclaredMethod("checkData", String.class);
    method.setAccessible(true);

    Assert.assertTrue((boolean) method.invoke(rm,"2019-12-12"));
    Assert.assertFalse((boolean)method.invoke(rm,"20191212"));



}

@Test
    public void testCheckLuogo()throws NoSuchMethodException,IllegalAccessException,InvocationTargetException{

    ReceivementManager rm=new ReceivementManager();
    Method method= rm.getClass().getDeclaredMethod("checkLuogo", String.class);
    method.setAccessible(true);

    Assert.assertTrue((boolean) method.invoke(rm,"Luogo"));
    Assert.assertFalse((boolean)method.invoke(rm,""));
}


}