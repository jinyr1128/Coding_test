import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(A + B); // A+B 출력
        System.out.println(A - B); // A-B 출력
        System.out.println(A * B); // A*B 출력
        System.out.println(A / B); // A/B(몫) 출력
        System.out.println(A % B); // A%B(나머지) 출력

        sc.close();
    }
}
