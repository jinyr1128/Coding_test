#include <bits/stdc++.h>
using namespace std;

using ll = long long;

struct RawItem { int c; ll x, y; };
struct PackedItem { int c, xidx; ll y; };
struct Query { int idx, xidx, c; ll thresh; };

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    if (!(cin >> n)) return 0;

    vector<RawItem> raw(n);
    vector<ll> xs; xs.reserve(n);
    for (int i = 0; i < n; ++i) {
        cin >> raw[i].c >> raw[i].x >> raw[i].y;
        xs.push_back(raw[i].x);
    }

    // compress x of items
    sort(xs.begin(), xs.end());
    xs.erase(unique(xs.begin(), xs.end()), xs.end());

    auto compIdx = [&](ll x)->int {
        // 1-based index
        return int(lower_bound(xs.begin(), xs.end(), x) - xs.begin()) + 1;
    };

    vector<PackedItem> items(n);
    for (int i = 0; i < n; ++i) {
        items[i].c = raw[i].c;
        items[i].y = raw[i].y;
        items[i].xidx = compIdx(raw[i].x);
    }

    // sort items by xidx ascending
    sort(items.begin(), items.end(), [&](const PackedItem& a, const PackedItem& b){
        return a.xidx < b.xidx;
    });

    int m;
    cin >> m;
    vector<Query> qs(m);
    // 각 x_q에 대해 "아이템 x <= x_q"인 개수 = lower_bound(items.x > x_q) - begin
    // -> items의 원시 xs가 아니라, 좌표압축된 xs를 사용:
    //    x_q 기준, items.x <= x_q 이려면, 압축 xs에서 (<= x_q) 개수 = lower_bound(xs, x_q+1) - xs.begin()
    vector<vector<int>> bucket(xs.size() + 1); // bucket[k] : k개의 아이템 x<=... 처리 후 답하는 질의들

    for (int i = 0; i < m; ++i) {
        ll xq; int cq; ll yq;
        cin >> xq >> cq >> yq;
        ll T = xq + yq; // threshold
        // 몇 개의 item을 반영한 뒤에 이 질의를 처리할지 (x <= xq)
        int k = int(lower_bound(xs.begin(), xs.end(), xq + 1) - xs.begin());
        // k=0이면 아무 아이템도 반영하지 않은 상태에서 판단
        qs[i] = { i, k, cq, T };
        bucket[k].push_back(i);
    }

    const int CMAX = 100000; // 문제 원 소스가 이 상한으로 DP를 구성
    const ll INF = (ll)9e18;

    // DP: bestMinY[w] = 지금까지 반영된 아이템으로 정확히 w를 만들 수 있을 때,
    //                     선택집합의 최소 y를 최대화한 값 (없으면 -1)
    static ll bestMinY[CMAX+1];
    bestMinY[0] = INF; // 공집합의 "min y" 는 무한대로 두면 min 연산에서 새 아이템 y를 그대로 반영
    for (int w = 1; w <= CMAX; ++w) bestMinY[w] = -1;

    vector<char> answer(m, 0);

    // 아이템을 x 증가 순서로 하나씩 반영하면서,
    // 해당 시점(k) 에 묶여 있는 질의를 처리
    int ptr = 0; // items processed count equals distinct x count? 주의: xs는 서로 다른 x만; items는 여러 개가 같은 xidx일 수 있음.
                 // 원 코드의 로직은 "arr[i].x(압축된 시작값)을 i번째 아이템의 위치로 사용"했지만,
                 // x값이 같은 아이템 여러 개가 존재하면 조금 다르게 처리해야 한다.
                 // 원 코드의 정밀 동작과 완전히 동일하게 맞추려면, "같은 x"를 갖는 모든 아이템을 처리한 후 k를 하나 증가시키며 질의를 처리해야 한다.

    // 따라서 xs의 인덱스를 따라가며, 그 x값을 가진 모든 item을 반영한 뒤 bucket[idx]를 처리하는 방식으로 구현

    int nItems = (int)items.size();
    int nX = (int)xs.size();

    // xidx는 1..nX. 각 xidx마다 아이템 몇 개 있는지 구간으로 묶어 처리
    vector<vector<PackedItem>> byX(nX + 1);
    for (auto &it : items) byX[it.xidx].push_back(it);

    // k = 0..nX 순서로 질의 처리
    // k==0: 아무 아이템도 반영 전
    for (int qi : bucket[0]) {
        int c = qs[qi].c;
        ll T = qs[qi].thresh;
        ll miny = (c >= 0 && c <= CMAX) ? bestMinY[c] : -1;
        answer[qi] = (miny > T);
    }

    for (int xk = 1; xk <= nX; ++xk) {
        // 이 xk에 해당하는 모든 아이템 반영
        for (const auto& it : byX[xk]) {
            int c = it.c;
            ll y = it.y;
            if (c < 0 || c > CMAX) continue;
            for (int w = CMAX; w >= c; --w) {
                ll from = bestMinY[w - c];
                if (from < 0) continue;
                ll cand = min(from, y);
                if (cand > bestMinY[w]) bestMinY[w] = cand;
            }
        }
        // 이 시점(x <= xs[xk-1])에서의 질의 처리
        for (int qi : bucket[xk]) {
            int c = qs[qi].c;
            ll T = qs[qi].thresh;
            ll miny = (c >= 0 && c <= CMAX) ? bestMinY[c] : -1;
            answer[qi] = (miny > T);
        }
    }

    for (int i = 0; i < m; ++i) {
        cout << (answer[i] ? "TAK" : "NIE") << '\n';
    }
    return 0;
}
