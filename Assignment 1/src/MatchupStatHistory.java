import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchupStatHistory implements gameCalculate {

	private String userInputTeamOne;
	private String userInputTeamTwo;
	private String printResults;
	private String printGuide;
	private String findTeamOne;
	private String findTeamTwo;
	private Boolean teamOneFound = false;
	private Boolean teamTwoFound = false;
	private PrintStream print = new PrintStream (System.out);
	

	/**
	 * This handler method is invoked in the menu method
	 * - find the games played between the desired two team and
	 *   prints the data to the screen
	 * @param input - is a scanner used to receive the user's input
	 */
	public Object action(Scanner input, ArrayList<Game> gameData){						
		findTeamOne ="\nPlease enter the first team: ";
		findTeamTwo ="\nPlease enter the second  team: ";			
		print.print(findTeamOne);
		userInputTeamOne = input.next().toUpperCase();	
		print.print(findTeamTwo);
		userInputTeamTwo = input.next().toUpperCase();				
		printGuide ="\nHistory of "+ userInputTeamOne + " vs. " +userInputTeamTwo + "\n"
				+"Year\tWeek\tAway\tAway Score\tHome\tHome Score \n"
				+"------------------------------------------------------------------";
		print.print(printGuide);
		 
		for(Game game : gameData){
			if((game.getHome().equals(userInputTeamOne) && game.getAway().equals(userInputTeamTwo)) ||
					(game.getAway().equals(userInputTeamOne) && game.getHome().equals(userInputTeamTwo))){
				print.print(printResults(game));
			}
		}
		if(!teamOneFound){
			printResults ="\nI'm sorry; "+ userInputTeamOne + " is not a valid team name. ";
			print.print(printResults);
		}
		else if(!teamTwoFound){
			printResults ="\nI'm sorry; "+ userInputTeamTwo + " is not a valid team name. ";
			print.print(printResults);
		}
		return null;	
	}
		public String printResults(Game game){
			teamOneFound = true;
			teamTwoFound = true;
			printResults = "\n" + game.getYear() + "\t" 
					+ game.getWeek() + "\t" 
					+ game.getAway() + "\t" 
					+ game.getAwayScore() + "\t\t" 
					+ game.getHome() + "\t" 
					+ game.getHomeScore();	
			return printResults;
		}
}
