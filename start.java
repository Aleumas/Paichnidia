import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class start {

	/* == Instance variables == */
	private static boolean validInput = false;
	private static Scanner userInput = new Scanner(System.in);

	/* == Main == */
	public static void main(String args[]) {

		clearScreen();
		while (!validInput) {
			String choice = askForUserInput();	
			if (isNumeric(choice)) {
				validInput = true;
				int gameIndex = Integer.parseInt(choice.strip());
				openGame(gameIndex);
			}
		}
		userInput.close();

	}

	/* == Helper methods == */
	private static void openGame(int gameIndex) {

		switch(gameIndex) {
			case 1: 
				System.out.println(" Tic Tac Toe");
				startTTT();
				break;
			default: 
				validInput = false;
				System.out.println(" This is not a valid game");
		}

	}

	private static String askForUserInput() {

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

		System.out.println();
		System.out.println(" 1. tic tac toe");
		System.out.println();

		System.out.print(" Which game would you like to play? : ");
		String userGameChoice = userInput.nextLine();
		System.out.println();

		return userGameChoice;

	}

	private static void startTTT() {

		tictactoe.displayBoard("TTTBoard.txt");
		int turnCount = 0;
		while (!tictactoe.win()) {
			int playerTurn = (turnCount % 2 == 0) ? 1 : 2;
			String playerMove = askPlayerForMove(playerTurn);
			tictactoe.manipulateBoard(playerTurn, playerMove);
			tictactoe.displayBoard("activeTTTBoard.txt");
			turnCount++;
		}

	}

	private static void clearScreen() {
		System.out.print("\033[H\033[2J"); 
		System.out.flush();
	}

	private static boolean isNumeric(String possibleNumericString) {

		if (possibleNumericString == null || possibleNumericString == "") { return false; }

		try {
			int number = Integer.parseInt(possibleNumericString);
		} catch (NumberFormatException e) { return false; }

		return true;

	}

	private static String askPlayerForMove(int playerNumber) {

		System.out.println();	
		System.out.print( "Player " + String.format("%d",playerNumber) + ", what is your move? : ");
		String playerMove = userInput.nextLine();
		System.out.println();	

		return playerMove;

	}

}
