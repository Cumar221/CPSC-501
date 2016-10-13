import java.io.PrintStream;
import java.util.Scanner;

public class MatchupStatHistory {

	private String userInput;
	private String userInput2;
	private String printResults;
	private String printResults2;
	private String printGuide;
	private String findTeam;
	private String findTeam2;
	private Boolean found;
	private Boolean found2;
	private int count; 
	

	/**
	 * This handler method is invoked in the menu method
	 * - find the games played between the desired two team and
	 *   prints the data to the screen
	 * @param input - is a scanner used to receive the user's input
	 */
	public void showMatchupHistory(Scanner input, Game[][] obj){			
		count = 0;
		found = false;
		found2 = false;			
		findTeam ="\nPlease enter the first team: ";
		findTeam2 ="\nPlease enter the second  team: ";			
		PrintStream print = new PrintStream (System.out);
		print.print(findTeam);
		userInput = input.next().toUpperCase();	
		print.print(findTeam2);
		userInput2 = input.next().toUpperCase();				
		printGuide ="\nHistory of "+ userInput + " vs. " +userInput2 + "\n"
				+"Year\tWeek\tAway\tAway Score\tHome\tHome Score \n"
				+"------------------------------------------------------------------";

		for (int year = 0; year <92; year++){
			for (int data = 1; data <272; data++){
				if(obj[year][data] != null){
					if(obj[year][data].getHome().equals(userInput)){
						found = true;
						if (obj[year][data].getAway().equals(userInput2)){	
							found2 = true;
							if(count == 0){
								print.print(printGuide);
								count++;
							}
							printResults = "\n" + obj[year][data].getYear() + "\t" 
									+ obj[year][data].getWeek() + "\t" 
									+ obj[year][data].getAway() + "\t" 
									+ obj[year][data].getAwayScore() + "\t\t" 
									+ obj[year][data].getHome() + "\t" 
									+ obj[year][data].getHomeScore();			     			
							print.print(printResults);								
						}				
					}			
					else if(obj[year][data].getHome().equals(userInput2) &&
							obj[year][data].getAway().equals(userInput)){	
						printResults2 = "\n" + obj[year][data].getYear() 
								+ "\t" + obj[year][data].getWeek() 
								+ "\t" + obj[year][data].getAway() + "\t" 
								+ obj[year][data].getAwayScore() 
								+ "\t\t" + obj[year][data].getHome() 
								+ "\t" + obj[year][data].getHomeScore();				
						print.print(printResults2);											
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
	}
}
