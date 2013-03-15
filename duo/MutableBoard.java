package duo;

import static duo.Color.*;
import java.lang.String;

/** Represents a Blokus Duo(tm) game board that may be changed.  This
 *  is a subtype of Board so that Board itself can represent a
 *  non-modifiable game board.
 * @author Ke Ma
 */
class MutableBoard extends Board {

    /** A new, empty MutableBoard. */
    MutableBoard() {
        super();
    }

    /** A new MutableBoard whose initial contents are copied from
     *  BOARD. (AI?????) */
    MutableBoard(Board board) {
    }

    /** Make the indicated MOVE on the current board for the player
     *  that is on move. */
    void makeMove(String move) {
        _prevMove = move;
        Pieces thispiece = new Pieces();
        Color thiscolor = _playerOnMove;
        String piecename = move.substring(0, 1);
        int col = getCoordinate(move.substring(1, 2));
        int row = getCoordinate(move.substring(2, 3));
        int ori = Integer.parseInt(move.substring(3, 4));
        int[][] initialPositions = thispiece.getInitialPositions(piecename);
        int[][] finalPositions = thispiece.processPositions(initialPositions, ori);

        int depth = finalPositions.length;
        int length = finalPositions[0].length;

        int i, j;
        for (i = 0; i < depth; i++) {
            for (j = 0; j < length; j++) {
                if (finalPositions[i][j] == 1) {
                    _board[14 - (depth + row - i)][col + j] = thiscolor;
                }
            }
        }
        //Change Status Board!
        modifyStatusBoard(thiscolor, piecename, col, row, ori);

        //Swap Player!
        if (thiscolor == ORANGE) {
            _playerOnMove = VIOLET;
            int oindex = Game._orangePieces.indexOf(move.substring(0, 1));
            Game._orangePieces = Game._orangePieces.substring(0, oindex) + Game._orangePieces.substring(oindex + 1);
        } else {
            _playerOnMove = ORANGE;
            int vindex = Game._violetPieces.indexOf(move.substring(0, 1));
            Game._violetPieces = Game._violetPieces.substring(0, vindex) + Game._violetPieces.substring(vindex + 1);

        }
        Game.increaseMoves();
        if (Game._totalMoves == 1) {
            restoreStatusCorner(_orangeStatusBoard);
            System.out.println("O restored");
        }
        if (Game._totalMoves == 2) {
            restoreStatusCorner(_violetStatusBoard);
            System.out.println("V restored");
        }

        //Delete the piece that has just been moved

    }

    void modifyStatusBoard(Color thiscolor, String piecename, int col, int row, int ori) {
        int[][] myStatusBoard;
        int[][] opStatusBoard;
        if (thiscolor == ORANGE) {
            myStatusBoard = _orangeStatusBoard;
            opStatusBoard = _violetStatusBoard;
        } else {
            myStatusBoard = _violetStatusBoard;
            opStatusBoard = _orangeStatusBoard;
        }
        Pieces thepiece = new Pieces();

        int[][] initialStatus = thepiece.getStatus(piecename);
        int[][] finalStatus = thepiece.processPositions(initialStatus, ori);
        int statusDepth = finalStatus.length;
        int statusLength = finalStatus[0].length;
        int m, n;
        for (m = 0; m < statusDepth; m++) {
            for (n = 0; n < statusLength; n++) {
                if (finalStatus[m][n] > myStatusBoard[16 - (row + statusDepth - m)][col + n]) {
                    myStatusBoard[16 - (row + statusDepth - m)][col + n] = finalStatus[m][n];
                }
            }
        }
        System.out.println("StautsBoard modified.");


        int[][] initialPositions = thepiece.getInitialPositions(piecename);
        int[][] finalPositions = thepiece.processPositions(initialPositions, ori);
        int depth = finalPositions.length;
        int length = finalPositions[0].length;
        int i, j;
        for (i = 0; i < depth; i++) {
            for (j = 0; j < length; j++) {
                if (finalPositions[i][j] == 1) {
                    opStatusBoard[15 - (row + depth - i)][col + j + 1] = 2;
                }
            }
        }
    }

    int[][] getStatusBoard(Color owner) {
        if (owner.equals(ORANGE)) {
            return _orangeStatusBoard;
        } else {
            return _violetStatusBoard;
        }
    }

    void restoreStatusCorner(int[][] someStatusBoard) {
        if (someStatusBoard[1][1] == 1) {
            someStatusBoard[1][1] = 0;
        }
        if (someStatusBoard[14][1] == 1) {
            someStatusBoard[14][1] = 0;
        }
        if (someStatusBoard[1][14] == 1) {
            someStatusBoard[1][14] = 0;
        }
        if (someStatusBoard[14][14] == 1) {
            someStatusBoard[14][14] = 0;
        }
    }
    /* Add methods and fill in. */
}
