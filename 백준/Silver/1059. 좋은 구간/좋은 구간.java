import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 처리
        int L = sc.nextInt();
        int[] S = new int[L];
        for (int i = 0; i < L; i++) {
            S[i] = sc.nextInt();
        }
        int n = sc.nextInt();

        // 집합 S를 정렬
        Arrays.sort(S);

        // n이 집합에 속하는지 확인
        if (Arrays.binarySearch(S, n) >= 0) {
            System.out.println(0);
            return;
        }

        // n을 포함할 수 있는 구간 찾기
        int left = 0;
        int right = Integer.MAX_VALUE;
        for (int i = 0; i < L; i++) {
            if (S[i] < n) {
                left = S[i];
            } else {
                right = S[i];
                break;
            }
        }

        // left + 1부터 right - 1까지가 가능한 구간
        int result = 0;
        for (int a = left + 1; a <= n; a++) {
            for (int b = n; b < right; b++) {
                if (a < b) {
                    result++;
                }
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}
