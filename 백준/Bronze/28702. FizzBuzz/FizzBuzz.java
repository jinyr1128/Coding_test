import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 입력 받기
        String first = scanner.nextLine();
        String second = scanner.nextLine();
        String third = scanner.nextLine();

        int nextNumber = -1;
        for (int i = 0; i < 3; ++i) {
            String str = (i == 0) ? first : (i == 1) ? second : third;
            if (str.startsWith("F") || str.startsWith("B")) {
                continue;
            }
            nextNumber = Integer.parseInt(str) + 3 - i;
            break;
        }

        if (nextNumber != -1) {
            if (nextNumber % 3 != 0 && nextNumber % 5 != 0) {
                System.out.println(nextNumber);
            } else {
                if (nextNumber % 3 == 0) {
                    System.out.print("Fizz");
                }
                if (nextNumber % 5 == 0) {
                    System.out.print("Buzz");
                }
                System.out.println();
            }
        }

        scanner.close();
    }
}

