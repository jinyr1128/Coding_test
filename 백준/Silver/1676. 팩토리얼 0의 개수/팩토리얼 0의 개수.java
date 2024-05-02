import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int count = 0;

        for (int i = 5; i <= N; i *= 5) {
            count += N / i;
        }

        System.out.println(count);
        sc.close();
    }
}
