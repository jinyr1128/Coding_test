import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int x1 = scanner.nextInt(), y1 = scanner.nextInt();
            int x2 = scanner.nextInt(), y2 = scanner.nextInt();
            int n = scanner.nextInt();
            int count = 0;

            for (int i = 0; i < n; i++) {
                int cx = scanner.nextInt(), cy = scanner.nextInt(), r = scanner.nextInt();
                boolean startInside = isInside(x1, y1, cx, cy, r);
                boolean endInside = isInside(x2, y2, cx, cy, r);

                // 시작점과 도착점 중 하나만 행성계 내부에 있을 경우 count 증가
                if (startInside != endInside) {
                    count++;
                }
            }

            System.out.println(count);
        }

        scanner.close();
    }

    private static boolean isInside(int x, int y, int cx, int cy, int r) {
        int distanceSquared = (x - cx) * (x - cx) + (y - cy) * (y - cy);
        return distanceSquared < r * r;
    }
}
