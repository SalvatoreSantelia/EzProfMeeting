import myJava.model.beans.Studente;
import myJava.model.general.MessageManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageManagerTest {
    @Test
    public void testGetStudentiContatti()throws SQLException{
        List<Studente> listaStudenti=new ArrayList<Studente>();
        Studente studente1=new Studente(1,"Salvatore","Santelia","0512104001","s.santelia1@studenti.unisa.it","3334455678",5),
               studente2=new Studente(2,"Rocco","Aliberti","0512104627","r.aliberti18@studenti.unisa.it","3334455678",1);

        listaStudenti.add(studente1);
        listaStudenti.add(studente2);
        MessageManager mm= new MessageManager();
        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getIdStudente(),listaStudenti.get(0).getIdStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getNomeStudente(),listaStudenti.get(0).getNomeStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getCognomeStudente(),listaStudenti.get(0).getCognomeStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getMatricola(),listaStudenti.get(0).getMatricola());
        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getEmailStudente(),listaStudenti.get(0).getEmailStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getTelefonoStudente(),listaStudenti.get(0).getTelefonoStudente());

        Assert.assertEquals(mm.getStudentiContattati(1).get(0).getMatricola(),listaStudenti.get(0).getMatricola());



        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getIdStudente(),listaStudenti.get(1).getIdStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getNomeStudente(),listaStudenti.get(1).getNomeStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getCognomeStudente(),listaStudenti.get(1).getCognomeStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getMatricola(),listaStudenti.get(1).getMatricola());
        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getEmailStudente(),listaStudenti.get(1).getEmailStudente());
        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getTelefonoStudente(),listaStudenti.get(1).getTelefonoStudente());

        Assert.assertEquals(mm.getStudentiContattati(1).get(1).getMatricola(),listaStudenti.get(1).getMatricola());

    }
@Test
public void testGetLastDataMessaggio(){

    /*Messaggio messaggio=new Messaggio(2,"2019-12-12","ciao",1,1);
    MessageManager mm=new MessageManager();

    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getIdMessaggio(),messaggio.getIdMessaggio());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getDataMessaggio(),messaggio.getDataMessaggio());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getTestoMessaggio(),messaggio.getTestoMessaggio());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getIdProfessore(),messaggio.getIdStudente());
    Assert.assertEquals(mm.getLastDataMessaggio(1,1).getIdStudente(),messaggio.getIdStudente());*/
}

    
}
