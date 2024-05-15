import java.io.*;
import java.util.*;

public class Main {
    static int[] parent, candySum, groupSize;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        candySum = new int[N + 1];
        groupSize = new int[N + 1];

        Arrays.fill(parent, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candySum[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        // 그룹별 사탕 수와 크기 계산
        List<int[]> groups = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (parent[i] < 0) {
                groups.add(new int[]{-parent[i], candySum[i]});
            }
        }

        // 0-1 배낭 문제 해결
        int[] dp = new int[K];
        for (int[] group : groups) {
            int groupSize = group[0];
            int groupCandy = group[1];
            for (int j = K - 1; j >= groupSize; j--) {
                dp[j] = Math.max(dp[j], dp[j - groupSize] + groupCandy);
            }
        }

        System.out.println(dp[K - 1]);
    }

    static int find(int a) {
        if (parent[a] < 0) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (parent[a] < parent[b]) {
                parent[a] += parent[b];
                candySum[a] += candySum[b];
                parent[b] = a;
            } else {
                parent[b] += parent[a];
                candySum[b] += candySum[a];
                parent[a] = b;
            }
        }
    }
}

