import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
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
	private static String [] array = new String[271];				
	private static ArrayList<Game> gameData = new ArrayList<>();
	/**
	 * This main method calls read & menu methods
	 * - all exceptions are caught here and handled appropriately
	 * 	 with error messages   
	 * @param args
	 * @throws FileNotFoundException 
	 * 	*/
	public static void main (String args[]) throws FileNotFoundException{ 				
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
				TeamStats teamStat = new TeamStats();
				teamStat.getTeamStatistics(scanner,gameData);     //TO DO:: do input for lower cases
				break;
			case "M":
				Scanner scanner2 = new Scanner(System.in);
				MatchupStats matchupStat =  new MatchupStats();
				matchupStat.getMatchupStatistics(scanner2,gameData);
				break;
			case "H":
				Scanner scanner3 = new Scanner(System.in); 
				MatchupStatHistory matchupStatH = new MatchupStatHistory();
				matchupStatH.showMatchupHistory(scanner3, gameData);
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
	public static ArrayList<Game> read() throws FileNotFoundException{								
		File folder = new File("allYears");
		File[] listOfFiles = folder.listFiles();
		Scanner scanner;

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	scanner = new Scanner(file);
		    	 while (scanner.hasNextLine()) {
		             array = scanner.nextLine().split(",");	
		             if(!array[0].equals("Year")){
			             gameData.add(new Game(Integer.valueOf(array[0]), array[1], array[2],
									array[3],Integer.parseInt(array[4]), 
									array[5], Integer.parseInt(array[6]),
									array[7], array[8],Integer.parseInt(array[9]),
									Integer.parseInt(array[10]),
									Integer.parseInt(array[11]), Integer.parseInt(array[12]))); 
		             }
		    	 }
		    }
		}	
		return gameData;
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







