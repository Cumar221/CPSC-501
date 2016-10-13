import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
/**
 * Comp 2631
 * Assignment 1
 * Instructor: Jason Heard
 * Due Date: Wed,Sept 24, 2014 
 * @author Cumar Yusuf
 */
public class Main {
	private static String userInput;
	private static String userInput2;
	private static Scanner fileIn;			
	private static Game[][] obj  = new Game[1000][1000];			
	private static String [] array = new String[271];				
	private static String temp;	
	private static int wins;
	private static int wins2;
	private static int total;
	private static int losses;
	private static int ties;
	private static double  pointPercentage;
	private static int points;
	private static int points2;
	private static String printResults;
	private static String printResults2;
	private static String printGuide;
	private static String findTeam;
	private static String findTeam2;
	private static Boolean found;
	private static Boolean found2;
	private static int count; 
	/**
	 * This main method calls read & menu methods
	 * - all exceptions are caught here and handled appropriately
	 * 	 with error messages   
	 * @param args
	 * 	*/
	public static void main (String args[]){ 				
		try{
			Scanner scanner = new Scanner(System.in);				
			read();
			menu(scanner);					
		}	
		catch (IOException e){
			e.printStackTrace();
		}
		catch (NumberFormatException y){
			System.out.println("number format problem");
		}
		catch (NullPointerException v){
			System.out.println("null pointer problem");
		}
		catch (IndexOutOfBoundsException c){
			System.out.println("index out of bound problem");
		}
	}
	/**
	 * This menu method calls the menu message for the user enter the desired input
	 * - input is read using the scanner and the responsible method handler is then invoked
	 * - method terminates the program once the user quits the program by entering 'Q'
	 * @param input  -this a scanner used to receive input from the user 
	 */
	public static void menu(Scanner input){
		menuMessage();
		while(input.hasNextLine()){				
			userInput = input.next();		
			switch(userInput.toUpperCase()){
			case "T":
				Scanner scanner = new Scanner(System.in); 
				getTeamStatistics(scanner);     //TO DO:: do input for lower cases
				break;
			case "M":
				Scanner scanner2 = new Scanner(System.in); 
				getMatchupStatistics(scanner2);
				break;
			case "H":
				Scanner scanner3 = new Scanner(System.in); 
				showMatchupHistory(scanner3);
				break;
			case "Q":
				System.out.println("Bye Bye");
				return;
			default:
				System.out.println("\nWrong Input pal. Please try again.");
				break;
			}
			menuMessage();
		}
	}
	/**
	 * This read methods reads the files and stores it in a 2D array
	 * How?
	 * - the file is read in a loop as the year is a variable and loops
	 *   to the next year once everything current year's games have been all read
	 * 2D array explanation:
	 * - the 2D array stores the years of all of the games in the 1st array
	 * - the second array stores an object array of each game in the current year  
	 * 
	 * @throws FileNotFoundException - throws an exception to main if the file cannot
	 * 									be read
	 */
	public static void read() throws FileNotFoundException{								
		for(int w = 1922, y = 0; w < 2014 && y < 92; w++, y++){
			fileIn = new Scanner ( new File ("allYears/"+w+".csv"));		
			for(int x = 0; x < 271; x++){	
				if(fileIn.hasNextLine()){
					temp = fileIn.nextLine();						 																							 
					if (temp.startsWith("Year")){
						// Handles the first line
					}else{				 
						array = temp.split(",");				
						obj[y][x] = new Game(); 
						obj[y][x].set(Integer.valueOf(array[0]), array[1], array[2],
								array[3],Integer.parseInt(array[4]), 
								array[5], Integer.parseInt(array[6]),
								array[7], array[8],Integer.parseInt(array[9]),
								Integer.parseInt(array[10]),
								Integer.parseInt(array[11]), Integer.parseInt(array[12])); 						 
					}			
				}					
			}									
		}			
	}	
	/**
	 * This handler method is invoked in the menu method
	 * - calculates statistics from games played by the team by going
	 *   though the 2D array and finding the chosen team and records the
	 *   statistics
	 * - error of team is handled in this method if the team is not found  
	 * @param input - is a scanner used to get the user's input
	 */
	public static statsCriteria getTeamStatistics(Scanner input){
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
	public static void getMatchupStatistics(Scanner input){			
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
	/**
	 * This handler method is invoked in the menu method
	 * - find the games played between the desired two team and
	 *   prints the data to the screen
	 * @param input - is a scanner used to receive the user's input
	 */
	public static void showMatchupHistory(Scanner input){			
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
	/**
	 * This method is invoked in main and just uses print stream
	 * to print out the menu for the user
	 */
	public static void menuMessage(){
		String menu ="\n\nAvailable Options \n"
				+ "T - Get team statistics \n"
				+ "M - Get matchup statistics \n"
				+ "H - Show matchup history \n"
				+ "Q - Quit \n\n"
				+ "Please enter your choice: ";

		PrintStream printMenu = new PrintStream (System.out);
		printMenu.print(menu);
	}
}







