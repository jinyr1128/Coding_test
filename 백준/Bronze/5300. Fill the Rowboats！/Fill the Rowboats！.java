import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 해적 수

        for (int i = 1; i <= N; i++) {
            System.out.print(i + " ");

            if (i % 6 == 0) {
                System.out.print("Go! ");
            }
        }

        // 마지막 Go!가 이미 출력되지 않았다면 한 번 더 출력
        if (N % 6 != 0) {
            System.out.print("Go!");
        }
    }
}
