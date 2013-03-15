package draw;

import java.util.*;

/**
 * The class that handles Circles.
 * @author Ke Ma
 */
public class CircExpr extends Combination {

    /** The List that stores the center coordinates and the radius */
    private List<Expr> _xyr;

    /**
     * The constructor of CircExpr.
     * @param operands List<Expr>
     */
    Circexpr(List<Expr> operands) {
        _xyr = operands;
    }

    /**
     * The execution of a CircExpr, which returns a new Circle.
     * @param machine Interpreter
     * @return Value 
     */
    Value execute(Interpreter machine) {
        double xcor = _xyr.get(0).execute(machine).doubleValue();
        double ycor = _xyr.get(1).execute(machine).doubleValue();
        double radius = _xyr.get(2).execute(machine).doubleValue();
        return new Circle(xcor, ycor, radius);
    }

    /**
     * simply overrides.
     * @param operands List<Expr>
     * @return Expr
     */
    Expr create(List<Expr> operands) {
        return null;
    }
}
