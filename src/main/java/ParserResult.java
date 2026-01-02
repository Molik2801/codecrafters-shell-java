import java.util.List;

public class ParserResult {
    public final List<String> tokens;
    public String redirection = "";

    public ParserResult(List<String> token , String redirections){
        this.tokens = token;
        this.redirection = redirections;
    }
}
