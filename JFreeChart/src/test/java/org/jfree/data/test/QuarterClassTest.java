package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import org.jfree.data.time.Quarter;
import org.junit.Test;
import org.junit.BeforeClass; // 🔹 Added this to set up timezone before tests run
import org.jfree.data.time.Year;

import  java.lang.String;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class QuarterClassTest {
    Quarter quarter;

    @BeforeClass
    public static void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }

    private void arrange() {
        quarter = new Quarter();
    }
    @Test
    public void testQuarterDefaultCtor(){
        arrange();
        LocalDate now = LocalDate.now();
        int expectedYear = now.getYear();
        int expectedQuarter = (now.getMonthValue()-1)/3+1;
        assertEquals(expectedYear, quarter.getYearValue());
        assertEquals(expectedQuarter, quarter.getQuarter());
    }
    @Test
    public void testQuarterCtor1(){
    	int q=3;
    	int y=2022;
        arrange(q,y);
        assertEquals(y, quarter.getYear().getYear());
        assertEquals(q, quarter.getQuarter());
    }
    @Test
    public void testQuarterCtor2(){
    	int q=1;
    	int y=2021;
    	Year year = new Year(y);
    	arrange(q,y);
    	assertEquals(y, year.getYear());
        assertEquals(q, quarter.getQuarter());
    }
	@Test
    public void testQuarterCtor3()throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date time = sdf.parse("02/01/2020");
    	Quarter quart =new Quarter(time);
    	 arrange(1,2020);
         assertEquals(quarter.getYear(),quart.getYear());
         assertEquals(quarter.getQuarter(),quart.getQuarter());
    }
	@Test
    public void testQuarterCtor4()throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date time = sdf.parse("01/01/2020");
    	Quarter quart =new Quarter(time);
    	 arrange(1,2020);
         assertEquals(quarter.getYear(),quart.getYear());
         assertEquals(quarter.getQuarter(),quart.getQuarter());
    }
    @Test
    public void testQuarterCtor5()throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // 🔹 Ensuring correct timezone
        Date time = sdf.parse("02/01/2022");
    	TimeZone zone = TimeZone.getTimeZone("UTC");
    	Quarter quart =new Quarter(time, Calendar.getInstance(zone));
    	 arrange(1,2022);
         assertEquals(quarter.getYear(),quart.getYear());
         assertEquals(quarter.getQuarter(),quart.getQuarter());
    }
    @Test
    public void testQuarterCtor6()throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        Date time = sdf.parse("01/01/2020");
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Quarter quart = new Quarter(time, Calendar.getInstance(zone));
        arrange(1,2020);
         assertEquals(quarter.getYear(),quart.getYear());
         assertEquals(quarter.getQuarter(),quart.getQuarter());
    }
    @Test
   public void testGetQuarter() {
    	arrange(2,2023);
        assertEquals(2, quarter.getQuarter());
    }
    @Test
   public void testGetYear() {
    	arrange(2,2023);
        assertEquals(2023, quarter.getYear().getYear());
    }
    @Test
    public void testPrevious1() {
     	arrange(1,2023);
     	String S="Q4/2022";
        assertEquals(Quarter.parseQuarter(S), quarter.previous());
         //System.out.println(quarter.previous());
     }
    @Test
    public void testPrevious2() {
     	arrange(4,2023);
     	String S="Q3/2023";
        assertEquals(Quarter.parseQuarter(S), quarter.previous());
         //System.out.println(quarter.previous());
     }
     @Test
    public void testNext1() {
     	arrange(4,2022);
     	String S="Q1/2023";
         assertEquals(Quarter.parseQuarter(S), quarter.next());
        //System.out.println(quarter.next());
     }
     @Test
     public void testNext2() {
      	arrange(3,2022);
      	String S="Q4/2022";
          assertEquals(Quarter.parseQuarter(S), quarter.next());
         //System.out.println(quarter.next());
      }
     @Test
     public void testGetSerialIndex1()
     {
    	 arrange(2,2023);
       	assertEquals(8094,quarter.getSerialIndex());
     }
     @Test
     public void testGetSerialIndex2()
     {
    	 arrange(1,1900);
       	assertEquals(7601,quarter.getSerialIndex());
     }
     @Test
     public void testEquals()
     {     
    	int q=1;
     	int y=2021;
    	arrange(q,y);
    	Quarter quart = new Quarter(q,y);
   	    assertEquals(true,quarter.equals(quart));
     }
     @Test
     public void testToString()
     {
    	 arrange(4,2024);
    	 assertEquals("Q4/2024",quarter.toString());
     }
     @SuppressWarnings("static-access")
	@Test
     public void testParseQuarter()
     {     
    	 arrange(4,2022);
         String S= "Q4/2022";  
     assertEquals(quarter.parseQuarter(S),Quarter.parseQuarter(S));
     } 
     
     @Test
     public void testCompareTo1()
     {
     	arrange(4,2021);
     	Quarter quart = new Quarter(4,2021);
    	assertEquals(0,quarter.compareTo(quart));
        //System.out.println(quarter.compareTo(quart));
     }
     @Test
     public void testCompareTo2()
     {
     	arrange(4,2021);
     	Quarter quart = new Quarter(2,2021);
    	assertEquals(2,quarter.compareTo(quart));
     }
     @Test
     public void testCompareTo3()
     {
     	arrange(2,2021);
     	Quarter quart = new Quarter(4,2021);
    	assertEquals(-2,quarter.compareTo(quart));
     }
     @Test
     public void testHashCode1()
     {
      	arrange(1,1900);
      	Quarter q = new Quarter(1,1900);
      	assertEquals(q.hashCode(),quarter.hashCode());
     }
     @Test
     public void testHashCode2()
     {
      	arrange(4,9999);
		Quarter q = new Quarter(4,9999);
      	assertEquals(q.hashCode(),quarter.hashCode());
     }
     @Test
     public void testHashCode3()
     {
      	arrange(2,2023);
		Quarter q = new Quarter(2,2023);
      	assertEquals(q.hashCode(),quarter.hashCode());
     }
     @Test
     public void testHashCode4()
     {
      	arrange(3,1973);
		Quarter q = new Quarter(3,1973);
      	assertEquals(q.hashCode(),quarter.hashCode());
     }
     @Test
     public void testGetFirstMilisecond1()
     {
         arrange(1,2023);
         TimeZone timeZone = TimeZone.getTimeZone("UTC");
         Calendar calendar = Calendar.getInstance(timeZone);
         calendar.set(2023, Calendar.JANUARY, 1, 0, 0, 0);
         calendar.set(Calendar.MILLISECOND, 0);
         assertEquals(1672524000000, quarter.getFirstMillisecond(Calendar.getInstance(timeZone)));
     }
       @Test
     public void testGetFirstMillisecond2()
     {
    	 arrange(2,2023);
         TimeZone timeZone = TimeZone.getTimeZone("UTC");
         Calendar calendar = Calendar.getInstance(timeZone);
         calendar.set(2023, Calendar.APRIL, 1, 0, 0, 0);
         calendar.set(Calendar.MILLISECOND, 0);
         assertEquals(1680300000000L,quarter.getFirstMillisecond(Calendar.getInstance(timeZone)));
     }
     @Test
     public void testGetFirstMillisecond3()
     {
    	 arrange(1,2022);
         TimeZone timeZone = TimeZone.getTimeZone("UTC");
         Calendar calendar = Calendar.getInstance(timeZone);
         calendar.set(2022, Calendar.JANUARY, 1, 0, 0, 0);
         calendar.set(Calendar.MILLISECOND, 0);
         assertEquals(1640988000000L,quarter.getFirstMillisecond(Calendar.getInstance(timeZone)));
     }
     @Test
     public void testGetFirstMillisecond4()
     {
    	 arrange(2,2022);
         TimeZone timeZone = TimeZone.getTimeZone("UTC");
         Calendar calendar = Calendar.getInstance(timeZone);
         calendar.set(2022, Calendar.APRIL, 1, 0, 0, 0);
         calendar.set(Calendar.MILLISECOND, 0);
         assertEquals(1648764000000L,quarter.getFirstMillisecond(Calendar.getInstance(timeZone)));
     }
     @Test
     public void testGetLastMillisecond1()
     {
    	 arrange(1,2023);
         TimeZone zone = TimeZone.getTimeZone("UTC"); // Ensure correct time zone
         assertEquals(1680307199999L,quarter.getLastMillisecond(Calendar.getInstance(zone)));
     }
     @Test
     public void testGetLastMillisecond2()
     {
    	 arrange(2,2023);
         TimeZone zone = TimeZone.getTimeZone("UTC"); // Ensure correct time zone
         assertEquals(1688169599999L,quarter.getLastMillisecond(Calendar.getInstance(zone)));
     }
    
}
