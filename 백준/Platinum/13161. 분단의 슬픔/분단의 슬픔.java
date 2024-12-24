import java.util.*;

public class Main {
    private static final int MAX_V = 502;
    private static final int S = 500;
    private static final int E = 501;
    private static final int INF = Integer.MAX_VALUE;

    private static int[][] capacity = new int[MAX_V][MAX_V];
    private static int[][] flow = new int[MAX_V][MAX_V];
    private static int[] level = new int[MAX_V];
    private static int[] work = new int[MAX_V];
    private static List<Integer>[] adj = new ArrayList[MAX_V];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for (int i = 0; i < MAX_V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int where = sc.nextInt();
            if (where == 1) {
                addEdge(S, i, INF);
            } else if (where == 2) {
                addEdge(i, E, INF);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int weight = sc.nextInt();
                if (weight > 0) {
                    addEdge(i, j, weight);
                }
            }
        }

        sc.close();

        int total = 0;
        while (bfs()) {
            Arrays.fill(work, 0);
            int flowAmount;
            while ((flowAmount = dfs(S, E, INF)) > 0) {
                total += flowAmount;
            }
        }

        System.out.println(total);

        boolean[] visited = new boolean[MAX_V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        visited[S] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adj[cur]) {
                if (!visited[next] && capacity[cur][next] > flow[cur][next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        List<Integer> aGroup = new ArrayList<>();
        List<Integer> bGroup = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                aGroup.add(i + 1);
            } else {
                bGroup.add(i + 1);
            }
        }

        printGroup(aGroup);
        printGroup(bGroup);
    }

    private static void addEdge(int from, int to, int cap) {
        if (!adj[from].contains(to)) {
            adj[from].add(to);
            adj[to].add(from);
        }
        capacity[from][to] += cap;
    }

    private static boolean bfs() {
        Arrays.fill(level, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        level[S] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adj[cur]) {
                if (level[next] == -1 && capacity[cur][next] > flow[cur][next]) {
                    level[next] = level[cur] + 1;
                    queue.add(next);
                }
            }
        }

        return level[E] != -1;
    }

    private static int dfs(int cur, int target, int currentFlow) {
        if (cur == target) {
            return currentFlow;
        }

        for (int i = work[cur]; i < adj[cur].size(); i++, work[cur]++) {
            int next = adj[cur].get(i);
            if (level[next] == level[cur] + 1 && capacity[cur][next] > flow[cur][next]) {
                int availableFlow = dfs(next, target, Math.min(currentFlow, capacity[cur][next] - flow[cur][next]));

                if (availableFlow > 0) {
                    flow[cur][next] += availableFlow;
                    flow[next][cur] -= availableFlow;
                    return availableFlow;
                }
            }
        }

        return 0;
    }

    private static void printGroup(List<Integer> group) {
        if (group.isEmpty()) {
            System.out.println();
        } else {
            for (int member : group) {
                System.out.print(member + " ");
            }
            System.out.println();
        }
    }
}
