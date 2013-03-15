package draw;

import java.util.*;

/**
 * The class that handles the draw operation.
 * @author Ke Ma
 */
public class DrawExpr extends Combination {

    /**
     * The constructor of DrawExpr.
     * @param operands 
     */
    Drawexpr(List<Expr> operands) {
        super(operands);
    }

    /**
     * The execution of a DrawExpr, which draws the picture.
     * @param machine Interpreter
     * @return Value
     */
    @Override
    Value execute(Interpreter machine) {
        Value x = _operands.get(0).execute(machine);
        if (x != null) {
            x.pictureValue().draw(machine.getPrintWriter());
        }
        return null;
    }

    /**
     * simply overrides.
     * @param operands
     * @return Expr
     */
    @Override
    Expr create(List<Expr> operands) {
        return null;
    }
}
