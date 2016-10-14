import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamStats implements gameCalculate {
	
	private String userInputTeam;								
	private int wins = 0;
	private int losses = 0;
	private int ties = 0;
	private double  pointPercentage = 0;
	private int points = 0;
	private String printResults = null;
	private String findTeam = null;
	private Boolean found = null;
	private TeamStatsObj teamStatObj = null;
	private PrintStream print = new PrintStream (System.out);

	/* (non-Javadoc)
	 * @see gameCalculate#action(java.util.Scanner, java.util.ArrayList)
	 */
	@Override
	public TeamStatsObj action(Scanner input,ArrayList<Game> gameData){
		findTeam ="\nPlease enter a team: ";			
		print.print(findTeam);
		userInputTeam = input.next().toUpperCase();

		for(Game game : gameData){
			found = true;
			if(game.getHome().equals(userInputTeam)){
				teamHome(game);
			}
			else if (game.getAway().equals(userInputTeam)){
				teamAway(game);
			}
		}
		if(!found){
			printResults ="\nI'm sorry; "+ userInputTeam + " is not a valid team name. ";
			print.print(printResults);
		}else{
			print.print(printResults());
			teamStatObj = new TeamStatsObj(wins, losses, ties, pointPercentage, points);
		}
		return teamStatObj;
	}
	/**
	 * 
	 * @return
	 */
	public String printResults(){
		pointPercentage = ((((double)(wins+losses+ties) / points) )*100);
		printResults ="\nStats for " + userInputTeam + ":\n"
				+ "  Wins:\t\t\t" + wins
				+ "\n  Losses:\t\t" + losses
				+ "\n  Ties:\t\t\t" + ties
				+ "\n  Total Points:\t\t" +points
				+ "\n  Total Points %:\t"+ pointPercentage + "\n\n";
		return printResults;
	}
	/**
	 * 
	 * @param game
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
	 * @param game
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
