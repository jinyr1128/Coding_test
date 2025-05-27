import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt(); // x 좌표
        int y = sc.nextInt(); // y 좌표

        if (x > 0 && y > 0) {
            System.out.println(1); // 제1사분면
        } else if (x < 0 && y > 0) {
            System.out.println(2); // 제2사분면
        } else if (x < 0 && y < 0) {
            System.out.println(3); // 제3사분면
        } else if (x > 0 && y < 0) {
            System.out.println(4); // 제4사분면
        }
    }
}
