#include <bits/stdc++.h>
using namespace std;

using ll = long long;

/* ---------- Fast Scanner ---------- */
struct FastScanner {
    static const int S = 1 << 20;
    int idx = 0, sz = 0; char buf[S];
    inline char gc() {
        if (idx >= sz) { sz = (int)fread(buf,1,S,stdin); idx = 0; if (!sz) return 0; }
        return buf[idx++];
    }
    template<typename T>
    bool next(T &out) {
        char c = gc(); if (!c) return false;
        T sign = 1, x = 0;
        while (c!='-' && (c<'0'||c>'9')) { c = gc(); if (!c) return false; }
        if (c=='-') { sign = -1; c = gc(); }
        for (; c>='0' && c<='9'; c = gc()) x = x*10 + (c - '0');
        out = x*sign; return true;
    }
} In;

/* ---------- LIS (strict) for integers ---------- */
int LIS_strict(const vector<int> &a) {
    vector<int> tail; tail.reserve(a.size());
    for (int x : a) {
        auto it = lower_bound(tail.begin(), tail.end(), x); // distinct -> strict
        if (it == tail.end()) tail.push_back(x);
        else *it = x;
    }
    return (int)tail.size();
}

/* ---------- 3D LIS via CDQ + Fenwick(Max) ---------- */
struct Node {
    int y, z, id; // x는 배열 인덱스로 이미 오름차순
};
struct FenwickMax {
    int n, ver = 1;
    vector<int> bit, vis;
    FenwickMax(int n=0){ init(n); }
    void init(int _n){ n=_n; bit.assign(n+2,0); vis.assign(n+2,0); ver=1; }
    inline void reset_step(){ ++ver; if (ver==INT_MAX){ // 안전
        fill(bit.begin(), bit.end(), 0);
        fill(vis.begin(), vis.end(), 0);
        ver = 1;
    }}
    inline void add(int i, int v){
        for (; i<=n; i+=i&-i){
            if (vis[i]!=ver){ vis[i]=ver; bit[i]=0; }
            if (bit[i] < v) bit[i] = v;
        }
    }
    inline int query(int i){
        int res = 0;
        for (; i>0; i-=i&-i)
            if (vis[i]==ver && bit[i]>res) res = bit[i];
        return res;
    }
};

void cdq(int l, int r, vector<Node> &a, vector<Node> &tmp, FenwickMax &fw, vector<int> &dp){
    if (l == r) { dp[a[l].id] = max(dp[a[l].id], 1); return; }
    int mid = (l + r) >> 1;
    cdq(l, mid, a, tmp, fw, dp);
    cdq(mid+1, r, a, tmp, fw, dp);

    fw.reset_step();
    int i = l, j = mid+1, k = l;

    // 두 구간이 y 오름차순으로 병합되도록 유지
    while (i <= mid && j <= r) {
        if (a[i].y < a[j].y) {
            fw.add(a[i].z, dp[a[i].id]);     // 왼쪽 점들만 누적
            tmp[k++] = a[i++];
        } else {
            int best = fw.query(a[j].z - 1); // z도 엄밀히 작아야 함
            if (best + 1 > dp[a[j].id]) dp[a[j].id] = best + 1;
            tmp[k++] = a[j++];
        }
    }
    while (j <= r) {
        int best = fw.query(a[j].z - 1);
        if (best + 1 > dp[a[j].id]) dp[a[j].id] = best + 1;
        tmp[k++] = a[j++];
    }
    while (i <= mid) {
        fw.add(a[i].z, dp[a[i].id]);
        tmp[k++] = a[i++];
    }
    for (int t=l; t<=r; ++t) a[t] = tmp[t]; // y 오름차순 유지
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int M, N;
    if (!In.next(M)) return 0;
    In.next(N);

    // 원본 값 읽기
    vector<vector<ll>> Q(M, vector<ll>(N));
    for (int i=0;i<M;i++)
        for (int j=0;j<N;j++)
            In.next(Q[i][j]);

    // 각 행의 전역 랭크 r[i][col] (1=가장 큼)
    vector<vector<int>> r(M, vector<int>(N));
    for (int i=0;i<M;i++){
        vector<int> idx(N); iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int a, int b){
            if (Q[i][a] != Q[i][b]) return Q[i][a] > Q[i][b]; // 값 내림차순
            return a < b; // 안정성(실제론 필요없음)
        });
        for (int pos=0; pos<N; ++pos) r[i][ idx[pos] ] = pos + 1;
    }

    if (M == 2) {
        // r1 오름차순 순서에서의 r2 수열의 LIS
        vector<int> seq(N);
        for (int col=0; col<N; ++col) seq[ r[0][col] - 1 ] = r[1][col];
        cout << LIS_strict(seq) << '\n';
        return 0;
    }

    // M == 3
    // x=r1 오름차순(=인덱스), 각 위치의 (y=r2, z=r3)
    vector<Node> a(N), tmp(N);
    for (int col=0; col<N; ++col) {
        int x = r[0][col] - 1;
        a[x] = Node{ r[1][col], r[2][col], x };
    }
    vector<int> dp(N, 1);
    FenwickMax fw(N);
    cdq(0, N-1, a, tmp, fw, dp);

    int ans = 0;
    for (int v : dp) if (v > ans) ans = v;
    cout << ans << '\n';
    return 0;
}
