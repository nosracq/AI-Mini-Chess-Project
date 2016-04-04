import java.util.Vector;
import java.io.*;

public class chess {
    // Global variables for game state
    static int playCount;
    static char nextMove;
    static char [][] board = new char[7][6];
    public chess() { reset();}

	// reset the state of the game / your internal variables - note that this function is highly dependent on your implementation
	public static void reset() {
	  playCount = 1;
	  nextMove = 'W';

      board[0][0] = '1';
      board[0][1] = ' ';
      board[0][2] = 'W';
      board[0][3] = '\n';
      board[1][0] = 'k';
      board[1][1] = 'q';
      board[1][2] = 'b';
      board[1][3] = 'n';
      board[1][4] = 'r';
      board[1][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[2][i] = 'p';
      board[2][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[3][i] = '.';
      board[3][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[4][i] = '.';
      board[4][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[5][i] = 'P';
      board[5][5] = '\n';

      board[6][0] = 'R';
      board[6][1] = 'N';
      board[6][2] = 'B';
      board[6][3] = 'Q';
      board[6][4] = 'K';
      board[6][5] = '\n';

	}

	// return the state of the game - one example is given below - note that the state has exactly 40 or 41 characters	
	public static String boardGet() {
  	    StringBuilder strBuilder = new StringBuilder();
		for(int i = 0; i < board.length; i++) {
		  for (int j = 0; j < board[j].length; j++) {
			strBuilder.append(board[i][j]);
			if (board[i][j]=='\n') break;
          }

        }
		return strBuilder.toString();
	}

	// read the state of the game from the provided argument and set your internal variables accordingly - 
	// note that the state has exactly 40 or 41 characters	
	public static void boardSet(String strIn) {
	    char [] chars = strIn.toCharArray();
		StringBuilder str = new StringBuilder(2);

		int idx = 0;
		while (idx < chars.length) {
		  for (int i = 0; i < board.length; i++)
     		for (int j = 0; j < board[j].length; j++) {
			  board[i][j] = chars[idx];
			  idx++;
			  if (board[i][j] == '\n') break;
			}
        }
		if (chars[1] == ' ') {
		  playCount = Character.getNumericValue(chars[0]);
		  nextMove = chars[2];
        }
		else {
		  str.append(chars[0]);
		  str.append(chars[1]);
		  playCount = Integer.valueOf(str.toString());
		  nextMove = chars[3];
		}

	}

	// determine the winner of the current state of the game and return '?' or '=' or 'W' or 'B' - 
	// note that we are returning a character and not a string	
	public static char winner() {
		
		return '?';
	}
	
	public static boolean isValid(int intX, int intY) {
		if (intX < 0) {
			return false;
			
		} else if (intX > 4) {
			return false;
			
		}
		
		if (intY < 0) {
			return false;
			
		} else if (intY > 5) {
			return false;
			
		}
		
		return true;
	}
	
	public static boolean isEnemy(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a piece from the side not on move - 
	    //note that we could but should not use the other is() functions in here but probably
		
		return false;
	}
	
	public static boolean isOwn(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a piece from the side on move - 
	    //note that we could but should not use the other is() functions in here but probably
		
		return false;
	}
	
	public static boolean isNothing(char charPiece) {
		// return whether the provided argument is not a piece / is an empty field - note that we could but should not use the other is() functions in here but probably
		
		return false;
	}


  // **************************************************************
  // *********************** END OF HOMEOWORK 1 *******************
  // **************************************************************

	
	public static int eval() {
		// with reference to the state of the game, return the the evaluation score of the side on move - note that positive means an advantage while negative means a disadvantage
		
		return 0;
	}
	
	public static Vector<String> moves() {
		// with reference to the state of the game and return the possible moves - one example is given below - note that a move has exactly 6 characters
		
		Vector<String> strOut = new Vector<String>();
		
		strOut.add("a5-a4\n");
		strOut.add("b5-b4\n");
		strOut.add("c5-c4\n");
		strOut.add("d5-d4\n");
		strOut.add("e5-e4\n");
		strOut.add("b6-a4\n");
		strOut.add("b6-c4\n");
		
		return strOut;
	}
	
	public static Vector<String> movesShuffled() {
		// with reference to the state of the game, determine the possible moves and shuffle them before returning them - note that you can call the chess.moves() function in here
		
		return new Vector<String>();
	}
	
	public static Vector<String> movesEvaluated() {
		// with reference to the state of the game, determine the possible moves and sort them in order of an increasing evaluation score before returning them - note that you can call the chess.moves() function in here
		
		return new Vector<String>();
	}
	
	public static void move(String charIn) {
		// perform the supplied move (for example "a5-a4\n") and update the state of the game / your internal variables accordingly - note that it advised to do a sanity check of the supplied move
	}
	
	public static String moveRandom() {
		// perform a random move and return it - one example output is given below - note that you can call the chess.movesShuffled() function as well as the chess.move() function in here
		
		return "a5-a4\n";
	}
	
	public static String moveGreedy() {
		// perform a greedy move and return it - one example output is given below - note that you can call the chess.movesEvaluated() function as well as the chess.move() function in here
		
		return "a5-a4\n";
	}
	
	public static String moveNegamax(int intDepth, int intDuration) {
		// perform a negamax move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a5-a4\n";
	}
	
	public static String moveAlphabeta(int intDepth, int intDuration) {
		// perform a alphabeta move and return it - one example output is given below - note that you can call the the other functions in here
		
		return "a5-a4\n";
	}
	
	public static void undo() {
		// undo the last move and update the state of the game / your internal variables accordingly - note that you need to maintain an internal variable that keeps track of the previous history for this
	}
}
