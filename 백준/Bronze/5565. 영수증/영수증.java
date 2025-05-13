import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int total = sc.nextInt(); // 총 가격
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += sc.nextInt(); // 9권의 가격을 합산
        }

        System.out.println(total - sum); // 남은 1권의 가격 출력
    }
}
