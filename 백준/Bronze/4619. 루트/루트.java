import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int B = scanner.nextInt();
            int N = scanner.nextInt();
            if (B == 0 && N == 0) break;

            int A = 1;
            int closestA = A;
            int minDifference = Math.abs((int)Math.pow(A, N) - B);

            while (Math.pow(A, N) <= B) {
                int currentDifference = Math.abs((int)Math.pow(A, N) - B);
                if (currentDifference < minDifference) {
                    minDifference = currentDifference;
                    closestA = A;
                }
                A++;
            }

            if (Math.abs((int)Math.pow(A, N) - B) < minDifference) {
                closestA = A;
            }

            System.out.println(closestA);
        }

        scanner.close();
    }
}
