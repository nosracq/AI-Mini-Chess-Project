import java.util.Vector;
import java.util.Stack;
import java.util.Collections;
import java.util.Arrays;
import java.lang.Math;
import java.io.*;

public class chess {
    // Global variables for game state
	//static double posInfinity = Double.POSITIVE_INFINITY;
	//static double negInfinity = Double.NEGATIVE_INFINITY;
    static int posInfinity = 1000000000;
    static int negInfinity = -1000000000;
    static int playCount;
    static char nextMove;
    static char [][] board = new char[7][6];
    static Vector<String> moves = new Vector<String>();
    static Stack<String> boardHist = new Stack<String>();
    public chess() { reset();}

	// reset the state of the game / your internal variables - note that this function is highly dependent on your implementation
	public static void reset() {
	  playCount = 1;
	  nextMove = 'W';

      board[6][0] = '1';
      board[6][1] = ' ';
      board[6][2] = 'W';
      board[6][3] = '\n';
      board[5][0] = 'k';
      board[5][1] = 'q';
      board[5][2] = 'b';
      board[5][3] = 'n';
      board[5][4] = 'r';
      board[5][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[4][i] = 'p';
      board[4][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[3][i] = '.';
      board[3][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[2][i] = '.';
      board[2][5] = '\n';

      for (int i = 0; i < 5; i++)
        board[1][i] = 'P';
      board[1][5] = '\n';

      board[0][0] = 'R';
      board[0][1] = 'N';
      board[0][2] = 'B';
      board[0][3] = 'Q';
      board[0][4] = 'K';
      board[0][5] = '\n';

	}

	// return the state of the game - one example is given below - note that the state has exactly 40 or 41 characters	
	public static String boardGet() {
  	    StringBuilder strBuilder = new StringBuilder();
		for(int i = board.length - 1; i >= 0; i--) {
		  for (int j = 0; j < board[i].length; j++) {
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
   		  for (int i = board.length - 1; i >= 0; i--)
     		for (int j = 0; j < board[j].length; j++) {
			  board[i][j] = chars[idx];
			  idx++;
			  if (board[i][j] == '\n') break;
			}
        }
		// Setting interna, playCount and nextMove
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
	  char winner;
	  String state = boardGet();
	  // check for a winner
	  if (state.contains("k") && !state.contains("K")) winner = 'B';
	  else if (!state.contains("k") && state.contains("K")) winner = 'W';
	  // check for draw
	  else if (playCount > 40)
		winner = '=';
	  // not a draw or a winner, game continues
	  else winner = '?';

	  return winner;
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

	// with reference to the state of the game, return whether the provided argument is a piece from the side not on move - 
    //note that we could but should not use the other is() functions in here but probably	
	public static boolean isEnemy(char charPiece) {
	    if (nextMove == 'W') {
		  switch(charPiece) {
		    case 'k': return true;
		    case 'q': return true;
		    case 'b': return true;
		    case 'n': return true;
		    case 'r': return true;
		    case 'p': return true;
          }
        }		
	    else if (nextMove == 'B') {
		  switch(charPiece) {
		    case 'K': return true;
		    case 'Q': return true;
		    case 'B': return true;
		    case 'N': return true;
		    case 'R': return true;
		    case 'P': return true;
          }
        }
		return false;
 	}
	
	public static boolean isOwn(char charPiece) {
		// with reference to the state of the game, return whether the provided argument is a piece from the side on move - 
	    //note that we could but should not use the other is() functions in here but probably
	    if (nextMove == 'B') {
		  switch(charPiece) {
		    case 'k': return true;
		    case 'q': return true;
		    case 'b': return true;
		    case 'n': return true;
		    case 'r': return true;
		    case 'p': return true;
          }
        }		
	    else if (nextMove == 'W') {
		  switch(charPiece) {
		    case 'K': return true;
		    case 'Q': return true;
		    case 'B': return true;
		    case 'N': return true;
		    case 'R': return true;
		    case 'P': return true;
          }
        }
		return false;
	}

	// return whether the provided argument is not a piece / is an empty field -
	// note that we could but should not use the other is() functions in here but probably	
	public static boolean isNothing(char charPiece) {
		switch(charPiece) {
		  case 'k': return false;
		  case 'q': return false;
		  case 'b': return false;
		  case 'n': return false;
		  case 'r': return false;
		  case 'p': return false;
		  case 'K': return false;
		  case 'Q': return false;
		  case 'B': return false;
		  case 'N': return false;
		  case 'R': return false;
		  case 'P': return false;
        }
		return true;
	}


    // **************************************************************
    // *********************** END OF HOMEOWORK 1 *******************
    // **************************************************************

	// with reference to the state of the game, return the evaluation score of the side on move - 
    // note that positive means an advantage while negative means a disadvantage	
    //
    // ----- Piece Values ------
    // pawn   = 1
    // knight = 3
    // bishop = 3
    // rook   = 5
    // queen  = 10
    // king   = 100
    // -------------------------
	public static int eval() {
	  String state = boardGet();
	  // adjustment to only contain board
	  state = state.substring(state.length() - 36);
	  int score = 0;
	  for (char c: state.toCharArray()){
		switch(c) {
		  // black
		  case 'k': score += 100; break;
		  case 'q': score += 10; break;
		  case 'r': score += 5; break;
	 	  case 'b': score += 3; break;
		  case 'n': score += 3; break;
		  case 'p': score += 1; break;
		  // white
		  case 'K': score -= 100; break;
		  case 'Q': score -= 10; break;
		  case 'R': score -= 5; break;
		  case 'B': score -= 3; break;
		  case 'N': score -= 3; break;
		  case 'P': score -= 1; break;
        }
      }
	  if (nextMove == 'W' && score != 0)
		score = -score;	
	
	  return score;
	}

	// with reference to the state of the game and return the possible moves - one example is given below - 
    // note that a move has exactly 6 characters
	public static Vector<String> moves() {
	  Vector<String> strOut = new Vector<String>();
	  char piece;

	  for (int row = 0; row < board.length; row++) {
		for (int col = 0; col < board[row].length; col++) {
		  piece = board[row][col];
		  if (isOwn(piece)) {
		    // Pawns
			if (Character.toUpperCase(piece)=='P') {
			  strOut.addAll(pawnMoves(col, row));
            }
			// Knights
			if (Character.toUpperCase(piece)=='N') {
			  strOut.addAll(knightMoves(col, row));
            }
			// Bishops
			if (Character.toUpperCase(piece)=='B') {
			  strOut.addAll(bishopMoves(col, row));
            }
            // Rooks
			if (Character.toUpperCase(piece)=='R') {
			  strOut.addAll(rookMoves(col, row));
            }
            // Queen
			if (Character.toUpperCase(piece)=='Q') {
			  strOut.addAll(queenMoves(col, row));
            }
            // King
			if (Character.toUpperCase(piece)=='K') {
			  strOut.addAll(kingMoves(col, row));
            }
		  }
        }
	  }
	  return strOut;
	}

    // translates the move from array indicies to a string representing the chess board coordinates
    public static String getMoveStr(int srcCol, int srcRow, int destCol, int destRow) {
	  StringBuilder moveStr = new StringBuilder();

	  switch (srcCol) {
        case 0: moveStr.append("a"); break;
	    case 1: moveStr.append("b"); break;
	    case 2: moveStr.append("c"); break;
	    case 3: moveStr.append("d"); break;
	    case 4: moveStr.append("e"); break;
      }	  
	  moveStr.append(String.valueOf(srcRow));
	  moveStr.append("-");
	  switch (destCol) {
        case 0: moveStr.append("a"); break;
	    case 1: moveStr.append("b"); break;
	    case 2: moveStr.append("c"); break;
	    case 3: moveStr.append("d"); break;
	    case 4: moveStr.append("e"); break;
      }
	  moveStr.append(String.valueOf(destRow));
	  moveStr.append("\n");
	  return moveStr.toString();
    }

    public static Vector<String> pawnMoves(int col, int row) {
	  Vector<String> pawnMoves = new Vector<String>();
	  // Valid pawn moves for white
	  if (nextMove == 'W') {
		// moving forward
		if (isValid(col, row + 1) && board[row + 1][col] == '.')
		  pawnMoves.add(getMoveStr(col, row + 1, col, row + 2));
		// moving diagonally left
		if (isValid(col-1, row + 1) && isEnemy(board[row + 1][col - 1]))
		  pawnMoves.add(getMoveStr(col, row + 1, col - 1, row + 2));
		// moving diagonally right
		if (isValid(col + 1, row + 1) && isEnemy(board[row + 1][col + 1]))
		  pawnMoves.add(getMoveStr(col, row + 1, col + 1, row + 2));
      }

	  // valid pawn moves for black
	  else if (nextMove == 'B') {
		// moving forward
		if (isValid(col, row - 1) && board[row - 1][col] == '.')
		  pawnMoves.add(getMoveStr(col, row + 1, col, row));
		// moving diagonally left
		if (isValid(col - 1, row - 1) && isEnemy(board[row - 1][col - 1]))
		  pawnMoves.add(getMoveStr(col, row + 1, col - 1, row));
		// moving diagonally right
		if (isValid(col + 1, row - 1) && isEnemy(board[row - 1][col + 1]))
		  pawnMoves.add(getMoveStr(col, row + 1, col + 1, row));
      }
	  return pawnMoves;	  
    }
    public static Vector<String> knightMoves(int col, int row) {
	  Vector<String> knightMoves = new Vector<String>();
	  // Valid knight moves for white
	  if (nextMove == 'W') {
	    // moving forward then right
		if (isValid(col + 1, row + 2) && !isOwn(board[row + 2][col + 1]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 1, row + 3));
		// moving forward then left
		if (isValid(col - 1, row + 2) && !isOwn(board[row + 2][col - 1]))
		  knightMoves.add(getMoveStr(col, row + 1, col - 1, row + 3));
	    // moving backward then right
		if (isValid(col + 1, row - 2) && !isOwn(board[row - 2][col + 1]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 1, row - 1));
		// moving backward then left
		if (isValid(col - 1, row - 2) && !isOwn(board[row - 2][col - 1]))		          
          knightMoves.add(getMoveStr(col, row + 1, col - 1, row - 1));
	    // moving right then forward
		if (isValid(col + 2, row + 1) && !isOwn(board[row + 1][col + 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 2, row  + 2));
		// moving left then forward
		if (isValid(col - 2, row + 1) && !isOwn(board[row + 1][col - 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col - 2, row  + 2));
		// moving right then backward
		if (isValid(col + 2, row - 1) && !isOwn(board[row - 1][col + 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 2, row));
	    // moving left then backward
		if (isValid(col - 2, row - 1) && !isOwn(board[row - 1][col - 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col - 2, row));
	  }
	  // Valid knight moves for black
	  else if (nextMove == 'B') {
	    // moving forward then right
		if (isValid(col + 1, row - 2) && !isOwn(board[row - 2][col + 1]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 1, row -1));
		// moving forward then left
		if (isValid(col - 1, row - 2) && !isOwn(board[row - 2][col - 1]))
		  knightMoves.add(getMoveStr(col, row + 1, col - 1, row - 1));

	    // moving backward then right
		if (isValid(col + 1, row + 2) && !isOwn(board[row + 2][col + 1]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 1, row + 3));

		// moving backward then left
		if (isValid(col - 1, row + 2) && !isOwn(board[row + 2][col - 1]))
		  knightMoves.add(getMoveStr(col, row + 1, col - 1, row + 3));

	    // moving right then forward
		if (isValid(col + 2, row - 1) && !isOwn(board[row - 1][col + 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 2, row));

		// moving left then forward
		if (isValid(col - 2, row - 1) && !isOwn(board[row - 1][col - 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col - 2, row));

		// moving right then backward
		if (isValid(col + 2, row + 1) && !isOwn(board[row + 1][col + 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col + 2, row + 2));

	    // moving left then backward
		if (isValid(col - 2, row + 1) && !isOwn(board[row + 1][col - 2]))
		  knightMoves.add(getMoveStr(col, row + 1, col - 2, row + 2));
      }
	  return knightMoves;
    }
    public static Vector<String> bishopMoves(int col, int row) {
	  Vector<String> bishopMoves = new Vector<String>();
	  int c;
	  int r;
	  // valid bishop moves for white
	  if (nextMove == 'W') {
		// changing tile colors when not capturing
		if (isValid(col, row + 1) && board[row + 1][col] == '.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col, row + 2));
        }
		// moving backward
		if (isValid(col, row - 1) && board[row - 1][col] == '.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col, row));
        }
		// moving right
		if (isValid(col + 1, row) && board[row][col + 1] =='.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col + 1, row + 1));
        }
		// moving left
		if (isValid(col - 1, row) && board[row][col - 1] == '.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col - 1, row + 1));
        }
    	// moving forward and to the right
		c = col + 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r++;
        }
	    // moving forward and to the left
		c = col - 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r++;
        }
	    // moving backward and to the right
		c = col + 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r--;
        }
	    // moving backward and to the left
		c = col - 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r--;
        }
	  }
	  // valid bishop moves for black
	  else if (nextMove == 'B') {
		// changing tile color when not capturing
		// moving forward
		if (isValid(col, row - 1) && board[row - 1][col] == '.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col, row));
        }
		// moving backward
		if (isValid(col, row + 1) && board[row + 1][col] == '.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col, row + 2));
        }
		// moving right
		if (isValid(col + 1, row) && board[row][col + 1] == '.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col + 1, row + 1));
        }
		// moving left
		if (isValid(col - 1, row) && board[row][col - 1] == '.') {
		  bishopMoves.add(getMoveStr(col, row + 1, col - 1, row + 1));
        }
	    // moving forward and to the right
		c = col + 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r--;
        }
	    // moving forward and to the left
		c = col - 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r--;
        }
	    // moving backward and to the right
		c = col + 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r++;
        }
	    // moving backward and to the left
		c = col - 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  bishopMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r++;
        }
	  }
	  return bishopMoves;
    }
    public static Vector<String> rookMoves(int col, int row) {
	  Vector<String> rookMoves = new Vector<String>();
	  int c;
	  int r;
	  // valid rook moves for white
	  if (nextMove == 'W') {
    	// moving forward
		c = col;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r++;
        }
	    // moving backward
		c = col;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r--;
        }
	    // moving to the right
		c = col + 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
        }
	    // moving to the left
		c = col - 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
        }
	  }
	  // valid rook moves for black
	  else if (nextMove == 'B') {
    	// moving forward
		c = col;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r--;
        }
	    // moving backward
		c = col;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r++;
        }
	    // moving to the right
		c = col + 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
        }
	    // moving to the left
		c = col - 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  rookMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
        }

      }
	  return rookMoves;
    }
    public static Vector<String> queenMoves(int col, int row) {
	  Vector<String> queenMoves = new Vector<String>();
	  int c;
	  int r;
	  // valid queen moves for white
	  if (nextMove == 'W') {
		// moving forward
		c = col;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r++;
		}
		// moving forward and to the right
		c = col + 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r++;
		}
		// moving forward and to the left
		c = col - 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r++;
		}
		// moving backward
		c = col;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r--;
		}
		// moving backward and to the right
		c = col + 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r--;
		}
		// moving backward and to the left
		c = col - 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r--;
		}
	    // moving to the right
		c = col + 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
        }
	    // moving to the left
		c = col - 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
        }

      }

	  // valid queen moves for black
	  else if (nextMove == 'B') {
		// moving forward
		c = col;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r--;
        }
		// moving forward and to the right
		c = col + 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r--;
        }
		// moving forward and to the left
		c = col - 1;
		r = row - 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r--;
        }
		// moving backward
		c = col;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  r++;
        }

		// moving backward and to the right
		c = col + 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
		  r++;
        }
		// moving backward and to the left
		c = col - 1;
		r = row + 1;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
		  r++;
        }
	    // moving to the right
		c = col + 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c++;
        }
	    // moving to the left
		c = col - 1;
		r = row;
		while (isValid (c, r) && !isOwn(board[r][c])) {
		  queenMoves.add(getMoveStr(col, row + 1, c, r + 1));
		  // cannot jump pieces
		  if (isEnemy(board[r][c])) break;
		  c--;
        }

      }
	  return queenMoves;
    }
    public static Vector<String> kingMoves(int col, int row) {
	  Vector<String> kingMoves = new Vector<String>();
	  // valid king moves for white
	  if (nextMove == 'W') {
		// moving forward
		if (isValid(col, row + 1) && !isOwn(board[row + 1][col])) {
		  kingMoves.add(getMoveStr(col, row + 1, col, row + 2));
        }
		// moving backward
		if (isValid(col, row - 1) && !isOwn(board[row - 1][col])) {
		  kingMoves.add(getMoveStr(col, row + 1, col, row));
        }
		// moving right
		if (isValid(col + 1, row) && !isOwn(board[row][col + 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col + 1, row + 1));
        }
		// moving left
		if (isValid(col - 1, row) && !isOwn(board[row][col - 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col - 1, row + 1));
        }
		// moving forward then right
		if (isValid(col + 1, row + 1) && !isOwn(board[row + 1][col + 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col + 1, row + 2));
        }
		// moving forward then left
		if (isValid(col - 1, row + 1) && !isOwn(board[row + 1][col - 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col - 1, row + 2));
        }
		// moving backward then right
		if (isValid(col + 1, row - 1) && !isOwn(board[row - 1][col + 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col + 1, row));
        }

		// moving backward then left
		if (isValid(col - 1, row - 1) && !isOwn(board[row - 1][col - 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col - 1, row));
        }
      }
	  // valid queen moves for black
	  else if (nextMove == 'B') {
		// moving forward
		if (isValid(col, row - 1) && !isOwn(board[row - 1][col])) {
		  kingMoves.add(getMoveStr(col, row + 1, col, row));
        }
		// moving backward
		if (isValid(col, row + 1) && !isOwn(board[row + 1][col])) {
		  kingMoves.add(getMoveStr(col, row + 1, col, row + 2));
        }
		// moving right
		if (isValid(col + 1, row) && !isOwn(board[row][col + 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col + 1, row + 1));
        }
		// moving left
		if (isValid(col - 1, row) && !isOwn(board[row][col - 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col - 1, row + 1));
        }
		// moving forward then right
		if (isValid(col + 1, row - 1) && !isOwn(board[row - 1][col + 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col + 1, row));
        }
		// moving forward then left
		if (isValid(col - 1, row - 1) && !isOwn(board[row - 1][col - 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col - 1, row));
        }
		// moving backward then right
		if (isValid(col + 1, row + 1) && !isOwn(board[row + 1][col + 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col + 1, row + 2));
        }

		// moving backward then left
		if (isValid(col - 1, row + 1) && !isOwn(board[row + 1][col - 1])) {
		  kingMoves.add(getMoveStr(col, row + 1, col - 1, row + 2));
        }

      }
	  return kingMoves;
    }
	
	// perform the supplied move (for example "a5-a4\n") and update the state of the game / your internal variables accordingly - 
    // note that it advised to do a sanity check of the supplied move
	public static void move(String charIn) {
	  char srcCol = charIn.charAt(0);
	  char destCol = charIn.charAt(3);
	  int srcColIdx;
	  int destColIdx;
	  int srcRowIdx;
	  int destRowIdx;
	  char [] plyChars = new char[2];

	  if (playCount < 41 && winner() == '?') {
	  // add current state of the board to boardHist for undo functionality
	  boardHist.push(boardGet());

	  // translate to array indecies
	  srcColIdx = colIndex(srcCol);
	  srcRowIdx = Character.getNumericValue(charIn.charAt(1)) - 1;
	  destColIdx = colIndex(destCol);
	  destRowIdx = Character.getNumericValue(charIn.charAt(4)) - 1;

	  // update vars for ply and color on move and queen promotion
	  if (nextMove == 'W') {
		nextMove = 'B';
		if (board[srcRowIdx][srcColIdx] == 'P' && destRowIdx == 5) {
  	      board[srcRowIdx][srcColIdx] = '.';
		  board[destRowIdx][destColIdx] = 'Q';
        }
		else {
	      // update board for all other moves
	      board[destRowIdx][destColIdx] = board[srcRowIdx][srcColIdx];
	      board[srcRowIdx][srcColIdx] = '.';
        }
	  }
	  else if (nextMove == 'B') {
		nextMove = 'W';
		if (board[srcRowIdx][srcColIdx] == 'p' && destRowIdx == 0) {
  	      board[srcRowIdx][srcColIdx] = '.';
		  board[destRowIdx][destColIdx] = 'q';
        }
		else {
	      // update board for all other moves
	      board[destRowIdx][destColIdx] = board[srcRowIdx][srcColIdx];
	      board[srcRowIdx][srcColIdx] = '.';
        }
		playCount++;
      }

	  plyChars = ("" + playCount).toCharArray();

	  // update board with ply and next move
	  if (playCount < 10) {
		board[6][0] = plyChars[0];
		board[6][1] = ' ';
		board[6][2] = nextMove;
		board[6][3] = '\n';
      }
	  else if (playCount >= 10) {
		board[6][0] = plyChars[0];
		board[6][1] = plyChars[1];
		board[6][2] = ' ';
		board[6][3] = nextMove;
		board[6][4] = '\n';
      }
	  }
	}
    public static int colIndex(char col) {
	  int idx = 0;
	  switch(col) {
	    case 'a': idx = 0; break;
	    case 'b': idx = 1; break; 
	    case 'c': idx = 2; break;
	    case 'd': idx = 3; break;
	    case 'e': idx = 4; break;
      }
	  return idx;
    }


    // **************************************************************
    // *********************** END OF HOMEOWORK 2 *******************
    // **************************************************************

	// undo the last move and update the state of the game / your internal variables accordingly - 
    // note that you need to maintain an internal variable that keeps track of the previous history for this	
	public static void undo() {
	  boardSet(boardHist.pop());
	}

	// with reference to the state of the game, determine the possible moves and shuffle them before returning them - 
    // note that you can call the chess.moves() function in here
	public static Vector<String> movesShuffled() {
	  Vector<String> rawMoves = moves();
	  Collections.shuffle(rawMoves);
	  return rawMoves;
	}
	
	// with reference to the state of the game, determine the possible moves and sort them in order of an increasing evaluation score before returning them - 
    // note that you can call the chess.movesShuffled() function in here
    public static Vector<String> movesEvaluated() {
	  Vector<String> sortedMoves = new Vector<String>();
	  Vector<String> randMoves = movesShuffled();
	  RatedMove[] movesWithEval = new RatedMove[randMoves.size()];

	  for(int i=0; i < randMoves.size(); i++) {
		// do the move
		move(randMoves.get(i));
		// store the move and eval score
	    movesWithEval[i] = new RatedMove(randMoves.get(i), eval());
		// undo the move
		undo();
      }

	  // sort moves in increasing order
	  Arrays.sort(movesWithEval);

	  // create a list that just has the moves sorted by eval score
	  for(RatedMove m: movesWithEval) {
		sortedMoves.add(m.getMove());
      }

	  return sortedMoves;
    }


    // Class for managing a list of moves assosiated with an eval score
    public static class RatedMove implements Comparable<RatedMove> {
	  public String move;
	  public int eval;

	  public RatedMove(String m, int ev) {
	    this.move = m;
	    this.eval = ev;
      }
	  public String getMove() { return move;}
	  public int getEval() { return eval;}
	  public int compareTo(RatedMove compareMove) {
	    int compareEval = ((RatedMove) compareMove).getEval();
	    // sort in ascending order
	    return this.eval - compareEval;
      }
    }


    // **************************************************************
    // *********************** END OF HOMEOWORK 3 *******************
    // **************************************************************


	// perform a random move and return it - one example output is given below - 
	// note that you can call the chess.movesShuffled() function as well as the chess.move() function in here	
	public static String moveRandom() {
	  Vector<String> moves = movesShuffled();
	  String randmove = moves.get(0);
	  move(randmove);		
	  return randmove;
	}

	// perform a greedy move and return it - one example output is given below - 
    // note that you can call the chess.movesEvaluated() function as well as the chess.move() function in here
	public static String moveGreedy() {
	  Vector<String> moves = movesEvaluated();
	  String greedymove = moves.get(0);
	  move(greedymove);		
	  return greedymove;
	}


    // **************************************************************
    // *********************** END OF HOMEOWORK 4 *******************
    // **************************************************************


    public static int negaMax(int depth) {
	  // reched the end
	  if (depth == 0 || (winner() != '?')) return eval();

	  int score =  negInfinity;

	  // moves() returns a vector of strings
	  for (String m: moves()) {
		move(m);
		score = Math.max(score, -negaMax(depth - 1));
		undo();
      }
	  return score;
    }
	// perform a negamax move and return it - one example output is given below - note that you can call the the other functions in here
	public static String moveNegamax(int depth, int duration) {

	  String bestMove = new String();
	  int score =  negInfinity;
	  int temp = 0;

	  for (String m: moves()) {
		//perform the move
		move(m);
		
		temp = -negaMax(depth-1);
		// undo move
		undo();

		if (temp > score) {
		  bestMove = m;
		  score = temp;
        }
      }
	  move(bestMove);
	  return bestMove;
	}


    // **************************************************************
    // *********************** END OF HOMEOWORK 5 *******************
    // **************************************************************

    public static int alphabeta(int depth, int alpha, int beta) {
	  if ((depth == 0) || (winner() != '?')) return eval();

	  int score = negInfinity;

	  for (String m: moves()) {
		move(m);
		score = Math.max(score, -alphabeta(depth - 1, -beta, -alpha));
		undo();

		alpha = Math.max(alpha, score);

		if (alpha >= beta) break;
      } 
	  return score;
    }
	// perform a alphabeta move and return it - one example output is given below - note that you can call the the other functions in here		
	public static String moveAlphabeta(int depth, int duration) {
	  String bestMove = new String();
	  int alpha = negInfinity;
	  int beta = posInfinity;
	  int temp = 0;

	  for (String m: moves()) {
		move(m);
		temp = -alphabeta(depth - 1, -beta, -alpha);
		undo();

		if (temp > alpha) {
		  bestMove = m;
		  alpha = temp;
        }
      }
		
	  return bestMove;
	}


    // **************************************************************
    // *********************** END OF HOMEOWORK 6 *******************
    // **************************************************************
	
}
