package draw;

import java.util.List;

/**
 * This class handles the subtraction operation.
 * @author Zihan Zhou cs61b-fn 21804833
 */
public class SubExpr extends Combination {
    /** The List<Expr> that stores the operands to SubExpr. */
    Private List<Expr> _operands;

    /**
     * Constructor of SubExpr which takes operands as its argument.
     * @param operands List<Expr>
     */
    Subexpr(List<Expr> operands) {
        super(operands);
        _operands = operands;
    }

    /**
     * Execute returns a Number c which is the difference of the two arguments.
     * @param machine Interpreter
     * @return Value */
    @Override
    Value execute(Interpreter machine) {
        if (_operands.size() != 2) {
            System.err.println("error: Incorrect number of arguments");
            System.exit(1);
        }
        double a = _operands.get(0).execute(machine).doubleValue();
        double b = _operands.get(1).execute(machine).doubleValue();
        double c = a - b;
        return new Number(c);
    }

    /**
     * Simply overrides.
     * @param operands List<Expr>
     * @return Expr */
    @Override
    Expr create(List<Expr> operands) {
        return null;
    }
}