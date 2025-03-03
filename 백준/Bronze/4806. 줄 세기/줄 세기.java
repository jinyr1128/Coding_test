import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int lineCount = 0;
        while (br.readLine() != null) { // 입력이 끝날 때까지 읽음
            lineCount++;
        }
        
        System.out.println(lineCount);
    }
}
