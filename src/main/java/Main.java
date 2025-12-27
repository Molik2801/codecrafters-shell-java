import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        while(true){
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] in = input.split("input");
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
            System.out.println(input + ": command not found");
        }
    }
}
