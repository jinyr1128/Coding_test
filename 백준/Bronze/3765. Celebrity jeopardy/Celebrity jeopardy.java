import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // while문을 사용하여 입력이 끝날 때까지 계속 처리
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(input);
        }

        scanner.close();
    }
}
