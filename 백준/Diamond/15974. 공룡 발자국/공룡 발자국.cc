#include <bits/stdc++.h>
using namespace std;

using ll = long long;

struct Pt {
    ll x, y;
};

inline int ccw(const Pt& a, const Pt& b, const Pt& c) {
    __int128 dx1 = (__int128)b.x - a.x;
    __int128 dy1 = (__int128)b.y - a.y;
    __int128 dx2 = (__int128)c.x - a.x;
    __int128 dy2 = (__int128)c.y - a.y;
    __int128 z = dx1*dy2 - dy1*dx2;
    if (z > 0) return 1;
    if (z < 0) return -1;
    return 0;
}
inline unsigned long long dist2(const Pt& a, const Pt& b) {
    long long dx = b.x - a.x, dy = b.y - a.y;
    return (unsigned long long)(dx*1ll*dx + dy*1ll*dy);
}

static int dp[2][3035][3035];
static int prv[2][3035][3035];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;
    vector<Pt> v(N);
    for (auto &p : v) cin >> p.x >> p.y;

    if (N < 4) { // k >= 2 -> 꼭짓점 2k >= 4가 필요
        cout << 0 << '\n';
        return 0;
    }

    // 1) 발뒤꿈치(최하 y, 유일) 찾기
    int heel = 0;
    for (int i = 1; i < N; ++i) if (v[i].y < v[heel].y) heel = i;
    swap(v[0], v[heel]);

    // 2) 발뒤꿈치 기준 각도 정렬 (동일 각도는 거리 오름차순)
    sort(v.begin()+1, v.end(), [&](const Pt& a, const Pt& b){
        int o = ccw(v[0], a, b);
        if (o != 0) return o > 0;
        return dist2(v[0], a) < dist2(v[0], b);
    });

    // 3) DP: i를 기준으로 좌/우 후보를 각도순 정렬 후 슬라이딩
    // dp[0][i][j]: j가 발가락(좌회전), dp[1][i][j]: i가 발가락(좌회전)
    for (int i = 1; i < N-1; ++i) {
        // 왼쪽 후보: i보다 앞쪽(인덱스 < i) + (v[0], v[j], v[i])가 일직선이 아닌 점
        vector<int> L; L.reserve(i+1);
        L.push_back(0); // 시작은 항상 발뒤꿈치에서 출발 가능
        for (int j = 1; j < i; ++j) if (ccw(v[0], v[j], v[i]) != 0) L.push_back(j);

        // 오른쪽 후보: i보다 뒤쪽(인덱스 > i) + (v[0], v[i], v[j])가 일직선이 아닌 점
        vector<int> R; R.reserve(N-i);
        for (int j = i+1; j < N; ++j) if (ccw(v[0], v[i], v[j]) != 0) R.push_back(j);

        auto cmpAroundI = [&](int a, int b) {
            int o = ccw(v[i], v[a], v[b]);
            if (o != 0) return o > 0;
            return dist2(v[i], v[a]) < dist2(v[i], v[b]);
        };
        sort(L.begin(), L.end(), cmpAroundI);
        sort(R.begin(), R.end(), cmpAroundI);

        // (A) dp[1][i][j] 갱신: i가 발가락(좌회전) → ccw(L, i, j) > 0 인 L들을 추가하며 최댓값 전파
        int pv = 0, best = 0, bestIdx = 0;
        for (int j : R) {
            while (pv < (int)L.size() && ccw(v[L[pv]], v[i], v[j]) > 0) {
                int t = L[pv++];
                if (best < dp[0][t][i] + 1) { best = dp[0][t][i] + 1; bestIdx = t; }
            }
            dp[1][i][j] = best;
            prv[1][i][j] = bestIdx;
        }

        // (B) dp[0][i][j] 갱신: j가 발가락(좌회전) → ccw(L, i, j) < 0 인 L들을 추가하며 전파
        reverse(L.begin(), L.end());
        reverse(R.begin(), R.end());
        pv = 0; best = 0; bestIdx = 0;
        for (int j : R) {
            while (pv < (int)L.size() && ccw(v[L[pv]], v[i], v[j]) < 0) {
                int t = L[pv++];
                if (best < dp[1][t][i] + 1) { best = dp[1][t][i] + 1; bestIdx = t; }
            }
            dp[0][i][j] = best;
            prv[0][i][j] = bestIdx;
        }
    }

    // 4) 닫힘(마지막 꼭짓점 j에서 발뒤꿈치로 돌아갈 때) 조건 확인:
    //    ccw(v[0], v[i], v[j]) == 1 (좌회전)이고 dp[0][i][j] 최대인 것을 선택
    int mx = 0, xi = -1, yj = -1;
    for (int i = 1; i < N; ++i) {
        for (int j = i+1; j < N; ++j) {
            if (ccw(v[0], v[i], v[j]) != 1) continue;
            if (mx < dp[0][i][j]) { mx = dp[0][i][j]; xi = i; yj = j; }
        }
    }

    if (mx == 0) { // 만들 수 없음
        cout << 0 << '\n';
        return 0;
    }

    // 5) 역추적: [ ... , t, i, j ] 순으로 거꾸로 복원
    vector<int> idxs; idxs.reserve(mx + 2);
    idxs.push_back(yj);
    idxs.push_back(xi);
    while (idxs.back() != 0) {
        int t = (int)idxs.size();
        int prevIdx = prv[t & 1][idxs[t-1]][idxs[t-2]];
        idxs.push_back(prevIdx);
    }
    reverse(idxs.begin(), idxs.end());

    cout << (int)idxs.size() << '\n';
    for (int id : idxs) {
        cout << v[id].x << ' ' << v[id].y << '\n';
    }
    return 0;
}
