import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int[] subTreeSize;
    static boolean[] visited;

    // 서브트리의 크기를 계산하는 DFS 함수
    public static int dfs(int node) {
        visited[node] = true;  // 현재 노드를 방문 표시
        int size = 1;  // 자기 자신도 서브트리의 일부이므로 크기를 1로 시작
        for (int child : tree.get(node)) {
            if (!visited[child]) {
                size += dfs(child);  // 자식 노드들의 서브트리 크기를 더함
            }
        }
        subTreeSize[node] = size;  // 현재 노드의 서브트리 크기를 저장
        return size;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();  // 트리의 정점의 수
        int R = sc.nextInt();  // 루트의 번호
        int Q = sc.nextInt();  // 쿼리의 수

        // 배열 초기화
        subTreeSize = new int[N + 1];  // 각 노드의 서브트리 크기를 저장할 배열
        visited = new boolean[N + 1];  // 방문 여부 확인

        // 트리 초기화 (인접 리스트 사용)
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 입력 받기 (간선 정보)
        for (int i = 0; i < N - 1; i++) {
            int U = sc.nextInt();
            int V = sc.nextInt();
            tree.get(U).add(V);  // U와 V를 연결
            tree.get(V).add(U);  // 양방향 간선이므로 V와 U도 연결
        }

        // DFS를 통해 루트에서부터 서브트리 크기를 계산
        dfs(R);

        // 쿼리 처리
        for (int i = 0; i < Q; i++) {
            int U = sc.nextInt();
            System.out.println(subTreeSize[U]);
        }

        sc.close();
    }
}
