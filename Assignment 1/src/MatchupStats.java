import java.io.PrintStream;
import java.util.Scanner;

public class MatchupStats {

	private String userInput;
	private String userInput2;									
	private int wins;
	private int wins2;
	private int total;
	private int points;
	private int points2;
	private String printResults;
	private String findTeam;
	private String findTeam2;
	private Boolean found;
	private Boolean found2;
	
	/**
	 * This handler method is invoked in the menu method
	 * - calculates statistics from games played between two teams
	 * - calculations are doing through looping the 2D array and recording
	 *   the statistics
	 * - error of the teams is caught in this method
	 *  How?
	 *  - if the first team cannot be found the method displays an error
	 *    message and does not worry about the second team whether it's
	 *    found or not and holds if the second cannot found
	 * @param input - is a scanner used to get the user's input
	 */
	public void getMatchupStatistics(Scanner input, Game[][] obj){			
		found  = false;
		found2 = false;	
		wins   = 0;
		wins2  = 0; 	
		points = 0;
		points2= 0; 
		findTeam ="\nPlease enter the first team: ";
		findTeam2 ="\nPlease enter the second team:  ";								
		PrintStream print = new PrintStream (System.out);
		print.print(findTeam);
		userInput = input.next().toUpperCase();
		print.print(findTeam2);
		userInput2 = input.next().toUpperCase();

		for (int year = 0; year <92; year++){
			for (int data = 1; data <272; data++){
				if(obj[year][data] != null){
					if(obj[year][data].getHome().equals(userInput)){
						found =true;
						if (obj[year][data].getAway().equals(userInput2)){
							found2 = true;
							if(obj[year][data].getResult().equals("H")){
								wins++;
								points = points + obj[year][data].getHomeScore();
							}
							if(obj[year][data].getResult().equals("A")){
								wins2++;
								points2 = points + obj[year][data].getAwayScore();
							}
						}
					}
					if(obj[year][data].getHome().equals(userInput2) &&
							obj[year][data].getAway().equals(userInput)){	
						if(obj[year][data].getResult().equals("H")){
							wins2++;
							points2 = points + obj[year][data].getHomeScore();	
						}
						if(obj[year][data].getResult().equals("A")){
							wins++;
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
		else if(!found2){
			printResults ="\nI'm sorry; "+ userInput2 + " is not a valid team name. ";
			print.print(printResults);
		}
		else{
			total = (wins+wins2);
			printResults ="\nStats for " + userInput + " vs. " + userInput2 + ":\n"
					+ "  Games Played:\t\t" + total
					+ "\n  "+ userInput + " won:\t\t" + wins
					+ "\n  "+ userInput2 +" won:\t\t" + wins2
					+ "\n  "+ userInput +" points:\t\t" + points
					+ "\n  "+ userInput2 +" points:\t\t" + points2 +"\n\n";
			print.print(printResults);
		}
	}

}
