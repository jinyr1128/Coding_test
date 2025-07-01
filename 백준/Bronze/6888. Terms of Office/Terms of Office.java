import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int startYear = sc.nextInt();
        int endYear = sc.nextInt();

        int lcm = lcm(4, lcm(2, lcm(3, 5))); // 60

        for (int year = startYear; year <= endYear; year++) {
            if ((year - startYear) % lcm == 0) {
                System.out.println("All positions change in year " + year);
            }
        }

        sc.close();
    }

    // 최대공약수
    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // 최소공배수
    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
