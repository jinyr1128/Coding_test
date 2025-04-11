import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] nums = new int[5];
        for (int i = 0; i < 5; i++) {
            nums[i] = scanner.nextInt();
        }

        int result = 1;

        while (true) {
            int count = 0;

            for (int i = 0; i < 5; i++) {
                if (result % nums[i] == 0) {
                    count++;
                }
            }

            if (count >= 3) {
                System.out.println(result);
                break;
            }

            result++;
        }
    }
}
