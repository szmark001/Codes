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
public class RectExpr extends Combination {
    /** The List that stores the starting point and the width and height of the rectangle */
    private List<Expr> _xywh;

    RectExpr(List<Expr> xywh) {
        _xywh = xywh;      
    }

   
    Value execute(Interpreter machine) {
        double xcor = _xywh.get(0).execute(machine).doubleValue();
        double ycor = _xywh.get(1).execute(machine).doubleValue();
        double width = _xywh.get(2).execute(machine).doubleValue();
        double height = _xywh.get(3).execute(machine).doubleValue();
        return new Rectangle(xcor, ycor, width, height);
    }

   
    Expr create(List<Expr> operands) {
        return new Numeral(execute(null).doubleValue());
    }

    }


