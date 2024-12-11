import java.util.*;

public class Main {
    static int n, m; // 도화지 크기
    static int[][] canvas; // 도화지 정보
    static boolean[][] visited; // 방문 여부
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동
    static int[] dy = {0, 0, -1, 1}; // 상하좌우 이동

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 처리
        n = sc.nextInt();
        m = sc.nextInt();
        canvas = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                canvas[i][j] = sc.nextInt();
            }
        }

        int count = 0; // 그림의 개수
        int maxArea = 0; // 가장 넓은 그림의 넓이

        // 도화지 전체 순회
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (canvas[i][j] == 1 && !visited[i][j]) {
                    count++; // 새로운 그림 발견
                    maxArea = Math.max(maxArea, bfs(i, j)); // 해당 그림의 넓이 계산
                }
            }
        }

        // 결과 출력
        System.out.println(count);
        System.out.println(maxArea);
    }

    // BFS를 사용하여 그림의 넓이를 계산
    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int area = 0; // 현재 그림의 넓이

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            area++;

            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 유효한 위치인지, 방문하지 않았는지, 1인지 확인
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && canvas[nx][ny] == 1) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return area; // 그림의 넓이 반환
    }
}