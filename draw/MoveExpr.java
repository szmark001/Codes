package draw;
import java.util.*;

/**
 * This class handles the move operation.
 * @author Ke Ma
 */
public class MoveExpr extends Combination {
    /** The List that stores the operands to move. */
    private List<Expr> _operands;
    
    /**
     * The constructor of MoveExpr.
     * @param operands List<Expr>
     */
    MoveExpr(List<Expr> operands) {
        super(operands);
        _operands = operands;
    }
    
    /**
     * The execution of a MoveExpr, which moves a picture.
     * @param machine Interpreter
     * @return Value
     */
    Value execute(Interpreter machine) {
        if (_operands != null) {
        Value x = _operands.get(0).execute(machine);
        double deltax = _operands.get(1).execute(machine).doubleValue();
        double deltay = _operands.get(2).execute(machine).doubleValue();     
        return x.pictureValue().translate(deltax, deltay);              
    } return null;
    }
    
    @Override
    Expr create(List<Expr> operands) {
        return null;
    } 
    
}
