import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pebbles = scanner.nextInt();
        scanner.close();

        int minCol = Integer.MAX_VALUE;
        int minRow = Integer.MAX_VALUE;
        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i <= pebbles; i++) {
            int j = (pebbles % i == 0) ? (pebbles / i) : (pebbles / i + 1);
            if (i + j < minSum) {
                minSum = i + j;
                minRow = i;
                minCol = j;
            }
        }

        System.out.println(minRow + " " + minCol);
    }
}
