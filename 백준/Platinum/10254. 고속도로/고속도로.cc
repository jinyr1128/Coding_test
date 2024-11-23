#include <iostream>
#include <vector>
#include <algorithm>

// 2D 좌표를 나타내는 구조체
struct Point {
    long long x, y;        // 실제 좌표
    long long relX, relY;  // 기준점에 대한 상대 좌표

    Point(long long x = 0, long long y = 0) : x(x), y(y), relX(1), relY(0) {}
};

// 두 점 사이의 거리 제곱 계산
long long calculateDistance(const Point& p1, const Point& p2) {
    return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
}

// CCW 계산: 두 벡터가 이루는 회전 방향
bool isCCW(const Point& p1, const Point& p2, const Point& p3) {
    return p1.x * p2.y + p2.x * p3.y + p3.x * p1.y - p3.x * p2.y - p2.x * p1.y - p1.x * p3.y > 0;
}

// 두 벡터의 외적을 계산하여 방향을 반환
long long crossProduct(const Point& p1, const Point& p2, const Point& p3, const Point& p4) {
    long long dx1 = p2.x - p1.x, dy1 = p2.y - p1.y;
    long long dx2 = p4.x - p3.x, dy2 = p4.y - p3.y;
    return dx1 * dy2 - dx2 * dy1;
}

// 좌표를 기준으로 정렬하기 위한 비교 함수
bool comparePoints(const Point& p1, const Point& p2) {
    if (p1.relX * p2.relY != p2.relX * p1.relY) 
        return p1.relX * p2.relY > p2.relX * p1.relY;
    return p1.y < p2.y || (p1.y == p2.y && p1.x < p2.x);
}

// 회전 캘리퍼스 알고리즘을 이용하여 가장 먼 두 점을 찾음
void findFarthestPoints(std::vector<int>& convexHullIndices, std::vector<Point>& points) {
    int l = 0, r = 1, n = convexHullIndices.size();
    int farthest1 = convexHullIndices[l], farthest2 = convexHullIndices[r];
    long long maxDistance = calculateDistance(points[convexHullIndices[l]], points[convexHullIndices[r]]);

    for (int i = 0; i < n * 2; ++i) {
        long long cross = crossProduct(points[convexHullIndices[l]], points[convexHullIndices[(l + 1) % n]],
                                       points[convexHullIndices[(r + 1) % n]], points[convexHullIndices[r]]);
        if (cross > 0) l = (l + 1) % n;
        else if (cross < 0) r = (r + 1) % n;
        else l = (l + 1) % n, r = (r + 1) % n;

        long long dist = calculateDistance(points[convexHullIndices[l]], points[convexHullIndices[r]]);
        if (dist > maxDistance) {
            maxDistance = dist;
            farthest1 = convexHullIndices[l];
            farthest2 = convexHullIndices[r];
        }
    }

    std::cout << points[farthest1].x << ' ' << points[farthest1].y << ' '
              << points[farthest2].x << ' ' << points[farthest2].y << '\n';
}

int main() {
    int testCases;
    std::cin >> testCases;

    while (testCases--) {
        int numPoints;
        std::cin >> numPoints;

        std::vector<Point> points(numPoints);
        for (int i = 0; i < numPoints; ++i) {
            std::cin >> points[i].x >> points[i].y;
        }

        std::sort(points.begin(), points.end(), comparePoints);
        for (int i = 1; i < numPoints; ++i) {
            points[i].relX = points[i].x - points[0].x;
            points[i].relY = points[i].y - points[0].y;
        }
        std::sort(points.begin() + 1, points.end(), comparePoints);

        std::vector<int> convexHullIndices = {0, 1};
        for (int i = 2; i < numPoints; ++i) {
            while (convexHullIndices.size() > 1) {
                int second = convexHullIndices.back();
                convexHullIndices.pop_back();
                int first = convexHullIndices.back();
                if (isCCW(points[first], points[second], points[i])) {
                    convexHullIndices.push_back(second);
                    break;
                }
            }
            convexHullIndices.push_back(i);
        }

        findFarthestPoints(convexHullIndices, points);
    }

    return 0;
}
