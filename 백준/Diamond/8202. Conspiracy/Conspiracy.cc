#include <bits/stdc++.h>
using namespace std;

static const int MAXN = 5005;

bitset<MAXN> adjb[MAXN]; // 1..n 사용
int degv[MAXN];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    if (!(cin >> n)) return 0;

    // 입력: 각 행 i: k_i, 이어서 k_i개의 이웃
    for (int i = 1; i <= n; ++i) {
        int k; cin >> k;
        degv[i] = k;
        for (int j = 0; j < k; ++j) {
            int a; cin >> a;
            adjb[i].set(a);
        }
    }
    // (입력은 대칭임이 보장되지만, 혹시 모를 일관성 위해 자기루프는 제거)
    for (int i = 1; i <= n; ++i) adjb[i].reset(i);

    // 차수 내림차순 정렬 (index)
    vector<int> ord(n);
    iota(ord.begin(), ord.end(), 1);
    sort(ord.begin(), ord.end(), [&](int u, int v){
        if (degv[u] != degv[v]) return degv[u] > degv[v];
        return u < v;
    });

    // 정렬된 차수열
    vector<int> d(n+1);
    for (int i = 1; i <= n; ++i) d[i] = degv[ord[i-1]];

    // m = max{i : d_i >= i-1}
    int m = 0;
    for (int i = 1; i <= n; ++i) {
        if (d[i] >= i-1) m = i;
        else break; // 이후는 감소하므로 더 볼 필요 없음
    }

    // Hammer–Simeone 등식 검증
    long long lhs = 0, rhs = 1LL * m * (m - 1);
    for (int i = 1; i <= m; ++i) lhs += d[i];
    for (int i = m+1; i <= n; ++i) rhs += min(d[i], m);
    if (lhs != rhs) {
        cout << 0 << '\n';
        return 0;
    }

    // 정준 분해: K0 = ord[1..m], I0 = 나머지
    bitset<MAXN> maskK; maskK.reset();
    vector<int> inK(n+1, 0);
    for (int i = 0; i < m; ++i) {
        int v = ord[i];
        inK[v] = 1;
        maskK.set(v);
    }

    // 각 정점에 대해 deg_K, deg_I 계산
    vector<int> degK(n+1, 0), degI(n+1, 0);
    for (int v = 1; v <= n; ++v) {
        // 이웃 중 K0에 속한 수
        bitset<MAXN> tmp = adjb[v] & maskK;
        degK[v] = (int)tmp.count();
        degI[v] = degv[v] - degK[v];
    }

    // K0이 클릭인지(안전 확인)
    for (int v = 1; v <= n; ++v) {
        if (inK[v]) {
            if (degK[v] != m - 1) {
                cout << 0 << '\n';
                return 0;
            }
        }
    }

    // I0이 독립집합인지(안전 확인)
    for (int v = 1; v <= n; ++v) if (!inK[v]) {
        // I0 내 이웃이 없어야 함
        // (adjb[v] & ~maskK).count() == 0
        bitset<MAXN> tmp = adjb[v] & (~maskK);
        if (tmp.any()) {
            cout << 0 << '\n';
            return 0;
        }
    }

    // 분해 개수 세기
    long long ans = 0;

    // 기본 분해 (K0, I0)
    if (m > 0 && m < n) ans += 1;

    // A) I_fullK: deg_K(u) == m (K0의 모든 정점과 인접) 인 u ∈ I0
    int I0_size = n - m;
    long long cntA = 0;
    if (I0_size >= 2) { // I를 비우면 안 되므로 I0가 1개일 땐 A 불가
        for (int u = 1; u <= n; ++u) if (!inK[u]) {
            if (degK[u] == m) cntA++;
        }
    }
    // I0_size == 1이면 A는 모두 I를 비우므로 허용 안 함

    // B) K_zeroI: deg_I(v) == 0 인 v ∈ K0 (I0에 이웃이 전혀 없음)
    long long cntB = 0;
    vector<int> K_zeroI; K_zeroI.reserve(m);
    if (m >= 2) { // K를 비우면 안 됨 → m==1일 땐 B 불가
        for (int v = 1; v <= n; ++v) if (inK[v]) {
            if (degI[v] == 0) {
                cntB++;
                K_zeroI.push_back(v);
            }
        }
    } else {
        // m==1인 경우에도 K_zeroI는 스왑(C)에서 필요하므로 채운다
        for (int v = 1; v <= n; ++v) if (inK[v]) {
            if (degI[v] == 0) K_zeroI.push_back(v);
        }
    }

    // C) 스왑: v∈K_zeroI 를 I로 빼고, u∈I0 중 deg_K(u)==m-1이며
    //          K0에서 유일하게 비인접한 정점이 v 인 u 를 K로 넣기
    // I_m1[v] = 그런 u의 개수
    vector<long long> Im1_cnt(n+1, 0);
    for (int u = 1; u <= n; ++u) if (!inK[u]) {
        if (degK[u] == m - 1) {
            // 결손된 K 정점이 누구인지 찾기
            // missingMask = K \ N(u)
            // bitset 에서 first set bit를 찾는 표준 함수가 없으므로 선형탐색
            int missing_v = -1;
            int found = 0;
            for (int i = 0; i < m; ++i) {
                int kv = ord[i];
                if (!adjb[u].test(kv)) { missing_v = kv; found++; if (found > 1) break; }
            }
            if (found == 1) {
                Im1_cnt[missing_v]++;
            }
            // found >=2 인 경우는 스왑 불가 → 무시
        }
    }
    long long cntC = 0;
    for (int v : K_zeroI) cntC += Im1_cnt[v];

    ans += cntA + cntB + cntC;

    cout << ans << '\n';
    return 0;
}
