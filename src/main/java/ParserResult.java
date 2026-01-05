import java.util.List;

public class ParserResult {
    public final List<String> tokens;
    public String redirection = "";
    public int std = 0;
    String Action = "";

    public ParserResult(List<String> token , String redirections , int std , String Action){
        this.tokens = token;
        this.redirection = redirections;
        this.std = std;
        this.Action = Action;
    }
}
