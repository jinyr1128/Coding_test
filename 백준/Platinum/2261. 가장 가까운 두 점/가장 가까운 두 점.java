import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        // x좌표 기준 정렬
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));

        // 가장 가까운 두 점의 거리 계산
        int result = findClosestDistance(points, 0, n - 1);
        System.out.println(result);
    }

    // 두 점 사이의 거리의 제곱 계산
    static int distance(Point p1, Point p2) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        return dx * dx + dy * dy;
    }

    // 가장 가까운 두 점의 거리 찾기 (분할 정복)
    static int findClosestDistance(Point[] points, int low, int high) {
        if (low == high) {
            return MAX;
        }

        if (low + 1 == high) {
            return distance(points[low], points[high]);
        }

        int mid = (low + high) / 2;
        int leftMin = findClosestDistance(points, low, mid);
        int rightMin = findClosestDistance(points, mid + 1, high);
        int minDistance = Math.min(leftMin, rightMin);

        // 중간 영역에서 기준선과 x값의 차이가 minDistance 이하인 점들을 찾음
        List<Point> strip = new ArrayList<>();
        int midX = points[mid].x;

        for (int i = low; i <= high; i++) {
            int dx = points[i].x - midX;
            if (dx * dx < minDistance) {
                strip.add(points[i]);
            }
        }

        // y좌표 기준 정렬
        strip.sort(Comparator.comparingInt(p -> p.y));

        // 중간 영역에서의 최소 거리 계산
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                int dy = strip.get(j).y - strip.get(i).y;
                if (dy * dy >= minDistance) {
                    break;
                }
                minDistance = Math.min(minDistance, distance(strip.get(i), strip.get(j)));
            }
        }

        return minDistance;
    }

    // 2차원 좌표를 표현하는 클래스
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
