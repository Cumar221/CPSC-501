import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TestJunit {

   @Test
   public void testGetTeamStat() throws FileNotFoundException  {
	  TeamStats teamStats = new TeamStats();
      statsCriteria statC = new statsCriteria(1690, 1866, 155, 5.82730084952028, 63683);
      
      String str = "unk";
      ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
      System.setIn(bais);
      Scanner scanner = new Scanner(System.in);
    
      assertTrue(EqualsBuilder.reflectionEquals(teamStats.getTeamStatistics(scanner, Main.read()),statC));
  
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