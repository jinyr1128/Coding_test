import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static int[] depth;
    static boolean[] visited;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 정점의 수
        int M = sc.nextInt(); // 간선의 수
        int R = sc.nextInt(); // 시작 정점
        
        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        // 인접 리스트를 오름차순으로 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }
        
        // 깊이 배열 및 방문 배열 초기화
        depth = new int[N + 1];
        visited = new boolean[N + 1];
        
        // 초기 깊이를 -1로 설정
        Arrays.fill(depth, -1);
        
        // DFS 수행
        dfs(R, 0);
        
        // 결과 출력
        for (int i = 1; i <= N; i++) {
            System.out.println(depth[i]);
        }
        
        sc.close();
    }

    // DFS 함수
    static void dfs(int node, int currentDepth) {
        visited[node] = true;
        depth[node] = currentDepth;
        
        // 인접 정점 탐색
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, currentDepth + 1);
            }
        }
    }
}
