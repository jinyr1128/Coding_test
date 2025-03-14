import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();

        if (h == 0) {
            System.out.println("1"); // 최소한의 숫자
        } else if (h == 1) {
            System.out.println("0"); // h=1일 경우 예외 처리
        } else {
            StringBuilder result = new StringBuilder();
            if (h % 2 == 1) {
                result.append("4"); // 홀수일 경우 첫 번째 숫자는 "4"
                h -= 1; // 하나를 사용했으므로 h 감소
            }
            for (int i = 0; i < h / 2; i++) {
                result.append("8"); // 나머지는 "8"으로 채움
            }
            System.out.println(result);
        }

        sc.close();
    }
}
