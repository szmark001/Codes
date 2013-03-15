package draw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** A simple demonstration of how Display works.  Starts a default display
 *  process and relays terminal input to it.  (Not part of the draw program).
 *  @author P. N. Hilfinger
 */
public final class Displayer {

    /** Entry to Displayer program. */
    public static void main(String... ignored) throws IOException {
        Display D = new Display();
        BufferedReader inp =
            new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = inp.readLine();
            if (line == null) {
                break;
            }
            D.append(line);
            D.append("\n");
        }

    }

    /** Not intended to be instantiated. */
    private Displayer() {
    }
}


















