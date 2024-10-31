#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

#define X first
#define Y second

using namespace std;

using ll = long long;
using Point = pair<ll, ll>;

const ll LARGE_VAL = 0x3f3f3f3f3f3f3f3f;
const Point ORIGIN = {0, 0};

int findCCW(const Point &pt1, const Point &pt2, const Point &pt3){
    ll dx1 = pt2.X - pt1.X, dy1 = pt2.Y - pt1.Y;
    ll dx2 = pt3.X - pt2.X, dy2 = pt3.Y - pt2.Y;
    ll cross = dx1 * dy2 - dx2 * dy1;
    return (cross > 0) - (cross < 0);
}

ll manhattanDist(const Point &pt1, const Point &pt2){
    return abs(pt1.X - pt2.X) + abs(pt1.Y - pt2.Y);
}

ll euclideanDistSquared(const Point &pt1, const Point &pt2){
    ll dx = pt1.X - pt2.X, dy = pt1.Y - pt2.Y;
    return dx * dx + dy * dy;
}

int numPoints;
Point points[101010], tempPoints[101010];

vector<Point> findConvexHull(){
    vector<Point> pts(points + 1, points + numPoints + 1);
    vector<Point> hull;
    swap(pts[0], *min_element(pts.begin(), pts.end()));
    sort(pts.begin() + 1, pts.end(), [&](const Point &a, const Point &b){
        int cw = findCCW(pts[0], a, b);
        if (cw != 0) return cw > 0;
        return euclideanDistSquared(pts[0], a) < euclideanDistSquared(pts[0], b);
    });
    for(const auto &pt : pts){
        while (hull.size() >= 2 && findCCW(hull[hull.size() - 2], hull.back(), pt) <= 0)
            hull.pop_back();
        hull.push_back(pt);
    }
    return move(hull);
}

bool isValidDirectCalipers(const Point &s1, const Point &e1, const Point &s2, const Point &e2){
    Point vec1 = { e1.X - s1.X, e1.Y - s1.Y };
    Point vec2 = { e2.X - s2.X, e2.Y - s2.Y };
    return findCCW(ORIGIN, vec1, vec2) >= 0;
}

pair<Point, Point> rotatingCalipers(){
    auto convexHull = findConvexHull();
    ll hullSize = convexHull.size(), maxDist = 0;
    Point ptA, ptB;
    for (int i = 0, j = 0; i < hullSize; i++){
        while (j + 1 < hullSize && isValidDirectCalipers(convexHull[i], convexHull[i + 1], convexHull[j], convexHull[j + 1])){
            ll distNow = euclideanDistSquared(convexHull[i], convexHull[j]);
            if (maxDist < distNow) maxDist = distNow, ptA = convexHull[i], ptB = convexHull[j];
            j++;
        }
        ll distNow = euclideanDistSquared(convexHull[i], convexHull[j]);
        if (maxDist < distNow) maxDist = distNow, ptA = convexHull[i], ptB = convexHull[j];
    }
    return make_pair(ptA, ptB);
}

ll solveManhattanMin(int start=1, int end=numPoints){
    if(start == end) return LARGE_VAL;
    int mid = (start + end) / 2;
    ll midLine = points[mid].X;
    ll minDist = min(solveManhattanMin(start, mid), solveManhattanMin(mid + 1, end));

    int l = start, r = mid + 1, idx = start;
    while (l <= mid && r <= end){
        if (points[l].Y < points[r].Y) tempPoints[idx++] = points[l++];
        else tempPoints[idx++] = points[r++];
    }
    while (l <= mid) tempPoints[idx++] = points[l++];
    while (r <= end) tempPoints[idx++] = points[r++];
    for (int i = start; i <= end; i++) points[i] = tempPoints[i];

    vector<Point> filtered;
    for (int i = start; i <= end; i++){
        ll dx = abs(points[i].X - midLine);
        if (dx < minDist) filtered.push_back(points[i]);
    }

    ll result = minDist;
    for (int i = 1; i < filtered.size(); i++){
        for (int j = i - 1; j >= 0; j--){
            ll dy = abs(filtered[i].Y - filtered[j].Y);
            if (dy >= minDist) break;
            result = min(result, manhattanDist(filtered[i], filtered[j]));
        }
    }
    return result;
}

ll solveEuclideanMin(int start=1, int end=numPoints){
    if (start == end) return LARGE_VAL;
    int mid = (start + end) / 2;
    ll midLine = points[mid].X;
    ll minDist = min(solveEuclideanMin(start, mid), solveEuclideanMin(mid + 1, end));

    int l = start, r = mid + 1, idx = start;
    while (l <= mid && r <= end){
        if (points[l].Y < points[r].Y) tempPoints[idx++] = points[l++];
        else tempPoints[idx++] = points[r++];
    }
    while (l <= mid) tempPoints[idx++] = points[l++];
    while (r <= end) tempPoints[idx++] = points[r++];
    for (int i = start; i <= end; i++) points[i] = tempPoints[i];

    vector<Point> filtered;
    for (int i = start; i <= end; i++){
        ll dx = points[i].X - midLine;
        if (dx * dx < minDist) filtered.push_back(points[i]);
    }

    ll result = minDist;
    for (int i = 1; i < filtered.size(); i++){
        for (int j = i - 1; j >= 0; j--){
            ll dy = filtered[i].Y - filtered[j].Y;
            if (dy * dy >= minDist) break;
            result = min(result, euclideanDistSquared(filtered[i], filtered[j]));
        }
    }
    return result;
}

void modifyCoordinates(bool divide){
    for (int i = 1; i <= numPoints; i++){
        auto [x, y] = points[i];
        points[i] = {x + y, x - y};
        if (divide) points[i].X /= 2, points[i].Y /= 2;
    }
}

ll maxChebyshevDist(){
    ll minX = LARGE_VAL, maxX = -LARGE_VAL;
    ll minY = LARGE_VAL, maxY = -LARGE_VAL;
    for (int i = 1; i <= numPoints; i++){
        minX = min(minX, points[i].X); maxX = max(maxX, points[i].X);
        minY = min(minY, points[i].Y); maxY = max(maxY, points[i].Y);
    }
    return max(maxX - minX, maxY - minY);
}

int main(){
    ios_base::sync_with_stdio(false); cin.tie(nullptr);
    cin >> numPoints;
    for (int i = 1; i <= numPoints; i++) cin >> points[i].X >> points[i].Y;

    auto [point1, point2] = rotatingCalipers();
    cout << euclideanDistSquared(point1, point2) << "\n";

    sort(points + 1, points + numPoints + 1);
    cout << solveEuclideanMin() << "\n";

    modifyCoordinates(false);
    cout << maxChebyshevDist() << "\n";
    modifyCoordinates(true);

    sort(points + 1, points + numPoints + 1);
    cout << solveManhattanMin() << "\n";

    cout << maxChebyshevDist() << "\n";

    modifyCoordinates(false);
    sort(points + 1, points + numPoints + 1);
    cout << solveManhattanMin() / 2 << "\n";
    modifyCoordinates(true);
}
