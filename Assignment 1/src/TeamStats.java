import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamStats {
	
	private String userInput;								
	private int wins = 0;
	private int losses = 0;
	private int ties = 0;
	private double  pointPercentage = 0;
	private int points = 0;
	private String printResults = null;
	private String findTeam = null;
	private Boolean found = null;
	private statsCriteria statsVar = null;
	private PrintStream print = new PrintStream (System.out);


	/**
	 * This handler method is invoked in the menu method
	 * - calculates statistics from games played by the team by going
	 *   though the 2D array and finding the chosen team and records the
	 *   statistics
	 * - error of team is handled in this method if the team is not found  
	 * @param input - is a scanner used to get the user's input
	 */
	public statsCriteria getTeamStatistics(Scanner input,ArrayList<Game> gameData){
		findTeam ="\nPlease enter a team: ";			
		print.print(findTeam);
		userInput = input.next().toUpperCase();

		for(Game game : gameData){
			found = true;
			if(game.getHome().equals(userInput)){
				teamHome(game);
			}
			else if (game.getAway().equals(userInput)){
				teamAway(game);
			}
		}
		if(!found){
			printResults ="\nI'm sorry; "+ userInput + " is not a valid team name. ";
			print.print(printResults);
		}else{
			print.print(printResults());
			statsVar = new statsCriteria(wins, losses, ties, pointPercentage, points);
		}
		return statsVar;
	}
	/**
	 * 
	 * @return
	 */
	public String printResults(){
		pointPercentage = ((((double)(wins+losses+ties) / points) )*100);
		printResults ="\nStats for " + userInput + ":\n"
				+ "  Wins:\t\t\t" + wins
				+ "\n  Losses:\t\t" + losses
				+ "\n  Ties:\t\t\t" + ties
				+ "\n  Total Points:\t\t" +points
				+ "\n  Total Points %:\t"+ pointPercentage + "\n\n";
		return printResults;
	}
	/**
	 * 
	 * @param obj
	 * @param year
	 * @param data
	 */
	public void teamAway(Game game){
		if(game.getResult().equals("H")){
			losses++;
			points = points + game.getAwayScore();
		}else if(game.getResult().equals("A")){
			wins++;
			points = points + game.getAwayScore();
		}else {
			ties++;
			points = points + game.getAwayScore();
		}
	}
	/**
	 * 
	 * @param obj
	 * @param year
	 * @param data
	 */
	public void teamHome(Game game){
		if(game.getResult().equals("H")){
			wins++;
			points = points + game.getHomeScore();
		}else if(game.getResult().equals("A")){
			losses++;
			points = points + game.getHomeScore();
		}else {
			ties++;
			points = points + game.getHomeScore();
		}
	}
}
