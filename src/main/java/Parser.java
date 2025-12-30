import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> parse(String input){
        List<String> parsedInput = new ArrayList<>();
        int gap = 0;
        int singleQuote = 0;
        int doubleQuote = 0;
        String temp = "";
        for(int i = 0 ; i < input.length() ; i++){
            if(input.charAt(i) == ' '){
                if(gap == 0){
                    gap = 1;
                    parsedInput.add(temp);
                    temp = "";
                }
                else{
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
            }
            else if(input.charAt(i) == '\"'){
                if(doubleQuote == 0){
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
        return parsedInput;
    }
}

//"helo 'world' "
