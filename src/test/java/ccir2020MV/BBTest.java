package ccir2020MV;
import ccir2020MV.exceptions.InvalidFormatException;
import ccir2020MV.model.base.Activity;
import ccir2020MV.model.base.Contact;
import ccir2020MV.model.repository.classes.RepositoryActivityFile;
import ccir2020MV.model.repository.classes.RepositoryContactFile;
import ccir2020MV.model.repository.interfaces.RepositoryActivity;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BBTest {
    @Test
    public void testAdress() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","","0765443453");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid address",message);
    }

    @Test
    public void testName() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("","address","0756434532");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid name",message);
    }

    @Test
    public void testTelefon1() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon2() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","0756");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon3() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","8976547382");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon4() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","0754dg3452");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }
    @Test
    public void testTelefon5() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        String phoneNR="";
        for(int i=0;i<254;i++)
        {
            phoneNR=phoneNR+i%10;

        }
        //System.out.println(phoneNR);
        try {
            repositoryContactFile.adaugContact("Nume","address", phoneNR);
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("Invalid phone number",message);
    }

    @Test
    public void succesAdd1() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","address","0756745839");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("",message);
    }
    @Test
    public void succesAdd2() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("N","address","0756745839");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("",message);
    }
    @Test
    public void succesAdd3() throws Exception {
        RepositoryContactFile repositoryContactFile=new RepositoryContactFile();
        String message="";
        try {
            repositoryContactFile.adaugContact("Nume","a","0756745839");
        }catch (InvalidFormatException e)
        {
            message=e.getCause().getMessage();

        }
        Assert.assertEquals("",message);
    }
    @Test
    public void testCerinta3(){
        RepositoryActivityFile repositoryActivityFile =null;
        try {
            repositoryActivityFile =new RepositoryActivityFile(new RepositoryContactFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar c=Calendar.getInstance();
        c.set(2012,Calendar.JANUARY,12,12,12,0);
        Date start=c.getTime();
        c.set(2012,Calendar.JANUARY,14,13,10,0);
        Date end=c.getTime();
        List<Contact> cl= null;
        Activity a=new Activity("Camelia", start, end,cl,"descr" );
        repositoryActivityFile.addActivity(a);
        //System.out.println(repositoryActivityFile.getActivities().size());
        c.set(2012,Calendar.JANUARY,14,18,12,1);
        start=c.getTime();
        c.set(2012,Calendar.JANUARY,15,16,10,0);
        end=c.getTime();
        c.set(2012,Calendar.JANUARY,14);
        Date d=c.getTime();
        Activity b=new Activity("Camelia", start, end,cl,"descr" );
        repositoryActivityFile.addActivity(b);
        List<Activity> result1=repositoryActivityFile.activitiesOnDate("Camelia",d);
        Assert.assertEquals(2,result1.size());
        c.set(2012,Calendar.JANUARY,16);
        d=c.getTime();
        List<Activity> result2=repositoryActivityFile.activitiesOnDate("Camelia",d);
        Assert.assertEquals(0,result2.size());
    }
}
