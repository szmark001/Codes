package draw;

/** The superclass of all types of Expr. An Expr is denoted by
 *  an S-expression, as indicated in the specifications.
 *  @author Ke Ma
 */
abstract class Expr {

    /** Execute me, modifying the state in MACHINE as appropriate and
     *  returning the resulting Value.
     *  @param machine Interpreter */
    Abstract Value execute(Interpreter machine);
    
    /**
     * this method is overridden in the symbol class.
     * @return String 
     */
    String getString() {
        return null;
    }
}
