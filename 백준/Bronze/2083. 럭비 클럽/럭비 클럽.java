import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 입력으로 회원의 이름, 나이, 몸무게를 받습니다.
            String name = scanner.next();
            int age = scanner.nextInt();
            int weight = scanner.nextInt();

            // 입력의 마지막 줄인지 확인합니다.
            if (name.equals("#") && age == 0 && weight == 0) {
                break;
            }

            // 회원의 분류를 결정합니다.
            if (age > 17 || weight >= 80) {
                System.out.println(name + " Senior");
            } else {
                System.out.println(name + " Junior");
            }
        }

        scanner.close();
    }
}
