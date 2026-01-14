import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Completion {
    public static String Complete(String s){
        Pattern pattern = Pattern.compile("^" + s + ".*");
        for(String cmd : List.of("echo" , "exit")){
            Matcher matcher = pattern.matcher(cmd);
            if(matcher.matches()){
                return cmd.replaceFirst(s , "").concat(" ");
            }
        }
        return " ";
    }
}
