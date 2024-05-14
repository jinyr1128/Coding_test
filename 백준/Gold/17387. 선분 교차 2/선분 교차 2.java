import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 선분의 끝 점 입력
        st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        // 두 번째 선분의 끝 점 입력
        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());

        if (doIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    // 두 선분이 교차하는지 확인하는 함수
    private static boolean doIntersect(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int o1 = ccw(x1, y1, x2, y2, x3, y3);
        int o2 = ccw(x1, y1, x2, y2, x4, y4);
        int o3 = ccw(x3, y3, x4, y4, x1, y1);
        int o4 = ccw(x3, y3, x4, y4, x2, y2);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        return (o1 == 0 && onSegment(x1, y1, x3, y3, x2, y2)) ||
               (o2 == 0 && onSegment(x1, y1, x4, y4, x2, y2)) ||
               (o3 == 0 && onSegment(x3, y3, x1, y1, x4, y4)) ||
               (o4 == 0 && onSegment(x3, y3, x2, y2, x4, y4));
    }

    // CCW (Counter Clockwise) 검사 함수
    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (result > 0) return 1;
        if (result < 0) return -1;
        return 0;
    }

    // 선분 위에 점이 있는지 확인하는 함수
    private static boolean onSegment(long x1, long y1, long x2, long y2, long x3, long y3) {
        return Math.min(x1, x3) <= x2 && x2 <= Math.max(x1, x3) &&
               Math.min(y1, y3) <= y2 && y2 <= Math.max(y1, y3);
    }
}
