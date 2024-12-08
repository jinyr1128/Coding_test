import java.io.*;
import java.util.*;

public class Main {

    static int n, s;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        dp = new boolean[n + 1][s + 1];

        // DP로 상태 탐색
        if (canFormTree(n - 2, 0)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    // 재귀적 탐색 함수
    static boolean canFormTree(int remainingNodes, int currentSum) {
        if (remainingNodes == 0 && currentSum == s) {
            return true;
        }
        if (remainingNodes == 0 || currentSum > s) {
            return false;
        }
        if (dp[remainingNodes][currentSum]) {
            return false;
        }
        dp[remainingNodes][currentSum] = true;

        // 가능한 모든 경우 탐색
        for (int i = 1; i <= remainingNodes; i++) {
            if (canFormTree(remainingNodes - i, currentSum + i * (i + 1) / 2)) {
                return true;
            }
        }
        return false;
    }
}
