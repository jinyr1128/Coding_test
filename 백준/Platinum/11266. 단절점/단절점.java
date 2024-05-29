import java.util.*;

public class Main {
    private static int V, E, time;
    private static List<Integer>[] adj;
    private static boolean[] visited;
    private static int[] disc, low, parent;
    private static Set<Integer> articulationPoints;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        V = sc.nextInt();
        E = sc.nextInt();
        
        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        visited = new boolean[V + 1];
        disc = new int[V + 1];
        low = new int[V + 1];
        parent = new int[V + 1];
        articulationPoints = new TreeSet<>();
        
        for (int i = 1; i <= V; i++) {
            parent[i] = -1;
        }
        
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                findArticulationPoints(i);
            }
        }
        
        System.out.println(articulationPoints.size());
        for (int point : articulationPoints) {
            System.out.print(point + " ");
        }
    }
    
    private static void findArticulationPoints(int u) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;
        
        for (int v : adj[u]) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                findArticulationPoints(v);
                
                low[u] = Math.min(low[u], low[v]);
                
                if (parent[u] == -1 && children > 1) {
                    articulationPoints.add(u);
                }
                
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    articulationPoints.add(u);
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
