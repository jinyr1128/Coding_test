#include <bits/stdc++.h>
using namespace std;

using ll = long long;

struct Point {
    ll x, y, w;
};

struct Node {
    long long sum, pref, suf, best;
};

struct SegTree {
    int n;
    vector<Node> st;

    SegTree() {}
    SegTree(const vector<long long>& a) { build(a); }

    static Node merge(const Node& L, const Node& R) {
        Node res;
        res.sum  = L.sum + R.sum;
        res.pref = max(L.pref, L.sum + R.pref);
        res.suf  = max(R.suf,  R.sum + L.suf);
        res.best = max({L.best, R.best, L.suf + R.pref});
        return res;
    }
    static Node make(ll v) {
        return Node{v, max(0LL, v), max(0LL, v), max(0LL, v)};
    }

    void build(const vector<long long>& a) {
        n = (int)a.size();
        st.assign(4*n, Node{0,0,0,0});
        function<void(int,int,int)> go = [&](int p, int l, int r) {
            if (l == r) { st[p] = make(a[l]); return; }
            int m = (l + r) >> 1;
            go(p<<1, l, m);
            go(p<<1|1, m+1, r);
            st[p] = merge(st[p<<1], st[p<<1|1]);
        };
        go(1, 0, n-1);
    }

    void update(int idx, ll val) {
        function<void(int,int,int)> go = [&](int p, int l, int r) {
            if (l == r) { st[p] = make(val); return; }
            int m = (l + r) >> 1;
            if (idx <= m) go(p<<1, l, m);
            else go(p<<1|1, m+1, r);
            st[p] = merge(st[p<<1], st[p<<1|1]);
        };
        go(1, 0, n-1);
    }
    long long bestAll() const { return st[1].best; }
};

static inline ll mygcd(ll a, ll b) {
    if (a < 0) a = -a;
    if (b < 0) b = -b;
    while (b) { ll t = a % b; a = b; b = t; }
    return a;
}

struct Event {
    int A, B;     // normalized key of normal vector (-dy, dx) reduced & oriented to upper half-plane
    int i, j;     // endpoints (indices of points)
};

// angle comparator on [0, π), since we normalized to upper half-plane (B>0 or B==0 && A>0)
static inline bool angleLess(const Event& e1, const Event& e2) {
    long long cp = 1LL*e1.A*e2.B - 1LL*e1.B*e2.A; // cross((A1,B1),(A2,B2)) = sin(β-α)*|v1||v2|
    if (cp != 0) return cp > 0;                   // cp>0  <=> angle(e1) < angle(e2)
    if (e1.A != e2.A) return e1.A < e2.A;        // tie-break to get strict weak ordering
    if (e1.B != e2.B) return e1.B < e2.B;
    if (e1.i != e2.i) return e1.i < e2.i;
    return e1.j < e2.j;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;
    vector<Point> P(N);
    for (int i = 0; i < N; ++i) {
        ll X, Y, W; cin >> X >> Y >> W;
        P[i] = {X, Y, W};
    }

    // 1) 초기 방향 θ=0⁺ : x 오름차순, x가 같으면 y 오름차순
    vector<int> order(N);
    iota(order.begin(), order.end(), 0);
    sort(order.begin(), order.end(), [&](int a, int b){
        if (P[a].x != P[b].x) return P[a].x < P[b].x;
        return P[a].y < P[b].y;
    });

    vector<int> pos(N);
    for (int k = 0; k < N; ++k) pos[order[k]] = k;

    vector<long long> arr(N);
    for (int k = 0; k < N; ++k) arr[k] = P[order[k]].w;

    SegTree seg(arr);
    long long ans = max(0LL, seg.bestAll());

    // 2) 모든 쌍에 대한 이벤트 만들기: key = normalize( -dy, dx )
    const long long M_est = 1LL * N * (N - 1) / 2;
    vector<Event> evs;
    evs.reserve((size_t)M_est);

    for (int i = 0; i < N; ++i) {
        for (int j = i+1; j < N; ++j) {
            ll dx = P[j].x - P[i].x;
            ll dy = P[j].y - P[i].y;
            ll A = -dy, B = dx;           // normal = rotate90+(dx,dy)
            ll g = mygcd(A, B);
            A /= g; B /= g;
            // normalize to upper half-plane [0, π): B>0 or (B==0 && A>0)
            if (B < 0 || (B == 0 && A < 0)) { A = -A; B = -B; }
            // (A,B) == (1,0) means θ=0. 우리는 θ=0⁺에서 시작 -> 이 묶음은 건너뛸 것이라도 정렬 위치 파악 위해 남겨둔다.
            evs.push_back(Event{(int)A, (int)B, i, j});
        }
    }

    if (!evs.empty()) sort(evs.begin(), evs.end(), angleLess);

    // 3) θ=0 묶음의 끝 위치 찾기 (key = (1,0))
    size_t start = 0, m = evs.size();
    while (start < m && !(evs[start].A == 1 && evs[start].B == 0)) ++start;
    while (start < m && evs[start].A == 1 && evs[start].B == 0) ++start;
    // 이제 start는 θ=0 묶음 바로 뒤. 없으면 0.

    // 그룹 유니크 포인트 수집을 위한 마킹(초기화 비용 없이 회전 카운터 사용)
    vector<int> seen(N, -1);
    int gid = 0;

    auto process_group = [&](size_t L, size_t R) {
        if (L >= R) return;
        ++gid;
        vector<int> pts;
        pts.reserve((R - L) * 2);

        for (size_t t = L; t < R; ++t) {
            int a = evs[t].i, b = evs[t].j;
            if (seen[a] != gid) { seen[a] = gid; pts.push_back(a); }
            if (seen[b] != gid) { seen[b] = gid; pts.push_back(b); }
        }

        if (pts.empty()) return;

        // 현재 순서에서의 위치로 바꿔 정렬 → 연속 구간(블록)들을 역전
        vector<int> poslist; poslist.reserve(pts.size());
        for (int id : pts) poslist.push_back(pos[id]);
        sort(poslist.begin(), poslist.end());

        int s = 0, S = (int)poslist.size();
        while (s < S) {
            int e = s;
            while (e + 1 < S && poslist[e+1] == poslist[e] + 1) ++e;
            int Lpos = poslist[s], Rpos = poslist[e];
            if (Lpos < Rpos) {
                // 블록 [Lpos..Rpos] 뒤집기
                vector<int> tmp; tmp.reserve(Rpos - Lpos + 1);
                for (int idx = Lpos; idx <= Rpos; ++idx) tmp.push_back(order[idx]);
                reverse(tmp.begin(), tmp.end());
                for (int t2 = 0; t2 < (int)tmp.size(); ++t2) {
                    int id = tmp[t2];
                    int at = Lpos + t2;
                    order[at] = id;
                    pos[id] = at;
                    seg.update(at, P[id].w);
                }
            }
            s = e + 1;
        }
        ans = max(ans, seg.bestAll()); // 빈 띠(0)는 마지막에 한 번 더 처리
    };

    // 4) θ를 0⁺→π 로 증가시키며 스윕
    // 첫 번째 구간: start..m-1
    size_t i = start;
    while (i < m) {
        size_t j = i + 1;
        while (j < m && evs[j].A == evs[i].A && evs[j].B == evs[i].B) ++j;
        // (1,0) 묶음(θ=0)은 이미 지나온 상태여야 함. 여기선 나올 일이 없음.
        process_group(i, j);
        i = j;
    }
    // 두 번째 구간: 0..(start-1) (여기엔 θ=0 묶음이 선행될 수 있음 → 건너뛴다)
    i = 0;
    while (i < start) {
        // θ=0 묶음은 스킵
        if (evs[i].A == 1 && evs[i].B == 0) {
            size_t j = i + 1;
            while (j < start && evs[j].A == 1 && evs[j].B == 0) ++j;
            i = j;
            continue;
        }
        size_t j = i + 1;
        while (j < start && evs[j].A == evs[i].A && evs[j].B == evs[i].B) ++j;
        process_group(i, j);
        i = j;
    }

    // 빈 띠 선택 가능
    ans = max(ans, 0LL);
    cout << ans << '\n';
    return 0;
}
