import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int minOdd = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int i = 0; i < 7; i++) {
            int number = scanner.nextInt();
            if (number % 2 != 0) { // 홀수인 경우
                sum += number;
                if (number < minOdd) {
                    minOdd = number;
                }
                hasOdd = true;
            }
        }

        if (hasOdd) {
            System.out.println(sum);
            System.out.println(minOdd);
        } else {
            System.out.println(-1);
        }
    }
}
