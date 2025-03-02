import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 삼항 연산자로 조건을 한 줄로 압축
        System.out.println((N % 8 == 0) ? 2 : (N % 8 == 6) ? 4 : (N % 8 == 3 || N % 8 == 7) ? 3 : N % 8);
    }
}

