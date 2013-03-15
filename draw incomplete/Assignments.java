/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;

import java.util.List;

/**
 *
 * @author Ke Ma
 */
public class Assignments extends Combination {
    private List<Expr> _myOperands;

    Assignments(List<Expr> operands) {
        super(operands);
        _myOperands = operands;
    }

    @Override
    Value execute(Interpreter machine) {
        String a = _myOperands.get(0).getString();
        Value b = _myOperands.get(1).execute(machine);
        Interpreter.getTable().put(a, b);
        return null;
    }

    @Override
    Expr create(List<Expr> operands) {
        return new Assignments(operands);
    }   
}