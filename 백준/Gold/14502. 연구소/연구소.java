import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] lab;
    static ArrayList<int[]> viruses;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxSafeArea = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n][m];
        viruses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) {
                    viruses.add(new int[] {i, j});
                }
            }
        }

        placeWalls(0);
        System.out.println(maxSafeArea);
    }

    public static void placeWalls(int wallCount) {
        if (wallCount == 3) {
            simulateVirus();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    placeWalls(wallCount + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    public static void simulateVirus() {
        int[][] tempLab = new int[n][m];
        for (int i = 0; i < n; i++) {
            tempLab[i] = lab[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : viruses) {
            queue.add(virus);
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && tempLab[nx][ny] == 0) {
                    tempLab[nx][ny] = 2;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        calculateSafeArea(tempLab);
    }

    public static void calculateSafeArea(int[][] tempLab) {
        int safeArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempLab[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safeArea);
    }
}
