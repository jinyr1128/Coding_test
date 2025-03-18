import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int a1 = sc.nextInt();
            int a2 = sc.nextInt();
            int a3 = sc.nextInt();

            // 종료 조건
            if (a1 == 0 && a2 == 0 && a3 == 0) {
                break;
            }

            // 등차수열(AP) 판별
            if (a2 - a1 == a3 - a2) {
                int a4 = a3 + (a3 - a2);
                System.out.println("AP " + a4);
            }
            // 등비수열(GP) 판별
            else {
                int a4 = a3 * (a3 / a2);
                System.out.println("GP " + a4);
            }
        }

        sc.close();
    }
}
