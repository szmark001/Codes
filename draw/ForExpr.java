package draw;

import java.util.*;

/**
 * The class that handles the ForExpr.
 * @author Ke Ma
 */
public class ForExpr extends Combination {

    /** The List that stores the ILU and commands */
    List<Expr> _operands = new ArrayList<Expr>();
    /** The List that stores the I L and U */
    List<Expr> _ilu = new ArrayList<Expr>();
    /** The List that stores the commands */
    List<Expr> _commands = new ArrayList<Expr>();

    /**
     * The constructor of ForExpr.
     * @param operands List<Expr>.
     */
    ForExpr(List<Expr> operands) {
        super(operands);
        _operands = operands;
        if (_operands.size() > 3) {
            _ilu = _operands.subList(0, 3);
            _commands = _operands.subList(3, _operands.size());
        }
    }

    /**
     * The execution of a ForExpr, which executes the commands according to the Specs.
     * @param machine Interpreter
     * @return Value
     */
    @Override
    Value execute(Interpreter machine) {
        System.out.println(_ilu);
        String k = _ilu.get(0).getString();
        double l = _ilu.get(1).execute(machine).doubleValue();
        double u = _ilu.get(2).execute(machine).doubleValue();
        if (u < l) {
            return null;
        } else {
            for (; l <= u; l++) {
                Interpreter.getTable().put(k, new Number(l));
                for (Expr a : _commands) {
                    a.execute(machine);
                }
            }
        }
        return null;
    }

    /**
     * Simply overrides.
     * @param operands List<Expr>
     * @return 
     */
    @Override
    Expr create(List<Expr> operands) {
        return null;
    }
}
