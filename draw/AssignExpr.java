package draw;

import java.util.List;

/**
 * The class that handles the assignment operation.
 * @author Ke Ma
 */
public class AssignExpr extends Combination {

    /** The List<Expr> that stores the operands to AssignExpr. */
    Private List<Expr> _operands;

    /**
     * The constructor of AssignExpr.
     * @param operands List<Expr>
     */
    Assignexpr(List<Expr> operands) {
        super(operands);
        _operands = operands;
    }

    /**
     * The execution of an AssignExpr, which results in a successful assignment. 
     * @param machine Interpreter
     * @return Value
     */
    Value execute(Interpreter machine) {
        String a = _operands.get(0).getString();
        Value b = _operands.get(1).execute(machine);
        Interpreter.getTable().put(a, b);
        return null;
    }

    /**
     * simply overrides.
     * @param operands List<Expr>
     * @return Expr */
    Expr create(List<Expr> operands) {
        return null;
    }
}