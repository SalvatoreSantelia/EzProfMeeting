import myJava.model.beans.User;
import myJava.model.general.AccessManager;
import myJava.model.general.MessageManager;
import myJava.model.professore.ReceivementManager;
import myJava.model.studente.BookingManager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class DataManagerTest {
    @Mock
    private AccessManager mockedAccessManager;
    private MessageManager mockedMessageManager;
    private BookingManager mckedBookingManager;
    private ReceivementManager mockedReceivementManager;

    @Test     
    public void testDoLogin()throws SQLException {
        mockedAccessManager=mock(AccessManager.class);
        User utente=new User("miaemail","miapassword","studente");
        when(mockedAccessManager.doLogin("miaemail","miapassword")).thenReturn(new User("miaemail","miapassword","studente"));
       // Assert.assertEquals();
    }

}
