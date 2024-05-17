import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<List<Integer>> tree;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        tree = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        dfs(1);

        int result = Math.min(dp[1][0], dp[1][1]);
        System.out.println(result);
    }

    public static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; // node가 얼리 아답터가 아닌 경우
        dp[node][1] = 1; // node가 얼리 아답터인 경우

        for (int child : tree.get(node)) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1]; // 자식이 무조건 얼리 아답터여야 함
                dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 자식이 얼리 아답터일 수도 있고 아닐 수도 있음
            }
        }
    }
}
