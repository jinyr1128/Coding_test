import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        // 가능한 제곱수를 미리 계산
        ArrayList<Integer> squares = new ArrayList<>();
        int i = 1;
        while (i * i <= n) {
            squares.add(i * i);
            i++;
        }

        // 동적 프로그래밍으로 문제 해결
        for (int j = 1; j <= n; j++) {
            for (int square : squares) {
                if (j < square) break;
                dp[j] = Math.min(dp[j], dp[j - square] + 1);
            }
        }

        bw.write(String.valueOf(dp[n]));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
