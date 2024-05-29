import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> graph;
    private static List<List<Integer>> reGraph;
    private static int[] scc;
    private static Stack<Integer> stack;
    private static boolean[] visit;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reGraph = new ArrayList<>();
        scc = new int[2 * N + 5];
        visit = new boolean[2 * N + 5];
        stack = new Stack<>();

        for (int i = 0; i < 2 * N + 5; i++) {
            graph.add(new ArrayList<>());
            reGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (u < 0) u = -u + N;
            if (v < 0) v = -v + N;
            graph.get(re(u, N)).add(v);
            graph.get(re(v, N)).add(u);
            reGraph.get(u).add(re(v, N));
            reGraph.get(v).add(re(u, N));
        }

        for (int i = 1; i <= 2 * N + 1; i++) {
            if (!visit[i]) dfs(i);
        }

        Arrays.fill(visit, false);
        int idx = 1;
        while (!stack.isEmpty()) {
            int x = stack.pop();
            if (!visit[x]) {
                reDfs(x, idx++);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (scc[i] == scc[i + N]) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }

    private static void dfs(int x) {
        visit[x] = true;
        for (int next : graph.get(x)) {
            if (!visit[next]) {
                dfs(next);
            }
        }
        stack.push(x);
    }

    private static void reDfs(int x, int y) {
        visit[x] = true;
        scc[x] = y;
        for (int next : reGraph.get(x)) {
            if (!visit[next]) {
                reDfs(next, y);
            }
        }
    }

    private static int re(int x, int N) {
        return x > N ? x - N : x + N;
    }
}
