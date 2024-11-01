import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 줄에서 테스트 케이스 수 입력
        int T = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        // 각 테스트 케이스에 대해 처리
        for (int i = 0; i < T; i++) {
            String input = scanner.nextLine();
            char first = input.charAt(0);  // 첫 글자
            char last = input.charAt(input.length() - 1);  // 마지막 글자
            System.out.println("" + first + last);
        }

        scanner.close();
    }
}
