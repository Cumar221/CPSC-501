import java.io.PrintStream;
import java.util.Scanner;

public class TeamStats {
	
	private String userInput;								
	private int wins;
	private int losses;
	private int ties;
	private double  pointPercentage;
	private int points;
	private String printResults;
	private String findTeam;
	private Boolean found;

	/**
	 * This handler method is invoked in the menu method
	 * - calculates statistics from games played by the team by going
	 *   though the 2D array and finding the chosen team and records the
	 *   statistics
	 * - error of team is handled in this method if the team is not found  
	 * @param input - is a scanner used to get the user's input
	 */
	public statsCriteria getTeamStatistics(Scanner input,Game[][] obj){
		statsCriteria statsVar = null;
		findTeam        = null;
		printResults    = null;
		found           = false;
		wins            = 0;
		losses          = 0;
		ties            = 0;
		points          = 0;
		pointPercentage = 0.0;
		findTeam ="\nPlease enter a team: ";			
		PrintStream print = new PrintStream (System.out);
		print.print(findTeam);
		userInput = input.next().toUpperCase();

		for (int year = 0; year <92; year++){
			for (int data = 1; data <272; data++){
				if(obj[year][data] != null){
					if(obj[year][data].getHome().equals(userInput)){
						found = true;
						if(obj[year][data].getResult().equals("H")){
							wins++;
							points = points + obj[year][data].getHomeScore();
						}
						else if(obj[year][data].getResult().equals("A")){
							losses++;
							points = points + obj[year][data].getHomeScore();
						}
						else {
							ties++;
							points = points + obj[year][data].getHomeScore();
						}
					}
					else if (obj[year][data].getAway().equals(userInput)){
						found = true;
						if(obj[year][data].getResult().equals("H")){
							losses++;
							points = points + obj[year][data].getAwayScore();
						}
						else if(obj[year][data].getResult().equals("A")){
							wins++;
							points = points + obj[year][data].getAwayScore();
						}
						else {
							ties++;
							points = points + obj[year][data].getAwayScore();
						}
					}
				}
			}		
		}
		if(!found){
			printResults ="\nI'm sorry; "+ userInput + " is not a valid team name. ";
			print.print(printResults);
		}
		else{
			pointPercentage = ((((double)(wins+losses+ties) / points) )*100);

			printResults ="\nStats for " + userInput + ":\n"
					+ "  Wins:\t\t\t" + wins
					+ "\n  Losses:\t\t" + losses
					+ "\n  Ties:\t\t\t" + ties
					+ "\n  Total Points:\t\t" +points
					+ "\n  Total Points %:\t"+ pointPercentage + "\n\n";
			
			statsVar = new statsCriteria(wins, losses, ties, pointPercentage, points);
			print.print(printResults);
		}
		return statsVar;
	}	
}
