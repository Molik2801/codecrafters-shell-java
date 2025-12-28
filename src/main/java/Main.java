import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String cwd[] = System.getenv("PATH").split(":");
        while(true){
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] in = input.split(" ");
            if(in[0].equals("exit")){ 
                break;
            }
            else if(in[0].equals("echo")){
                String res = "";
                for(int i = 1 ; i < in.length; i++){
                    res += in[i];
                    res += " ";
                }
                System.out.println(res);
            }
            else if(in[0].equals("type")){
                if(in.length < 2){
                    System.out.println("type: missing operand");
                    continue;
                }
                if((in[1].equals("echo")) || (in[1].equals("type")) || (in[1].equals("exit"))){
                    System.out.println(in[1] + " is a shell builtin");
                }
                else {
                    String cmd = in[1];
                    boolean ok = false;
                    for(String dir : cwd){
                        Path p = Path.of(dir , cmd);
                        if(Files.exists(p) && Files.isExecutable(p)){
                            System.out.println(cmd + " is " + p.toString());
                            ok = true;
                            break;
                        }
                    }
                    if(!ok){
                        System.out.println(input + ": not found");
                    }
                }
            }
            else{
                System.out.println(input + ": not found");
            }
        }
    }
}
