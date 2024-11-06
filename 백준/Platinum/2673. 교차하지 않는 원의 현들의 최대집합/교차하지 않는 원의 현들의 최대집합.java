import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] dp = new int[101][101]; // DP 배열
    static int[] A = new int[101]; // 각 점의 현 끝점 저장
    static int N;

    // 점 i와 r 사이에서 교차하지 않는 최대 현의 개수를 찾는 함수
    static int f(int i, int r) {
        if (i >= r) return 0; // 시작점이 종료점 이상이면 0 반환
        if (dp[i][r] != -1) return dp[i][r]; // 이미 계산된 경우 바로 반환

        dp[i][r] = f(i + 1, r); // 기본값: 다음 위치로 이동

        // 점 i가 현의 시작점인 경우 현의 양 끝을 연결해 가능한 최대 개수 계산
        if (A[i] != 0 && A[i] <= r) {
            dp[i][r] = Math.max(dp[i][r], f(i + 1, A[i] - 1) + f(A[i] + 1, r) + 1);
        }
        return dp[i][r];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int[] row : dp) Arrays.fill(row, -1); // DP 배열 초기화
        N = Integer.parseInt(br.readLine().trim()); // 현의 개수 입력

        // 각 현의 양 끝점 입력 처리
        while (N-- > 0) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            if (a > b) { // a가 더 크면 swap
                int temp = a;
                a = b;
                b = temp;
            }
            A[a] = b; // 점 a에 대해 b를 끝점으로 설정
        }

        // 최대 현의 개수 출력
        System.out.println(f(1, 100));
    }
}
