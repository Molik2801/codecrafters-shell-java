import java.util.ArrayList;
import java.util.List;

public class Parser {
    public ParserResult parse(String input){
        List<String> parsedInput = new ArrayList<>();
        String redirection = "";
        int gap = 0;
        int singleQuote = 0;
        int doubleQuote = 0;
        String temp = "";
        int redir = -1;
        for(int i = 0 ; i < input.length() ; i++){
            if((input.charAt(i) == '>') || ((temp.charAt(i) == '1') && (temp.charAt(i+1) == '>'))){
                redir = i+1;
            }
            if(input.charAt(i) == ' '){
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
            while(temp.charAt(redir) != ' '){
                redirection += temp.charAt(redir);
                redir++;
            }
        }

        ParserResult result = new ParserResult(parsedInput, redirection);
        return result;
    }
}

