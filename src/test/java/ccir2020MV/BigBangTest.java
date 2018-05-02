package ccir2020MV;

import ccir2020MV.exceptions.InvalidFormatException;
import ccir2020MV.model.repository.classes.RepositoryContactFile;
import org.junit.Assert;
import org.junit.Test;

public class BigBangTest {
    @Test
    public void test()  {
       BBTest bbTest=new BBTest();
       WBTest wbTest=new WBTest();
        try
        {
            //testare cerinta i
            bbTest.succesAdd1();
            bbTest.succesAdd2();
            bbTest.testAdress();
            //testare cerinta iii
            bbTest.testCerinta3();
            //testare cerinta ii
            wbTest.testAddInsidePeriod();
            wbTest.testAddAfterPeriod();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
