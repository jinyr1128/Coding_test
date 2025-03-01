import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N 입력 받기
        int N = sc.nextInt();

        // 조건에 따라 출력
        if (N == 0) {
            System.out.println("YONSEI");
        } else if (N == 1) {
            System.out.println("Leading the Way to the Future");
        }

        sc.close();
    }
}
