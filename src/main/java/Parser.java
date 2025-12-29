import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> parse(String input){
        List<String> parsedInput = new ArrayList<>();
        int gap = 0;
        int quo = 0;
        String temp = "";
        for(int i = 0 ; i < input.length() ; i++){
            if(input.charAt(i) == ' '){
                if(gap == 0){
                    gap = 1;
                    parsedInput.add(temp);
                    temp = "";
                }
                else{
                    if(quo == 1){
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
            else if(input.charAt(i) == '\''){
                quo = quo ^ 1;
            }
            else{
                temp += input.charAt(i);
            }
        }
        if(!temp.isEmpty())parsedInput.add(temp);
        return parsedInput;
    }
}
