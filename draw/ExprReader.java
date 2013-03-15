package draw;
import java.io.*;
import java.util.*;

/** An ExprReader reads expressions from a source given to its constructor,
 *  returning (unexecuted) Exprs as requested.
 *  @author Ke Ma
 */
class ExprReader {

    /** A new ExprReader that reads from INP. */
    private final Reader _inp;

    /**
     * The constructor of ExprReader.
     * @param inp 
     */
    ExprReader(Reader inp) {
        _inp = inp;
        _tokens = new StreamTokenizer(_inp);
        _tokens.ordinaryChar('(');
        _tokens.ordinaryChar(')');
        _tokens.wordChars((int) ':', 58);
        _tokens.commentChar((int) ';');
        _tokens.quoteChar((int) '"');
        _tokens.wordChars((int) '=', 61);
        _tokens.wordChars((int) '+', (int) '+');
        _tokens.wordChars((int) '*', (int) '*');
        _tokens.wordChars((int) '/', (int) '/');
        _tokens.wordChars((int) 'A', (int) 'Z');
        _tokens.wordChars((int) 'a', (int) 's');
    }

    /**
     * Reads and returns the next command from my input source,
     * without executing it.  Returns null at end of file.
     * @return Expr
     */
    Expr read() {
        try {
            while (_tokens.nextToken() != _tokens.TT_EOF) {
                if (_tokens.ttype == -2) {
                    Numeral a = new Numeral(_tokens.nval);
                    return a;
                } else if (_tokens.ttype == -3) {
                    String a = new String(_tokens.sval);
                    return new Symbol(a);
                } else if (_tokens.ttype == (int) '(') {
                    _tokens.nextToken();
                    String s;
                    if (_tokens.ttype == -3) {
                        s = _tokens.sval;
                    } else {
                        s = Character.toString((char) _tokens.ttype);
                    }
                    System.out.println("operator: " + s);
                    Expr x = Combination.create(s, getOperands());
                    return x;
                }
            }
        } catch (IOException e) {
            System.err.println("error: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("end of file reached");

        return null;
    }

    /**
     * gets the operands after getting the operator.
     * @Return List<Expr>
     */
    List<Expr> getOperands() {
        ArrayList<Expr> x = new ArrayList<Expr>();
        try {
            while (_tokens.nextToken() != (int) ')') {
                _tokens.pushBack();
                x.add(read());
            }
            return x;
        } catch (IOException e) {
            System.err.println("error: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    /**
     * the getter of _tokens.
     * @return StreamTokenizer
     */
    static StreamTokenizer getTokens() {
        return _tokens;
    }
    /** My input source.  We use a StreamTokenizer because it is
     *  "tuned" to the constituents of an S-expression. */
    private static StreamTokenizer _tokens;
}