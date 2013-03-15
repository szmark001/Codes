package draw;

import java.util.*;

/**
 * The class that handles the filled circles.
 * @author Zihan Zhou cs61b-fn 21804833
 */
public class FilledCircExpr extends Combination {

    /** The List that stores the center coordinates and the radius */
    private List<Expr> _xyr;

    /**
     * The constructor of FilledCircExpr.
     * @param operands List<Expr>
     */
    Filledcircexpr(List<Expr> operands) {
        _xyr = operands;
    }

    /**
     * The execution of an FilledCircExpr, which returns a new filled circle. 
     * @param machine Interpreter
     * @return Value
     */
    Value execute(Interpreter machine) {
        double xcor = _xyr.get(0).execute(machine).doubleValue();
        double ycor = _xyr.get(1).execute(machine).doubleValue();
        double radius = _xyr.get(2).execute(machine).doubleValue();
        return new FilledCircle(xcor, ycor, radius);
    }

    /**
     * simply overrides.
     * @param operands List<Expr>
     * @return Expr */
    Expr create(List<Expr> operands) {
        return null;
    }
}
