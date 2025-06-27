import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt(); // 테스트 케이스 수

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt(); // 현재 테스트 케이스의 n
            System.out.print("Pairs for " + n + ":");

            boolean first = true;

            // a < b, a + b == n
            for (int a = 1; a < n; a++) {
                int b = n - a;

                if (a < b) { // 두 수는 달라야 하고, a < b 조건
                    if (!first) {
                        System.out.print(","); // 쌍 사이에 쉼표
                    }
                    System.out.print(" " + a + " " + b);
                    first = false;
                }
            }

            System.out.println(); // 테스트 케이스 끝
        }

        sc.close();
    }
}

