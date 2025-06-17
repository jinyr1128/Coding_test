import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();  // 1 <= x <= 10^9 이므로 long 사용

        int mod = (int)(x % 3);

        if (mod == 1) {
            System.out.println("U");
        } else if (mod == 2) {
            System.out.println("O");
        } else {
            System.out.println("S");
        }
    }
}
