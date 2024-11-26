import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_LEN = 3_000_001; // 최대 돌 개수
    static int[] fibo = new int[32];      // 피보나치 수
    static int[] dp = new int[MAX_LEN];  // Grundy 수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 피보나치 수 계산
        generateFibonacci();

        // Grundy 수 계산
        calculateGrundyNumbers();

        // 입력 처리
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int result = 0;

        for (int i = 0; i < N; i++) {
            int pile = Integer.parseInt(st.nextToken());
            result ^= dp[pile]; // Nim-Sum 계산
        }

        // 결과 출력
        System.out.println(result != 0 ? "koosaga" : "cubelover");
    }

    // 피보나치 수 생성
    private static void generateFibonacci() {
        fibo[0] = 1;
        fibo[1] = 1;
        for (int i = 2; i < 32; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
            if (fibo[i] > MAX_LEN) break; // 범위를 초과하면 중단
        }
    }

    // Grundy 수 계산
    private static void calculateGrundyNumbers() {
        for (int i = 1; i < MAX_LEN; i++) {
            boolean[] mex = new boolean[32]; // mex 계산을 위한 방문 배열
            for (int j = 0; j < 32; j++) {
                if (fibo[j] > i) break; // 피보나치 수가 현재 값보다 크면 중단
                mex[dp[i - fibo[j]]] = true;
            }
            // mex 값 중 가장 작은 값 찾기
            for (int k = 0; k < 32; k++) {
                if (!mex[k]) {
                    dp[i] = k;
                    break;
                }
            }
        }
    }
}
