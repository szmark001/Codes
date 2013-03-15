package duo;
import java.util.Hashtable;

/** The class that represents all the pieces. 
 * @author Ke Ma
 */
public class Pieces {
    
    /** The Hashtable that stores the pieces and their positions. */
    Hashtable<String, int[][]> _pieces = new Hashtable<String, int[][]>();
    
    /** The Hashtable that stores the pieces(status-wise) information. */
    Hashtable<String, int[][]> _piecesConstruct = new Hashtable<String, int[][]>();

    Pieces() {
        
        _pieces.put("W", new int[][] {{0, 0, 1}, {0, 1, 1}, {1, 1, 0}});
        _pieces.put("Z", new int[][] {{1, 1, 0}, {0, 1, 0}, {0, 1, 1}});
        _pieces.put("I", new int[][] {{1}, {1}, {1}, {1}, {1}});
        _pieces.put("L", new int[][] {{1, 0}, {1, 0}, {1, 0}, {1, 1}});
        _pieces.put("U", new int[][] {{1, 1}, {1, 0}, {1, 1}});
        _pieces.put("T", new int[][] {{0, 1, 0}, {0, 1, 0}, {1, 1, 1}});
        _pieces.put("X", new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}});
        _pieces.put("V", new int[][] {{0, 0, 1}, {0, 0, 1}, {1, 1, 1}});
        _pieces.put("F", new int[][] {{0, 1, 1}, {1, 1, 0}, {0, 1, 0}});
        _pieces.put("P", new int[][] {{1, 1}, {1, 1}, {1, 0}});
        _pieces.put("Y", new int[][] {{1, 0}, {1, 1}, {1, 0}, {1, 0}});
        _pieces.put("N", new int[][] {{1, 0}, {1, 0}, {1, 1}, {0, 1}});
        _pieces.put("z", new int[][] {{1, 1, 0}, {0, 1, 1}});
        _pieces.put("i", new int[][] {{1}, {1}, {1}, {1}});
        _pieces.put("d", new int[][] {{0, 1}, {0, 1}, {1, 1}});
        _pieces.put("s", new int[][] {{1, 1}, {1, 1}});
        _pieces.put("t", new int[][] {{0, 1, 0}, {1, 1, 1}});
        _pieces.put("3", new int[][] {{1}, {1}, {1}});
        _pieces.put("v", new int[][] {{1, 0}, {1, 1}});
        _pieces.put("2", new int[][] {{1}, {1}});
        _pieces.put("1", new int[][] {{1}});
        _piecesConstruct.put("W", new int[][] {{0, 0, 1, 2, 1}, {0, 1, 2, 2, 2}, {1, 2, 2, 2, 2}, {2, 2, 2, 2, 1}, {1, 2, 2, 1, 0}});
        _piecesConstruct.put("Z", new int[][] {{1, 2, 2, 1, 0}, {2, 2, 2, 2, 0}, {1, 2, 2, 2, 1}, {0, 2, 2, 2, 2}, {0, 1, 2, 2, 1}});
        _piecesConstruct.put("I", new int[][] {{1, 2, 1}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {1, 2, 1}});
        _piecesConstruct.put("L", new int[][] {{1, 2, 1, 0}, {2, 2, 2, 0}, {2, 2, 2, 0}, {2, 2, 2, 1}, {2, 2, 2, 2}, {1, 2, 2, 1}});
        _piecesConstruct.put("U", new int[][] {{1, 2, 2, 1}, {2, 2, 2, 2}, {2, 2, 2, 1}, {2, 2, 2, 2}, {1, 2, 2, 1}});
        _piecesConstruct.put("T", new int[][] {{0, 1, 2, 1, 0}, {0, 2, 2, 2, 0}, {1, 2, 2, 2, 1}, {2, 2, 2, 2, 2}, {1, 2, 2, 2, 1}});
        _piecesConstruct.put("X", new int[][] {{0, 1, 2, 1, 0}, {1, 2, 2, 2, 1}, {2, 2, 2, 2, 2}, {1, 2, 2, 2, 1}, {0, 1, 2, 1, 0}});
        _piecesConstruct.put("V", new int[][] {{0, 0, 1, 2, 1}, {0, 0, 2, 2, 2}, {1, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {1, 2, 2, 2, 1}});
        _piecesConstruct.put("F", new int[][] {{0, 1, 2, 2, 1}, {1, 2, 2, 2, 2}, {2, 2, 2, 2, 1}, {1, 2, 2, 2, 0}, {0, 1, 2, 1, 0}});
        _piecesConstruct.put("P", new int[][] {{1, 2, 2, 1}, {2, 2, 2, 2}, {2, 2, 2, 2}, {2, 2, 2, 1}, {1, 2, 1, 0}});
        _piecesConstruct.put("Y", new int[][] {{1, 2, 1, 0}, {2, 2, 2, 1}, {2, 2, 2, 2}, {2, 2, 2, 1}, {2, 2, 2, 0}, {1, 2, 1, 0}});
        _piecesConstruct.put("N", new int[][] {{1, 2, 1, 0}, {2, 2, 2, 0}, {2, 2, 2, 1}, {2, 2, 2, 2}, {1, 2, 2, 2}, {0, 1, 2, 1}});
        _piecesConstruct.put("z", new int[][] {{1, 2, 2, 1, 0}, {2, 2, 2, 2, 1}, {1, 2, 2, 2, 2}, {0, 1, 2, 2, 1}});
        _piecesConstruct.put("i", new int[][] {{1, 2, 1}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {1, 2, 1}});
        _piecesConstruct.put("d", new int[][] {{0, 1, 2, 1}, {0, 2, 2, 2}, {1, 2, 2, 2}, {2, 2, 2, 2}, {1, 2, 2, 1}});
        _piecesConstruct.put("s", new int[][] {{1, 2, 2, 1}, {2, 2, 2, 2}, {2, 2, 2, 2}, {1, 2, 2, 1}});
        _piecesConstruct.put("t", new int[][] {{0, 1, 2, 1, 0}, {1, 2, 2, 2, 1}, {2, 2, 2, 2, 2}, {1, 2, 2, 2, 1}});
        _piecesConstruct.put("3", new int[][] {{1, 2, 1}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {1, 2, 1}});
        _piecesConstruct.put("v", new int[][] {{1, 2, 1, 0}, {2, 2, 2, 1}, {2, 2, 2, 2}, {1, 2, 2, 1}});
        _piecesConstruct.put("2", new int[][] {{1, 2, 1}, {2, 2, 2}, {2, 2, 2}, {1, 2, 1}});
        _piecesConstruct.put("1", new int[][] {{1, 2, 1}, {2, 2, 2}, {1, 2, 1}});    
    }
    
    int[][] getInitialPositions(String piecename) {
        return(_pieces.get(piecename));
    }
    
    int[][] getStatus(String piecename) {
        return(_piecesConstruct.get(piecename));
    }
    
    int[][] rotate(int[][] origpositions) {
        int depth = origpositions.length;
        int length = origpositions[0].length;
        int newdepth = length;
        int newlength = depth;
        int[][] rotated = new int[newdepth][newlength];
        int i, j;
        for (i = 0; i < newdepth; i++) {
            for (j = 0; j < newlength; j++) {
                rotated[i][j] = origpositions[newlength - 1 - j][i];
            }
        }
        return rotated;
    }

    int[][] flip(int[][] origpositions) {
        int newdepth = origpositions.length;
        int newlength = origpositions[0].length;
        int[][] flipped = new int[newdepth][newlength];
        int i, j;
        for (i = 0; i < newdepth; i++) {
            for (j = 0; j < newlength; j++) {
                flipped[i][j] = origpositions[i][newlength - 1 - j];
            }
        }
        return flipped;
    }
    
    int[][] processPositions(int[][] initialPositions, int ori) {
        int[][] finalPositions;
        if (ori == 0) {
            finalPositions = initialPositions;
        } else if (ori == 1) {
            finalPositions = rotate(initialPositions);
        } else if (ori == 2) {
            finalPositions = rotate(rotate(initialPositions));
        } else if (ori == 3) {
            finalPositions = rotate(rotate(rotate(initialPositions)));
        } else if (ori == 4) {
            finalPositions = flip(initialPositions);
        } else if (ori == 5) {
            finalPositions = flip(rotate(initialPositions));
        } else if (ori == 6) {
            finalPositions = flip(rotate(rotate(initialPositions)));
        } else {
            finalPositions = flip(rotate(rotate(rotate(initialPositions))));
        }
        return finalPositions;
    }
        
     
        
}