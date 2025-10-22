import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 지폐의 수 N을 입력받습니다.
        int N = sc.nextInt();

        // 2. 총액을 저장할 변수를 long 타입으로 선언합니다.
        long totalAmount = 0;

        // 3. N번 반복합니다.
        for (int i = 0; i < N; i++) {
            // 가로 길이와 세로 길이를 입력받습니다.
            int width = sc.nextInt();
            int height = sc.nextInt(); // 세로 길이는 판별에 사용되지 않지만 입력은 받아야 함

            // 4. 가로 길이에 따라 총액에 더합니다.
            if (width == 136) {
                totalAmount += 1000;
            } else if (width == 142) {
                totalAmount += 5000;
            } else if (width == 148) {
                totalAmount += 10000;
            } else if (width == 154) {
                totalAmount += 50000;
            }
        }

        sc.close();

        // 5. 총액을 출력합니다.
        System.out.println(totalAmount);
    }
}