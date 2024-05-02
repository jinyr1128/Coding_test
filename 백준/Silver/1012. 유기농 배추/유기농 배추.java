import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            int M = sc.nextInt();
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[][] field = new int[N][M];

            for (int i = 0; i < K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                field[y][x] = 1; 
            }

            int wormCount = 0; 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] == 1) { 
                        dfs(field, i, j, N, M); 
                        wormCount++; 
                    }
                }
            }
            
            sb.append(wormCount).append("\n");
        }
        
        System.out.print(sb.toString());
        sc.close();
    }

    private static void dfs(int[][] field, int y, int x, int N, int M) {
        if (x < 0 || y < 0 || x >= M || y >= N || field[y][x] == 0) {
            return;
        }
        field[y][x] = 0;
        
        dfs(field, y + 1, x, N, M);
        dfs(field, y - 1, x, N, M);
        dfs(field, y, x + 1, N, M);
        dfs(field, y, x - 1, N, M);
    }
}
