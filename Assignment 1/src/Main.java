import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
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
	private static String [] gameCompnentSplit = new String[271];				
	private static ArrayList<Game> gameData = new ArrayList<>();
	private static final String TEAM_STATS = "T";
	private static final String MATCHUP_STATS = "M";
	private static final String MATCHUP_STATS_HISTORY = "H";
	private static final String QUIT = "Q";
	private static final String ERROR = "E";
	private static ArrayList<String> arrList = new ArrayList<>();
	private static gameCalculate gcal;
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
		gameCalculate gcal = null;
		
		while(input.hasNextLine()){				
			userInput = input.next();
			gcal = gameTest(userInput);
			gcal.action(input, gameData);
			menuMessage();
		}
	}
	private static gameCalculate gameTest(String userInput) {
		HashMap<String, gameCalculate> test = new HashMap<>();
		
		test.put(TEAM_STATS, new TeamStats());
		test.put(MATCHUP_STATS, new MatchupStats());
		test.put(MATCHUP_STATS_HISTORY, new MatchupStatHistory());
		test.put(QUIT, new Quit());
		test.put(ERROR, new Exit());
		
		
		arrList.add(TEAM_STATS);
		arrList.add(MATCHUP_STATS);
		arrList.add(QUIT);
		arrList.add(MATCHUP_STATS_HISTORY);
		
		if(arrList.contains(userInput.toUpperCase())){	
			gcal = test.get(userInput.toUpperCase());
		}
		else{
			gcal = test.get("E");
		}
		return gcal;
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
		gameData.removeAll(gameData);
		Scanner scanner;

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	scanner = new Scanner(file);
		    	 while (scanner.hasNextLine()) {
		             gameCompnentSplit = scanner.nextLine().split(",");	
		             if(!gameCompnentSplit[0].equals("Year")){
			             gameData.add(new Game(Integer.valueOf(gameCompnentSplit[0]), 
			             gameCompnentSplit[1], gameCompnentSplit[2],
						 gameCompnentSplit[3],Integer.parseInt(gameCompnentSplit[4]), 
						 gameCompnentSplit[5], Integer.parseInt(gameCompnentSplit[6]),
						 gameCompnentSplit[7], gameCompnentSplit[8],Integer.parseInt(gameCompnentSplit[9]),
						Integer.parseInt(gameCompnentSplit[10]),
						Integer.parseInt(gameCompnentSplit[11]), Integer.parseInt(gameCompnentSplit[12]))); 
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







