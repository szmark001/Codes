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
public class RectExpr extends Expr{
    /** The List that stores the starting point and the width and height of the rectangle */
    private List<Expr> _xywh;
    /** The List that stores the color of the rectangle being created */
    private List<Expr> _rectRGB;
    /** The double that store the linewidth of the rectangle being created */
    private double _rectLWidth;

    RectExpr(List<Expr> xywh) {
        _xywh = xywh;
        _rectRGB = (GraphicsState.getRGB());
        _rectLWidth = (GraphicsState.getLinewidth());        
    }

    @overide
    Value Execute(Interpreter machine) {
        double xcor = _xywh.get(0).execute(machine).doubleValue();
        double ycor = _xywh.get(1).execute(machine).doubleValue();
        double width = _xywh.get(2).execute(machine).doubleValue();
        double height = _xywh.get(3).execute(machine).doubleValue();
        return new Picture
    }

    @Override
    Expr create(List<Expr> operands) {
        return new Numeral(execute(null).doubleValue());
    }
        
    }


}