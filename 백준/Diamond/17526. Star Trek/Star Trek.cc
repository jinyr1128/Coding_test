#include <bits/stdc++.h>
#define optimize_io ios::sync_with_stdio(false); cin.tie(nullptr);
using namespace std;

using ll = long long;

// 직선 정보를 저장하는 구조체
struct Line {
    mutable ll slope, intercept, point;
    bool operator<(const Line& other) const { return slope < other.slope; }
    bool operator<(ll x) const { return point < x; }
};

// Convex Hull Trick을 이용한 최적화
struct ConvexHullTrick : multiset<Line, less<>> {
    static constexpr ll INF = LLONG_MAX;

    // 두 기울기와 절편으로 교점을 구함
    ll divide(ll numerator, ll denominator) {
        return numerator / denominator - ((numerator ^ denominator) < 0 && numerator % denominator);
    }

    // 두 직선의 교점 비교 및 업데이트
    bool isIntersecting(iterator x, iterator y) {
        if (y == end()) return x->point = INF, false;
        if (x->slope == y->slope) x->point = x->intercept > y->intercept ? INF : -INF;
        else x->point = divide(y->intercept - x->intercept, x->slope - y->slope);
        return x->point >= y->point;
    }

    // 새로운 직선을 삽입
    void insertLine(ll slope, ll intercept) {
        auto z = insert({slope, intercept, 0}), y = z++, x = y;
        while (isIntersecting(y, z)) z = erase(z);
        if (x != begin() && isIntersecting(--x, y)) isIntersecting(x, erase(y));
        while ((y = x) != begin() && (--x)->point >= y->point) isIntersecting(x, erase(y));
    }

    // x값에 대한 최소 y값 쿼리
    ll getMinimum(ll x) {
        auto l = *lower_bound(x);
        return l.slope * x + l.intercept;
    }
} CHT;

int planetCount, prepTime[100001], speed[100001], cumulativeDistance[100001];
ll minTime[100001];

int main() {
    optimize_io;
    cin >> planetCount;

    // 인접 행성 간의 누적 거리 계산
    for (int i = 2; i <= planetCount; i++) {
        cin >> cumulativeDistance[i];
        cumulativeDistance[i] += cumulativeDistance[i - 1];
    }

    // 각 행성의 준비 시간과 우주선 속도 입력
    for (int i = 1; i < planetCount; i++) {
        cin >> prepTime[i] >> speed[i];
    }

    // 첫 번째 행성의 우주선을 삽입
    CHT.insertLine(-speed[1], -prepTime[1]);

    // DP를 이용한 최소 시간 계산
    for (int i = 2; i <= planetCount; i++) {
        minTime[i] = -CHT.getMinimum(cumulativeDistance[i]);  // 현재 위치까지의 최소 시간
        CHT.insertLine(-speed[i], (ll)speed[i] * cumulativeDistance[i] - minTime[i] - prepTime[i]);  // 새로운 경로 추가
    }

    // 최종 행성까지의 최소 시간 출력
    cout << minTime[planetCount] << '\n';
}
