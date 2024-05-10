import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] inc = new int[N];
        int[] dec = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            inc[i] = 1;  // 모든 원소의 증가 부분 수열 초기값은 자기 자신이므로 1
            dec[i] = 1;  // 모든 원소의 감소 부분 수열 초기값은 자기 자신이므로 1
        }

        // 증가하는 부분 수열을 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    inc[i] = Math.max(inc[i], inc[j] + 1);
                }
            }
        }

        // 감소하는 부분 수열을 계산 (역방향)
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (A[j] < A[i]) {
                    dec[i] = Math.max(dec[i], dec[j] + 1);
                }
            }
        }

        // 가장 긴 바이토닉 부분 수열의 길이를 찾기
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            maxLength = Math.max(maxLength, inc[i] + dec[i] - 1);
        }

        System.out.println(maxLength);
    }
}
