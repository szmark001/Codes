package duo;

import static duo.Color.*;

/** Abstract class of all players.
* @author Ke Ma 
*/
class AI extends Player {
   
   int _currentBestValue;
   String _move;

   /** An AI named NAME, playing the COLOR pieces, and using UI
    *  for messages.  This kind of player computes its own moves. */
   AI(String name, UI ui) {
       super(name, ui);
   }

   
   @Override
   void move() {
       _move = "";
       _currentBestValue = -999999;
       Board currentBoard = _game.getBoard();
       int[][] myStatusBoard;
       int[][] opStatusBoard;
       
       if (currentBoard.playerOnMove() == ORANGE) {
           myStatusBoard = currentBoard._orangeStatusBoard;
           opStatusBoard = currentBoard._violetStatusBoard;
       } else {
           myStatusBoard = currentBoard._violetStatusBoard;
           opStatusBoard = currentBoard._orangeStatusBoard;
       }
       
       int i, j;
       for (i = 0; i < 16; i++) {
           for (j = 0; j < 16; j++) {
               if (myStatusBoard[i][j] == 1) {
                   chooseMove(i, j, myStatusBoard);
               }
           }
       }
       _game.move(_move);
   }
   
   /** Returns the best move for a certain "[i][j] == 1" point. */
   void chooseMove(int row, int col, int[][] myStatusBoard) {
       String leftPieces;
       Board currentBoard = _game.getBoard();
       
       if (currentBoard.playerOnMove() == ORANGE) {
           leftPieces = Game._orangePieces;
       } else {
           leftPieces = Game._violetPieces;
       }  
       
       System.out.println(leftPieces);
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
                           trialMove = piecename + currentBoard.changeBack(newCol) + currentBoard.changeBack(newRow) + ori;                       
                           if (currentBoard.isLegal(trialMove)) {
                               if (calcMove(trialMove, myStatusBoard) > _currentBestValue) {
                                   _currentBestValue = calcMove(trialMove, myStatusBoard);
                                   _move = trialMove;
                                   System.out.println(_move);
                               }
                           }
                           }
                       }
                   }
               }
           }
       }
   }
   
   /** Returns the point of a certain move with respect to a certain StatusBoard. */
   int calcMove(String move, int[][] myStatusBoard) {
       int value = 0;
       Pieces thispiece = new Pieces();
       String piecename = move.substring(0, 1);
       int ori = Integer.parseInt(move.substring(3, 4));
       int[][] initialPositions = thispiece.getInitialPositions(piecename);
       int[][] finalPositions = thispiece.processPositions(initialPositions, ori);
       value = numOfSquares(finalPositions) * 10 + numOfOnes(myStatusBoard)*1000 - numOfTwos(myStatusBoard)*200;
       return value;
       
   }



   
   /** Return the number of squares of a certain piece. */
   int numOfSquares(int[][] somePiece) {
       int s = 0;
       int depth = somePiece.length;
       int length = somePiece[0].length;
       int i, j;
       for (i = 0; i < depth; i++) {
           for (j = 0; j < length; j++) {
               if (somePiece[i][j] == 1) {
                   s++;
               }
           }
       }
       return s;
   }
   
   /** Return the number of 1's in a certain statusBoard. */
   int numOfOnes(int[][] someStatusBoard) {
       int s = 0;
       int i, j;
       for (i = 0; i < 16; i++) {
           for (j = 0; j < 16; j++) {
               if (someStatusBoard[i][j] == 1) {
                   s++;
               }
           }
       }
       return s;      
   }
   
   /** Return the number of 2's in a certain statusBoard,
    * not counting the outmost 2's and the 2's of a piece itself. */
   int numOfTwos(int[][] someStatusBoard) {
       int s = 0;
       int i, j;
       for (i = 1; i < 15; i++) {
           for (j = 1; j < 15; j ++) {
               if (someStatusBoard[i][j] == 2) {
                   s++;
               }
           }
       }
       return s;
   }
   
   /** Return the number of 0's in a certain statusBoard. */
   int numOfZeros(int[][] someStatusBoard) {
       int s = 0;
       int i, j;
       for (i = 0; i < 16; i++) {
           for (j = 0; j < 16; j++) {
               if (someStatusBoard[i][j] == 0) {
                   s++;
               }
           }
       }
       return s;      
   }

}