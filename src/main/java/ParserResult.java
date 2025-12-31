import java.util.List;

public class ParserResult {
    public final List<String> tokens;
    public final String redirection;

    public ParserResult(List<String> token , String redirection){
        this.tokens = token;
        this.redirection = redirection;
    }
}
