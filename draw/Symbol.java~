// This is a SUGGESTED skeleton file.  Throw it away if you want.
package draw;

/** A primitive Expr that corresponds to a numeral in a program, such as the
 *  numeral 3.0 in the statement (:= x 3.0).
 */
class Symbol extends Expr {


    /** A Numeral whose value is VALUE. */
    Symbol(String value) {
        _str = value;
    }
    
    Symbol(double value) {
        _value = new Number(value);
    }

    /** When executed, a Literal simply yields the Number formed by
     *  the argument to its constructor. */
    @Override
    Value execute(Interpreter ignored) {
        if (Interpreter.getTable().containsKey(_str)) {
            return Interpreter.getTable().get(_str);
        } else {
            System.err.println("error: Unbound variable: " + _str);
            System.exit(1);
        } return null;
    }
    
    @Override
    String getString() {
        return _str;
    }
    

    /** The value I represent. */
    private Value _value;
    private String _str;
}

