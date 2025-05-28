import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 노래 길이 (틱 수)

        double revolutions = n / 4.0; // 한 회전에 4틱
        System.out.printf("%.2f\n", revolutions); // 소수점 2자리까지 출력
    }
}
