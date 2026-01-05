import java.util.ArrayList;
import java.util.List;

public class Parser {
    public ParserResult parse(String input){
        List<String> parsedInput = new ArrayList<>();
        String redirection = "";
        int singleQuote = 0;
        int doubleQuote = 0;
        String temp = "";
        int redir = -1;
        int std = 0;
        String Action = "";
        for(int i = 0 ; i < input.length() ; i++){
            if(input.charAt(i) == '>'){
                redir = i+1;
                Action = "Redirect";
                break;
            }
            if((i+1 < input.length()) && ((input.charAt(i) >= '1') && (input.charAt(i) <= '9')) && (input.charAt(i+1) == '>')){
                redir = i+2;
                std = input.charAt(i) - '0';
                Action = "Redirect";
                break;
            }
            if((i+1 < input.length()) && (input.charAt(i) == '>') && (input.charAt(i+1) == '>')){
                redir = i+2;
                Action = "Append";
                break;
            }
            if((i+2 < input.length()) && (input.charAt(i) <= '9') && (input.charAt(i) >= '1') && (input.charAt(i+1) == '>') && (input.charAt(i+2) == '>')){
                redir = i+3;
                std = input.charAt(i) - '0';
                Action = "Append";
                break;
            }
            else if(input.charAt(i) == ' '){
                if((singleQuote == 1) || (doubleQuote == 1)){
                    temp += ' ';
                }
                else{
                    if(!temp.isEmpty()){
                        parsedInput.add(temp);
                        temp = "";
                    }
                }
            }
            else if(input.charAt(i) == '\\'){
                if((doubleQuote == 1) && ((input.charAt(i+1) == '\\') || (input.charAt(i+1) == '\"'))){
                    temp += input.charAt(i+1);
                    i++;
                }
                else if((doubleQuote == 1) || (singleQuote == 1)){
                    temp += input.charAt(i);
                }
                else{
                    temp += input.charAt(i+1);
                    i++;
                }
            }
            else if(input.charAt(i) == '\"'){
                if(singleQuote == 1){
                    temp += input.charAt(i);
                }
                else if(doubleQuote == 0){
                    singleQuote = -1;
                    doubleQuote = 1;
                }
                else{
                    singleQuote = 0;
                    doubleQuote = 0;
                }
            }
            else if(input.charAt(i) == '\''){
                if(singleQuote == -1){
                    temp += input.charAt(i);
                }
                else{
                    singleQuote = singleQuote ^ 1;
                }
            }
            else{
                temp += input.charAt(i);
            }
        }
        if(!temp.isEmpty())parsedInput.add(temp);
        if(redir != -1){
            while(redir < input.length()){
                redirection += input.charAt(redir);
                redir++;
            }
        }

        redirection = redirection.trim();
        if (redirection.startsWith("\"") && redirection.endsWith("\"")) {
            redirection = redirection.substring(1, redirection.length() - 1);
        }
        if (redirection.startsWith("\'") && redirection.endsWith("\'")) {
            redirection = redirection.substring(1, redirection.length() - 1);
        }
        ParserResult result = new ParserResult(parsedInput, redirection , std , Action);
        return result;
    }
}

