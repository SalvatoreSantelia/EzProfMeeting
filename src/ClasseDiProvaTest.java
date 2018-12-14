import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClasseDiProvaTest {

    @Test
public void testaVerify(){

    String diocane=ClasseDiProva.verify("HappyMessage");
    Assert.assertEquals("HappyMessage",diocane);
}

}