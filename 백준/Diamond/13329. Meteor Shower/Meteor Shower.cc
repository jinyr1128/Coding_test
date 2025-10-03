#include <bits/stdc++.h>
using namespace std;
using i64 = int64_t;

/*----- 2D 벡터(점) -----*/
struct Point {
    int x, y;
    Point(): x(0), y(0) {}
    Point(int _x, int _y): x(_x), y(_y) {}

    // 벡터 차
    Point operator-(const Point& v) const { return { x - v.x, y - v.y }; }

    // 극각(원점 기준 각도) 비교용 정렬 연산자
    // (x1,y1) < (x2,y2)  <=>  x1*y2 > y1*x2  (극각이 더 작다)
    //  - 동일 직선상(원점)으로의 정렬을 위해 사용
    bool operator<(const Point& v) const {
        return 1ll * x * v.y > 1ll * y * v.x;
    }
};

// 외적
inline i64 cross(const Point& a, const Point& b) {
    return 1ll * a.x * b.y - 1ll * a.y * b.x;
}
// CCW(반시계) 판정: ccw(o, p, q) = cross(p-o, q-o)
inline i64 ccw(const Point& o, const Point& p, const Point& q) {
    return cross(p - o, q - o);
}

/*
  각 폴리곤 i에 대해,
  angStart[i] : 극각이 가장 작은 꼭짓점 (각도 시작)
  angEnd[i]   : 극각이 가장 큰 꼭짓점 (각도 끝)
  -> i 폴리곤은 sweep 시 [angStart[i], angEnd[i]] 구간에서 "보일 수 있는 후보"가 됨
*/
array<Point, 100005> angStart, angEnd;

/*----- sweep 중 활성 집합에 넣는 원소 -----*/
struct ActiveEntry {
    int id; // 폴리곤 번호(1..n)
    ActiveEntry(int _id): id(_id) {}

    // 활성 집합에서의 정렬 기준:
    // 현재 sweep 각도에서 "가장 앞(가까운)" 폴리곤이 집합의 begin() 에 오도록
    // 두 폴리곤의 "각도 구간 방향(angStart->angEnd)"과 비교 폴리곤의 시작점을 CCW로 비교
    bool operator<(const ActiveEntry& other) const {
        if (angStart[id] < angStart[other.id])
            return ccw(angStart[id], angEnd[id], angStart[other.id]) < 0;
        else
            return ccw(angStart[other.id], angEnd[other.id], angStart[id]) > 0;
    }
};

set<ActiveEntry> active;                                   // sweep 중 활성 폴리곤 집합
array<set<ActiveEntry>::iterator, 100005> iterInActive;    // 각 폴리곤의 set iterator 저장
bitset<100005> isVisible;                                   // 한 번이라도 최전면에 등장한(보인) 폴리곤 표시

int main() {
    cin.tie(nullptr)->sync_with_stdio(false);

    int n; 
    cin >> n;

    // 이벤트: +i = 폴리곤 i의 "각도 시작", -i = 폴리곤 i의 "각도 끝"
    vector<int> events;
    events.reserve(2 * n);

    for (int i = 1; i <= n; ++i) {
        // 초기값: 각도 최소점을 (−1,0), 최대점을 (+1,0)으로 두고 갱신 시작
        angStart[i] = Point(-1, 0);
        angEnd[i]   = Point(+1, 0);

        int m; 
        cin >> m;                       // i번째 폴리곤의 꼭짓점 수
        while (m--) {
            int x, y; 
            cin >> x >> y;
            Point v(x, y);
            // 극각 기준 최소/최대 갱신 (연산자< 정의 활용)
            angStart[i] = min(angStart[i], v);
            angEnd[i]   = max(angEnd[i], v);
        }
        // 각도 시작/끝 이벤트 등록
        events.emplace_back(+i);
        events.emplace_back(-i);
    }

    // 이벤트들을 "각도" 순서로 정렬
    //  +i 는 angStart[i]를, -i 는 angEnd[i]를 키로 사용
    sort(events.begin(), events.end(), [] (int a, int b) {
        return (a > 0 ? angStart[a] : angEnd[-a]) < (b > 0 ? angStart[b] : angEnd[-b]);
    });

    // 각도 sweep
    for (int eid : events) {
        if (eid > 0) {
            // 폴리곤 eid가 각도 구간에 진입
            iterInActive[eid] = active.emplace(eid).first;
        } else {
            // 폴리곤 (-eid)가 각도 구간에서 이탈
            active.erase(iterInActive[-eid]);
        }
        // 현재 각도에서 "최전면"에 있는(= begin()) 폴리곤을 보이는 것으로 체크
        if (!active.empty()) 
            isVisible.set(active.begin()->id);
    }

    // 보이지 않는(= 한번도 최전면에 있지 않은) 폴리곤 수 = n - 보였던 개수
    cout << (n - (int)isVisible.count());
    return 0;
}
