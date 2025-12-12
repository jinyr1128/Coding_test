import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 친구 수, M: 식당 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] scores = new int[N][M];
        int[] maxScores = new int[N]; // 각 친구가 준 별점 중 최댓값 저장

        // 점수 입력 및 각 친구별 최댓값 계산
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int currentMax = 0;
            for (int j = 0; j < M; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
                // 현재 친구의 최댓값 갱신
                if (scores[i][j] > currentMax) {
                    currentMax = scores[i][j];
                }
            }
            maxScores[i] = currentMax;
        }

        StringBuilder sb = new StringBuilder();

        // 각 식당별로(열 기준) 필요한 조작 횟수 계산
        for (int j = 0; j < M; j++) {
            int changeCount = 0;
            for (int i = 0; i < N; i++) {
                // 친구 i가 식당 j에 준 점수가 본인의 최댓값보다 작다면
                // 해당 점수를 최댓값으로 변경해야 하므로 조작 횟수 1 증가
                if (scores[i][j] < maxScores[i]) {
                    changeCount++;
                }
            }
            sb.append(changeCount).append(" ");
        }

        // 결과 출력
        System.out.println(sb.toString().trim());
    }
}