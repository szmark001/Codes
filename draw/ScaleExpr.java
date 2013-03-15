/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;
import java.util.*;

/**
 *
 * @author Zihan Zhou cs61b-fn 21804833
 */
public class ScaleExpr extends Combination {
    
    ScaleExpr(List<Expr> operands) {
        super(operands);
    }
    
    Value execute(Interpreter machine) {  
        Value x = _operands.get(0).execute(machine);
        double factor = _operands.get(1).execute(machine).doubleValue();    
        return x.pictureValue().scale(factor);             
    }
    
    @Override
    Expr create(List<Expr> operands) {
        return null;
    } 
    
}
