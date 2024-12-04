import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static List<List<Integer>> graph = new ArrayList<>();
    static Stack<Integer> stack = new Stack<>();
    static int[] low, dfn, sccId;
    static boolean[] visited;
    static int nodeNum = 0, sccCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // number of lamps
        m = Integer.parseInt(st.nextToken()); // number of participants

        int totalNodes = 2 * n;
        low = new int[totalNodes + 1];
        dfn = new int[totalNodes + 1];
        sccId = new int[totalNodes + 1];
        visited = new boolean[totalNodes + 1];

        Arrays.fill(dfn, -1);

        for (int i = 0; i <= totalNodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            char c1 = st.nextToken().charAt(0);
            int x2 = Integer.parseInt(st.nextToken());
            char c2 = st.nextToken().charAt(0);
            int x3 = Integer.parseInt(st.nextToken());
            char c3 = st.nextToken().charAt(0);

            // Process colors: R -> true, B -> false
            x1 = (c1 == 'R' ? x1 : notX(x1));
            x2 = (c2 == 'R' ? x2 : notX(x2));
            x3 = (c3 == 'R' ? x3 : notX(x3));

            // Add implications for 2-SAT
            graph.get(notX(x1)).add(x2);
            graph.get(notX(x2)).add(x1);
            graph.get(notX(x2)).add(x3);
            graph.get(notX(x3)).add(x2);
            graph.get(notX(x3)).add(x1);
            graph.get(notX(x1)).add(x3);
        }

        // Run Tarjan's algorithm to find SCCs
        for (int i = 1; i <= totalNodes; i++) {
            if (dfn[i] == -1) {
                tarjan(i);
            }
        }

        // Check if a valid solution exists
        for (int i = 1; i <= n; i++) {
            if (sccId[i] == sccId[notX(i)]) {
                System.out.println("-1");
                return;
            }
        }

        // Determine the color of each lamp
        char[] result = new char[n + 1];
        for (int i = 1; i <= n; i++) {
            if (sccId[i] < sccId[notX(i)]) {
                result[i] = 'R';
            } else {
                result[i] = 'B';
            }
        }

        // Print result
        for (int i = 1; i <= n; i++) {
            System.out.print(result[i]);
        }
    }

    // Negate a node: flip between x and notX(x)
    private static int notX(int x) {
        return x > n ? x - n : x + n;
    }

    // Tarjan's algorithm for finding SCCs
    private static void tarjan(int current) {
        low[current] = dfn[current] = ++nodeNum;
        stack.push(current);
        visited[current] = true;

        for (int next : graph.get(current)) {
            if (dfn[next] == -1) { // If next node is unvisited
                tarjan(next);
                low[current] = Math.min(low[current], low[next]);
            } else if (visited[next]) { // If next node is in the stack
                low[current] = Math.min(low[current], dfn[next]);
            }
        }

        if (low[current] == dfn[current]) {
            while (true) {
                int node = stack.pop();
                visited[node] = false;
                sccId[node] = sccCount;
                if (node == current) break;
            }
            sccCount++;
        }
    }
}
