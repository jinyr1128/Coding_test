import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] storage;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0}; // 상, 하
    static int[] dy = {0, 0, -1, 1}; // 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        storage = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                storage[i][j] = Integer.parseInt(st.nextToken());
                if (storage[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && storage[nx][ny] == 0) {
                    storage[nx][ny] = storage[current[0]][current[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        int maxDays = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (storage[i][j] == 0) {
                    return -1;
                }
                maxDays = Math.max(maxDays, storage[i][j]);
            }
        }

        return maxDays - 1; // 모두 익는데 필요한 최소 일수
    }
}
