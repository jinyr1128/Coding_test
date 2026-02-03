import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // E: 목표 지점, L: 최대 점프 거리, B: 소의 마리 수
        int E = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 소가 있는 위치를 표시할 배열 (좌표가 E까지이므로 E+1 크기 할당)
        boolean[] isCow = new boolean[E + 1];
        for (int i = 0; i < B; i++) {
            int cowLoc = Integer.parseInt(br.readLine());
            isCow[cowLoc] = true;
        }

        // 각 좌표까지의 최소 점프 횟수를 저장할 배열
        int[] dist = new int[E + 1];
        Arrays.fill(dist, -1); // -1로 초기화 (방문하지 않음)

        // BFS를 위한 큐
        Queue<Integer> queue = new LinkedList<>();

        // 시작 지점 초기화
        dist[0] = 0;
        queue.add(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 목표 지점에 도달했으면 결과 출력 후 종료
            if (curr == E) {
                System.out.println(dist[E]);
                return;
            }

            // 1부터 L까지의 거리만큼 점프 시도
            for (int jump = 1; jump <= L; jump++) {
                int next = curr + jump;

                // 1. 해변 범위를 벗어나지 않고
                // 2. 소가 없는 위치이며
                // 3. 아직 방문하지 않은 곳이라면
                if (next <= E && !isCow[next] && dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.add(next);
                }
            }
        }
    }
}