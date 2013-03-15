// This is a SUGGESTED skeleton file.  Throw it away if you want.
package draw;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.io.IOException;
import java.util.*;

/** An Interpreter maintains all necessary information about the running
 *  program, such as the values of variables and the destination of output.
 */
class Interpreter {

    /** An Interpreter that prints its Postscript output on
     *  PSOUT. */
    private static Hashtable<String, Value> _varvalTable = new Hashtable<String, Value>();
    
    Interpreter(PrintWriter psOut) {
        _psOut = psOut; 
        
    }

    /** Read a drawing program from RDR and execute it. */

    void readAndExecute(ExprReader rdr) {
        try {
            _psOut.println("%!PS-Adobe-2.0\n\n");
            while (ExprReader.getTokens().nextToken() != -1) {
                ExprReader.getTokens().pushBack();
                rdr.read().execute(new Interpreter(_psOut));
            }           
            _psOut.println("\n\nshowpage");
        } catch (IOException e) {
                System.err.println("error: " + e.getMessage());
                System.exit(1);            
            }
        }
        
 
    static Hashtable<String, Value> getTable() {
        return _varvalTable;
    }

    private PrintWriter _psOut;

    // Fill this in to supply all information needed to execute Exprs.

}
