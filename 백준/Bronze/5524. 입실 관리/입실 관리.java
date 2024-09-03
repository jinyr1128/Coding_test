import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 번째 줄에서 N을 입력 받음
        int N = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 소비

        // N개의 이름을 입력받고, 소문자로 변환하여 출력
        for (int i = 0; i < N; i++) {
            String name = scanner.nextLine();
            System.out.println(name.toLowerCase());
        }

        scanner.close();
    }
}
