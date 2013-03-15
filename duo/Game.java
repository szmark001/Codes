package duo;

import java.util.Random;
import static duo.Color.*;
import java.lang.String;

/** Supervisor for a game of Duo.
 *  @author Ke Ma 
 */
class Game {

    /** The List that represents the 21 pieces of the ORANGE player. */
    public static String _orangePieces = "WZILUTXVFPYNzidst3v21";
    /** The List that represents the 21 pieces of the VIOLET player. */
    public static String _violetPieces = "WZILUTXVFPYNzidst3v21";
    /** The integer that counts the total moves. */
    public static int _totalMoves = 0;

    /** A new Game representing one game between ORANGEPLAYER and
     *  VIOLETPLAYER.  Initially, the board is empty. Uses UI for input
     *  and output.  If SEED is non-zero, uses it to seed a random
     *  number generator. */
    Game(Player orangePlayer, Player violetPlayer, UI ui, long seed) {
        _player[0] = orangePlayer;
        _player[1] = violetPlayer;
        _ui = ui;
        if (seed == 0) {
            _rand = new Random();
        } else {
            _rand = new Random(seed);
        }
        _board = new MutableBoard();
    }

    /** Returns the current board (immutably). */
    Board getBoard() {
        return _board;
    }

    /** Increase the total moves by 1. */
    static void increaseMoves() {
        _totalMoves = _totalMoves + 1;
    }

    /** Returns the number of moves that have been made. */
    int getNumMoves() {
        return _totalMoves;  // FIXED
    }

    /** Perform MOVE on the current board (the color of the piece
     *  placed depends on whose move it is). */
    void move(String move) {      
        if (move.substring(0, 1).equals("b")) {
            _ui.reportBoardStandard(_board);
            if (_board.playerOnMove() == ORANGE) {
                _player[0].move();
            } else {
                _player[1].move();
            }
        } else if (move.substring(0, 1).equals("q")) {
            System.out.println("Game Exit!");
            System.exit(911);
        } 
        else if (move.substring(0, 1).equals("h")) {
            
               if (_board.playerOnMove().equals(ORANGE)) {
                   System.out.println("myBoard:");
                   System.out.println(toString(_board.getStatusBoard(_board.playerOnMove())));
                   //System.out.println("other board:");
                   //System.out.println(toString(_board.getStatusBoard(VIOLET)));
               } else {
                   System.out.println("myBoard:");
                   System.out.println(toString(_board.getStatusBoard(_board.playerOnMove())));
                   //System.out.println("other board:");
                   //System.out.println(toString(_board.getStatusBoard(ORANGE)));
               }
               if (_board.playerOnMove() == ORANGE) {
                _player[0].move();
            } else {
                _player[1].move();
            }
           }
        
        else if (_board.playerOnMove() == ORANGE) {
            if (!_board.isWellFormed(move)) {
                System.out.println("error: Your move is syntactically incorrect, try again!");
                _player[0].move();
            }
            else if (_orangePieces.indexOf(move.substring(0, 1)) != -1) {
                if (!_board.isLegal(move)) {
                    System.out.println("eror: Your move is illegal, try again!");
                    _player[0].move();
                } else {
                    _board.makeMove(move);
                }
            } else {
                System.out.println("This ORANGE piece is not available!");
                _player[0].move();
            }



        } else if (_board.playerOnMove() == VIOLET){
            if (!_board.isWellFormed(move)) {
                System.out.println("error: Your move is syntactically incorrect, try again!");
                _player[1].move();
            } else if (_violetPieces.indexOf(move.substring(0, 1)) != -1) {
                if (!_board.isLegal(move)) {
                    System.out.println("eror: Your move is illegal, try again!");
                    _player[1].move();
                } else {
                    _board.makeMove(move);
                }
            } else {
                System.out.println("This VIOLET piece is not available!");
                _player[1].move();
            }
        }
        else {
            System.out.println("move: "+move+" why is this even reached?");
        }
       
    }

    /** Starting from the current board, complete a game between the
     *  two players, reporting all results on my Reporter. */
    void play() {
        Game game = this;
        _player[0].startGame(game, ORANGE);
        _player[1].startGame(game, VIOLET);
        Color currentPlayer = _board.playerOnMove();
        while (_board.canMove(currentPlayer)) {
            if (currentPlayer == ORANGE) {
                _player[0].move();
            } else {
                _player[1].move();
            }
            currentPlayer = _board.playerOnMove();
        }
        _ui.reportWinner(_board.orangeSquares(), _board.violetSquares());
        _ui.reportBoardStandard(_board);
    }

    /** Returns a uniformly distributed pseudo-random integer between
     *  0 and N-1 (inclusive).  Assumes N > 0. */
    int nextRand(int n) {
        return _rand.nextInt(n);
    }

    /** Returns a uniformly distributed pseudo-random int. */
    int nextRand() {
        return _rand.nextInt();
    }
    /** The players in this game: orange is _player[0] and violet
     *  is _player[1]. */
    private final Player[] _player = new Player[2];
    /** The current board. */
    private MutableBoard _board;
    /** The UI I and my players use for input and output. */
    private final UI _ui;
    /** A random number generator for use by my players. */
    private Random _rand;

    static String toString(int[][] x) {
       String out = "";
       int r = x.length;
       int c = x[0].length;
       for (int i = 0; i < r; i++) {
           for (int j = 0; j < c; j++) {
               out = out + x[i][j];
           }
           out = out + "\n";
       }
       return out;
   }
    
    public String getOPieces() {
        return _orangePieces;
    }
    
    public String getVPieces() {
        return _violetPieces;
    }

}
