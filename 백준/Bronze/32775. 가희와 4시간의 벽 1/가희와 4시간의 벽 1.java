import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 고속철도 및 항공편 소요 시간 입력
        int S_ab = sc.nextInt();
        int F_ab = sc.nextInt();

        // 비교 후 출력
        if (S_ab <= F_ab) {
            System.out.println("high speed rail");
        } else {
            System.out.println("flight");
        }

        sc.close();
    }
}
