import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] grid = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        // 초기값 설정
        for (int i = 0; i < 3; i++) {
            maxDp[i] = grid[0][i];
            minDp[i] = grid[0][i];
        }

        // DP 배열 업데이트
        for (int i = 1; i < N; i++) {
            int[] newMaxDp = new int[3];
            int[] newMinDp = new int[3];

            newMaxDp[0] = Math.max(maxDp[0], maxDp[1]) + grid[i][0];
            newMaxDp[1] = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]) + grid[i][1];
            newMaxDp[2] = Math.max(maxDp[1], maxDp[2]) + grid[i][2];

            newMinDp[0] = Math.min(minDp[0], minDp[1]) + grid[i][0];
            newMinDp[1] = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]) + grid[i][1];
            newMinDp[2] = Math.min(minDp[1], minDp[2]) + grid[i][2];

            // Update DP arrays
            maxDp = newMaxDp;
            minDp = newMinDp;
        }

        // 결과 계산 및 출력
        int maxScore = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int minScore = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
        System.out.println(maxScore + " " + minScore);
    }
}
