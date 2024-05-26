import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        map = new int[N][M];
        dp = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                dp[i][j] = -1; // 초기값 -1로 설정 (방문하지 않음을 의미)
            }
        }
        
        System.out.println(dfs(0, 0));
        
        sc.close();
    }
    
    public static int dfs(int x, int y) {
        // 도착 지점에 도달하면 경로 1개를 찾은 것
        if (x == N - 1 && y == M - 1) {
            return 1;
        }
        
        // 이미 계산한 경로가 있다면 그 값을 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        
        dp[x][y] = 0; // 경로 초기화
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                // 내리막길인 경우에만 이동
                if (map[nx][ny] < map[x][y]) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }
        
        return dp[x][y];
    }
}
