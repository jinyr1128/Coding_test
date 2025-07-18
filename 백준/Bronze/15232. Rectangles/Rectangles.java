import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // R과 C 입력 받기
        int R = Integer.parseInt(br.readLine()); // 행의 개수
        int C = Integer.parseInt(br.readLine()); // 열의 개수
        
        // C개의 별을 하나의 문자열로 만들기
        String stars = "*".repeat(C); // C개의 '*' 문자열을 생성
        
        // R번 반복하여 출력
        for (int i = 0; i < R; i++) {
            System.out.println(stars); // 각 줄에 해당하는 별 출력
        }
    }
}
