package ccir2020MV;

import ccir2020MV.exceptions.InvalidFormatException;
import ccir2020MV.model.base.Activity;
import ccir2020MV.model.base.Contact;
import ccir2020MV.model.repository.classes.RepositoryActivityFile;
import ccir2020MV.model.repository.classes.RepositoryContactFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TopDownIntegration {
    @Test
    public void testA()  {
        RepositoryContactFile repositoryContactFile=null;
        String message="";
        try {
            repositoryContactFile=new RepositoryContactFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            repositoryContactFile.adaugContact("Nume","address","0756745830");
        } catch (InvalidFormatException e) {
          Assert.fail();
        }
        Assert.assertEquals("",message);

    }
    @Test
    public void testAIntB(){
        boolean intA=false,intB;
        RepositoryContactFile repositoryContactFile=null;
        RepositoryActivityFile repositoryActivityFile=null;
        String message="";
        try {
            repositoryContactFile=new RepositoryContactFile();
            repositoryActivityFile=new RepositoryActivityFile(new RepositoryContactFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int len=repositoryContactFile.getContacts().size();
        try {
            repositoryContactFile.adaugContact("Nume","address","0756745830");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        if(repositoryContactFile.getContacts().size()==len+1)
            intA=true;
        Calendar c=Calendar.getInstance();
        c.set(2012, Calendar.JANUARY,12,12,12,0);
        Date start=c.getTime();
        c.set(2012,Calendar.JANUARY,14,13,10,0);
        Date end=c.getTime();
        List<Contact> cl= null;
        Activity a=new Activity("Camelia", start, end,cl,"descr" );
        intB=repositoryActivityFile.addActivity(a);
        Assert.assertTrue(intA&&intB);
    }
    @Test
    public void IntegrationABC(){
        boolean intA=false,intB=false,intC=false;
        RepositoryContactFile repositoryContactFile=null;
        RepositoryActivityFile repositoryActivityFile=null;
        String m="";
        try {
            repositoryContactFile=new RepositoryContactFile();
            repositoryActivityFile=new RepositoryActivityFile(new RepositoryContactFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int len=repositoryContactFile.getContacts().size();
        try {
            repositoryContactFile.adaugContact("Nume","address","0756745830");
        } catch (InvalidFormatException e) {

            Assert.fail();
        }
        if(repositoryContactFile.getContacts().size()==len+1)
            intA=true;
        Calendar c=Calendar.getInstance();
        c.set(2012, Calendar.JANUARY,12,12,12,0);
        Date start=c.getTime();
        c.set(2012,Calendar.JANUARY,14,13,10,0);
        Date end=c.getTime();
        List<Contact> cl= null;
        Activity a=new Activity("Camelia", start, end,cl,"descr" );
        intB=repositoryActivityFile.addActivity(a);

        c.set(2012,Calendar.JANUARY,14);
        Date d=c.getTime();
        List<Activity> result1=repositoryActivityFile.activitiesOnDate("Camelia",d);
        if(1==result1.size())
            intC=true;
        Assert.assertTrue(intA&&intB&&intC);
    }
}
