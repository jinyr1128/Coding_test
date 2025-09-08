#include <bits/stdc++.h>
using namespace std;

using ll = long long;
using i128 = __int128_t;

struct FastScanner {
    static const int S = 1 << 20;
    int idx = 0, sz = 0; char buf[S];
    inline char read() {
        if (idx >= sz) { sz = (int)fread(buf,1,S,stdin); idx = 0; if (!sz) return 0; }
        return buf[idx++];
    }
    template<typename T>
    bool nextInt(T &out) {
        char c = read(); if (!c) return false;
        T sign = 1, x = 0;
        while (c!='-' && (c<'0'||c>'9')) { c = read(); if (!c) return false; }
        if (c=='-') { sign=-1; c=read(); }
        for (; c>='0'&&c<='9'; c=read()) x = x*10 + (c-'0');
        out = x*sign; return true;
    }
} In;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int m, n;
    if (!In.nextInt(m)) return 0;
    In.nextInt(n);

    vector<pair<ll,ll>> prod(m), cons(n);
    for (int i=0;i<m;i++){
        ll p,d; In.nextInt(p); In.nextInt(d);
        prod[i] = {p,d};
    }
    for (int j=0;j<n;j++){
        ll q,e; In.nextInt(q); In.nextInt(e);
        cons[j] = {q,e};
    }

    // --------- 생산자 SKYLINE (lower-left minimal) ----------
    // 같은 p에서는 d가 작은 것만 남기고, p 오름차순으로 훑으면서
    // d가 "엄격히" 작아질 때만 채택 -> d는 내림차순이 된다.
    sort(prod.begin(), prod.end(), [](auto &a, auto &b){
        if (a.first != b.first) return a.first < b.first;
        return a.second < b.second; // 같은 p면 작은 d 먼저
    });
    vector<pair<ll,ll>> Puniq;
    Puniq.reserve(prod.size());
    for (int i=0;i<m;){
        int j=i; ll p = prod[i].first, mind = prod[i].second;
        while (j<m && prod[j].first==p) { mind = min(mind, prod[j].second); j++; }
        Puniq.push_back({p, mind});
        i = j;
    }
    vector<ll> Pp, Pd;
    Pp.reserve(Puniq.size()); Pd.reserve(Puniq.size());
    ll curMinD = (ll)4e18;
    for (auto &pr : Puniq){
        if (pr.second < curMinD){ // d가 더 작아질 때만 채택
            Pp.push_back(pr.first);
            Pd.push_back(pr.second);
            curMinD = pr.second;
        }
    }

    // --------- 소비자 SKYLINE (upper-right maximal) ----------
    // 같은 q에서는 e가 큰 것만 남기고, q 오름차순 정렬 후
    // 오른쪽->왼쪽으로 스캔하며 e가 "엄격히" 커질 때만 채택
    sort(cons.begin(), cons.end(), [](auto &a, auto &b){
        if (a.first != b.first) return a.first < b.first;
        return a.second > b.second; // 같은 q면 큰 e를 앞으로
    });
    vector<pair<ll,ll>> Cuniq;
    Cuniq.reserve(cons.size());
    for (int i=0;i<n;){
        int j=i; ll q = cons[i].first, maxe = cons[i].second;
        while (j<n && cons[j].first==q) { maxe = max(maxe, cons[j].second); j++; }
        Cuniq.push_back({q, maxe});
        i = j;
    }
    vector<pair<ll,ll>> CresRev;
    CresRev.reserve(Cuniq.size());
    ll curMaxE = (ll)-4e18;
    for (int k=(int)Cuniq.size()-1; k>=0; --k){
        if (Cuniq[k].second > curMaxE){
            CresRev.push_back(Cuniq[k]);
            curMaxE = Cuniq[k].second;
        }
    }
    reverse(CresRev.begin(), CresRev.end()); // q 오름, e 내림
    vector<ll> Cq, Ce;
    Cq.reserve(CresRev.size()); Ce.reserve(CresRev.size());
    for (auto &pr : CresRev){ Cq.push_back(pr.first); Ce.push_back(pr.second); }

    int Rn = (int)Pp.size();
    int Cn = (int)Cq.size();
    if (Rn==0 || Cn==0){ cout << 0 << '\n'; return 0; }

    // --------- 각 행 i의 유효 열 구간 [L(i), R(i)] ----------
    vector<int> L(Rn), Rr(Rn);

    // L(i): q_j > p_i 가 처음 되는 곳 (Cq 오름)
    for (int i=0;i<Rn;i++){
        L[i] = (int)(upper_bound(Cq.begin(), Cq.end(), Pp[i]) - Cq.begin());
    }
    // R(i): e_j > d_i 가 마지막인 곳 (Ce 내림)
    auto last_idx_e_gt = [&](ll d)->int{
        // Ce[0]..Ce[Cn-1] strictly decreasing
        int lo=0, hi=Cn;
        while (lo < hi){
            int mid = (lo+hi)>>1;
            if (Ce[mid] > d) lo = mid + 1;
            else hi = mid;
        }
        return lo - 1; // <0 이면 없음
    };
    for (int i=0;i<Rn;i++){
        Rr[i] = last_idx_e_gt(Pd[i]);
    }

    // --------- anti-Monge 행렬의 행별 최대값: D&C 최적화 ----------
    long long answer = 0;

    function<void(int,int,int,int)> solve = [&](int il, int ir, int jl, int jr){
        if (il > ir) return;
        int im = (il + ir) >> 1;

        int lo = max(jl, L[im]);
        int hi = min(jr, Rr[im]);

        long long bestVal = 0;
        int bestJ = jl;
        if (lo <= hi){
            // im 행에서 [lo..hi]만 보면 된다
            for (int j=lo; j<=hi; ++j){
                // (Cq[j]-Pp[im])*(Ce[j]-Pd[im])  (양수 보장)
                ll dx = Cq[j] - Pp[im];
                ll dy = Ce[j] - Pd[im];
                long long val = (long long)((i128)dx * (i128)dy);
                if (val > bestVal){
                    bestVal = val; bestJ = j;
                }
            }
            if (bestVal > answer) answer = bestVal;
            // 좌측은 <= bestJ, 우측은 >= bestJ
            solve(il, im-1, jl, bestJ);
            solve(im+1, ir, bestJ, jr);
        } else {
            // 이 구간에서는 im 행에 유효한 열이 없음 → 범위를 줄이지 않고 진행
            solve(il, im-1, jl, jr);
            solve(im+1, ir, jl, jr);
        }
    };

    solve(0, Rn-1, 0, Cn-1);
    cout << answer << '\n';
    return 0;
}
