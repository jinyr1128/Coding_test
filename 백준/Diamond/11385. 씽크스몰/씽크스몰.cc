#include <bits/stdc++.h>
using namespace std;

using ll = long long;
using u64 = unsigned long long;
using i128 = __int128_t;

/********** Fast Scanner **********/
struct FastScanner {
    static const int S = 1 << 20;
    int idx = 0, sz = 0; char buf[S];
    inline char read() {
        if (idx >= sz) {
            sz = (int)fread(buf, 1, S, stdin);
            idx = 0;
            if (sz == 0) return 0;
        }
        return buf[idx++];
    }
    template<typename T>
    bool nextInt(T &out) {
        char c = read(); if (!c) return false;
        T sign = 1, x = 0;
        while (c!='-' && (c<'0'||c>'9')) { c = read(); if (!c) return false; }
        if (c=='-') { sign = -1; c = read(); }
        for (; c>='0' && c<='9'; c = read()) x = x*10 + (c - '0');
        out = x*sign; return true;
    }
} In;

/********** mod utils **********/
static inline ll mod_pow(ll a, ll e, ll mod) {
    ll r = 1 % mod; a %= mod;
    while (e) {
        if (e & 1) r = (ll)((i128)r * a % mod);
        a = (ll)((i128)a * a % mod);
        e >>= 1;
    }
    return r;
}
static inline ll mod_inv(ll a, ll mod) {
    return mod_pow((a % mod + mod) % mod, mod - 2, mod);
}
static inline ll norm(ll x, ll mod) { x %= mod; if (x < 0) x += mod; return x; }

/********** Iterative NTT **********/
struct NTT {
    int mod, root; // primitive root for this prime
    NTT(int mod, int root): mod(mod), root(root) {}

    void ntt(vector<int> &a, bool invert) const {
        int n = (int)a.size();
        int lg = __builtin_ctz(n);
        static vector<int> rev; rev.assign(n, 0);
        for (int i = 1; i < n; ++i)
            rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (lg - 1));
        for (int i = 0; i < n; ++i)
            if (i < rev[i]) swap(a[i], a[rev[i]]);

        for (int len = 2; len <= n; len <<= 1) {
            ll wlen = mod_pow(root, (mod - 1) / len, mod);
            if (invert) wlen = mod_inv(wlen, mod);
            for (int i = 0; i < n; i += len) {
                ll w = 1;
                int half = len >> 1;
                for (int j = 0; j < half; ++j) {
                    int u = a[i + j];
                    int v = (int)(w * a[i + j + half] % mod);
                    int x = u + v; if (x >= mod) x -= mod;
                    int y = u - v; if (y < 0) y += mod;
                    a[i + j] = x;
                    a[i + j + half] = y;
                    w = (w * wlen) % mod;
                }
            }
        }
        if (invert) {
            ll inv_n = mod_inv(n, mod);
            for (int i = 0; i < n; ++i)
                a[i] = (int)(a[i] * inv_n % mod);
        }
    }

    // Convolution under this mod
    vector<int> convolution(const vector<int>& A, const vector<int>& B) const {
        int need = (int)A.size() + (int)B.size() - 1;
        int n = 1; while (n < need) n <<= 1;
        vector<int> fa(n, 0), fb(n, 0);
        for (int i = 0; i < (int)A.size(); ++i) fa[i] = A[i] % mod;
        for (int j = 0; j < (int)B.size(); ++j) fb[j] = B[j] % mod;
        ntt(fa, false); ntt(fb, false);
        for (int i = 0; i < n; ++i) fa[i] = (int)((ll)fa[i] * fb[i] % mod);
        ntt(fa, true);
        fa.resize(need);
        return fa;
    }
};

/********** CRT for two coprime moduli **********
 * Given x ≡ a (mod m1), x ≡ b (mod m2), with gcd(m1,m2)=1:
 * t = (b - a) * inv(m1 mod m2) mod m2
 * x = a + m1 * t   (unique in [0, m1*m2))
 ***********************************************/
static inline u64 crt2_u64(ll a, ll m1, ll b, ll m2, ll inv_m1_mod_m2) {
    ll t = norm(b - a, m2);
    t = (ll)((i128)t * inv_m1_mod_m2 % m2);
    i128 x = (i128)a + (i128)m1 * t;
    return (u64)x; // guaranteed < m1*m2
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    if (!In.nextInt(N)) return 0;
    In.nextInt(M);

    // degrees N, M -> lengths N+1, M+1
    vector<int> A(N + 1), B(M + 1);
    for (int i = 0; i <= N; ++i) { int v; In.nextInt(v); A[i] = v; }
    for (int j = 0; j <= M; ++j) { int v; In.nextInt(v); B[j] = v; }

    // Two NTT-friendly primes and primitive roots
    const ll mod1 = 998244353;       // 2^23 * 119 + 1
    const ll mod2 = 1012924417;      // 2^21 * 483 + 1
    const int root1 = 3;             // primitive root for mod1
    const int root2 = 5;             // primitive root for mod2  (3는 원시근이 아님!)

    NTT ntt1((int)mod1, root1);
    NTT ntt2((int)mod2, root2);

    // Convolution under each modulus
    vector<int> C1 = ntt1.convolution(A, B); // residues mod mod1
    vector<int> C2 = ntt2.convolution(A, B); // residues mod mod2
    const int L = (int)C1.size();            // = N + M + 1

    // Precompute inverse of m1 modulo m2 for CRT
    const ll inv_m1_mod_m2 = mod_inv(mod1 % mod2, mod2);

    // XOR all coefficients reconstructed via CRT
    u64 ans = 0;
    for (int k = 0; k < L; ++k) {
        u64 ck = crt2_u64(C1[k], mod1, C2[k], mod2, inv_m1_mod_m2);
        ans ^= ck;
    }
    cout << ans << '\n';
    return 0;
}
