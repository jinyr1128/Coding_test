import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] X = new int[N];
        int maxVal = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
            if (X[i] > maxVal) {
                maxVal = X[i];
            }
        }

        // 점수 배열 및 위치 배열 초기화
        int[] scores = new int[N];
        int[] pos = new int[maxVal + 1];

        // 위치 배열에 각 값의 인덱스를 저장
        for (int i = 0; i < N; i++) {
            pos[X[i]] = i;
        }

        // 각 값에 대해 배수 관계를 확인하여 점수 업데이트
        for (int i = 0; i < N; i++) {
            int val = X[i];
            for (int multiple = val * 2; multiple <= maxVal; multiple += val) {
                if (pos[multiple] != 0 || (pos[multiple] == 0 && multiple == X[0])) { // 다수의 경우의 수를 처리
                    scores[pos[val]]++;
                    scores[pos[multiple]]--;
                }
            }
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            sb.append(scores[i]).append(" ");
        }
        System.out.print(sb.toString().trim());
    }
}
