#include <bits/stdc++.h>
using namespace std;

static const int MOD = 998244353;

inline int addmod(int a, int b){ a += b; if(a >= MOD) a -= MOD; return a; }
inline int submod(int a, int b){ a -= b; if(a < 0) a += MOD; return a; }
inline int mulmod(long long a, long long b){ return int((a * b) % MOD); }

struct FastScanner {
    static const int S = 1<<20;
    int idx=0, sz=0; char buf[S];
    inline char read() {
        if (idx >= sz) { sz = (int)fread(buf,1,S,stdin); idx=0; if (!sz) return 0; }
        return buf[idx++];
    }
    bool nextInt(int &out){
        char c = read(); if(!c) return false;
        int sign=1, x=0;
        while(c!='-' && (c<'0'||c>'9')){ c=read(); if(!c) return false; }
        if(c=='-'){ sign=-1; c=read(); }
        for(; c>='0'&&c<='9'; c=read()) x = x*10 + (c-'0');
        out = x*sign; return true;
    }
    bool nextToken(string &s){
        char c = read(); if(!c) return false;
        s.clear();
        while(c==' '||c=='\n'||c=='\r'||c=='\t'){ c=read(); if(!c) return false; }
        for(; c && !(c==' '||c=='\n'||c=='\r'||c=='\t'); c=read()) s.push_back(c);
        return true;
    }
} In;

int N;
string S;
vector<int> pw10; // <-- 이름 변경

/* Segment Tree storage (index 1-based node):
   val[node] : value of segment
   w[node][d] : sum of 10^pos for positions in this node holding digit d (mod MOD)
   tag[node][d] : lazy mapping of digit d -> tag[node][d] (uchar 0..9)
   has[node] : whether tag is non-identity
*/
struct SegTree {
    int n;
    vector<int> val;
    vector<int> w;                 // flattened 10 per node: w[node*10 + d]
    vector<unsigned char> tag;     // flattened 10 per node
    vector<unsigned char> has;     // has lazy?

    SegTree(int n=0): n(n) {
        val.assign(4*n+5, 0);
        w.assign((4*n+5)*10, 0);
        tag.assign((4*n+5)*10, 0);
        has.assign(4*n+5, 0);
    }

    inline int& W(int node, int d){ return w[node*10 + d]; }
    inline unsigned char& TAG(int node, int d){ return tag[node*10 + d]; }

    inline void set_identity_tag(int node){
        for(int d=0; d<10; ++d) TAG(node,d) = (unsigned char)d;
        has[node] = 0;
    }

    void build(int node, int l, int r, const string &S){
        set_identity_tag(node);
        if(l == r){
            int d = S[l-1] - '0';
            val[node] = d % MOD;
            for(int t=0; t<10; ++t) W(node,t) = 0;
            W(node, d) = 1; // 10^0
            return;
        }
        int mid = (l+r)>>1;
        build(node<<1, l, mid, S);
        build(node<<1|1, mid+1, r, S);
        int lenR = r - mid;
        // combine
        val[node] = addmod( mulmod(val[node<<1], pw10[lenR]), val[node<<1|1] );
        for(int d=0; d<10; ++d){
            int leftW  = W(node<<1, d);
            int rightW = W(node<<1|1, d);
            W(node,d) = addmod( mulmod(leftW, pw10[lenR]), rightW );
        }
    }

    // apply single mapping (from->to) to this node (fully covered case): O(1)
    inline void apply_single(int node, int from, int to){
        if(from == to) return;
        int wf = W(node, from);
        if(wf){
            // val += (to - from) * wf
            int delta = ( (to - from) % MOD + MOD ) % MOD;
            val[node] = addmod( val[node], mulmod(delta, wf) );
            W(node, to) = addmod( W(node, to), wf );
            W(node, from) = 0;
        }
        // compose lazy tag: tag = (from->to) ∘ tag
        for(int d=0; d<10; ++d){
            unsigned char &x = TAG(node,d);
            if(x == (unsigned char)from) x = (unsigned char)to;
        }
        has[node] = 1;
    }

    // apply general mapping 'g' (size 10) to node (used in push): O(10)
    inline void apply_map_general(int node, const unsigned char g[10]){
        int tmp[10];
        for(int d=0; d<10; ++d) tmp[d] = W(node,d);
        for(int d=0; d<10; ++d) W(node,d) = 0;
        for(int d=0; d<10; ++d){
            int t = (int)g[d];
            if(tmp[d]) W(node, t) = addmod( W(node, t), tmp[d] );
        }
        int v = 0;
        for(int t=0; t<10; ++t){
            if(W(node,t)) v = addmod(v, mulmod(t, W(node,t)));
        }
        val[node] = v;
        // compose lazy tag: tag = g ∘ tag
        for(int d=0; d<10; ++d){
            TAG(node,d) = g[ TAG(node,d) ];
        }
        has[node] = 1;
    }

    inline void push(int node){
        if(!has[node]) return;
        unsigned char g[10];
        for(int d=0; d<10; ++d) g[d] = TAG(node,d);

        int lc = node<<1, rc = lc|1;
        // left child
        apply_map_general(lc, g);
        // right child
        apply_map_general(rc, g);

        set_identity_tag(node);
    }

    void update(int node, int l, int r, int ql, int qr, int from, int to){
        if(from == to) return;
        if(ql <= l && r <= qr){
            apply_single(node, from, to);
            return;
        }
        push(node);
        int mid = (l+r)>>1;
        if(ql <= mid) update(node<<1, l, mid, ql, qr, from, to);
        if(qr >  mid) update(node<<1|1, mid+1, r, ql, qr, from, to);
        int lenR = r - mid;
        val[node] = addmod( mulmod(val[node<<1], pw10[lenR]), val[node<<1|1] );
        for(int d=0; d<10; ++d){
            int leftW  = W(node<<1, d);
            int rightW = W(node<<1|1, d);
            W(node,d) = addmod( mulmod(leftW, pw10[lenR]), rightW );
        }
    }

    // returns (value, length) for [ql,qr]
    pair<int,int> query(int node, int l, int r, int ql, int qr){
        if(qr < l || r < ql) return {0, 0};
        if(ql <= l && r <= qr) return { val[node], r-l+1 };
        push(node);
        int mid = (l+r)>>1;
        auto L = query(node<<1, l, mid, ql, qr);
        auto R = query(node<<1|1, mid+1, r, ql, qr);
        if(L.second == 0) return R;
        if(R.second == 0) return L;
        int combined = addmod( mulmod(L.first, pw10[R.second]), R.first );
        return { combined, L.second + R.second };
    }
};

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    if(!In.nextToken(S)) return 0;
    N = (int)S.size();

    int Q; In.nextInt(Q);

    pw10.assign(N+1, 0); // <-- 이름 변경
    pw10[0] = 1;
    for(int i=1;i<=N;i++) pw10[i] = mulmod(pw10[i-1], 10);

    SegTree st(N);
    st.build(1, 1, N, S);

    for(int qi=0; qi<Q; ++qi){
        int type; In.nextInt(type);
        if(type == 1){
            int i,j, from, to;
            In.nextInt(i); In.nextInt(j); In.nextInt(from); In.nextInt(to);
            st.update(1, 1, N, i, j, from, to);
        }else{
            int i,j; In.nextInt(i); In.nextInt(j);
            auto res = st.query(1, 1, N, i, j);
            cout << res.first << '\n';
        }
    }
    return 0;
}
