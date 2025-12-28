import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
                if((in[1].equals("echo")) || (in[1].equals("type")) || (in[1].equals("exit")) || (in[1].equals("pwd"))){
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
                        System.out.println(cmd + ": not found");
                    }
                }
            }
            else if(in[0].equals("pwd")){
                String dir = System.getProperty("user.dir");
                System.out.println(dir);
            }
            else if(in[0].equals("cd")){
                Path curDir = Path.of(System.getProperty("user.dir"));
                Path nex = curDir.resolve(Path.of(in[1])).normalize();
                if(!Files.isDirectory(nex)){
                    System.out.println("cd: " + nex + ": No such file or directory");
                }
                else{
                    curDir = nex;
                }
            }
            else{
                String cmd = in[0];
                boolean ok = false;
                List<String> arg = new ArrayList<>();
                for(String dir : cwd){
                    Path p = Path.of(dir , cmd);
                    if(Files.exists(p) && Files.isExecutable(p)){
                        arg.add(cmd);
                        for(int i = 1 ; i < in.length ; i++){
                            arg.add(in[i]);
                        }
                        Process process = new ProcessBuilder(arg).start();
                        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                            String line;
                            while((line = reader.readLine()) != null){
                                System.out.println(line);
                            }
                        }
                        int exitcode = process.waitFor();
                        ok = true;
                        break;
                    }
                }
                if(!ok){
                    System.out.println(input + ": not found");
                }
            }
        }
    }
}
