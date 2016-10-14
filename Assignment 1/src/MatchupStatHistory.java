import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchupStatHistory {

	private String userInput;
	private String userInput2;
	private String printResults;
	private String printResults2;
	private String printGuide;
	private String findTeam;
	private String findTeam2;
	private Boolean found = false;
	private Boolean found2 = false;
	private int count = 0; 
	private PrintStream print = new PrintStream (System.out);
	

	/**
	 * This handler method is invoked in the menu method
	 * - find the games played between the desired two team and
	 *   prints the data to the screen
	 * @param input - is a scanner used to receive the user's input
	 */
	public void showMatchupHistory(Scanner input, ArrayList<Game> gameData){						
		findTeam ="\nPlease enter the first team: ";
		findTeam2 ="\nPlease enter the second  team: ";			
		print.print(findTeam);
		userInput = input.next().toUpperCase();	
		print.print(findTeam2);
		userInput2 = input.next().toUpperCase();				
		printGuide ="\nHistory of "+ userInput + " vs. " +userInput2 + "\n"
				+"Year\tWeek\tAway\tAway Score\tHome\tHome Score \n"
				+"------------------------------------------------------------------";
		
		for(Game game : gameData){
			if(game.getHome().equals(userInput)){
				found = true;
				if (game.getAway().equals(userInput2)){	
					found2 = true;
					if(count == 0){
						print.print(printGuide);
						count++;
					}
					printResults = "\n" + game.getYear() + "\t" 
							+ game.getWeek() + "\t" 
							+ game.getAway() + "\t" 
							+ game.getAwayScore() + "\t\t" 
							+ game.getHome() + "\t" 
							+ game.getHomeScore();			     			
					print.print(printResults);								
				}				
			}			
			else if(game.getHome().equals(userInput2) &&
					game.getAway().equals(userInput)){	
				printResults2 = "\n" + game.getYear() 
						+ "\t" + game.getWeek() 
						+ "\t" + game.getAway() + "\t" 
						+ game.getAwayScore() 
						+ "\t\t" + game.getHome() 
						+ "\t" + game.getHomeScore();				
				print.print(printResults2);											
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
	}
	
	
}
