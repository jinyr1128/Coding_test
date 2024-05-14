import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int t = 0; t < T; t++) {
            long x = scanner.nextLong();
            long y = scanner.nextLong();
            System.out.println(minWarpCount(x, y));
        }
        scanner.close();
    }

    private static int minWarpCount(long x, long y) {
        long distance = y - x;
        long maxMove = (long) Math.sqrt(distance);

        if (maxMove * maxMove == distance) {
            return (int) (2 * maxMove - 1);
        } else if (distance <= maxMove * maxMove + maxMove) {
            return (int) (2 * maxMove);
        } else {
            return (int) (2 * maxMove + 1);
        }
    }
}
