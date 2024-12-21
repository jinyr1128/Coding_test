import java.util.*;

public class Main {
    static int N, M;
    static int[] X;
    static int[][][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        X = new int[N + 1];

        X[0] = 0; // 수아의 초기 위치를 0으로 설정
        for (int i = 1; i <= N; i++) {
            X[i] = sc.nextInt();
        }

        Arrays.sort(X); // 위치를 정렬

        int start = Arrays.binarySearch(X, 0); // 수아의 위치 찾기
        if (start < 0) start = -(start + 1);

        int maxCandies = 0;

        for (int L = 0; L <= N; L++) {
            dp = new int[N + 1][N + 1][2];
            for (int[][] matrix : dp) {
                for (int[] row : matrix) {
                    Arrays.fill(row, -1);
                }
            }

            maxCandies = Math.max(maxCandies, L * M - solve(start, start, 0, L));
        }

        System.out.println(maxCandies);
        sc.close();
    }

    static int solve(int left, int right, int direction, int L) {
        if (L - (right - left) == 0) return 0;
        if (dp[left][right][direction] != -1) return dp[left][right][direction];

        int time = L - (right - left);
        int result = Integer.MAX_VALUE;

        if (direction == 1) { // 오른쪽으로 이동 중
            if (right + 1 <= N) {
                result = Math.min(result, solve(left, right + 1, 1, L) + time * (X[right + 1] - X[right]));
            }
            if (left - 1 >= 0) {
                result = Math.min(result, solve(left - 1, right, 0, L) + time * (X[right] - X[left - 1]));
            }
        } else { // 왼쪽으로 이동 중
            if (right + 1 <= N) {
                result = Math.min(result, solve(left, right + 1, 1, L) + time * (X[right + 1] - X[left]));
            }
            if (left - 1 >= 0) {
                result = Math.min(result, solve(left - 1, right, 0, L) + time * (X[left] - X[left - 1]));
            }
        }

        return dp[left][right][direction] = result;
    }
}
