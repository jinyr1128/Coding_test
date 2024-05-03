import java.util.*;

public class Main {
    static int N;
    static char[][] grid;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        scanner.nextLine();
        grid = new char[N][N];

        for (int i = 0; i < N; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }

        System.out.println(countRegions(false) + " " + countRegions(true));
    }

    static int countRegions(boolean colorBlind) {
        visited = new boolean[N][N];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, grid[i][j], colorBlind);
                    count++;
                }
            }
        }
        
        return count;
    }

    static void dfs(int x, int y, char color, boolean colorBlind) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                if (colorBlind) {
                    if ((color == 'R' || color == 'G') && (grid[nx][ny] == 'R' || grid[nx][ny] == 'G')) {
                        dfs(nx, ny, color, colorBlind);
                    } else if (color == 'B' && grid[nx][ny] == 'B') {
                        dfs(nx, ny, color, colorBlind);
                    }
                } else {
                    if (grid[nx][ny] == color) {
                        dfs(nx, ny, color, colorBlind);
                    }
                }
            }
        }
    }
}
