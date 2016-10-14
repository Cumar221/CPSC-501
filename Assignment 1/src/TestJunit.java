import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TestJunit {
	
   @Test
   public void testGetTeamStat() throws FileNotFoundException  {
	  /*
	   Stats for UNK:
		   Wins:			1690
		   Losses:		1866
		   Ties:			155
		   Total Points:		63683
		   Total Points %:	5.82730084952028
	   */
	  TeamStats teamStats = new TeamStats();
      TeamStatsObj statC = new TeamStatsObj(1690, 1866, 155, 5.82730084952028, 63683);
      
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
   public void testGetMatchupStatistics() throws FileNotFoundException{
	   /*
	   Games Played:		137
	   UNK won:				61
	   GB won:				76
	   UNK points:			1642
	   GB points:			1658
	   */
	   
	   MatchupStats matchupStats = new MatchupStats();
	   MatchUpStatsObj matchupStatObj = new MatchUpStatsObj(137, 61, 76, 1642, 1658);
	   
	   String str = "unk\n" + "gb";
	   ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
	   System.setIn(bais);
	   Scanner scanner = new Scanner(System.in);
    
	   assertTrue(EqualsBuilder.reflectionEquals(matchupStats.getMatchupStatistics(scanner, Main.read()), matchupStatObj));
	   
	   scanner.close();
   }
   @Test
   public void testShowMatchupHistory(){
	   
   }
}