import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 한 줄 입력받기
        String input = br.readLine();

        // 대문자로 변환
        String upper = input.toUpperCase();

        // 출력
        System.out.println(upper);
    }
}
