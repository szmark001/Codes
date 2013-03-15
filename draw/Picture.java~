// This is a SUGGESTED skeleton file.  Throw it away if you want.
package draw;

import java.io.PrintWriter;
import java.util.*;

/** The interface describing all kinds of Pictures.
 */
abstract class Picture extends Value {

    Picture() {
        
    }
    Picture pictureValue() {
        return this;
    }

    /** Draw me on OUT.  Default implementation does nothing. */
    void draw(PrintWriter out) {
    }

    /** Returns new Picture resulting from scaling me by a factor of FACTOR,
     *  which must be > 0.  Default implementation cannot be scaled. */
    Picture scale(double factor) {
        throw new IllegalArgumentException("picture cannot be scaled");
    }

    /** Return new Picture resulting from translating me by an amount (X, Y).
     *  Default implementation cannot be translated. */
    Picture translate(double x, double y) {
        throw new IllegalArgumentException("picture cannot be translated");
    }

    /** Return new Picture resulting from rotating me by D degrees
     *  counterclockwise.  Default implementation cannot be rotated. */
    Picture rotate(double d) {
        throw new IllegalArgumentException("picture cannot be rotated");
    }

}
