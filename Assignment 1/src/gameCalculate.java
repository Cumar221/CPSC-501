import java.util.ArrayList;
import java.util.Scanner;

public interface gameCalculate {

	/**
	 * This handler method is invoked in the menu method
	 * - calculates statistics from games played by the team by going
	 *   though the 2D array and finding the chosen team and records the
	 *   statistics
	 * - error of team is handled in this method if the team is not found  
	 * @param input - is a scanner used to get the user's input
	 */
	Object action(Scanner input, ArrayList<Game> gameData);
	

}