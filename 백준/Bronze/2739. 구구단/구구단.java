import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 사용자로부터 구구단의 단수 N을 입력받음

        for (int i = 1; i <= 9; i++) {
            System.out.printf("%d * %d = %d\n", N, i, N * i);  // N단 출력
        }
    }
}
