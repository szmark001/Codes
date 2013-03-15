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
public class LineExpr extends Combination {
    /** The List that stores the starting point and ending point of the line */
    private List<Expr> _x0y0x1y1;
    
    LineExpr(List<Expr> operands) {
        _x0y0x1y1 = operands;      
    }

    Value execute(Interpreter machine) {
        double x0 = _x0y0x1y1.get(0).execute(machine).doubleValue();
        double y0 = _x0y0x1y1.get(1).execute(machine).doubleValue();
        double x1 = _x0y0x1y1.get(2).execute(machine).doubleValue();
        double y1 = _x0y0x1y1.get(3).execute(machine).doubleValue();
        return new Line(x0, y0, x1, y1);
    }
    
     Expr create(List<Expr> operands) {
        return null;
    }
}
