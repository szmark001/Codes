// This is a SUGGESTED skeleton file.  Throw it away if you want.
package draw;

import java.util.List;
import java.util.Hashtable;

/** The superclass of all types of Expr other than symbols and
 *  numerals.  A Combination has an operator and zero or more operands.
 *  @author Ke Ma
 */
abstract class Combination extends Expr {

    /** A new Combination that has OPERANDS as its operands. */
    Combination(List<Expr> operands) {
        _operands = operands;
    }
    Combination() {
        _operands = null;
    }

    /** A factory method that returns a new instance of my type of
     *  Expr with the given OPERANDS. Each subtype of Combination
     *  defines this method with one that checks that the operands
     *  are acceptable in number for the expression and constructs a new
     *  Combination of that subtype.  A single Combination of each subtype
     *  (with dummy operands) can serve as an "exemplar" for instances of
     *  that Combination.  The intent is that these exemplars, being ordinary
     *  objects, can be put into a look-up table, with the operator
     *  symbol as the look-up key.  */
    abstract Expr create(List<Expr> operands);

    /** Return a new Combination of the type corresponding to the operator
     *  OPER, and having OPERANDS as its operands. */
static Expr create(String oper, List<Expr> operands) {
        Hashtable<String, Expr> optable = new Hashtable<String, Expr>();
        optable.put("+", new AddExpr(operands));
        optable.put("-", new SubExpr(operands));
        optable.put("*", new MultExpr(operands));
        optable.put("/", new DivExpr(operands));
        optable.put("sin", new SinExpr(operands));
        optable.put("cos", new CosExpr(operands));
        optable.put("sqrt", new SqrtExpr(operands));
        optable.put(":=", new AssignExpr(operands));
        optable.put("rect", new RectExpr(operands));
        optable.put("filledrect", new FilledCircExpr(operands));
        optable.put("filledrect", new FilledRectExpr(operands));
        optable.put("line", new LineExpr(operands));
        optable.put("circ", new CircExpr(operands));
        optable.put("filledcirc", new FilledCircExpr(operands));
        optable.put("move", new MoveExpr(operands));
        optable.put("rotate", new RotateExpr(operands));
        optable.put("scale", new ScaleExpr(operands));
        optable.put("group", new GroupExpr(operands));
        optable.put("color", new ColorExpr(operands));
        optable.put("for", new ForExpr(operands));
        optable.put("linewidth", new LineWidthExpr(operands));
        optable.put("draw", new DrawExpr(operands));
        return optable.get(oper);
        
    }

    /** The list of all my operands, as provided to my constructor. */
    protected final List<Expr> _operands;

}

