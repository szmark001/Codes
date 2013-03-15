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
public class GroupExpr extends Combination {
    
    GroupExpr(List<Expr> operands) {
        super(operands);
    }
    
    Value execute(Interpreter machine) { 
        List<Picture> newlist = new ArrayList<Picture>();
        if (_operands != null) {
            for (Expr a : _operands) {
                newlist.add(a.execute(machine).pictureValue());
            }
        }
        return new GroupPicture(newlist); 
    }

    @Override
    Expr create(List<Expr> operands) {
        return null;
    }
    
}
