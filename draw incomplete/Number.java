// This is a SUGGESTED skeleton file.  Throw it away if you want.
package draw;

/** A numeric value in the interpreter.
 */
class Number extends Value {

    /** A Number representing VALUE. */
    Number(double value) {
        _value = value;
    }

    Number(String svalue) {
        _svalue = svalue;
    }

    @Override
    double doubleValue() {
        return _value;
    }
    


    /** The value I represent. */
    private double _value;
    private String _svalue;
}
