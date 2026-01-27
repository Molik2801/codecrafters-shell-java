import org.jline.reader.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class ShellCompleter implements Completer {

    private static final List<String> COMMANDS = new ArrayList<>(
        List.of("echo", "exit", "pwd", "type", "ls", "cat")
    );
    static String cwd[] = System.getenv("PATH").split(":");

    static {
        for (String dir : cwd) {
            Path dirPath = Path.of(dir);

            if (!Files.isDirectory(dirPath)) continue;

            try (var stream = Files.list(dirPath)) {
                stream
                    .filter(Files::isRegularFile)
                    .filter(Files::isExecutable)
                    .map(p -> p.getFileName().toString())
                    .forEach(COMMANDS::add);
            } catch (IOException ignored) {}
        }
    }

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
