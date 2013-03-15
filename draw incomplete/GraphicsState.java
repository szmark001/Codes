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
public class GraphicsState {
    /** The List that stores the RGB values */
    private static List<Expr> _rgb;

    /** The double that stores the linewidth */
    private static double _linewidth = 1;
    
    /** The List that store the current point */
    private static List<Expr> _coordinate;

    /** The method that returns the RGB values
     * 
     * @return List<Expr> */
    static List<Expr> getRGB() {
        if (_rgb.isEmpty()) {
            _rgb.add(new Numeral(0));
            _rgb.add(new Numeral(0));
            _rgb.add(new Numeral(0));
        }
        return _rgb;
    }

    /** Set the RGB values to the corresponding values in the ArrayList newRGB and return them in an ArrayList<Numeral> */
    /**
     * 
     * @param newRGB List<Expr>
     * @return List <Expr> */
    static void setRGB(List<Expr> newRGB) {
        double newR = newRGB.get(0).execute(null).doubleValue();
        double newG = newRGB.get(1).execute(null).doubleValue();
        double newB = newRGB.get(2).execute(null).doubleValue();
        if (newR < 0 || newR > 1) {
            System.err.println("error: Incorrect Red Value" + newR);
            System.exit(1);
        } else if (newG < 0 || newG > 1) {
            System.err.println("error: Incorrect Green Value" + newG);
            System.exit(1);
        } else if (newB < 0 || newB > 1) {
            System.err.println("error: Incorrect Blue Value" + newB);
            System.exit(1);
        } else {
            _rgb = newRGB;
        }
    }

    /** return the current linewidth */
    static double getLinewidth() {
        return _linewidth;
    }

    /** set the linewidth to the new value and then return it */
    static void setLinewidth(double newLinewidth) {
        _linewidth = newLinewidth;
    }

    /** returns the current xy position */
    static List<Expr> getCoordinate() {
        if (_coordinate.isEmpty()) {
            _coordinate.add(new Numeral(0));
            _coordinate.add(new Numeral(0));           
        }
        return _coordinate;
    }

    /** set the current xy position to the corresponding values in the ArrayList */
    static void setCoordinate(List<Expr> newCoordinate) {
        _coordinate = newCoordinate;
    }

    static void clearCoordinate() {
        _coordinate.clear();
    }

}
