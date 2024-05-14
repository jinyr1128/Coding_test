import java.io.*;
import java.util.*;

public class Main {
    static class State {
        int rx, ry, bx, by, depth;
        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }

    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }

    private static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.depth >= 10) return -1;

            for (int i = 0; i < 4; i++) {
                int[] nextRed = move(current.rx, current.ry, dx[i], dy[i]);
                int[] nextBlue = move(current.bx, current.by, dx[i], dy[i]);

                if (board[nextBlue[0]][nextBlue[1]] == 'O') continue;
                if (board[nextRed[0]][nextRed[1]] == 'O') return current.depth + 1;

                if (nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1]) {
                    if (distance(current.rx, current.ry, nextRed[0], nextRed[1]) > distance(current.bx, current.by, nextBlue[0], nextBlue[1])) {
                        nextRed[0] -= dx[i];
                        nextRed[1] -= dy[i];
                    } else {
                        nextBlue[0] -= dx[i];
                        nextBlue[1] -= dy[i];
                    }
                }

                if (!visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]]) {
                    visited[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]] = true;
                    queue.add(new State(nextRed[0], nextRed[1], nextBlue[0], nextBlue[1], current.depth + 1));
                }
            }
        }

        return -1;
    }

    private static int[] move(int x, int y, int dx, int dy) {
        while (board[x + dx][y + dy] != '#' && board[x][y] != 'O') {
            x += dx;
            y += dy;
        }
        return new int[]{x, y};
    }

    private static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}

