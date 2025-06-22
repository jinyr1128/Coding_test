import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n >= 5) {
            System.out.println(0);
        } else {
            int fact = 1;
            for (int i = 2; i <= n; i++) {
                fact *= i;
            }
            System.out.println(fact % 10);
        }
    }
}
