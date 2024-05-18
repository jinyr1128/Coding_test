import java.util.Scanner;

public class Main {
    static final int MOD = 10007;
    static int[][] binomialCoeff = new int[53][53];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCards = scanner.nextInt();

        initializeBinomialCoeff();

        int result = calculateWinningCases(numCards);

        System.out.println(result);
    }

    // 이항계수 테이블 초기화
    private static void initializeBinomialCoeff() {
        for (int n = 0; n <= 52; n++) {
            binomialCoeff[n][0] = 1;
            binomialCoeff[n][n] = 1;
            for (int k = 1; k < n; k++) {
                binomialCoeff[n][k] = (binomialCoeff[n - 1][k - 1] + binomialCoeff[n - 1][k]) % MOD;
            }
        }
    }

    // 포카드의 경우의 수 계산
    private static int calculateWinningCases(int numCards) {
        int result = 0;
        for (int i = 4; i <= numCards; i += 4) {
            int sign = ((i / 4) % 2 == 1) ? 1 : -1;
            int combinationPart = binomialCoeff[13][i / 4] * binomialCoeff[52 - i][numCards - i] % MOD;
            result = (result + sign * combinationPart) % MOD;
        }

        if (result < 0) {
            result += MOD;
        }

        return result;
    }
}

