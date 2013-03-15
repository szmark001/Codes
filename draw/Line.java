/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draw;
import java.util.*;
import java.io.*;
/**
 *
 * @author Ke Ma
 */
public class Line extends Picture {
    /** the double that stores the x-coordinate of the starting point */
    private double _x0;
    /** the double that stores the y-coordinate of the starting point */
    private double _y0;
    /** the double that stores the x-coordinate of the ending point */
    private double _x1;
    /** the double that stores the y-coordinate of the ending point */
    private double _y1;
    /** the List<Expr> that stores the RGB */
    private List<Expr> _rgb = GraphicsState.getRGB();
    /** The double that stores the linewidth */
    private double _linewidth = GraphicsState.getLinewidth();
    
    
    Line(double x0, double y0, double x1, double y1) {
        _x0 = x0;
        _y0 = y0;
        _x1 = x1;
        _y1 = y1;  
    }

    void draw(PrintWriter out) {
        double r = _rgb.get(0).execute(null).doubleValue();
        double g = _rgb.get(1).execute(null).doubleValue();
        double b = _rgb.get(2).execute(null).doubleValue();
        out.println(r + " " + g + " " + b + " " + "setrgbcolor");
        out.println(_linewidth + " " + "setlinewidth");
        out.println(_x0 + " " + _y0 + " " + "moveto");
        out.println(_x1 + " " + _y1 + " " + "lineto" + " " + "stroke");
    }

    Picture scale(double factor) {
        _x0 *= factor;
        _y0 *= factor;
        _x1 *= factor;
        _y1 *= factor;
        return new Line(_x0, _y0, _x1, _y1);
    }
    
    Picture translate(double x, double y) {
        _x0 += x;
        _y0 += y;
        _x1 += x;
        _y1 += y;
        return new Line(_x0, _y0, _x1, _y1);
    }
    
    Picture rotate(double d) {
        double dradian = Math.toRadians(d);
        double cosd = Math.cos(dradian);
        double sind = Math.sin(dradian);
        double newx0 = cosd * _x0 - sind * _y0;
        double newy0 = sind * _x0 + cosd * _y0;
        double newx1 = cosd * _x1 - sind * _y1;
        double newy1 = cosd * _x1 + cosd * _y1;
        _x0 = newx0;
        _y0 = newy0;
        _x1 = newx1;
        _y1 = newy1;
        return new Line(_x0, _y0, _x1, _y1);
    }
  
}
    
    

