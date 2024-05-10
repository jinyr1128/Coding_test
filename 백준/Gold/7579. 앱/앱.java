import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = new int[N];
        int[] cost = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        int maxCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i];
        }

        int[] dp = new int[maxCost + 1];
        Arrays.fill(dp, 0);
        
        for (int i = 0; i < N; i++) {
            for (int c = maxCost; c >= cost[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - cost[i]] + memory[i]);
            }
        }

        for (int c = 0; c <= maxCost; c++) {
            if (dp[c] >= M) {
                System.out.println(c);
                break;
            }
        }
    }
}
