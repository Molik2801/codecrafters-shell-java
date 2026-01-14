import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    
    public Input(){
        Runtime.getRuntime()
                .addShutdownHook(
                    new Thread(
                        () -> {
                            resetTerminalRawMode();
                        }));
    }

    private static void setTerminalRawMode(){
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void resetTerminalRawMode(){
        String[] cmd = {"/bin/sh", "-c", "stty sane </dev/tty"};
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String input(BufferedReader inp , StringBuilder sb) throws IOException{
        setTerminalRawMode();
        StringBuilder cur = new StringBuilder();
        char c;
        while(true){
            c = (char)inp.read();
            
            if(c == 0x0D || c == 0x0A){
                System.out.print("\r\n");
                sb.append(cur.toString());
                String inputLine = sb.toString();
                sb.setLength(0);
                resetTerminalRawMode();
                return inputLine;
            }
            else if(c == 0x7F){
                System.out.print("\b \b");
                if(cur.length() == 0){
                    if(sb.length() == 0)continue;
                    sb.setLength(sb.length() - 1);
                }
                else{
                    cur.setLength(cur.length() - 1);
                }
            }
            else if(c == 0x09){
                String comp = Completion.Complete(cur.toString());
                String half = cur.toString();
                sb.append(half + comp);
                cur.setLength(0);
                System.out.print(comp);
            }
            else if(c == ' '){
                sb.append(cur.toString() + " ");
                cur.setLength(0);
                System.out.print(" ");
            }
            else{
                cur.append(c);
                System.out.print(c);
            }
        }
    }
}
