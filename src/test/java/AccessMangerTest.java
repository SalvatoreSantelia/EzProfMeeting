import myJava.model.beans.Professore;
import myJava.model.beans.Studente;
import myJava.model.beans.User;
import myJava.model.general.AccessManager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLException;

public class AccessMangerTest {

    @Test
    public void testDoLogin()throws SQLException {
        AccessManager am=new AccessManager();
        User user=new User("miaemail","miapassword","studente");
        Assert.assertEquals(user.getEmail(),am.doLogin("miaemail","miapassword").getEmail());

        Assert.assertEquals(user.getPassword(),am.doLogin("miaemail","miapassword").getPassword());
        Assert.assertEquals(user.getTipo(),am.doLogin("miaemail","miapassword").getTipo());
    }
    @Test
    public void testGetProfessoreByEmail()throws SQLException{
        AccessManager am=new AccessManager();
        Professore professore=new Professore(1,"Filomena","Ferrucci","fferrucci@unisa.it","0819876543","stecca F");
        Assert.assertEquals(professore.getIdProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getIdProfessore());
        Assert.assertEquals(professore.getNomeProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getNomeProfessore());
        Assert.assertEquals(professore.getCognomeProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getCognomeProfessore());
        Assert.assertEquals(professore.getEmailProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getEmailProfessore());
        Assert.assertEquals(professore.getTelefonoProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getTelefonoProfessore());
        Assert.assertEquals(professore.getUfficioProfessore(),am.getProfessoreByEmail("fferrucci@unisa.it").getUfficioProfessore());

    }

    @Test
    public void testGetStudenteByEmail() throws SQLException{
        AccessManager am=new AccessManager();
       Studente stu=new Studente(1,"Salvatore","Santelia","0512104001","s.santelia1@studenti.unisa.it","3334455678",5);
        Assert.assertEquals(stu.getIdStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getIdStudente());
        Assert.assertEquals(stu.getNomeStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getNomeStudente());
        Assert.assertEquals(stu.getCognomeStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getCognomeStudente());
        Assert.assertEquals(stu.getMatricola(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getMatricola());
        Assert.assertEquals(stu.getEmailStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getEmailStudente());
        Assert.assertEquals(stu.getTelefonoStudente(),am.getStudenteByEmail("s.santelia1@studenti.unisa.it").getTelefonoStudente());

    }
}
