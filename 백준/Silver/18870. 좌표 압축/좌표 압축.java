import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 좌표를 저장할 배열과 인덱스를 함께 저장할 자료구조
        int[] original = new int[N];
        int[][] data = new int[N][2]; // [0]: 값, [1]: 원래 인덱스

        for (int i = 0; i < N; i++) {
            original[i] = Integer.parseInt(st.nextToken());
            data[i][0] = original[i];
            data[i][1] = i;
        }

        // 값에 따라 배열 정렬
        Arrays.sort(data, (a, b) -> a[0] - b[0]);

        // 좌표 압축 결과를 저장할 배열
        int[] compressed = new int[N];
        int rank = 0; // 압축된 좌표의 순위
        compressed[data[0][1]] = rank; // 첫 번째 원소는 0부터 시작

        // 정렬된 배열을 순회하면서 좌표 압축 수행
        for (int i = 1; i < N; i++) {
            if (data[i][0] != data[i - 1][0]) { // 이전 원소와 값이 다르면 순위 증가
                rank++;
            }
            compressed[data[i][1]] = rank; // 원래 인덱스 위치에 순위 저장
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(compressed[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
