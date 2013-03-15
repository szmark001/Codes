package draw;
import java.util.*;
import java.io.*;
/**
 *
 * @author Ke Ma
 */
public class GroupPicture extends Picture {
    /** the List<Picture> that stores the multiple pictures in a group */
    private List<Picture> _operands;
    /** the List<Expr> that stores the RGB */
    private List<Expr> _rgb = GraphicsState.getRGB();
    /** The double that stores the linewidth */
    private double _linewidth = GraphicsState.getLinewidth();
    
    
    GroupPicture(List<Picture> operands) {
        _operands = operands;
    }

    void draw(PrintWriter out) {
        if (_operands != null) {
        for (Picture a : _operands) {
            a.draw(out);
        }
        }
    }


    Picture scale(double factor) {
        List<Picture> newop = new ArrayList<Picture>();
        if (_operands != null) {
            for (Picture a : _operands) {
              newop.add(a.scale(factor));
            }
        }
        return new GroupPicture(newop);
    }
    
    Picture translate(double x, double y) {
        List<Picture> newop = new ArrayList<Picture>();
         if (_operands != null) {
            for (Picture a : _operands) {
                newop.add(a.translate(x, y));
    }
         }
         System.out.println("translating");
         return new GroupPicture(newop);
    }
    
    Picture rotate(double d) {
        List<Picture> newop = new ArrayList<Picture>();
        if (_operands != null) {
            for (Picture a : _operands) {
                newop.add(a.rotate(d));
       
    }
         }
         return new GroupPicture(newop);
    }

}