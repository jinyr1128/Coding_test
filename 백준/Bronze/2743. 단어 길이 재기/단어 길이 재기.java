import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner 객체를 사용하여 입력을 받습니다.
        Scanner scanner = new Scanner(System.in);

        // 문자열을 입력받습니다.
        String word = scanner.nextLine();

        // 문자열의 길이를 계산합니다.
        int length = word.length();

        // 결과를 출력합니다.
        System.out.println(length);

        // Scanner 객체를 닫습니다.
        scanner.close();
    }
}
