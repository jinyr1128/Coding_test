import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 점의 개수

        long[] x = new long[N];
        long[] y = new long[N];
        
        // 점들의 좌표를 입력 받음
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        // 신발끈 정리를 이용한 면적 계산
        long area = 0;
        for (int i = 0; i < N; i++) {
            int next = (i + 1) % N; // 다음 인덱스 (순환)
            area += x[i] * y[next] - y[i] * x[next];
        }
        area = Math.abs(area); // 절댓값을 취함

        System.out.printf("%.1f\n", area / 2.0); // 면적을 2로 나누고 출력 형식에 맞추어 출력
    }
}
