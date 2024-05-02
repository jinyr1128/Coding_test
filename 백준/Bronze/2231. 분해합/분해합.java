import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 1; i < N; i++) {
            int sum = i;
            int current = i;

            while (current > 0) {
                sum += current % 10;
                current /= 10;
            }

            if (sum == N) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(0);
    }
}
