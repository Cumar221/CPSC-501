import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;


import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TestJunit {

   @Test
   public void testGetTeamStat() throws FileNotFoundException  {
      statsCriteria statC = new statsCriteria(1690, 1866, 155, 5.82730084952028, 63683);
      
      String str = "gb";
      ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
      System.setIn(bais);
      Scanner scanner = new Scanner(System.in);
    
      Main.read();
      assertTrue(EqualsBuilder.reflectionEquals(Main.getTeamStatistics(scanner),statC));
  
      scanner.close();
   }
   @Test 
   public void testReadFile(){
	   
   }
   @Test
   public void testGetMatchupStatistics(){
	   
   }
   @Test
   public void testShowMatchupHistory(){
	   
   }
}