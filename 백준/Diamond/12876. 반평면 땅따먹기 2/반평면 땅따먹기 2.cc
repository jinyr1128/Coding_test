#include <bits/stdc++.h>
using namespace std;

using ll = long long;
using i128 = __int128_t;

struct FastScanner {
    static const int S = 1<<20;
    int idx=0, sz=0; char buf[S];
    inline char gc() {
        if (idx>=sz) { sz=(int)fread(buf,1,S,stdin); idx=0; if (!sz) return 0; }
        return buf[idx++];
    }
    template<typename T>
    bool nextInt(T &out){
        char c = gc(); if(!c) return false;
        T sign=1, x=0;
        while (c!='-' && (c<'0'||c>'9')) { c=gc(); if(!c) return false; }
        if (c=='-') { sign=-1; c=gc(); }
        for (; c>='0' && c<='9'; c=gc()) x = x*10 + (c-'0');
        out = x*sign; return true;
    }
} In;

struct Line {
    ll a, b;
    inline ll eval(ll x) const { return (ll)((i128)a * (i128)x + (i128)b); }
};

struct Change {
    int idx;        // node index
    Line oldLine;   // previous line
    bool oldHas;    // previous has flag
};

struct LiChaoDiscrete {
    int M; // number of x points
    vector<ll> X; // compressed x's (sorted unique)
    vector<Line> seg;      // current best line at node
    vector<char> has;      // whether node has a line
    vector<Change> stk;    // rollback stack

    LiChaoDiscrete() {}
    LiChaoDiscrete(const vector<ll>& xs) { init(xs); }

    void init(const vector<ll>& xs) {
        X = xs; M = (int)X.size();
        seg.assign(M*4, Line{0, 0});
        has.assign(M*4, 0);
        stk.clear();
    }

    inline void assignNode(int p, const Line& nl) {
        // push old state
        stk.push_back({p, seg[p], (bool)has[p]});
        seg[p] = nl;
        has[p] = 1;
    }

    void addLine(Line nl) { if(M) addLine(1, 0, M, nl); }

    void addLine(int p, int l, int r, Line nl) {
        if (!has[p]) { assignNode(p, nl); return; }
        int m = (l + r) >> 1;
        ll xl = X[l], xm = X[m], xr = X[r-1];
        Line cur = seg[p];
        // ensure seg[p] is the better line at xm
        if (cur.eval(xm) < nl.eval(xm)) {
            assignNode(p, nl); // seg[p] <- nl
            nl = cur;          // the worse line remains in nl
            cur = seg[p];
        }
        if (r - l == 1) return;
        if (nl.eval(xl) > cur.eval(xl)) addLine(p<<1, l, m, nl);
        else if (nl.eval(xr) > cur.eval(xr)) addLine(p<<1|1, m, r, nl);
        // else: nl is worse over whole interval => discard
    }

    ll queryIdx(int pos) const { // pos in [0..M)
        if (M == 0) return LLONG_MIN;
        return query(1, 0, M, pos);
    }

    ll query(int p, int l, int r, int pos) const {
        ll res = has[p] ? seg[p].eval(X[pos]) : LLONG_MIN;
        if (r - l == 1) return res;
        int m = (l + r) >> 1;
        if (pos < m) return max(res, query(p<<1, l, m, pos));
        else          return max(res, query(p<<1|1, m, r, pos));
    }

    int snapshot() const { return (int)stk.size(); }

    void rollback(int snap) {
        while ((int)stk.size() > snap) {
            auto ch = stk.back(); stk.pop_back();
            seg[ch.idx] = ch.oldLine;
            has[ch.idx] = ch.oldHas;
        }
    }
};

// ----- Time segment tree (range add of line indices) -----
struct TimeSeg {
    int N; // range [1..N) in half-open, leaves at times 1..N-1 (we'll use N = n+1)
    vector<int> head; // adjacency head per node
    vector<int> to, nxt;
    int ec = 0;

    TimeSeg() {}
    TimeSeg(int N): N(N) {
        head.assign(4*N, -1);
    }

    void reserveEdges(int E) {
        to.resize(E); nxt.resize(E);
    }

    inline void addEdge(int node, int id) {
        to[ec] = id; nxt[ec] = head[node]; head[node] = ec++;
    }

    void addRange(int node, int nl, int nr, int l, int r, int id) {
        if (r <= nl || nr <= l) return;
        if (l <= nl && nr <= r) { addEdge(node, id); return; }
        int mid = (nl + nr) >> 1;
        addRange(node<<1, nl, mid, l, r, id);
        addRange(node<<1|1, mid, nr, l, r, id);
    }
};

// ----- Main -----
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    if (!In.nextInt(n)) return 0;

    struct Op { int type; ll a,b; int ref; ll x; };
    vector<Op> ops(n+1); // 1-based time
    vector<int> typ(n+1, 0);

    vector<ll> xs; xs.reserve(n);
    vector<int> addStart(n+1, 0), addEnd(n+1, 0); // for type-1 operations
    vector<ll> A(n+1,0), B(n+1,0);  // store a,b for type-1 at its time
    vector<int> qPos(n+1, -1); // pos in compressed X for queries (else -1)

    int addCount = 0, queryCount = 0;

    for (int t = 1; t <= n; ++t) {
        int tp; In.nextInt(tp);
        typ[t] = tp;
        if (tp == 1) {
            ll a, b; In.nextInt(a); In.nextInt(b);
            ops[t] = {tp, a, b, 0, 0};
            addStart[t] = t;
            addEnd[t] = n+1; // default until removed
            A[t] = a; B[t] = b;
            addCount++;
        } else if (tp == 2) {
            int i; In.nextInt(i);
            ops[t] = {tp, 0, 0, i, 0};
            // i is a previous type-1 time, not yet removed
            addEnd[i] = t; // active on [i, t)
        } else { // tp == 3
            ll x; In.nextInt(x);
            ops[t] = {tp, 0, 0, 0, x};
            xs.push_back(x);
            queryCount++;
        }
    }

    // compress X for queries
    sort(xs.begin(), xs.end());
    xs.erase(unique(xs.begin(), xs.end()), xs.end());
    int M = (int)xs.size();

    for (int t = 1; t <= n; ++t) {
        if (typ[t] == 3) {
            int pos = (int)(lower_bound(xs.begin(), xs.end(), ops[t].x) - xs.begin());
            qPos[t] = pos;
        }
    }

    // Build time segment tree and distribute line ids (using adjacency list)
    TimeSeg tseg(n+1);
    // Estimate maximum edges: each add goes to at most ~4*log2(N) nodes
    // Safe upper bound ~ 40 * addCount
    int Emax = max(1, addCount * 40);
    tseg.reserveEdges(Emax);

    for (int t = 1; t <= n; ++t) {
        if (typ[t] == 1) {
            int l = addStart[t];
            int r = addEnd[t];
            if (l < r) tseg.addRange(1, 1, n+1, l, r, t); // store op-index t as line id
        }
    }

    LiChaoDiscrete lichao(xs);

    // DFS over time segtree with rollback Li Chao
    vector<string> outputs; outputs.reserve(queryCount);

    function<void(int,int,int)> dfs = [&](int node, int nl, int nr) {
        int snap = lichao.snapshot();

        // add all lines for this node
        for (int e = tseg.head[node]; e != -1; e = tseg.nxt[e]) {
            int id = tseg.to[e]; // op index of type-1
            lichao.addLine(Line{A[id], B[id]});
        }

        if (nr - nl == 1) {
            int t = nl; // time index
            if (t <= n && typ[t] == 3) {
                if (M == 0) {
                    outputs.emplace_back("EMPTY");
                } else {
                    ll ans = lichao.queryIdx(qPos[t]);
                    if (ans == LLONG_MIN) outputs.emplace_back("EMPTY");
                    else                   outputs.emplace_back(to_string(ans));
                }
            }
        } else {
            int mid = (nl + nr) >> 1;
            dfs(node<<1, nl, mid);
            dfs(node<<1|1, mid, nr);
        }

        lichao.rollback(snap);
    };

    dfs(1, 1, n+1);

    // Print
    for (auto &s : outputs) cout << s << '\n';
    return 0;
}
