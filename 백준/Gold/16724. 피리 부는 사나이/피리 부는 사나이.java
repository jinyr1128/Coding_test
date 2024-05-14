import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static boolean[][] visited;
    static boolean[][] finished;
    static int safezone;
    static int[][] map;
    
    static int[] dr = {-1, 1, 0, 0}; // U, D, L, R
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        // 지도 입력을 받아 방향을 정수로 변환
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                switch (c) {
                    case 'U': map[i][j] = 0; break;
                    case 'D': map[i][j] = 1; break;
                    case 'L': map[i][j] = 2; break;
                    case 'R': map[i][j] = 3; break;
                }
            }
        }
        
        visited = new boolean[N][M]; // 방문 여부 확인
        finished = new boolean[N][M]; // 사이클 검증 여부 확인
        safezone = 0;
        
        // 각 지점에서 DFS 수행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                }
            }
        }
        
        System.out.println(safezone);
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;
        
        int nr = r + dr[map[r][c]];
        int nc = c + dc[map[r][c]];
        
        if (!visited[nr][nc]) {
            dfs(nr, nc);
        } else if (!finished[nr][nc]) {
            safezone++;
        }
        
        finished[r][c] = true;
    }
}
