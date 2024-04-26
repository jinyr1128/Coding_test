import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, int[]> degrees = new HashMap<>();
        
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new LinkedList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new LinkedList<>()).add(edge[0]);
            
            degrees.computeIfAbsent(edge[0], k -> new int[2])[0]++; // Out-degree
            degrees.computeIfAbsent(edge[1], k -> new int[2])[1]++; // In-degree
        }
        
        int generated = -1;
        for (Map.Entry<Integer, int[]> entry : degrees.entrySet()) {
            if (entry.getValue()[0] >= 2 && entry.getValue()[1] == 0) {
                generated = entry.getKey();
                break;
            }
        }
        
        int[] answer = {generated, 0, 0, 0};
        Set<Integer> visited = new HashSet<>();
        visited.add(generated);
        
        for (int start : graph.get(generated)) {
            int[] edgeNum = new int[4];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);
            
            while (!queue.isEmpty()) {
                int node = queue.poll();
                
                if (node == start) degrees.get(node)[1]--;
                
                int out = degrees.get(node)[0], in = degrees.get(node)[1];
                if (out == 2 && in == 2) edgeNum[3]++;
                else if (out == 1 && in == 1) edgeNum[2]++;
                else if (out == 1 && in == 0) edgeNum[1]++;
                else if (out == 0 && in == 1) edgeNum[0]++;
                
                for (int next : graph.getOrDefault(node, Collections.emptyList())) {
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
            
            if (edgeNum[3] == 1) answer[3]++;
            else if (edgeNum[0] == 1 || edgeNum[2] == 0) answer[2]++;
            else answer[1]++;
        }
        
        return answer;
    }
}
