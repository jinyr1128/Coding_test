import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 백준/프로그래머스 등에서 Main 클래스로 제출해야 하는 경우 이 코드를 사용하세요.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 값을 정수로 읽어옵니다.
        int n = Integer.parseInt(br.readLine());

        // N개의 꼭짓점에서 4개를 선택하는 조합의 수를 계산합니다.
        // N * (N-1) * (N-2) * (N-3) 은 값이 커질 수 있으므로 long 타입으로 계산하여 오버플로우를 방지합니다.
        long combinations = (long)n * (n - 1) * (n - 2) * (n - 3) / 24;

        // 결과를 출력합니다.
        System.out.println(combinations);
    }
}