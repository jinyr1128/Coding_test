import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 마을 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] cost = new int[n];
        int sum = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            sum += cost[i];
            max = Math.max(max, cost[i]);
        }

        // 전체 합에서 가장 큰 비용을 제외한 것이 최소 비용
        System.out.println(sum - max);
    }
}
