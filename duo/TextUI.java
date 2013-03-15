package duo;

import static duo.Color.*;
import java.util.Scanner;

/** A simple textual implementation of the user interface.
 *  @author Ke Ma 
 */
class TextUI implements UI {

    @Override
    public String getMove(Color color, int numMoves, String prevMove) {
        System.out.println(Board._playerOnMove);
        System.out.print("Player " + color + "'s move. ");
        System.out.print("There has been " + numMoves + " moves so far. ");
        if (numMoves > 0) {
            System.out.print("previous move was: " + prevMove + " .");
        }
        System.out.println("Please make your move.");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.next();
        if (input.isEmpty()) {
            System.out.println("Your input is empty. Please move.");
            return getMove(color, numMoves, prevMove);
        } else {
            return input;
        }
    }

    @Override
    public void reportWinner(int orangeScore, int violetScore) {
        if (orangeScore > violetScore) {
            System.out.println("Orange" + " " + "wins" + " " + "(" + orangeScore + "-" + violetScore + ")");
        } else if (orangeScore < violetScore) {
            System.out.println("Violet" + " " + "wins" + " " + "(" + orangeScore + "-" + violetScore + ")");
        } else {
            System.out.println("Tie" + " " + "game" + " " + "(" + orangeScore + "-" + violetScore + ")");
        }
    }

    @Override
    public void reportBoard(Board board) {
        // FILL IN
    }

    @Override
    public void reportBoardStandard(Board board) {
        System.out.print("===");
        int m, n;
        for (m = 0; m < 14; m++) {
            System.out.println("");
            System.out.print(" " + " ");
            for (n = 0; n < 14; n++) {
                if (board.get(n, m) == ORANGE) {
                    System.out.print("O");
                } else if (board.get(n, m) == VIOLET) {
                    System.out.print("V");
                } else {
                    System.out.print("-");
                }
            }
        }
        System.out.println("");
        System.out.print("===");
    }

    @Override
    public void reportMove(Color color, int numMoves, String move) {
        // FILL IN
    }

    @Override
    public void reportError(String message) {
        System.err.println(message);
    }

    @Override
    public void report(String message) {
        System.out.println(message);
    }
}
