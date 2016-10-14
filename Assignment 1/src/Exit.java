import java.util.ArrayList;
import java.util.Scanner;

public class Exit implements gameCalculate{

	@Override
	public Object action(Scanner input, ArrayList<Game> gameData) {
		System.out.println("Sorry you have entered a wrong input. Please try again.");
		return null;
	}
}
