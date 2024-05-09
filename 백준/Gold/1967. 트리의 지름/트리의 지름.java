import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int target;
        int weight;
        
        Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        List<List<Node>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n - 1; i++) {
            String[] input = br.readLine().split(" ");
            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);
            
            tree.get(parent).add(new Node(child, weight));
            tree.get(child).add(new Node(parent, weight));
        }
        
        // 첫 번째 BFS 실행
        int[] furthestNodeInfo = bfs(1, tree, n);
        int startNode = furthestNodeInfo[0];  // 가장 먼 노드 번호
        
        // 두 번째 BFS 실행
        int[] result = bfs(startNode, tree, n);
        
        System.out.println(result[1]);  // 가장 멀리 있는 노드까지의 거리 출력
    }
    
    static int[] bfs(int start, List<List<Node>> tree, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[n + 1];
        
        queue.add(start);
        visited[start] = true;
        distance[start] = 0;
        
        int maxDistance = 0;
        int furthestNode = start;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (Node node : tree.get(current)) {
                if (!visited[node.target]) {
                    visited[node.target] = true;
                    distance[node.target] = distance[current] + node.weight;
                    queue.add(node.target);
                    
                    if (distance[node.target] > maxDistance) {
                        maxDistance = distance[node.target];
                        furthestNode = node.target;
                    }
                }
            }
        }
        
        return new int[] { furthestNode, maxDistance };
    }
}
