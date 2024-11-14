import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int depth;
        int index;
        long weight;

        public Node(int depth, int index, long weight) {
            this.depth = depth;
            this.index = index;
            this.weight = weight;
        }
    }

    static int n;
    static int pv = 0;
    static int[] id;
    static long[] weights;
    static ArrayList<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        id = new int[n + 1];
        weights = new long[n + 1];

        // 트리를 DFS로 순회하며 노드 깊이와 인덱스 기록
        dfs(1, 0);

        // 가중치 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        long initialMax = Long.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            long weight = Long.parseLong(st.nextToken());
            weights[id[i]] = weight;
            nodes.get(id[i]).weight = weight;
            initialMax = Math.max(initialMax, weight);
        }

        // 모든 가중치가 음수인 경우 가장 큰 값 하나를 출력
        if (initialMax <= 0) {
            System.out.println(initialMax);
            return;
        }

        long maxSum = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = i; j < 20; j++) {
                long currentSum = 0, maxInRectangle = 0;
                for (Node node : nodes) {
                    if (node.depth < i || node.depth > j) continue;
                    currentSum = Math.max(0, currentSum + node.weight);
                    maxInRectangle = Math.max(maxInRectangle, currentSum);
                }
                maxSum = Math.max(maxSum, maxInRectangle);
            }
        }

        // 결과 출력
        System.out.println(maxSum);
    }

    // DFS로 트리 노드를 순회하며 노드 깊이와 인덱스 기록
    static void dfs(int node, int depth) {
        if (node > n) return;

        // 왼쪽 자식 노드 방문
        if (node * 2 <= n) dfs(node * 2, depth + 1);

        // 현재 노드 깊이와 인덱스 기록
        id[node] = pv;
        nodes.add(new Node(depth, pv++, 0));

        // 오른쪽 자식 노드 방문
        if (node * 2 + 1 <= n) dfs(node * 2 + 1, depth + 1);
    }
}
