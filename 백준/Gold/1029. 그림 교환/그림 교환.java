import java.util.Scanner;

public class Main {
    static int N; // 예술가 수
    static int[][] priceBoard; // 가격 정보
    static int[][][] dp; // 메모이제이션 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        priceBoard = new int[N][N];
        dp = new int[1 << N][N][10]; // dp 배열 초기화

        // 가격 정보 입력
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                priceBoard[i][j] = line.charAt(j) - '0';
            }
        }

        // dp 배열 초기화
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        // 초기 상태에서 재귀 호출 시작
        int result = Resell(1, 0, 0) + 1; // 0번 아티스트가 첫 번째 소유자이므로 포함 (+1)
        System.out.println(result);
        sc.close();
    }

    // 재귀 함수
    static int Resell(int visited, int artist, int price) {
        if (dp[visited][artist][price] != -1) {
            return dp[visited][artist][price];
        }

        int maxOwners = 0;

        // 다른 예술가들을 탐색하며 거래 가능 여부 확인
        for (int i = 1; i < N; i++) { // 0번 예술가는 최초 소유자이므로 제외
            if ((visited & (1 << i)) == 0 && priceBoard[artist][i] >= price) {
                // i번 예술가에게 판매 가능한 경우
                int nextVisited = visited | (1 << i);
                maxOwners = Math.max(maxOwners, Resell(nextVisited, i, priceBoard[artist][i]) + 1);
            }
        }

        dp[visited][artist][price] = maxOwners;
        return maxOwners;
    }
}
