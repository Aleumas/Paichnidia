import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class start {

	/* == Instance variables == */
	private static boolean isValidUserInput = false;

	/* == Main == */
	public static void main(String args[]) {

		while (!isValidUserInput) {
			// Ask user for gameChoice	
			String userGameChoice = askForInput();	

			// Validate input and open game 	
			try {
				isValidUserInput = true;
				int choice = Integer.parseInt(userGameChoice.strip());
				openGame(choice);
			} catch (NumberFormatException e) {continue;}
		}
	
	}


	/* == Helper methods == */
	private static void openGame(int gameChoice) {
		switch(gameChoice) {
			case 1: 
				System.out.println(" Tic Tac Toe");
				tictactoe.main(null);
				break;
			default: 
				isValidUserInput = false;
				System.out.println(" This is not a valid game");
		}

	}

	private static String askForInput() {

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
		String userGameChoice = userInput.nextLine();
		userInput.close();
		System.out.println();
		
		return userGameChoice;

	}
}
