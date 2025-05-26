import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 딸의 수
        int O = sc.nextInt(); // 남은 메달 수

        int minTotal = Integer.MAX_VALUE;
        int maxTotal = Integer.MIN_VALUE;

        for (int total = N; total <= 1000; total++) {
            int share = total / N;
            int remainder = total % N;

            int taken;
            if (remainder == 0) {
                taken = share;
            } else {
                // 막내는 항상 작은 몫을 가져감
                taken = share;
            }

            int remaining = total - taken;

            if (remaining == O) {
                minTotal = Math.min(minTotal, total);
                maxTotal = Math.max(maxTotal, total);
            }
        }

        System.out.println(minTotal + " " + maxTotal);
    }
}
