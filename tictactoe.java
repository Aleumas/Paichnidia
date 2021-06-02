import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class tictactoe {

	

	static char[] symbols = {'-', 'x', 'o'};
	static ArrayList<String> moveHistory = new ArrayList<String>();

	protected static void displayBoard(String board) {

		try {
			File boardFile = new File(board);
			Scanner reader = new Scanner(boardFile);
			while (reader.hasNextLine()) {
				String boardLine = reader.nextLine();
				System.out.println(boardLine);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {}

	}

	protected static void manipulateBoard(int playerTurn, String position) {
		clearPlayerMoves();
		moveHistory.add(position + symbols[playerTurn]);

		try {
			File boardFile = new File("TTTBoard.txt");
			Scanner reader = new Scanner(boardFile);
			int lineNumber = 0;
			while (reader.hasNextLine()) {
				String boardLine = reader.nextLine();
				// Isolate rows on board 
				if (lineNumber % 3 == 0 && lineNumber != 0) {
					char rowIndex = boardLine.charAt(1);
					String newBoardLine = boardLine; 
					for (String move : moveHistory) {
						// Isolate chosen row on board
						if (move.charAt(0) == rowIndex) {
							int columnPosition = convertColumnNotation(move); 
							if (columnPosition != -1) {
								if (boardLine.charAt(columnPosition) == '-') {
									newBoardLine = newBoardLine.substring(0, columnPosition) + move.charAt(2) + 
										newBoardLine.substring(columnPosition + 1, newBoardLine.length());
								}

							}
						}
					}

					writePlayerMove(newBoardLine);
				} else {
					writePlayerMove(boardLine);
				}

				lineNumber++;		
			}

			reader.close();
			
		} catch (FileNotFoundException e) {}

	}

	private static void writePlayerMove(String lineToWrite){
		try {
			FileWriter writer = new FileWriter("activeTTTBoard.txt", true);
			writer.write(lineToWrite);
			writer.write(System.getProperty( "line.separator" ));
			writer.close();
		} catch (IOException e) { System.out.println(e); }
	}

	private static void clearPlayerMoves() {
		try {
			FileWriter writer = new FileWriter("activeTTTBoard.txt");
			writer.write("");
			writer.close();
		} catch (IOException e) { System.out.println(e); }
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

	private static boolean win() {
		
		return false;
	}
}
