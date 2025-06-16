import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;

        for (int i = 0; i < 6; i++) {
            int containers = sc.nextInt();
            total += containers;
        }

        System.out.println(total * 5);  // 환불 금액(센트 단위)
    }
}
