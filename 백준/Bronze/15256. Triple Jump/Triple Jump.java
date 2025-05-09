import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();  // 데이터 세트 개수

        for (int t = 1; t <= K; t++) {
            int n = sc.nextInt();  // 현재 데이터 세트의 위치 수
            double[] x = new double[n];

            for (int i = 0; i < n; i++) {
                x[i] = sc.nextDouble();
            }

            int startIdx = -1;

            // 점프 시작 인덱스 찾기 ([30.0, 30.2] 범위)
            for (int i = 0; i < n; i++) {
                if (x[i] >= 30.0 && x[i] <= 30.2) {
                    startIdx = i;
                    break;
                }
            }

            double result = 0.0;

            // 점프 시작했으면, 그 후 3개 이후 지점부터 최소값을 구한다.
            if (startIdx != -1 && startIdx + 3 < n) {
                double minVal = x[startIdx + 3];
                for (int i = startIdx + 3; i < n; i++) {
                    minVal = Math.min(minVal, x[i]);
                }
                result = minVal - 30.0;
            }

            // 출력
            System.out.printf("Data Set %d:\n", t);
            System.out.printf("%.2f\n\n", result);
        }
    }
}
