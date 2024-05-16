import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 선분의 좌표 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Point p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        
        // 두 번째 선분의 좌표 입력
        st = new StringTokenizer(br.readLine(), " ");
        Point p3 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point p4 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // 결과 출력
        System.out.println(checkIntersection(p1, p2, p3, p4));
        br.close();
    }

    // 교차 여부와 교차점 계산 함수
    private static String checkIntersection(Point p1, Point p2, Point p3, Point p4) {
        StringBuilder result = new StringBuilder();

        int ccw1 = ccw(p1, p2, p3);
        int ccw2 = ccw(p1, p2, p4);
        int ccw3 = ccw(p3, p4, p1);
        int ccw4 = ccw(p3, p4, p2);
        
        int product1 = ccw1 * ccw2;
        int product2 = ccw3 * ccw4;

        // 선분이 교차하는 경우
        if (product1 <= 0 && product2 < 0 || product1 < 0 && product2 <= 0) {
            result.append(1).append('\n');
            String slope1 = calculateSlope(p1, p2);
            String slope2 = calculateSlope(p3, p4);
            double intersectionX, intersectionY;
            
            if (slope1.equals("INF")) {
                intersectionX = p1.x;
                double slope2Val = Double.parseDouble(slope2);
                intersectionY = slope2Val * (intersectionX - p3.x) + p3.y;
            } else if (slope2.equals("INF")) {
                intersectionX = p3.x;
                double slope1Val = Double.parseDouble(slope1);
                intersectionY = slope1Val * (intersectionX - p1.x) + p1.y;
            } else {
                double slope1Val = Double.parseDouble(slope1);
                double slope2Val = Double.parseDouble(slope2);
                intersectionX = (slope1Val * p1.x - slope2Val * p3.x + p3.y - p1.y) / (slope1Val - slope2Val);
                intersectionY = slope1Val * (intersectionX - p1.x) + p1.y;
            }
            result.append(intersectionX).append(' ').append(intersectionY);
        } else if (product1 == 0 && product2 == 0) {
            if (ccw1 == 0 && ccw2 == 0 && ccw3 == 0 && ccw4 == 0) { 
                int overlapCheck = isOverlapping(p1, p2, p3, p4);
                if (overlapCheck > 0) result.append(1);
                else result.append(0);

                if (overlapCheck == 2) {
                    result.append('\n');
                    if (pointsMatch(p1, p3) || pointsMatch(p1, p4)) result.append(p1.x).append(' ').append(p1.y);
                    else if (pointsMatch(p2, p3) || pointsMatch(p2, p4)) result.append(p2.x).append(' ').append(p2.y);    
                }
            } else {
                result.append(1).append('\n');
                if (pointsMatch(p1, p3) || pointsMatch(p1, p4)) result.append(p1.x).append(' ').append(p1.y);
                else if (pointsMatch(p2, p3) || pointsMatch(p2, p4)) result.append(p2.x).append(' ').append(p2.y);
            }    
        } else {
            result.append(0);
        }

        return result.toString();
    }

    // 기울기 계산 함수
    private static String calculateSlope(Point p1, Point p2) {
        if (p1.x == p2.x) return "INF";  // 수직선인 경우
        double slope = ((double) p2.y - p1.y) / (p2.x - p1.x);
        return String.valueOf(slope);
    }

    // 두 선분이 겹치는지 확인하는 함수
    private static int isOverlapping(Point p1, Point p2, Point p3, Point p4) {
        int A, B, C, D;
        if (p1.x == p2.x) {
            A = Math.min(p1.y, p2.y);
            B = Math.max(p1.y, p2.y);
            C = Math.min(p3.y, p4.y);
            D = Math.max(p3.y, p4.y);
        } else {
            A = Math.min(p1.x, p2.x);
            B = Math.max(p1.x, p2.x);
            C = Math.min(p3.x, p4.x);
            D = Math.max(p3.x, p4.x);
        }

        if (A == D || B == C) return 2;
        else if (A < D && C < B) return 1;
        else return 0;
    }

    // CCW 알고리즘 함수
    private static int ccw(Point p1, Point p2, Point p3) {
        long crossProduct = (long) p1.x * p2.y + (long) p2.x * p3.y + (long) p3.x * p1.y
                          - (long) p1.y * p2.x - (long) p2.y * p3.x - (long) p3.y * p1.x;
        if (crossProduct == 0) return 0;  // 일직선 상에 있음
        return crossProduct > 0 ? 1 : -1;  // 반시계 방향, 시계 방향
    }

    // 두 점이 같은지 확인하는 함수
    private static boolean pointsMatch(Point p1, Point p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }
}

