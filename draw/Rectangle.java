package draw;

import java.util.*;
import java.io.*;

/**
 *
 * @author Zihan Zhou cs61b-fn 21804833
 */
public class Rectangle extends Picture {

    /** the List<Expr> that stores the RGB */
    private final List<Expr> _rgb;
    /** The double that stores the linewidth */
    private final double _linewidth;
    /** The doubles that represent the x-y values of the four points of a rectangle */
    private double[] _pointa = new double[2];
    private double[] _pointb = new double[2];
    private double[] _pointc = new double[2];
    private double[] _pointd = new double[2];


    Rectangle(double xcor, double ycor, double width, double height) {
        _pointa[0] = xcor;
        _pointa[1] = ycor;    
        _pointb[0] = xcor;
        _pointb[1] = ycor + height;
        _pointc[0] = xcor + width;
        _pointc[1] = ycor + height;
        _pointd[0] = xcor + width;
        _pointd[1] = ycor;
        _rgb = GraphicsState.getRGB();
        _linewidth = GraphicsState.getLinewidth();
        
    }

    Rectangle(double[] pointa, double[] pointb, double[]pointc, double[]pointd, List<Expr> rgb, double linewidth) {
        _pointa = pointa;
        _pointb = pointb;
        _pointc = pointc;
        _pointd = pointd;
        _rgb = rgb;
        _linewidth = linewidth;
    }

    @Override
    void draw(PrintWriter out) {
        double r = _rgb.get(0).execute(null).doubleValue();
        double g = _rgb.get(1).execute(null).doubleValue();
        double b = _rgb.get(2).execute(null).doubleValue();
        out.println(r + " " + g + " " + b + " " + "setrgbcolor");
        out.println(_linewidth + " " + "setlinewidth");
        out.println(_pointa[0] + " " + _pointa[1] + " " + "moveto");
        out.println(_pointb[0] + " " + _pointb[1] + " " + "lineto");
        out.println(_pointc[0] + " " + _pointc[1] + " " + "lineto");
        out.println(_pointd[0] + " " + _pointd[1] + " " + "lineto");
        out.println(_pointa[0] + " " + _pointa[1] + " " + "lineto" + " " + "stroke");
    }
    

    @Override
    Picture scale(double factor) {
        double[] newpointa = _pointa;
        double[] newpointb = _pointb;
        double[] newpointc = _pointc;
        double[] newpointd = _pointd;
        newpointa[0] *= factor;
        newpointa[1] *= factor;
        newpointb[0] *= factor;
        newpointb[1] *= factor;
        newpointc[0] *= factor;
        newpointc[1] *= factor;
        newpointd[0] *= factor;
        newpointd[1] *= factor;       
        List<Expr> rgb = _rgb;
        double linewidth = _linewidth;
        return new Rectangle(newpointa, newpointb, newpointc, newpointd, rgb, linewidth);
    }

    @Override
    Picture translate(double x, double y) {
        double[] newpointa = _pointa;
        double[] newpointb = _pointb;
        double[] newpointc = _pointc;
        double[] newpointd = _pointd;
        newpointa[0] += x;
        newpointa[1] += y;
        newpointb[0] += x;
        newpointb[1] += y;
        newpointc[0] += x;
        newpointc[1] += y;
        newpointd[0] += x;
        newpointd[1] += y; 
        List<Expr> rgb = _rgb;
        double linewidth = _linewidth;    
        return new Rectangle(newpointa, newpointb, newpointc, newpointd, rgb, linewidth);
    }

    @Override
    Picture rotate(double d) {
        double dradian = Math.toRadians(d);
        double cosd = Math.cos(dradian);
        double sind = Math.sin(dradian);
        double[] newpointa = new double[2];
        double[] newpointb = new double[2];
        double[] newpointc = new double[2];
        double[] newpointd = new double[2];
        newpointa[0] = cosd * _pointa[0] - sind * _pointa[1];
        newpointa[1] = sind * _pointa[0] + cosd * _pointa[1];
        newpointb[0] = cosd * _pointb[0] - sind * _pointb[1];
        newpointb[1] = sind * _pointb[0] + cosd * _pointb[1];
        newpointc[0] = cosd * _pointc[0] - sind * _pointc[1];
        newpointc[1] = sind * _pointc[0] + cosd * _pointc[1];
        newpointd[0] = cosd * _pointd[0] - sind * _pointd[1];
        newpointd[1] = sind * _pointd[0] + cosd * _pointd[1];
        List<Expr> rgb = _rgb;
        double linewidth = _linewidth;    
        return new Rectangle(newpointa, newpointb, newpointc, newpointd, rgb, linewidth);
    
    }
}