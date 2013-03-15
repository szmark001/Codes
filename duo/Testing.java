package duo;

import org.junit.Test;
import ucb.junit.textui;
import static org.junit.Assert.*;
import static duo.Color.*;

/** Unit tests for the duo package. */
public class Testing {

    /** Run all JUnit tests in the duo package. */
    public static void main(String[] ignored) {
        textui.runClasses(duo.Testing.class);
    }

    // Add tests.  Here's a sample.

    @Test
    public void emptyBoard() {
        Board b = new Board();
        for (int c = 0; c < b.SIZE; c += 1) {
            for (int r = 0; r < b.SIZE; r += 1) {
                assertEquals(EMPTY, b.get(c, r));
            }
        }
    }


}
