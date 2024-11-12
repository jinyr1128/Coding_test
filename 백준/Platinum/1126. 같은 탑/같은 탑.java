import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] blocks;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 블록의 개수 입력
        N = Integer.parseInt(br.readLine());
        blocks = new int[N];

        // 블록 높이 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        // dp 배열 초기화
        dp = new int[N + 1][500001];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int answer = findMaxHeight(0, 0);
        System.out.println(answer > 0 ? answer : -1);
    }

    // 다이나믹 프로그래밍을 통해 최대 탑 높이 계산
    static int findMaxHeight(int index, int diff) {
        if (index == N) {
            return (diff == 0) ? 0 : Integer.MIN_VALUE;
        }

        if (dp[index][diff] != -1) {
            return dp[index][diff];
        }

        int skip = findMaxHeight(index + 1, diff); // 현재 블록을 사용하지 않는 경우
        int addToTaller = findMaxHeight(index + 1, diff + blocks[index]) + blocks[index]; // 더 높은 탑에 현재 블록 추가
        int addToShorter = findMaxHeight(index + 1, Math.abs(diff - blocks[index])) + (diff > blocks[index] ? 0 : blocks[index] - diff); // 더 낮은 탑에 현재 블록 추가

        dp[index][diff] = Math.max(skip, Math.max(addToTaller, addToShorter));
        return dp[index][diff];
    }
}
