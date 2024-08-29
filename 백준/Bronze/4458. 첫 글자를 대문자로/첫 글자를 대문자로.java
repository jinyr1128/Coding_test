import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 소모

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            // 첫 글자를 대문자로 변환하고 나머지 부분과 연결
            String result = line.substring(0, 1).toUpperCase() + line.substring(1);
            System.out.println(result);
        }

        scanner.close();
    }
}
