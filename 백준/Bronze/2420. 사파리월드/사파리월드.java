import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 두 정수를 입력받습니다.
        long N = scanner.nextLong();
        long M = scanner.nextLong();

        // 두 정수의 차이의 절대값을 구합니다.
        long difference = Math.abs(N - M);

        // 결과를 출력합니다.
        System.out.println(difference);
        
        scanner.close();
    }
}
