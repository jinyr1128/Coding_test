import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 테스트 케이스의 수
        int[] dp = new int[11]; // dp 배열 초기화, n은 11보다 작다

        // 초기 조건 설정
        dp[0] = 1;
        if (dp.length > 1) dp[1] = 1;
        if (dp.length > 2) dp[2] = 2;

        // DP 배열 채우기, n이 3 이상일 때
        for (int i = 3; i < 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        // 각 테스트 케이스 처리
        for (int t = 0; t < T; t++) {
            int n = scanner.nextInt();
            System.out.println(dp[n]);
        }
        
        scanner.close();
    }
}
