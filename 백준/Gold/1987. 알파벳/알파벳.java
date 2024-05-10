import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int R, C, maxCount = 0;
    static char[][] board;
    static boolean[] visited = new boolean[26];  // 알파벳 A-Z까지 방문 여부
    static int[] dx = {-1, 1, 0, 0};  // 상, 하 이동
    static int[] dy = {0, 0, -1, 1};  // 좌, 우 이동

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sizes = br.readLine().split(" ");
        R = Integer.parseInt(sizes[0]);
        C = Integer.parseInt(sizes[1]);
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 초기 위치에서 시작 (0, 0)
        visited[board[0][0] - 'A'] = true;  // 초기 위치 알파벳 방문 표시
        dfs(0, 0, 1);  // 시작점, 카운트 시작은 1부터

        System.out.println(maxCount);
    }

    static void dfs(int x, int y, int count) {
        maxCount = Math.max(maxCount, count);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[board[nx][ny] - 'A']) {
                visited[board[nx][ny] - 'A'] = true;
                dfs(nx, ny, count + 1);
                visited[board[nx][ny] - 'A'] = false;
            }
        }
    }
}
