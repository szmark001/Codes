package duo;

import static duo.Color.*;

/** Abstract class of all players.
 * @author Ke Ma
 */
class Human extends Player {

    /** A Human player named NAME, playing the COLOR pieces, and using UI
     *  for input and messages.  This kind of player prompts for moves
     *  from the user. */
    Human(String name, UI ui) {
        super(name, ui);
    }

    @Override
    void move() {
        Board theBoard = _game.getBoard();
        String prevMove = theBoard.getPrevMove();
        String move = _ui.getMove(theBoard._playerOnMove, _game.getNumMoves(), prevMove);
        _game.move(move);
    }

}