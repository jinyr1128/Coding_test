#include <iostream>
#include <vector>
#include <algorithm>

typedef long long ll;

// 점을 나타내는 구조체
struct Point {
    ll x, y, deltaX, deltaY;
    Point(ll x = 0, ll y = 0) : x(x), y(y), deltaX(1), deltaY(0) {}
    // 정렬 기준 정의: 기준점과의 방향 및 y, x 좌표 기준
    bool operator<(Point& other) {
        if (deltaX * other.deltaY ^ other.deltaX * deltaY) 
            return deltaX * other.deltaY > other.deltaX * deltaY;
        return y < other.y ? true : (y == other.y && x < other.x);
    }
};

// 세 점의 방향성을 계산하는 CCW 함수
ll inline computeCCW(const Point& p1, const Point& p2, const Point& p3) {
    ll result = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y
                - p3.x * p2.y - p2.x * p1.y - p1.x * p3.y;
    return result > 0 ? 1 : result < 0 ? -1 : 0;
}

// 두 선분이 분리되었는지 확인
bool inline areDisjoint(ll a, ll b, ll c, ll d) {
    return std::max(a, b) < std::min(c, d) || std::min(a, b) > std::max(c, d);
}

int numBlackPoints, numWhitePoints;
std::vector<Point> blackPoints, whitePoints, blackHull, whiteHull;

// 볼록 껍질 계산 (Monotone Chain Algorithm)
void computeConvexHull(std::vector<Point>& points, std::vector<Point>& hull) {
    std::sort(points.begin(), points.end());
    for (int i = 1; i < points.size(); ++i) {
        points[i].deltaX = points[i].x - points[0].x;
        points[i].deltaY = points[i].y - points[0].y;
    }
    std::sort(points.begin() + 1, points.end());

    for (int i = 0; i < points.size(); ++i) {
        while (hull.size() >= 2 && computeCCW(hull[hull.size() - 2], hull.back(), points[i]) <= 0) {
            hull.pop_back();
        }
        hull.push_back(points[i]);
    }
}

// 점이 다각형 내부에 있는지 확인
bool isPointInside(const Point& point, const std::vector<Point>& hull) {
    ll initialDirection = computeCCW(hull[0], hull[1], point);
    for (int i = 1; i < hull.size(); ++i) {
        if (initialDirection * computeCCW(hull[i], hull[(i + 1) % hull.size()], point) <= 0) {
            return false;
        }
    }
    return true;
}

// 하나의 다각형이 다른 다각형 내부에 있는지 확인
bool isPolygonInside(const std::vector<Point>& polygonA, const std::vector<Point>& polygonB) {
    if (polygonB.size() > 2) {
        for (const Point& point : polygonA) {
            if (isPointInside(point, polygonB)) return true;
        }
    }
    return false;
}

// 두 선분이 교차하는지 확인
bool doSegmentsIntersect(const Point& a, const Point& b, const Point& c, const Point& d) {
    int directionAB = computeCCW(a, b, c) * computeCCW(a, b, d);
    int directionCD = computeCCW(c, d, a) * computeCCW(c, d, b);
    if (directionAB == 0 && directionCD == 0) {
        return !areDisjoint(a.x, b.x, c.x, d.x) && !areDisjoint(a.y, b.y, c.y, d.y);
    }
    return directionAB <= 0 && directionCD <= 0;
}

// 두 다각형의 선분이 교차하는지 확인
bool doPolygonsOverlap(const std::vector<Point>& polygonA, const std::vector<Point>& polygonB) {
    int n = polygonA.size(), m = polygonB.size();
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (doSegmentsIntersect(polygonA[i], polygonA[(i + 1) % n], polygonB[j], polygonB[(j + 1) % m])) {
                return true;
            }
        }
    }
    return false;
}

// 문제 풀이 함수
void solve() {
    blackPoints.clear();
    whitePoints.clear();
    blackHull.clear();
    whiteHull.clear();

    std::cin >> numBlackPoints >> numWhitePoints;
    for (ll x, y, i = 0; i < numBlackPoints; ++i) {
        std::cin >> x >> y;
        blackPoints.push_back(Point(x, y));
    }
    for (ll x, y, i = 0; i < numWhitePoints; ++i) {
        std::cin >> x >> y;
        whitePoints.push_back(Point(x, y));
    }
    computeConvexHull(blackPoints, blackHull);
    computeConvexHull(whitePoints, whiteHull);

    if (isPolygonInside(blackHull, whiteHull) || 
        isPolygonInside(whiteHull, blackHull) || 
        doPolygonsOverlap(blackHull, whiteHull)) {
        std::cout << "NO\n";
    } else {
        std::cout << "YES\n";
    }
}

int main() {
    int testCases;
    std::cin >> testCases;
    while (testCases--) solve();
}
