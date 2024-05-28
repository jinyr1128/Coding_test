import java.util.*;

public class Main {
    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] office;
    static List<int[]> cctvs = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1}; // 우, 상, 좌, 하
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        office = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                office[i][j] = sc.nextInt();
                if (office[i][j] != 0 && office[i][j] != 6) {
                    cctvs.add(new int[]{i, j});
                }
            }
        }
        
        dfs(0);
        System.out.println(ans);
    }
    
    static void dfs(int idx) {
        if (idx == cctvs.size()) {
            ans = Math.min(ans, getBlindSpot());
            return;
        }
        
        int x = cctvs.get(idx)[0];
        int y = cctvs.get(idx)[1];
        int[][] backup = new int[N][M];
        
        for (int dir = 0; dir < 4; dir++) {
            copyArray(office, backup);
            if (office[x][y] == 1) {
                check(x, y, dir);
            } else if (office[x][y] == 2) {
                check(x, y, dir);
                check(x, y, dir + 2);
            } else if (office[x][y] == 3) {
                check(x, y, dir);
                check(x, y, dir + 1);
            } else if (office[x][y] == 4) {
                check(x, y, dir);
                check(x, y, dir + 1);
                check(x, y, dir + 2);
            } else if (office[x][y] == 5) {
                check(x, y, dir);
                check(x, y, dir + 1);
                check(x, y, dir + 2);
                check(x, y, dir + 3);
            }
            dfs(idx + 1);
            copyArray(backup, office);
        }
    }
    
    static void check(int x, int y, int dir) {
        dir %= 4;
        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || office[nx][ny] == 6) return;
            if (office[nx][ny] == 0) office[nx][ny] = -1;
            x = nx;
            y = ny;
        }
    }
    
    static int getBlindSpot() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i][j] == 0) count++;
            }
        }
        return count;
    }
    
    static void copyArray(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(from[i], 0, to[i], 0, M);
        }
    }
}
