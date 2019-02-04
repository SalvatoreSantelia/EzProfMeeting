import myJava.model.beans.Messaggio;
import myJava.model.beans.Studente;
import myJava.model.general.AccessManager;
import myJava.model.general.MessageManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageManagerTest {
    MessageManager mm;
  @Before
  public void istanzia(){

      mm=new MessageManager();

  }
    @Test
    public void testGetStudentiContatti()throws SQLException{
        List<Studente> listaStudenti=new ArrayList<Studente>();
        Studente studente1=new Studente(1,"Salvatore","Santelia","0512104001","s.santelia1@studenti.unisa.it","3334455678",5),
               studente2=new Studente(2,"Rocco","Aliberti","0512104627","r.aliberti18@studenti.unisa.it","3334455678",1);

        listaStudenti.add(studente1);
        listaStudenti.add(studente2);

        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getIdStudente(),listaStudenti.get(1).getIdStudente());


        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getIdStudente(),listaStudenti.get(0).getIdStudente());
        Assert.assertNull(mm.getStudentiContattati(0));
        Assert.assertNull(mm.getStudentiContattati(48));

    }
/**@Test
public void testGetLastDataMessaggio(){

    Messaggio messaggio=new Messaggio("",2,t,"ciao",1,1,"");
    MessageManager mm=new MessageManager();

    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getIdMessaggio(),messaggio.getIdMessaggio());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getDataMessaggio(),messaggio.getDataMessaggio());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getTestoMessaggio(),messaggio.getTestoMessaggio());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getIdProfessore(),messaggio.getIdStudente());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getIdStudente(),messaggio.getIdStudente());
}
**/
@Test
public void testInviaMessaggio(){
MessageManager mm=new MessageManager();
    Assert.assertFalse(mm.inviaMessaggio(0,1,"",""));
    Assert.assertFalse(mm.inviaMessaggio(1,0,"",""));
    Assert.assertFalse(mm.inviaMessaggio(1,1,"","albero"));
    Assert.assertFalse(mm.inviaMessaggio(1,1,"albero",""));
    Assert.assertTrue(mm.inviaMessaggio(1,1,"CIAOPROF","studente"));
    Assert.assertFalse(mm.inviaMessaggio(3213,213,"",""));







}
@Test
public void testGetLastDataMessaggio(){
    MessageManager mm=new MessageManager();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    SimpleDateFormat formatterr = new SimpleDateFormat("HH:mm:ss");
    Time timestampOra=new Time(System.currentTimeMillis());


    Messaggio m=new Messaggio("studente",2,timestampOra,date,"CIAOPROF",1,1);

    Assert.assertEquals(m.getIdMessaggio(),mm.getLastDataMessaggio(1,1).getIdMessaggio());
    Assert.assertNull(mm.getLastDataMessaggio(0,0));
   Assert.assertNull(mm.getLastDataMessaggio(31212312,23131));



}

@Test

public void getArrayListMessaggio(){

    ArrayList<Messaggio> listaMess =new ArrayList<Messaggio>();

    listaMess.add(new Messaggio("",2,new java.sql.Time(System.currentTimeMillis()),new Date(),"",1,1));
    listaMess.add(new Messaggio("",9,new java.sql.Time(System.currentTimeMillis()),new Date(),"",1,1));

    Assert.assertEquals(listaMess.get(1).getIdMessaggio(),mm.getArrayListMessaggio(1,1).get(1).getIdMessaggio());
    Assert.assertEquals(listaMess.get(0).getIdMessaggio(),mm.getArrayListMessaggio(1,1).get(0).getIdMessaggio());
    Assert.assertNull(mm.getArrayListMessaggio(0,1));
    Assert.assertNull(mm.getArrayListMessaggio(323123,13122));
}

}
