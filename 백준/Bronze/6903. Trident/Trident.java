import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // t, s, h 입력 받기
        int t = sc.nextInt();
        int s = sc.nextInt();
        int h = sc.nextInt();

        // 1. 윗부분 (tines)
        for (int i = 0; i < t; i++) {
            System.out.println("*" + " ".repeat(s) + "*" + " ".repeat(s) + "*");
        }

        // 2. 중간 가로줄
        System.out.println("*".repeat(3 + 2 * s));

        // 3. 손잡이 (handle)
        String handle = " ".repeat(s + 1) + "*";
        for (int i = 0; i < h; i++) {
            System.out.println(handle);
        }

        sc.close();
    }
}
