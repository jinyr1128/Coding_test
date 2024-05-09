import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, wall; // wall은 벽을 부순 상태를 나타낸다. 0은 안 부숨, 1은 부숨

        public Point(int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        
        int[][] map = new int[n][m];
        int[][][] distance = new int[n][m][2]; // 3차원 배열로 거리 저장

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j) - '0';
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        // BFS 시작
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0));
        distance[0][0][0] = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    // 벽을 만났고 아직 벽을 부수지 않았다면
                    if (map[nx][ny] == 1 && current.wall == 0) {
                        if (distance[current.x][current.y][0] + 1 < distance[nx][ny][1]) {
                            distance[nx][ny][1] = distance[current.x][current.y][0] + 1;
                            queue.add(new Point(nx, ny, 1));
                        }
                    }
                    // 벽이 아닌 경우
                    if (map[nx][ny] == 0) {
                        if (distance[current.x][current.y][current.wall] + 1 < distance[nx][ny][current.wall]) {
                            distance[nx][ny][current.wall] = distance[current.x][current.y][current.wall] + 1;
                            queue.add(new Point(nx, ny, current.wall));
                        }
                    }
                }
            }
        }

        // 최단 거리 계산
        int answer = Math.min(distance[n - 1][m - 1][0], distance[n - 1][m - 1][1]);
        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
    }
}
