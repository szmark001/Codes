package draw;

import java.util.List;

/**
 * This class handles the division operation.
 * @author Ke Ma
 */
public class DivExpr extends Combination {

    /** The List<Expr> that stores the operands to DivExpr. */
    private List<Expr> _operands;

    /**
     * Constructor of DivExpr which takes operands as its argument.
     * @param operands List<Expr>
     */
    DivExpr(List<Expr> operands) {
        super(operands);
        _operands = operands;
    }

    /**
     * Execute returns a Number c which is the quotient of the two arguments.
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
        if (b == 0) {
            System.err.println("error: Can't divide by zero");
            System.exit(1);
            return null;
        } else {
            double c = a / b;
            return new Number(c);
        }
    }

    /**
     * simply overrides.
     * @param operands List<Expr>
     * @return Expr */
    @Override
    Expr create(List<Expr> operands) {
        return new Numeral(execute(null).doubleValue());
    }
}