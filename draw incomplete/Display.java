package draw;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.InputStream;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

/** A Display is a Writer that sends all output written to it to a
 *  Postscript viewer that interprets the output as Postscript commands,
 *  displaying the results on the screen.   As with other Writers, output
 *  written to a Display need not get seen by the viewer until the user
 *  calls flush().
 *  @author P. N. Hilfinger
 */
public class Display extends Writer {

    /** Name of executable for ghostscript program.  Must be in current PATH. */
    static final String DEFAULT_DISPLAY_PROGRAM = "gs";
    /** Ghostscript name of output device. */
    static final String DEFAULT_DEVICE = "x11";
    /** Default width of of displayed image (pixels). */
    static final int DEFAULT_WIDTH = 600;
    /** Default height of of displayed image (pixels). */
    static final int DEFAULT_HEIGHT = 600;

    /** A new Display that sends output to an execution of PROGRAM. */
    public Display(String program) {
        _program = program;
        _process = null;
        _width = DEFAULT_WIDTH;
        _height = DEFAULT_HEIGHT;
        _device = DEFAULT_DEVICE;
        _closed = false;
    }

    /** A new Display that sends output to a default viewer. */
    public Display() {
        this(DEFAULT_DISPLAY_PROGRAM);
    }

    /** Returns the current display width, in pixels. */
    public int getWidth() {
        return _width;
    }

    /** Set the current display width in pixels to WIDTH>0.  Invalid after
     *  the first character of output is written. */
    public void setWidth(int width) {
        if (_process != null || _closed) {
            throw new IllegalStateException("too late to set width");
        } else if (width <= 0) {
            throw new IllegalArgumentException("invalid width");
        }
        _width = width;
    }

    /** Returns the current display height, in pixels. */
    public int getHeight() {
        return _height;
    }

    /** Set the current display height in pixels to HEIGHT>0.  Invalid after
     *  the first character of output is written. */
    public void setHeight(int height) {
        if (_process != null || _closed) {
            throw new IllegalStateException("too late to set height");
        } else if (_height <= 0) {
            throw new IllegalArgumentException("invalid height");
        }
        _height = height;
    }

    /** Write CBUF[OFF .. OFF+LEN-1] to the current viewer program, starting
     *  the display if necessary. */
    public void write(char[] cbuf, int off, int len) throws IOException {
        int nextOff;
        startProcess();
        nextOff = off;
        for (int i = off; i < off + len; i += 1) {
            if (cbuf[i] == '\n') {
                _displayIn.write(cbuf, nextOff, i - nextOff + 1);
                _displayIn.flush();
                nextOff = i + 1;
                try {
                    switch (_responseQueue.take()) {
                    case OK:
                        break;
                    case ERROR:
                        close();
                        throw new IOException("Postscript error");
                    case QUIT:
                        close();
                        throw new IOException("Viewer program terminated");
                    default:
                        assert false : "Statement should not be reached.";
                    }
                } catch (InterruptedException e) {
                    assert false : "Unexpected exception";
                }
            }
        }
        if (nextOff < off + len) {
            _displayIn.write(cbuf, nextOff, off + len - nextOff);
        }
    }

    /** Terminate the current viewer program. Further output to the
     *  display causes an exception. */
    public void close() {
        if (_closed) {
            return;
        }
        _closed = true;
        if (_process == null) {
            return;
        }
        try {
            _process.getOutputStream().close();
            _process.getInputStream().close();
        } catch (IOException e) {
            /* Ignore IOException. */
        }
        _process.destroy();
    }

    /** Make sure that any pending output has been transmitted to the display
     *  program. */
    public void flush() throws IOException {
        if (_process == null || _closed) {
            return;
        }
        _displayIn.flush();
    }

    /** Start the display process and the process to read its text output. */
    private void startProcess() throws IOException {
        if (_closed) {
            throw new IllegalStateException("Display is closed");
        }
        if (_process != null) {
            return;
        }
        ProcessBuilder p =
            new ProcessBuilder(_program,
                                "-sDEVICE=" + _device,
                                String.format("-g%dx%d", _width, _height));
        Map<String, String> env = p.environment();
        env.remove("XFILESEARCHPATH");
        _process = p.start();
        _stdoutReader = new OutReader();
        _displayIn = new OutputStreamWriter(_process.getOutputStream());
        _stdoutReader.start();
    }

    /** Format of expected messages from display process. */
    private static final Pattern RESPONSE_PATN =
        Pattern.compile("(?s)(Error:.*?)?GS(?:<\\d+)?>"
                         + "|>>showpage, press <return> to continue<<");

    /** Receiver for output from the display process.  Translates messages
     *  received to entries in _responseQueue, and terminates when the display
     *  process closes its output. */
    private class OutReader extends Thread {
        @Override
        public void run() {
            try {
                boolean foundOne;
                InputStream displayOut = _process.getInputStream();
                Scanner displayMsgs = new Scanner(displayOut);
                foundOne = false;
                while (displayMsgs.findWithinHorizon(RESPONSE_PATN, 0) != null)
                {
                    MatchResult mat = displayMsgs.match();
                    if (foundOne) {
                        _responseQueue.put(mat.group(1) != null ? ERROR : OK);
                    }
                    foundOne = true;
                }
                _responseQueue.put(QUIT);
            } catch (InterruptedException e) {
                assert false : "Unexpected exception.";
            }
        }
    }

    /** Meanings of messages from display program. */
    private static final int
        OK = 0,
        ERROR = 1,
        QUIT = 2;

    /** Queue of messages from display program. */
    private LinkedBlockingQueue<Integer> _responseQueue
        = new LinkedBlockingQueue<Integer>();
    /** True iff this display has been closed. */
    private boolean _closed;
    /** Source of display program's input. */
    private Writer _displayIn;
    /** The process running the ghostscript display program. */
    private Process _process;
    /** The thread reading output from _process. */
    private Thread _stdoutReader;
    /** Width of the displayed image (pixels). */
    private int _width;
    /** Height of the displayed image (pixels). */
    private int _height;
    /** Name of display program executable. */
    private String _program;
    /** Ghostscript device to be used for output. */
    private String _device;

}
