import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
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
			
		} catch (FileNotFoundException e) {System.out.println(e);}

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

	protected static boolean win() {
		ArrayList<String> player1Moves = new ArrayList<String>();
		ArrayList<String> player2Moves = new ArrayList<String>();

		for (int i = 0; i < moveHistory.size(); i++) {
			if (i % 2 == 0)	{ player1Moves.add(moveHistory.get(i)); }
			else { player2Moves.add(moveHistory.get(i)); }
		}

		HashSet<Character> rows = new HashSet<Character>();
		HashSet<Character> columns = new HashSet<Character>();
		
		for (String move : player1Moves) {
			char player1Row = move.charAt(0);
			char player1Column = move.charAt(1);
			rows.add(player1Row);
			columns.add(player1Column);
		}
		

		if (rows.size() == player1Moves.size() - 2) { System.out.println("Player 1 wins"); return true; }
		else if (columns.size() == player1Moves.size() - 2) { System.out.println("Player 1 wins"); return true; }
		
		rows.clear();
		columns.clear();

		for (String move : player2Moves) {
			char player2Row = move.charAt(0);
			char player2Column = move.charAt(1);
			rows.add(player2Row);
			columns.add(player2Column);
		}

		if (rows.size() == player2Moves.size() - 2) { System.out.println("Player 2 wins"); return true; }
		else if (columns.size() == player2Moves.size() - 2) { System.out.println("Player 2 wins"); return true; }

		return false;
	}
}
