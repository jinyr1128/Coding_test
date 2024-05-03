import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int M = Integer.parseInt(br.readLine());
        int S = 0;
        
        for (int i = 0; i < M; i++) {
            String[] command = br.readLine().split(" ");
            String op = command[0];
            int x;
            
            switch (op) {
                case "add":
                    x = Integer.parseInt(command[1]) - 1;
                    S |= (1 << x);
                    break;
                case "remove":
                    x = Integer.parseInt(command[1]) - 1;
                    S &= ~(1 << x);
                    break;
                case "check":
                    x = Integer.parseInt(command[1]) - 1;
                    bw.write(((S & (1 << x)) != 0 ? "1" : "0") + "\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(command[1]) - 1;
                    S ^= (1 << x);
                    break;
                case "all":
                    S = (1 << 20) - 1;
                    break;
                case "empty":
                    S = 0;
                    break;
            }
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}
