package draw;

import java.util.*;

/**
 * The class that handles the setting color command.
 * @author Ke Ma
 */
public class ColorExpr extends Combination {

    /** The List<Expr> that stores the operands to ColorExpr. */
    private List<Expr> _rgb;

    /**
     * The constructor of ColorExpr.
     * @param operands List<Expr>
     */
    ColorExpr(List<Expr> operands) {
        super(operands);
        _rgb = operands;
    }

    /**
     * The execution of an ColorExpr, which results in a successful color setting. 
     * @param machine Interpreter
     * @return Value
     */
    @Override
    Value execute(Interpreter machine) {
        GraphicsState.setRGB(_rgb);
        return null;
    }

    /**
     * simply overrides.
     * @param operands List<Expr>
     * @return Expr */
    @Override
    Expr create(List<Expr> operands) {
        return new ColorExpr(operands);
    }
}
