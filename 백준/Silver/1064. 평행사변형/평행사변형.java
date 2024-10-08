import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int x3 = sc.nextInt();
        int y3 = sc.nextInt();

        double d1 = distance(x2, y2, x3, y3);
        double d2 = distance(x3, y3, x1, y1);
        double d3 = distance(x1, y1, x2, y2);

        // 세 점이 일직선인 경우, 평행사변형을 만들 수 없는 경우 처리
        if ((x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1)) {
            System.out.println("-1");
        } else {
            // 최댓값과 최솟값의 차이를 출력
            double result = (Math.max(d1, Math.max(d2, d3)) - Math.min(d1, Math.min(d2, d3))) * 2;
            System.out.printf("%.16f%n", result);
        }

        sc.close();
    }

    // 두 점 사이 거리 계산
    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }
}
