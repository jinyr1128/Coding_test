import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int cityNum, pathNum, vertexNum;
    static int[][] capacity, flow;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        cityNum = sc.nextInt();
        pathNum = sc.nextInt();
        vertexNum = (cityNum + 1) * 2;

        capacity = new int[vertexNum][vertexNum];
        flow = new int[vertexNum][vertexNum];

        // 각 도시의 in -> out 용량 1 설정
        for (int i = 2; i < vertexNum; i += 2) {
            capacity[i][i + 1] = 1;
        }

        // 간선 입력
        for (int i = 0; i < pathNum; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int uIn = u * 2, uOut = uIn + 1;
            int vIn = v * 2, vOut = vIn + 1;

            // u -> v, v -> u의 간선 생성
            capacity[uOut][vIn] = INF;
            capacity[vOut][uIn] = INF;
        }

        int sourceOut = (1 * 2) + 1;
        int sinkIn = (2 * 2);

        System.out.println(minimumCut(sourceOut, sinkIn));
    }

    static int minimumCut(int source, int sink) {
        int totalFlow = 0;

        while (true) {
            int[] parent = new int[vertexNum];
            Arrays.fill(parent, -1);
            Queue<Integer> queue = new LinkedList<>();
            parent[source] = source;
            queue.add(source);

            // BFS로 증가 경로 탐색
            while (!queue.isEmpty() && parent[sink] == -1) {
                int u = queue.poll();

                for (int v = 2; v < vertexNum; v++) {
                    if (capacity[u][v] - flow[u][v] > 0 && parent[v] == -1) {
                        queue.add(v);
                        parent[v] = u;
                    }
                }
            }

            // 증가 경로가 없는 경우
            if (parent[sink] == -1) {
                break;
            }

            // 경로 상의 최소 잔여 용량 계산
            int amount = INF;
            for (int p = sink; p != source; p = parent[p]) {
                amount = Math.min(capacity[parent[p]][p] - flow[parent[p]][p], amount);
            }

            // 잔여 용량을 업데이트
            for (int p = sink; p != source; p = parent[p]) {
                flow[parent[p]][p] += amount;
                flow[p][parent[p]] -= amount;
            }

            totalFlow += amount;
        }

        return totalFlow;
    }
}
