#include <bits/stdc++.h>
using namespace std;

using ll = long long;

/* -------- fast IO -------- */
struct FastScanner {
    static const int S = 1<<20;
    int idx=0, sz=0; char buf[S];
    inline char read() {
        if (idx>=sz) { sz=(int)fread(buf,1,S,stdin); idx=0; if (!sz) return 0; }
        return buf[idx++];
    }
    template<typename T>
    bool nextInt(T &out) {
        char c=read(); if (!c) return false;
        T sign=1, x=0;
        while (c!='-' && (c<'0'||c>'9')) { c=read(); if (!c) return false; }
        if (c=='-') { sign=-1; c=read(); }
        for (; c>='0'&&c<='9'; c=read()) x = x*10 + (c-'0');
        out = x*sign; return true;
    }
} In;

static const int MAXN = 1000000;

/* -------- persistent segtree (sum, point add) --------
   Arrays for compact memory: 3 * node_count ints
*/
struct PST {
    vector<int> L, R, S;   // child indices, sum
    int n;                 // size of base domain [1..n]
    PST(int n, int reserve_nodes): n(n) {
        L.reserve(reserve_nodes);
        R.reserve(reserve_nodes);
        S.reserve(reserve_nodes);
        // node 0 = null
        L.push_back(0); R.push_back(0); S.push_back(0);
    }
    inline int newnode(int lc, int rc, int sv) {
        L.push_back(lc); R.push_back(rc); S.push_back(sv);
        return (int)S.size()-1;
    }
    // build is implicit (all zeros), root 0 represents empty tree

    // point add: returns new root
    int add(int prev_root, int s, int e, int pos, int delta) {
        int cur = newnode( L[prev_root], R[prev_root], S[prev_root] + delta );
        if (s == e) return cur;
        int mid = (s + e) >> 1;
        if (pos <= mid) {
            int nl = add(L[prev_root], s, mid, pos, delta);
            L[cur] = nl;
        } else {
            int nr = add(R[prev_root], mid+1, e, pos, delta);
            R[cur] = nr;
        }
        return cur;
    }
    // range sum on given root
    int query(int root, int s, int e, int l, int r) const {
        if (!root || r < s || e < l) return 0;
        if (l <= s && e <= r) return S[root];
        int mid = (s + e) >> 1;
        int res = 0;
        if (l <= mid) res += query(L[root], s, mid, l, r);
        if (r >  mid) res += query(R[root], mid+1, e, l, r);
        return res;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!In.nextInt(N)) return 0;

    // read array
    vector<int> A(N+1);
    for (int i=1;i<=N;i++) In.nextInt(A[i]);

    // coordinate compression (faster than unordered_map at this scale)
    {
        vector<int> comp(A.begin()+1, A.end());
        sort(comp.begin(), comp.end());
        comp.erase(unique(comp.begin(), comp.end()), comp.end());
        for (int i=1;i<=N;i++) {
            A[i] = (int)(lower_bound(comp.begin(), comp.end(), A[i]) - comp.begin()) + 1; // 1..K
        }
    }
    int K = 0; // max compressed value
    {
        // after compression, max value equals number of uniques
        vector<int> tmp(A.begin()+1, A.end());
        K = *max_element(tmp.begin(), tmp.end());
    }

    // persistent tree
    // reserve nodes: approx (N + repeats) * logN * 2 (safety).
    // We'll reserve for the common case N*22; vector will grow if needed.
    int approx_nodes = (int)( (long long)N * 22 );
    PST pst(N, approx_nodes);

    vector<int> root(N+1, 0);
    vector<int> last(K+1, 0); // last position for each value

    // build versions
    for (int i=1;i<=N;i++) {
        int r = root[i-1];
        // add +1 at i
        r = pst.add(r, 1, N, i, +1);
        int v = A[i];
        if (last[v] != 0) {
            r = pst.add(r, 1, N, last[v], -1);
        }
        last[v] = i;
        root[i] = r;
    }

    int Q; In.nextInt(Q);
    long long prevAns = 0;
    for (int i=1;i<=Q;i++) {
        long long x, r; In.nextInt(x); In.nextInt(r);
        long long l = x + prevAns;
        if (l < 1) l = 1;
        if (l > r) l = r; // constraints guarantee 1<=l<=r
        int ans = pst.query(root[(int)r], 1, N, (int)l, (int)r);
        prevAns = ans;
        cout << ans << '\n';
    }
    return 0;
}
