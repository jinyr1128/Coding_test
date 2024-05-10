import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simulateCheeseMelting());
    }

    public static void bfsMarkAir() {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][M];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && grid[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static int simulateCheeseMelting() {
        int timePassed = 0;

        while (true) {
            bfsMarkAir();
            List<int[]> melting = new ArrayList<>();

            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (grid[i][j] == 1) {
                        int exposedSides = 0;

                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (visited[nx][ny]) {
                                exposedSides++;
                            }
                        }

                        if (exposedSides >= 2) {
                            melting.add(new int[]{i, j});
                        }
                    }
                }
            }

            if (melting.isEmpty()) {
                break;
            }

            for (int[] cheese : melting) {
                grid[cheese[0]][cheese[1]] = 0;
            }

            timePassed++;
        }

        return timePassed;
    }
}
