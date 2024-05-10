import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[N + 1];
        
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList.get(A).add(B);
            inDegree[B]++;
        }
        
        // 위상 정렬 수행
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" ");
            
            for (int next : adjList.get(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        System.out.println(sb.toString().trim());
    }
}
