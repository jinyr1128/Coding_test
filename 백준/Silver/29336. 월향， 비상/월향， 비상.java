import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 운영진 수
        int M = Integer.parseInt(st.nextToken()); // 조건 수

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a); // 역량의 내림차순 정렬
        long currentQuality = 0; // 현재 퀄리티 합

        // 각 운영진의 초기 역량을 입력받아 우선순위 큐에 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int ability = Integer.parseInt(st.nextToken());
            queue.offer(ability);
        }

        // 각 조건을 저장할 배열 생성
        int[][] conditions = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            conditions[i][0] = Integer.parseInt(st.nextToken()); // T_i 값
            conditions[i][1] = Integer.parseInt(st.nextToken()); // Q_i 값
        }

        // 조건을 순차적으로 처리
        for (int i = 0; i < M; i++) {
            int requiredDay = conditions[i][0];
            int requiredQuality = conditions[i][1];

            // 현재 조건을 만족할 때까지 퀄리티를 증가
            while (!queue.isEmpty() && currentQuality < requiredQuality) {
                int currentBest = queue.poll();
                currentQuality += (currentBest + requiredDay);
            }

            // 현재 조건을 만족할 수 없는 경우
            if (currentQuality < requiredQuality) {
                System.out.println(-1);
                return;
            }
        }

        // 남아 있는 역량들을 최종 조건의 날까지 추가해 최대 퀄리티를 계산
        int lastDay = conditions[M - 1][0];
        while (!queue.isEmpty()) {
            currentQuality += (queue.poll() + lastDay);
        }

        // 출력: 최종 퀄리티 합
        System.out.println(currentQuality);
    }
}
