import java.util.*;
import java.io.*;

public class Main {
    static class State {
        int r, c, keys, moves;

        public State(int r, int c, int keys, int moves) {
            this.r = r;
            this.c = c;
            this.keys = keys;
            this.moves = moves;
        }
    }

    static int N, M;
    static char[][] maze;
    static boolean[][][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        visited = new boolean[N][M][64];  

        Queue<State> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == '0') {
                    queue.add(new State(i, j, 0, 0));
                    visited[i][j][0] = true;
                }
            }
        }

        System.out.println(bfs(queue));
    }

    static int bfs(Queue<State> queue) {
        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (maze[cur.r][cur.c] == '1') {
                return cur.moves;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || maze[nr][nc] == '#' || visited[nr][nc][cur.keys]) continue;

                char cell = maze[nr][nc];
                if (cell >= 'A' && cell <= 'F') {
                    int door = 1 << (cell - 'A');
                    if ((cur.keys & door) == 0) continue;
                }

                if (cell >= 'a' && cell <= 'f') {
                    int newKeys = cur.keys | (1 << (cell - 'a'));
                    if (!visited[nr][nc][newKeys]) {
                        visited[nr][nc][newKeys] = true;
                        queue.add(new State(nr, nc, newKeys, cur.moves + 1));
                    }
                } else {
                    visited[nr][nc][cur.keys] = true;
                    queue.add(new State(nr, nc, cur.keys, cur.moves + 1));
                }
            }
        }

        return -1;
    }
}