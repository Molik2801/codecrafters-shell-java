import org.jline.reader.*;
import org.jline.reader.impl.*;
import org.jline.terminal.*;
import org.jline.reader.impl.DefaultParser;

import java.util.List;

public class Input {

    private final LineReader reader;
    public Input() throws Exception {
        var parser = new DefaultParser();
        parser.setEscapeChars(new char[0]);

        Terminal terminal = TerminalBuilder.builder()
                            .system(true)
                            .build();
        
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new ShellCompleter())
                .parser(parser)
                .build();
    }

    public String input() {
        try {
            return reader.readLine("$ ");
        } catch (UserInterruptException e) {
            return "";
        } catch (EndOfFileException e) {
            System.exit(0);
            return "";
        }
    }
}
