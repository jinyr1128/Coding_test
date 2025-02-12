import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력받기
        int n = sc.nextInt();

        // 코드1의 수행 횟수 계산
        long count = (long) n * (n - 1) * (n - 2) / 6;

        // 수행 횟수 출력
        System.out.println(count);

        // 다항식 최고 차수 출력 (3중 루프 → O(n^3) → 차수는 3)
        System.out.println(3);

        sc.close();
    }
}
