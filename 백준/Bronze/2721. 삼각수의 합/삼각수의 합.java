import java.util.Scanner;

public class Main {

    // 삼각수 T(n)을 계산하는 함수
    public static int triangularNumber(int n) {
        return n * (n + 1) / 2;
    }

    // W(n)을 계산하는 함수
    public static int weightedTriangularSum(int n) {
        int sum = 0;
        for (int k = 1; k <= n; k++) {
            sum += k * triangularNumber(k + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        
        // 각 테스트 케이스에 대해 결과 계산
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            System.out.println(weightedTriangularSum(n));
        }
        
        scanner.close();
    }
}
