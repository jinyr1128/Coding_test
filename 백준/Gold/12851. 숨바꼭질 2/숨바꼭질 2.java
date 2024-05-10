import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        int[] dist = new int[MAX + 1]; // 거리 저장 배열
        int[] count = new int[MAX + 1]; // 방법의 수 저장 배열
        Arrays.fill(dist, -1); // 초기화
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        dist[N] = 0;
        count[N] = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : new int[]{current - 1, current + 1, current * 2}) {
                if (next < 0 || next > MAX) continue;

                if (dist[next] == -1) { // 처음 방문하는 경우
                    dist[next] = dist[current] + 1;
                    count[next] = count[current];
                    queue.add(next);
                } else if (dist[next] == dist[current] + 1) { // 이미 방문했던 위치이지만 최소 시간으로 재방문하는 경우
                    count[next] += count[current];
                }
            }
        }

        System.out.println(dist[K]);
        System.out.println(count[K]);
    }
}
