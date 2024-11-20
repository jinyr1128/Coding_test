import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4]; // 각 방향별 비용 저장 (0: 상, 1: 하, 2: 좌, 3: 우)
        for (int[][] row : cost) {
            for (int[] dir : row) {
                Arrays.fill(dir, Integer.MAX_VALUE); // 비용을 무한대로 초기화
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, -1)); // 초기 위치 (0,0), 비용 0, 방향 -1(아직 방향 없음)
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0; // 시작 지점 비용 초기화
        }

        int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nextCost = node.cost;

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 1) {
                    continue; // 격자를 벗어나거나 벽인 경우
                }

                // 방향이 바뀌었으면 코너 비용 추가
                if (node.direction != -1 && node.direction != i) {
                    nextCost += 600; // 직선 + 코너 (500 + 100)
                } else {
                    nextCost += 100; // 직선 도로
                }

                if (nextCost < cost[nx][ny][i]) {
                    cost[nx][ny][i] = nextCost;
                    queue.add(new Node(nx, ny, nextCost, i));
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            minCost = Math.min(minCost, cost[n - 1][n - 1][i]);
        }

        return minCost;
    }

    static class Node {
        int x, y, cost, direction;

        Node(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }
    }
}
