package draw;
import java.util.List;

/**
 * This class handles the cosine operation.
 * @author Ke Ma
 */
public class CosExpr extends Combination {
    /** The List<Expr> that stores the operands to CosExpr. */
    Private List<Expr> _operands;

    /**
     * Constructor of CosExpr which takes operands as its argument.
     * @param operands List<Expr>
     */
    Cosexpr(List<Expr> operands) {
        super(operands);
        _operands = operands;
    }

    /**
     * Execute returns a Number c which is the cos value of the argument.
     * @param machine Interpreter
     * @return Value */
    @Override
    Value execute(Interpreter machine) {
        if (_operands.size() != 1) {
            System.err.println("error: Incorrect number of arguments");
            System.exit(1);
        }
        double a = _operands.get(0).execute(machine).doubleValue();
        a = a * Math.PI / 180;
        double b = Math.cos(a);
        return new Number(b);
    }

    /**
     * Simply overrides.
     * @param operands List<Expr>
     * @return Expr */
    @Override
    Expr create(List<Expr> operands) {
        return new Numeral(execute(null).doubleValue());
    }
}