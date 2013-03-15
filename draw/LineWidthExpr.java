/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;
import java.util.*;

/**
 *
 * @author Ke Ma
 */
public class LineWidthExpr extends Combination {   
    private double _linewidth;
    
    LineWidthExpr(List<Expr> operands) {
        super(operands);
    }
    
    @Override
    Value execute(Interpreter machine) {           
        _linewidth = _operands.get(0).execute(machine).doubleValue();
        GraphicsState.setLinewidth(_linewidth);
        return new Number(_linewidth);
        }

    @Override
    Expr create(List<Expr> operands) {
        return new LineWidthExpr(operands);
    } 
    
}
