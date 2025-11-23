import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. N 입력
        int n = Integer.parseInt(br.readLine());

        // 2. 수열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기값 설정
        // 첫 번째 숫자를 입력받아 초기 최대값과 현재 합으로 설정
        int firstVal = Integer.parseInt(st.nextToken());
        int currentSum = firstVal; // i번째 숫자까지의 연속 합 중 최댓값
        int maxSum = firstVal;     // 전체 최댓값

        // 3. 두 번째 숫자부터 N번째 숫자까지 순회
        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 점화식 적용:
            // (이전 합 + 현재 숫자)와 (현재 숫자) 중 더 큰 것을 선택
            // 즉, 이전 합이 음수라서 손해라면 버리고 현재 숫자부터 새로 시작함
            currentSum = Math.max(num, currentSum + num);

            // 전체 최댓값 갱신
            maxSum = Math.max(maxSum, currentSum);
        }

        // 4. 결과 출력
        System.out.println(maxSum);
    }
}