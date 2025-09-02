#include <bits/stdc++.h>
using namespace std;

using ll = long long;
static const ll INF64 = (ll)4e18;

// ---------- Fast scanner ----------
struct FastScanner {
    static const int BUFSIZE = 1<<20;
    int idx=0, sz=0; char buf[BUFSIZE];
    inline char read() {
        if (idx >= sz) { sz = (int)fread(buf,1,BUFSIZE,stdin); idx=0; if (!sz) return 0; }
        return buf[idx++];
    }
    template<typename T>
    bool nextInt(T &out) {
        char c = read(); if (!c) return false;
        T sign = 1, x=0;
        while (c!='-' && (c<'0'||c>'9')) { c=read(); if(!c) return false; }
        if (c=='-') { sign=-1; c=read(); }
        for (; c>='0'&&c<='9'; c=read()) x = x*10 + (c-'0');
        out = x*sign; return true;
    }
} In;

// ---------- Original tree (array adjacency) ----------
struct Edge { int to; int w; int nxt; };
int N, Q;
vector<int> head;          // size N, -1 initially
vector<Edge> E;            // size 2*(N-1)
int ePtr = 0;

inline void addEdge(int u, int v, int w){
    E[ePtr] = {v, w, head[u]}; head[u] = ePtr++;
}

const int LOG = 20;        // 2^19 > 5e5
vector<int> tin, tout, depth;
vector<ll> distRoot;
vector<array<int, LOG>> up;

// iterative DFS to fill tin/tout, distRoot, depth, up[0]
void build_lca() {
    tin.resize(N); tout.resize(N);
    depth.assign(N, 0);
    distRoot.assign(N, 0);
    up.assign(N, {});
    int timer = 0;

    vector<int> it(N);
    for (int i=0;i<N;i++) it[i] = head[i];

    vector<int> st; st.reserve(N);
    vector<char> entered(N, 0);
    int root = 0;
    up[root][0] = root; // root's parent is itself
    st.push_back(root);

    while(!st.empty()){
        int u = st.back();
        if (!entered[u]) {
            entered[u] = 1;
            tin[u] = timer++;
        }

        int &eid = it[u];
        // advance to next unprocessed child
        while (eid != -1 && E[eid].to == (up[u][0]==u? -1: up[u][0])) eid = E[eid].nxt; // skip parent edge if it matches
        if (eid == -1) {
            tout[u] = timer++;
            st.pop_back();
            continue;
        }
        int e = eid; eid = E[e].nxt;
        int v = E[e].to;
        if (v == up[u][0]) continue; // parent
        up[v][0] = u;
        depth[v]  = depth[u] + 1;
        distRoot[v] = distRoot[u] + (ll)E[e].w;
        st.push_back(v);
    }

    // binary lifting table
    for (int j=1;j<LOG;j++)
        for (int v=0; v<N; v++)
            up[v][j] = up[ up[v][j-1] ][j-1];
}

inline bool is_anc(int u, int v){ // u is ancestor of v ?
    return tin[u] <= tin[v] && tout[v] <= tout[u];
}

int lca(int a, int b){
    if (is_anc(a,b)) return a;
    if (is_anc(b,a)) return b;
    for (int j=LOG-1;j>=0;j--){
        int x = up[a][j];
        if (!is_anc(x, b)) a = x;
    }
    return up[a][0];
}
inline ll dist_uv(int a, int b){
    int c = lca(a,b);
    return distRoot[a] + distRoot[b] - 2LL*distRoot[c];
}

// ---------- Per query virtual tree (array adjacency, directed parent->child) ----------
struct VEdge { int to; int nxt; ll w; };
inline void vt_add_edge(int p, int c, ll w,
                        vector<int>& vtHead, vector<VEdge>& vte, int &vtPtr,
                        vector<int>& par, vector<ll>& wPar) {
    vte[vtPtr] = {c, vtHead[p], w};
    vtHead[p] = vtPtr++;
    par[c] = p; wPar[c] = w;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    if (!In.nextInt(N)) return 0;
    In.nextInt(Q);

    head.assign(N, -1);
    E.resize(2*(N-1));

    for (int i=0;i<N-1;i++){
        int A,B; int D; In.nextInt(A); In.nextInt(B); In.nextInt(D);
        addEdge(A,B,D);
        addEdge(B,A,D);
    }
    build_lca();

    // id map: original node id -> compressed index (per query); we reuse & reset only used entries
    vector<int> id(N, -1);

    for (int qi=0; qi<Q; ++qi){
        int S, T; In.nextInt(S); In.nextInt(T);
        vector<int> X(S), Y(T);
        for (int i=0;i<S;i++) In.nextInt(X[i]);
        for (int i=0;i<T;i++) In.nextInt(Y[i]);

        // 1) gather points = X ∪ Y
        vector<int> pts; pts.reserve(S+T);
        pts.insert(pts.end(), X.begin(), X.end());
        pts.insert(pts.end(), Y.begin(), Y.end());
        auto byTin = [&](int a, int b){ return tin[a] < tin[b]; };
        sort(pts.begin(), pts.end(), byTin);

        // 2) add LCAs of consecutive points
        int P = (int)pts.size();
        vector<int> all = pts;
        all.reserve(2*P);
        for (int i=1;i<P;i++) {
            int c = lca(pts[i-1], pts[i]);
            all.push_back(c);
        }
        sort(all.begin(), all.end(), byTin);
        all.erase(unique(all.begin(), all.end()), all.end());
        int K = (int)all.size();
        // compressed id
        for (int i=0;i<K;i++) id[all[i]] = i;

        // 3) build virtual tree edges (parent -> child)
        vector<int> vtHead(K, -1);
        vector<VEdge> vte; vte.resize(max(0, K-1));
        int vtPtr = 0;
        vector<int> par(K, -1);
        vector<ll>  wPar(K, 0);

        vector<int> st; st.reserve(K);
        st.push_back(all[0]); // store original node ids
        for (int i=1;i<K;i++){
            int v = all[i];
            while (!is_anc(st.back(), v)) {
                int u = st.back(); st.pop_back();
                int p = st.back();
                vt_add_edge(id[p], id[u], dist_uv(p,u), vtHead, vte, vtPtr, par, wPar);
            }
            st.push_back(v);
        }
        while ((int)st.size() > 1){
            int u = st.back(); st.pop_back();
            int p = st.back();
            vt_add_edge(id[p], id[u], dist_uv(p,u), vtHead, vte, vtPtr, par, wPar);
        }
        int rootIdx = id[all[0]];
        if (par[rootIdx] == -1) { par[rootIdx] = rootIdx; wPar[rootIdx] = 0; }

        // 4) mark X and Y on compressed indices
        vector<ll> distX(K, INF64), distY(K, INF64);
        for (int x : X) distX[id[x]] = 0;
        for (int y : Y) distY[id[y]] = 0;

        // 5) post-order (children -> parent) without recursion
        vector<int> post; post.reserve(K);
        {
            vector<pair<int,char>> stk; stk.reserve(K*2);
            stk.push_back({rootIdx, 0});
            while(!stk.empty()){
                auto [u, state] = stk.back(); stk.pop_back();
                if (!state) {
                    stk.push_back({u, 1});
                    for (int e = vtHead[u]; e != -1; e = vte[e].nxt)
                        stk.push_back({vte[e].to, 0});
                } else {
                    post.push_back(u);
                }
            }
        }
        // down-prop from children to parent
        for (int u : post) {
            for (int e = vtHead[u]; e != -1; e = vte[e].nxt) {
                int v = vte[e].to; ll w = vte[e].w;
                // child v -> parent u
                distX[u] = min(distX[u], distX[v] + w);
                distY[u] = min(distY[u], distY[v] + w);
            }
        }

        // 6) pre-order (parent -> child) without recursion
        {
            vector<int> pre; pre.reserve(K);
            pre.push_back(rootIdx);
            for (size_t p=0; p<pre.size(); ++p) {
                int u = pre[p];
                for (int e = vtHead[u]; e != -1; e = vte[e].nxt) {
                    int v = vte[e].to; ll w = vte[e].w;
                    pre.push_back(v);
                    // parent u -> child v
                    distX[v] = min(distX[v], distX[u] + w);
                    distY[v] = min(distY[v], distY[u] + w);
                }
            }
        }

        // 7) answer
        ll ans = INF64;
        for (int i=0;i<K;i++) {
            ll cand = distX[i] + distY[i];
            if (cand < ans) ans = cand;
        }
        cout << ans << '\n';

        // 8) cleanup id[]
        for (int v : all) id[v] = -1;
    }
    return 0;
}
