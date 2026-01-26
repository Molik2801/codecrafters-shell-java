import org.jline.reader.*;
import java.util.*;

public class ShellCompleter implements Completer {

    private static final List<String> COMMANDS =
            List.of("echo", "exit", "pwd", "type", "ls", "cat");

    @Override
    public void complete(LineReader reader,
                         ParsedLine line,
                         List<Candidate> candidates) {

        String word = line.word();

        for (String cmd : COMMANDS) {
            if (cmd.startsWith(word)) {
                candidates.add(new Candidate(cmd));
            }
        }
    }
}
