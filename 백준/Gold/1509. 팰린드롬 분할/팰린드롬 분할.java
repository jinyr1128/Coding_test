import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        boolean[][] isPalindrome = new boolean[n][n];
        int[] dp = new int[n];

        // 모든 길이 1의 부분 문자열은 팰린드롬이다.
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }

        // 길이 2의 부분 문자열에 대한 팰린드롬을 확인
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPalindrome[i][i + 1] = true;
            }
        }

        // 길이가 3 이상인 부분 문자열에 대한 팰린드롬을 확인
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1]) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        // dp 배열을 이용하여 최소 팰린드롬 분할 개수 계산
        for (int i = 0; i < n; i++) {
            if (isPalindrome[0][i]) {
                dp[i] = 1;
            } else {
                dp[i] = i + 1;  // 최악의 경우, 모든 문자를 하나씩 분할
                for (int j = 1; j <= i; j++) {
                    if (isPalindrome[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }

        System.out.println(dp[n - 1]);
    }
}
