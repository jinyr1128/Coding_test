import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;

        while (true) {
            int bet = sc.nextInt();
            if (bet == -1) break;
            sum += bet;
        }

        System.out.println(sum);
    }
}
