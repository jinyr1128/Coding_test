import java.util.*;

public class Main {
    static final int MAX_NODES = 40001;
    static final int TREE_HEIGHT = 20;
    
    static int[] depth = new int[MAX_NODES];
    static int[][] parent = new int[MAX_NODES][TREE_HEIGHT];
    static int[][] dist = new int[MAX_NODES][TREE_HEIGHT];
    static List<List<Pair>> adj = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nodeNum = sc.nextInt();

        for (int i = 0; i <= nodeNum; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < nodeNum - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            adj.get(a).add(new Pair(b, cost));
            adj.get(b).add(new Pair(a, cost));
        }

        findParents(1, 0, 0, 0);

        for (int k = 1; k < TREE_HEIGHT; k++) {
            for (int i = 1; i <= nodeNum; i++) {
                if (parent[i][k - 1] != 0) {
                    parent[i][k] = parent[parent[i][k - 1]][k - 1];
                    dist[i][k] = dist[i][k - 1] + dist[parent[i][k - 1]][k - 1];
                }
            }
        }

        int pairNum = sc.nextInt();

        for (int i = 0; i < pairNum; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(getDistance(a, b));
        }
    }

    static void findParents(int currentNode, int par, int dep, int cost) {
        depth[currentNode] = dep;
        parent[currentNode][0] = par;
        dist[currentNode][0] = cost;

        for (Pair neighbor : adj.get(currentNode)) {
            if (neighbor.node != par) {
                findParents(neighbor.node, currentNode, dep + 1, neighbor.cost);
            }
        }
    }

    static int getDistance(int a, int b) {
        int sum = 0;

        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];
        for (int i = 0; diff > 0; i++) {
            if ((diff & 1) != 0) {
                sum += dist[a][i];
                a = parent[a][i];
            }
            diff >>= 1;
        }

        if (a != b) {
            for (int k = TREE_HEIGHT - 1; k >= 0; k--) {
                if (parent[a][k] != 0 && parent[a][k] != parent[b][k]) {
                    sum += dist[a][k] + dist[b][k];
                    a = parent[a][k];
                    b = parent[b][k];
                }
            }
            sum += dist[a][0] + dist[b][0];
        }

        return sum;
    }

    static class Pair {
        int node, cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
