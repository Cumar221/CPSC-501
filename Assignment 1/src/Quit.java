import java.util.ArrayList;
import java.util.Scanner;

public class Quit implements gameCalculate{

	@Override
	public Object action(Scanner input, ArrayList<Game> gameData) {
		System.out.println("Bye Bye ......");
		System.exit(0);
		return null;
	}
}
