import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchupStats {

	private String userInputTeamOne = null;
	private String userInputTeamTwo = null;									
	private int teamOneWins = 0;
	private int teamTwoWins = 0;
	private int total = 0;
	private int teamOnePoints = 0;
	private int teamTwoPoints = 0;
	private String printResults = null;
	private String findTeamOne = null;
	private String findTeamTwo = null;
	private Boolean foundTeamOne = false;
	private Boolean foundTeamTwo = false;
	private PrintStream print = new PrintStream (System.out);
	private MatchUpStatsObj matchUSO = null;
	
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
	public MatchUpStatsObj getMatchupStatistics(Scanner input, ArrayList<Game> gameData){			
		findTeamOne ="\nPlease enter the first team: ";
		findTeamTwo ="\nPlease enter the second team:  ";								
		print.print(findTeamOne);
		userInputTeamOne = input.next().toUpperCase();
		print.print(findTeamTwo);
		userInputTeamTwo = input.next().toUpperCase();

		for(Game game : gameData){
			matchUpHelper(game);
		}
		if(!foundTeamOne){
			printResults ="\nI'm sorry; "+ userInputTeamOne + " is not a valid team name. ";
			print.print(printResults);
		}
		else if(!foundTeamTwo){
			printResults ="\nI'm sorry; "+ userInputTeamTwo + " is not a valid team name. ";
			print.print(printResults);
		}
		else{
			print.print(printResults());
			matchUSO = new MatchUpStatsObj(total, teamOneWins, teamTwoWins, teamOnePoints, teamTwoPoints);
		}
		return matchUSO;
	}
	public String printResults(){
		total = (teamOneWins+teamTwoWins);
		printResults ="\nStats for " + userInputTeamOne + " vs. " + userInputTeamTwo + ":\n"
				+ "  Games Played:\t\t" + total
				+ "\n  "+ userInputTeamOne + " won:\t\t" + teamOneWins
				+ "\n  "+ userInputTeamTwo +" won:\t\t" + teamTwoWins
				+ "\n  "+ userInputTeamOne +" points:\t\t" + teamOnePoints
				+ "\n  "+ userInputTeamTwo +" points:\t\t" + teamTwoPoints +"\n\n";
		return printResults;
	}
	
	public void matchUpHelper(Game game){
		if(game.getHome().equals(userInputTeamOne)){
			foundTeamOne =true;
			if (game.getAway().equals(userInputTeamTwo)){
				foundTeamTwo = true;
				if(game.getResult().equals("H")){
					teamOneWins++;
					teamOnePoints = teamOnePoints + game.getHomeScore();
				}
				if(game.getResult().equals("A")){
					teamTwoWins++;
					teamTwoPoints = teamOnePoints + game.getAwayScore();
				}
			}
		}
	}
}
