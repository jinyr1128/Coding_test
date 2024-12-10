import java.util.*;

public class Solution {
    public int solution(int n, int[][] vertex) {
        // 인접 리스트 생성
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 간선 정보를 그래프에 추가
        for (int[] edge : vertex) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // BFS를 위한 큐와 방문 배열 초기화
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] distances = new int[n + 1];
        
        // 1번 노드부터 시작
        queue.offer(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[current] + 1;
                    queue.offer(neighbor);
                }
            }
        }
        
        // 최대 거리 계산
        int maxDistance = 0;
        for (int distance : distances) {
            maxDistance = Math.max(maxDistance, distance);
        }
        
        // 최대 거리 노드 개수 계산
        int count = 0;
        for (int distance : distances) {
            if (distance == maxDistance) {
                count++;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int[][] vertex = {
            {3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}
        };
        System.out.println(sol.solution(n, vertex)); // 출력: 3
    }
}
