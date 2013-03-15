package duo;

import static duo.Color.*;
import java.lang.String;

/** Represents a Blokus Duo(tm) game board.
 * @author Ke Ma
 */
class Board {

    /** Number of rows and columns in a board. */
    public static final int _SIZE = 14;
    /** The 2-D Array that represents the board. */
    protected Color[][] _board = new Color[_SIZE][_SIZE];
    /** The String that represents pieces (should be the first element of each move). */
    public static final String _PIECES = "WZILUTXVFPYNzidstv321";
    /** The String that represents positions (should be the second or third element of each move). */
    public static final String _POSITIONS = "0123456789abcd";
    /** The String that represents the orientations (should be the last element of each move). */
    public static final String _ORIENTATIONS = "01234567";
    /** The Color that represents the turn. ORANGE is the first player. */
    public static Color _playerOnMove = ORANGE;
    /** The String that represents the previous move. */
    public String _prevMove = "";
    /** The 2-D int array that reprents the status for ORNAGE player. */
    public int[][] _orangeStatusBoard = new int[16][16];
    /** The 2-D int array that reprents the status for VIOLET player. */
    public int[][] _violetStatusBoard = new int[16][16];

    /** Return the current contents of the square in column COL and row ROW. */
    Color get(int col, int row) {
        return _board[row][col];  // FIXED.
    }

    /** Constructor for Board. */
    Board() {
        int i, j;
        for (i = 0; i < 14; i++) {
            for (j = 0; j < 14; j++) {
                _board[i][j] = EMPTY;
            }
        }
        int m, n;
        for (m = 0; m < 16; m++) {
            _orangeStatusBoard[0][m] = 2;
            _orangeStatusBoard[15][m] = 2;
            _violetStatusBoard[0][m] = 2;
            _violetStatusBoard[15][m] = 2;
        }
        for (n = 0; n < 16; n++) {
            _orangeStatusBoard[n][0] = 2;
            _orangeStatusBoard[n][15] = 2;
            _violetStatusBoard[n][0] = 2;
            _violetStatusBoard[n][15] = 2;
        }
        _orangeStatusBoard[1][1] = 1;
        _orangeStatusBoard[1][14] = 1;
        _orangeStatusBoard[14][1] = 1;
        _orangeStatusBoard[14][14] = 1;
        _violetStatusBoard[1][1] = 1;
        _violetStatusBoard[1][14] = 1;
        _violetStatusBoard[14][1] = 1;
        _violetStatusBoard[14][14] = 1;

    }

    /** Return the color of player whose turn it is. */
    Color playerOnMove() {
        return _playerOnMove;
    }

    /** Returns true iff MOVE is a syntactically correct move. */
    static boolean isWellFormed(String move) {
        if (move.length() != 4) {
            return false;
        } else {
            boolean mPieces = _PIECES.indexOf(move.substring(0, 1)) != -1;
            boolean mCol = _POSITIONS.indexOf(move.substring(1, 2)) != -1;
            boolean mRow = _POSITIONS.indexOf(move.substring(2, 3)) != -1;
            boolean mOrientations = _ORIENTATIONS.indexOf(move.substring(3, 4)) != -1;
            return mPieces && mCol && mRow && mOrientations;
        }
    }

    /** Returns true iff MOVE is currently legal. */
    boolean isLegal(String move) {
        int[][] theStatusBoard;
        if (_playerOnMove == ORANGE) {
            theStatusBoard = _orangeStatusBoard;
        } else {
            theStatusBoard = _violetStatusBoard;
        }
        Pieces thispiece = new Pieces();
        String piecename = move.substring(0, 1);
        int col = getCoordinate(move.substring(1, 2));
        int row = getCoordinate(move.substring(2, 3));
        int ori = Integer.parseInt(move.substring(3, 4));
        int[][] initialPositions = thispiece.getInitialPositions(piecename);
        int[][] finalPositions = thispiece.processPositions(initialPositions, ori);
        int depth = finalPositions.length;
        int length = finalPositions[0].length;

        if (row + depth - 1 > 13 || col + length - 1 > 13) {
            System.out.println("Your move makes your piece out of the board, try again!");
            return false;
        }

        boolean has1 = false;
        boolean no2 = true;

        int i, j;
        for (i = 0; i < depth; i++) {
            for (j = 0; j < length; j++) {
                if (finalPositions[i][j] == 1) {
                    if (theStatusBoard[15 - (row + depth - i)][col + j + 1] == 1) {
                        has1 = true;
                    } else if (theStatusBoard[15 - (row + depth - i)][col + j + 1] == 2) {
                        return false;
                    }
                }
            }
        }
        System.out.println("has1: " + has1);
        return has1;
    }

    static int getCoordinate(String s) {
        int i;
        if (s.equals("a")) {
            i = 10;
        } else if (s.equals("b")) {
            i = 11;
        } else if (s.equals("c")) {
            i = 12;
        } else if (s.equals("d")) {
            i = 13;
        } else {
            i = Integer.parseInt(s);
        }
        return i;
    }

    /** Returns the boolean that represents a player can move. */
    boolean canMove(Color player) {
        int[][] myStatusBoard;
        if (_playerOnMove == ORANGE) {
            myStatusBoard = _orangeStatusBoard;
        } else {
            myStatusBoard = _violetStatusBoard;
        }
        if (Game._totalMoves < 2) {
            return true;
        }
        int i, j;
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {
                if (myStatusBoard[i][j] == 1) {
                    if (findMove(i, j, myStatusBoard)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Returns true if there is a legal move. 
     * @param row int
     * @param col int
     * @param 
     * @return boolean
     */
    boolean findMove(int row, int col, int[][] someStatusBoard) {
        String leftPieces;
        if (_playerOnMove == ORANGE) {
            leftPieces = Game._orangePieces;
        } else {
            leftPieces = Game._violetPieces;
        }
        String trialMove = "";
        int numleftPieces = leftPieces.length();
        int l, ori;
        for (l = 0; l < numleftPieces; l++) {
            for (ori = 0; ori < 8; ori++) {
                String piecename = leftPieces.substring(l, l + 1);
                Pieces thispiece = new Pieces();
                int[][] initialPositions = thispiece.getInitialPositions(piecename);
                int[][] finalPositions = thispiece.processPositions(initialPositions, ori);
                int depth = finalPositions.length;
                int length = finalPositions[0].length;

                int m, n;
                for (m = 0; m < depth; m++) {
                    for (n = 0; n < length; n++) {
                        if (finalPositions[m][n] == 1) {
                            int newCol = col - 1 - n;
                            int newRow = 15 - row - depth;
                            if (newCol >= 0 && newRow >= 0) {
                            trialMove = piecename + changeBack(newCol) + changeBack(newRow) + ori;
                            System.out.println(newCol);
                            System.out.println(newRow);
                            System.out.println(trialMove);
                            if (isLegal(trialMove)) {
                                return true;
                            }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /** Returns the number of ORANGE squares on the board. */
    int orangeSquares() {
        int s = 0;
        int i, j;
        for (i = 0; i < 14; i++) {
            for (j = 0; j < 14; j++) {
                if (_board[i][j] == ORANGE) {
                    s++;
                }
            }
        }
        return s;
    }

    /** Returns the number of VIOLET squares on the board. */
    int violetSquares() {
        int s = 0;
        int i, j;
        for (i = 0; i < 14; i++) {
            for (j = 0; j < 14; j++) {
                if (_board[i][j] == VIOLET) {
                    s++;
                }
            }
        }
        return s;
    }

    String getPrevMove() {
        return _prevMove;
    }
    
    String changeBack(int CoordinateInNum) {
        String s = "";
        if (CoordinateInNum == 10) {
            s = "a";
        } else if (CoordinateInNum == 11) {
            s = "b";
        } else if (CoordinateInNum == 12) {
            s = "c";
        } else if (CoordinateInNum == 13) {
            s = "d";
        } else {
            s = Integer.toString(CoordinateInNum);
        }
        return s;
    }
    /* Add methods and fill in */
    
}
