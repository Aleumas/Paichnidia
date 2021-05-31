import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tictactoe {
	public static void main(String args[]) {

		// Display game board
		displayBoard();

	}

	private static void displayBoard() {

		try {
			File boardFile = new File("TTTBoard.txt");
			Scanner reader = new Scanner(boardFile);
			while (reader.hasNextLine()) {
				String board = reader.nextLine();
				System.out.println(board);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {}
	}
}
