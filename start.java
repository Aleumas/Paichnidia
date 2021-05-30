import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class start {

	public static void main(String args[]) {

		// Display title
		try {
			File title = new File("title.txt");
			Scanner reader = new Scanner(title);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				System.out.println(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error reading file");
		}

		// Display game options
		System.out.println();
		System.out.println(" 1. tic tac toe");
		System.out.println();

		// Get user input
		System.out.print(" Which game would you like to play? : ");
		Scanner userInput = new Scanner(System.in);
		//String userGameChoice = userInput.nextLine();
		userInput.close();
		
		// TODO: Display choosen game

	}

}
