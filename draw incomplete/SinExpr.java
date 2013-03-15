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
public class SinExpr extends Combination {
    private List<Expr> _myOperands;
    
    SinExpr(List<Expr> operands) {
        super(operands);
        _myOperands = operands;
    }
    
    @Override
    Value execute(Interpreter machine) {
        double a = _myOperands.get(0).execute(machine).doubleValue();
        double b = Math.sin(a);
        return new Number(b);
    }


    @Override
    Expr create(List<Expr> operands) {
        return new Numeral(execute(null).doubleValue());
    }

    
    
}