// This is a SUGGESTED skeleton file.  Throw it away if you want.
package draw;

/** Superclass describing values manipulated by the drawing
 *  system (stored into variables, or passed as function arguments).
 *  Provides default implementations for catching errors.
 *  @author
 */
abstract class Value {

    /** Return my value as a number.  Throws IllegalStateException if I am
     *  not a number.  Default implementation. */
    double doubleValue() {
        throw new IllegalStateException("value is not a number");
    }

    /** Return my value as a Picture.  Throws IllegalStateException if I am not
     *  a Picture.  Default implementation. */
    Picture pictureValue() {
        throw new IllegalStateException("value is not a picture");
    }
}


