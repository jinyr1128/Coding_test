import java.io.*;
import java.util.*;

class Node {
    int vertex, weight;

    Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {
    static List<List<Node>> adjList;
    static boolean[] visited;
    static int farthestNode = 0;
    static int maxDistance = 0;

    private static void bfs(int start) {
        Arrays.fill(visited, false);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));
        visited[start] = true;
        maxDistance = 0;
        farthestNode = start;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node next : adjList.get(current.vertex)) {
                if (!visited[next.vertex]) {
                    visited[next.vertex] = true;
                    queue.offer(new Node(next.vertex, current.weight + next.weight));
                    if (current.weight + next.weight > maxDistance) {
                        maxDistance = current.weight + next.weight;
                        farthestNode = next.vertex;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                int w = Integer.parseInt(st.nextToken());
                adjList.get(u).add(new Node(v, w));
                adjList.get(v).add(new Node(u, w));  // Undirected graph
            }
        }

        // Find the farthest node from any node (we start from 1)
        bfs(1);
        // Use the farthest node found in the first BFS to find the farthest node from it
        bfs(farthestNode);
        
        System.out.println(maxDistance);
    }
}
