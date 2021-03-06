package draw;
import java.util.*;
import java.io.*;
/**
 *
 * @author Zihan Zhou cs61b-fn 21804833
 */
public class FilledCircle extends Picture {
    /** the double that stores the x-coordinate */
    private double _xcor;
    /** the double that stores the y-coordinate */
    private double _ycor;
    /** the double that stores the radius */
    private double _radius;
    /** the List<Expr> that stores the RGB */
    private final List<Expr> _rgb;
    /** The double that stores the linewidth */
    private final double _linewidth;
    /**
     *
     * @param xcor double
     * @param ycor double
     * @param radius double
     */
    Filledcircle(double xcor, double ycor, double radius) {
        _xcor = xcor;
        _ycor = ycor;
        _radius = radius;
        _rgb = GraphicsState.getRGB();
        _linewidth = GraphicsState.getLinewidth();   
    }
    
    Filledcircle(double xcor, double ycor, double radius, List<Expr> rgb, double linewidth) {
        _xcor = xcor;
        _ycor = ycor;
        _radius = radius;
        _rgb = rgb;
        _linewidth = linewidth;    
    }
    
  
    /**
     *
     * @Param out PrintWriter
     */
    Void draw(PrintWriter out) {
        double r = _rgb.get(0).execute(null).doubleValue();
        double g = _rgb.get(1).execute(null).doubleValue();
        double b = _rgb.get(2).execute(null).doubleValue();
        out.println(r + " " + g + " " + b + " " + "setrgbcolor");
        out.println(_linewidth + " " + "setlinewidth");
        out.println((_xcor + _radius) + " " + _ycor + " " + "moveto");
        out.println(_xcor + " " + _ycor + " " + _radius + " " + "0" + " " + "360" + " " + "arc" + " " + "fill");
    }
    /**
     *
     * @param factor double
     * @return Picture
     */
    Picture scale(double factor) {
        double newx = _xcor * factor;
        double newy = _ycor * factor;
        double newr = _radius * factor;
        List<Expr> newrgb = _rgb;
        double newlinewidth = _linewidth;
        return new FilledCircle(newx, newy, newr, newrgb, newlinewidth);
    }
    /**
     *
     * @Param x
     * @param y
     * @return 
     */
    Picture translate(double x, double y) {
        double newx = _xcor + x;
        double newy = _ycor + y;
        double newr = _radius;
        List<Expr> newrgb = _rgb;
        double newlinewidth = _linewidth;
        return new FilledCircle(newx, newy, newr, newrgb, newlinewidth);
    }

    Picture rotate(double d) {
        double dradian = Math.toRadians(d);
        double cosd = Math.cos(dradian);
        double sind = Math.sin(dradian);
        double newx = cosd * _xcor - sind * _ycor;
        double newy = sind * _xcor + cosd * _ycor;
        double newr = _radius;
        List<Expr> newrgb = _rgb;
        double newlinewidth = _linewidth;
        return new FilledCircle(newx, newy, newr, newrgb, newlinewidth);   
   }

}
