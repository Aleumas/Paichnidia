import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tictactoe {

	char[] symbols = {'-', 'x', 'o'};

	protected static void displayBoard() {

		try {
			File boardFile = new File("TTTBoard.txt");
			Scanner reader = new Scanner(boardFile);
			while (reader.hasNextLine()) {
				String boardLine = reader.nextLine();
				System.out.println(boardLine);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {}

	}

	private static void clearBoard() {

	}

	protected static void manipulateBoard(char symbol, String position) {
		try {
			File boardFile = new File("TTTBoard.txt");
			Scanner reader = new Scanner(boardFile);
			int lineNumber = 0;
			while (reader.hasNextLine()) {
				String boardLine = reader.nextLine();
				// Isolate rows on board 
				if (lineNumber % 3 == 0 && lineNumber != 0) {
					char rowIndex = boardLine.charAt(1);
					// Isolate chosen row on board
					if (position.charAt(0) == rowIndex) {
						String newBoardLine = boardLine; 
						int columnPosition = convertColumnNotation(position); 
						if (columnPosition != -1) {
							if (boardLine.charAt(columnPosition) == '-') {
								newBoardLine = newBoardLine.substring(0, columnPosition) + 'x' + newBoardLine.substring(columnPosition + 1, newBoardLine.length());
							}

						}
						System.out.println(newBoardLine);
					} else {
						System.out.println(boardLine);
					}
				} else {
					System.out.println(boardLine);
				}

				lineNumber++;		
			}
			reader.close();
			
		} catch (FileNotFoundException e) {}

	}

	

	private static int convertColumnNotation(String position) {
		switch (position.charAt(1)) {
			case 'a': 
				return 4;
			case 'b':
				return 10;
			case 'c':
				return 16;
			default: 
				return -1;
		}
	}
}
