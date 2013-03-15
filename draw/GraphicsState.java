package draw;

import java.util.*;

/**
 * The class that stores the current graphics state.
 * @author Ke Ma
 */
public class GraphicsState {

    /** The List that stores the RGB values. */
    private static List<Expr> _rgb = new ArrayList<Expr>();
    /** The double that stores the linewidth. */
    private static double _linewidth = 1;
    /** The List that store the current point. */
    private static List<Expr> _coordinate = new ArrayList<Expr>();

    /** 
     * The method that returns the RGB values
     * @return List<Expr> 
     */
    static List<Expr> getRGB() {
        System.out.println("getcolor");
        if (_rgb.size() == 0) {
            _rgb.add(new Numeral(0));
            _rgb.add(new Numeral(0));
            _rgb.add(new Numeral(0));
        }
        return _rgb;
    }

    /**
     * Set the RGB values to the corresponding values 
     * in the ArrayList newRGB and return them in an ArrayList<Numeral>.
     * @param newRGB List<Expr>
     * @return List <Expr> 
     */
    static void setRGB(List<Expr> newRGB) {
        System.out.println("setcolor");
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

    /** 
     * return the current linewidth
     * @return double
     */
    static double getLinewidth() {
        return _linewidth;
    }

    /** 
     * set the linewidth to the new value and then return it. 
     * @param newLinewidth double
     */
    static void setLinewidth(double newLinewidth) {
        _linewidth = newLinewidth;
    }

    /** 
     * returns the current xy position 
     * @return List<Expr>
     */
    static List<Expr> getCoordinate() {
        return _coordinate;
    }

    /** 
     * set the current xy position to the corresponding values in the ArrayList 
     * @param newCoordinate List<Expr>
     */
    static void setCoordinate(List<Expr> newCoordinate) {
        _coordinate = newCoordinate;
    }
}
