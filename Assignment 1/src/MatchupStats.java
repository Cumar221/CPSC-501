import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchupStats {

	private String userInput = null;
	private String userInput2 = null;									
	private int wins = 0;
	private int wins2 = 0;
	private int total = 0;
	private int points = 0;
	private int points2 = 0;
	private String printResults = null;
	private String findTeam = null;
	private String findTeam2 = null;
	private Boolean found = false;
	private Boolean found2 = false;
	private PrintStream print = new PrintStream (System.out);
	
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
	public void getMatchupStatistics(Scanner input, ArrayList<Game> gameData){			
		findTeam ="\nPlease enter the first team: ";
		findTeam2 ="\nPlease enter the second team:  ";								
		print.print(findTeam);
		userInput = input.next().toUpperCase();
		print.print(findTeam2);
		userInput2 = input.next().toUpperCase();

		for(Game game : gameData){
			matchUpHelper(game);
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
			print.print(printResults());
		}
	}
	public String printResults(){
		total = (wins+wins2);
		printResults ="\nStats for " + userInput + " vs. " + userInput2 + ":\n"
				+ "  Games Played:\t\t" + total
				+ "\n  "+ userInput + " won:\t\t" + wins
				+ "\n  "+ userInput2 +" won:\t\t" + wins2
				+ "\n  "+ userInput +" points:\t\t" + points
				+ "\n  "+ userInput2 +" points:\t\t" + points2 +"\n\n";
		return printResults;
	}
	
	public void matchUpHelper(Game game){
		if(game.getHome().equals(userInput)){
			found =true;
			if (game.getAway().equals(userInput2)){
				found2 = true;
				if(game.getResult().equals("H")){
					wins++;
					points = points + game.getHomeScore();
				}
				if(game.getResult().equals("A")){
					wins2++;
					points2 = points + game.getAwayScore();
				}
			}
		}
		
	}
}
