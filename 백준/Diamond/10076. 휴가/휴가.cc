#include <bits/stdc++.h>
using namespace std;

struct PST {
    struct Node { int l=0, r=0, cnt=0; long long sum=0; };
    vector<Node> t;
    vector<long long> vals;           // 압축 해제 값(오름차순)
    vector<int> root;                 // prefix별 루트
    int n = 0, m = 0;                 // n: 배열 길이, m: 서로 다른 값 개수

    PST() {}
    PST(const vector<long long>& a) { build(a); }

    void build(const vector<long long>& a) {
        n = (int)a.size() - 1;        // a는 1-indexed
        vals.assign(a.begin()+1, a.end());
        sort(vals.begin(), vals.end());
        vals.erase(unique(vals.begin(), vals.end()), vals.end());
        m = (int)vals.size();
        t.reserve((size_t)n * 20 + 5);
        t.push_back(Node());          // 0번 노드: null
        root.assign(n+1, 0);

        auto idx_of = [&](long long x)->int {
            return (int)(lower_bound(vals.begin(), vals.end(), x) - vals.begin());
        };

        for (int i = 1; i <= n; ++i) {
            int pos = idx_of(a[i]);
            root[i] = upd(root[i-1], 0, m-1, pos);
        }
    }

    int newnode(const Node &from) {
        t.push_back(from);
        return (int)t.size()-1;
    }

    int upd(int prev, int L, int R, int pos) {
        int cur = newnode(t[prev]);
        t[cur].cnt += 1;
        t[cur].sum += vals[pos];
        if (L != R) {
            int mid = (L+R)>>1;
            if (pos <= mid) {
                int nl = upd(t[cur].l, L, mid, pos);
                t[cur].l = nl;
            } else {
                int nr = upd(t[cur].r, mid+1, R, pos);
                t[cur].r = nr;
            }
        }
        return cur;
    }

    // [l..r] 구간에서 가장 큰 k개의 합 (값은 오름차순 압축 → 오른쪽이 큰 값)
    long long topk(int l, int r, int k) {
        if (k <= 0 || l > r) return 0LL;
        k = min(k, r - l + 1);
        return query(root[l-1], root[r], 0, m-1, k);
    }

    long long query(int leftRoot, int rightRoot, int L, int R, int k) {
        if (k <= 0) return 0LL;
        if (L == R) {
            int have = t[rightRoot].cnt - t[leftRoot].cnt;
            if (have <= 0) return 0LL;
            return vals[L] * 1LL * min(k, have);
        }
        int mid = (L+R)>>1;
        int rL = t[leftRoot].r, rR = t[rightRoot].r;
        int cntRight = t[rR].cnt - t[rL].cnt;                // 큰 값(오른쪽) 개수
        if (cntRight >= k) {
            return query(rL, rR, mid+1, R, k);
        } else {
            long long sumRight = (t[rR].sum - t[rL].sum);
            return sumRight + query(t[leftRoot].l, t[rightRoot].l, L, mid, k - cntRight);
        }
    }
};

struct Solver {
    int n;              // 1-indexed 크기
    int S;              // 시작 위치(1-indexed)
    long long D;        // 총 일수
    vector<long long> A;// 1-indexed
    long long answer = 0;

    long long solve_one_direction() {
        PST pst(A);
        long long best = 0;

        // f(L,R) = [L,R]에서 top-k 합, k = min(R-L+1, D - (S + R - 2L))
        auto value = [&](int L, int R)->long long {
            long long kraw = D - (S + R - 2LL*L);
            if (kraw <= 0) return 0LL;
            int k = (int)min<long long>(R - L + 1, kraw);
            return pst.topk(L, R, k);
        };

        // Divide & Conquer optimization:
        // L ∈ [1..S], R ∈ [S..n], optR(L)가 비감소.
        function<void(int,int,int,int)> dnc = [&](int Llo, int Lhi, int Rlo, int Rhi) {
            if (Llo > Lhi) return;
            int Lmid = (Llo + Lhi) >> 1;
            long long bestVal = -1;
            int bestR = Rlo;
            int lo = max(Rlo, S), hi = Rhi;
            for (int R = lo; R <= hi; ++R) {
                long long cur = value(Lmid, R);
                if (cur > bestVal) { bestVal = cur; bestR = R; }
            }
            best = max(best, bestVal);
            dnc(Llo, Lmid-1, Rlo, bestR);
            dnc(Lmid+1, Lhi, bestR, Rhi);
        };

        dnc(1, S, S, n);
        return best;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n0, s0;
    long long d;
    if (!(cin >> n0 >> s0 >> d)) return 0;
    vector<long long> arr0(n0);
    for (int i = 0; i < n0; ++i) cin >> arr0[i];

    // 1) 원배열 기준 (왼→오)
    Solver sv1;
    sv1.n = n0;
    sv1.S = s0 + 1;                       // 1-indexed
    sv1.D = d;
    sv1.A.assign(n0 + 1, 0);
    for (int i = 1; i <= n0; ++i) sv1.A[i] = arr0[i-1];
    long long ans = sv1.solve_one_direction();

    // 2) 배열 뒤집어서 (오→왼) 동일하게
    vector<long long> arr1 = arr0;
    reverse(arr1.begin(), arr1.end());
    Solver sv2;
    sv2.n = n0;
    sv2.S = (n0 - 1 - s0) + 1;            // 뒤집힌 배열에서의 시작점(1-indexed)
    sv2.D = d;
    sv2.A.assign(n0 + 1, 0);
    for (int i = 1; i <= n0; ++i) sv2.A[i] = arr1[i-1];
    ans = max(ans, sv2.solve_one_direction());

    cout << ans << '\n';
    return 0;
}
