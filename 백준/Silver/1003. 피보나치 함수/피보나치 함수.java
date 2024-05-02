import java.util.Scanner;

public class Main {
    static int[][] dp = new int[41][2]; // [n][0]은 n번째 피보나치 수에서 0이 출력되는 횟수, [n][1]은 1이 출력되는 횟수

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 0과 1의 초기 출력 횟수 설정
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        // 2부터 40까지 각 피보나치 수에서 0과 1이 출력되는 횟수를 계산
        for (int i = 2; i <= 40; i++) {
            for (int j = 0; j <= 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 2][j]; // i번째는 (i-1)번째와 (i-2)번째의 합
            }
        }

        // 테스트 케이스 입력
        int T = scanner.nextInt();
        
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            System.out.println(dp[N][0] + " " + dp[N][1]); // N번째 피보나치 수에서 0과 1이 출력되는 횟수 출력
        }

        scanner.close();
    }
}
