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
		   
		Stats for GB:
		  Wins:			724
		  Losses:		553
		  Ties:			34
		  Total Points:		26931
		  Total Points %:	4.867995989751588
		  
		Stats for SEA:
		  Wins:			305
		  Losses:		315
		  Ties:			0
		  Total Points:		13032
		  Total Points %:	4.757519950890117
	   */
	   
	  TeamStats teamStats = new TeamStats();
      TeamStatsObj statC = new TeamStatsObj(1690, 1866, 155, 5.82730084952028, 63683);
      TeamStatsObj statC2 = new TeamStatsObj(724, 553, 34, 4.867995989751588, 26931);
      TeamStatsObj statC3 = new TeamStatsObj(305, 315, 0, 4.757519950890117, 13032);
      
      String str = "unk\n" + "gb\n" + "sea";
      ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
      System.setIn(bais);
      Scanner scanner = new Scanner(System.in);
      
      assertTrue(EqualsBuilder.reflectionEquals(teamStats.action(scanner, Main.read()),statC));
      assertTrue(EqualsBuilder.reflectionEquals(teamStats.action(scanner, Main.read()),statC2));
      assertTrue(EqualsBuilder.reflectionEquals(teamStats.action(scanner, Main.read()),statC3));
      
      scanner.close();
   }
   @Test 
   public void testReadFile(){
	 
   }
   @Test
   public void testGetMatchupStatistics() throws FileNotFoundException{
	   /*
	   Stats for UNK vs. GB:
		   Games Played:		137
		   UNK won:				61
		   GB won:				76
		   UNK points:			1642
		   GB points:			1658
	   
	   Stats for SEA vs. GB:
		  Games Played:		5
		  SEA won:			3
		  GB won:			2
		  SEA points:		72
		  GB points:		85
		  
		  
		Stats for UNK vs. SEA:
		  Games Played:		23
		  UNK won:			16
		  SEA won:			7
		  UNK points:		429
		  SEA points:		445

	   */
	   
	   MatchupStats matchupStats = new MatchupStats();
	   MatchUpStatsObj matchupStatObj = new MatchUpStatsObj(137, 61, 76, 1642, 1658);
	   MatchUpStatsObj matchupStatObj2 = new MatchUpStatsObj(5, 3, 2, 72, 85);
	   MatchUpStatsObj matchupStatObj3 = new MatchUpStatsObj(23, 16, 7, 429, 445);
	   
	   String str = "unk\n" + "gb\n" + "sea\n" + "gb\n" + "unk\n" + "sea";
	   ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
	   System.setIn(bais);
	   Scanner scanner = new Scanner(System.in);
    
	   assertTrue(EqualsBuilder.reflectionEquals(matchupStats.action(scanner, Main.read()), matchupStatObj));
	   assertTrue(EqualsBuilder.reflectionEquals(matchupStats.action(scanner, Main.read()), matchupStatObj2));
	   assertTrue(EqualsBuilder.reflectionEquals(matchupStats.action(scanner, Main.read()), matchupStatObj3));
	   
	   scanner.close();
   }
   @Test
   public void testShowMatchupHistory(){
	   
   }
}