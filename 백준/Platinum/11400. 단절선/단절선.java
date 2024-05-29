import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100001;
    static int V, E;
    static List<Integer>[] adj = new ArrayList[MAX];
    static int[] visited = new int[MAX];
    static List<Pair> answer = new ArrayList<>();
    static int order = 0;

    static class Pair implements Comparable<Pair> {
        int first, second;
        Pair(int first, int second) {
            this.first = Math.min(first, second);
            this.second = Math.max(first, second);
        }
        @Override
        public int compareTo(Pair other) {
            if (this.first == other.first) {
                return this.second - other.second;
            }
            return this.first - other.first;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 1; i <= V; i++) {
            if (visited[i] == 0) {
                dfs(0, i);
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (Pair p : answer) {
            System.out.println(p.first + " " + p.second);
        }
    }

    static int dfs(int parent, int node) {
        visited[node] = ++order;
        int res = visited[node];

        for (int next : adj[node]) {
            if (next == parent) continue;
            if (visited[next] == 0) {
                int subtree = dfs(node, next);
                if (subtree > visited[node]) {
                    answer.add(new Pair(node, next));
                }
                res = Math.min(res, subtree);
            } else {
                res = Math.min(res, visited[next]);
            }
        }
        return res;
    }
}
