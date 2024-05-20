import java.util.*;

public class Main {
    static int V, E;
    static List<Integer>[] graph;
    static List<Integer>[] revGraph;
    static List<List<Integer>> SCC;
    static Stack<Integer> stack;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();

        graph = new ArrayList[V + 1];
        revGraph = new ArrayList[V + 1];
        SCC = new ArrayList<>();
        stack = new Stack<>();
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            revGraph[b].add(a);
        }

        // First DFS to fill the stack
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        // Reset visited array for second DFS
        Arrays.fill(visited, false);

        // Second DFS to find SCCs
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> scc = new ArrayList<>();
                revDfs(node, scc);
                SCC.add(scc);
            }
        }

        // Sort SCCs and their contents
        for (List<Integer> scc : SCC) {
            Collections.sort(scc);
        }
        SCC.sort(Comparator.comparingInt(list -> list.get(0)));

        // Output the result
        System.out.println(SCC.size());
        for (List<Integer> scc : SCC) {
            for (int node : scc) {
                System.out.print(node + " ");
            }
            System.out.println("-1");
        }
    }

    static void dfs(int node) {
        visited[node] = true;
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        stack.push(node);
    }

    static void revDfs(int node, List<Integer> scc) {
        visited[node] = true;
        scc.add(node);
        for (int next : revGraph[node]) {
            if (!visited[next]) {
                revDfs(next, scc);
            }
        }
    }
}
