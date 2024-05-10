import java.io.*;
import java.util.*;

class Point {
    int x, y, dist;

    Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    static int[][] map;
    static int N;
    static int sharkSize = 2;
    static int sharkEat = 0;
    static int time = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int sharkX = 0, sharkY = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Point result = bfs(sharkX, sharkY);
            if (result == null) break; // 먹을 수 있는 물고기가 없는 경우

            time += result.dist;
            sharkEat++;
            if (sharkEat == sharkSize) {
                sharkSize++;
                sharkEat = 0;
            }
            map[result.x][result.y] = 0;
            sharkX = result.x;
            sharkY = result.y;
        }

        System.out.println(time);
    }

    static Point bfs(int startX, int startY) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        List<Point> fish = new ArrayList<>();

        visited[startX][startY] = true;
        queue.add(new Point(startX, startY, 0));

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] <= sharkSize) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, current.dist + 1));
                    if (map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
                        fish.add(new Point(nx, ny, current.dist + 1));
                    }
                }
            }
        }

        if (fish.isEmpty()) return null;
        return fish.stream().min((p1, p2) -> {
            if (p1.dist != p2.dist) return p1.dist - p2.dist;
            if (p1.x != p2.x) return p1.x - p2.x;
            return p1.y - p2.y;
        }).orElse(null);
    }
}
