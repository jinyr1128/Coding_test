#include <bits/stdc++.h>
using namespace std;

using ll = long long;
const ll INF = (ll)4e18;

int N, M;
ll A[10][10];

// 4비트씩 열당 라벨(0..9) 인코딩/디코딩
inline uint64_t encode_labels(const array<int, 9>& lab, int M) {
    // lab 은 이미 정규화되어 있다고 가정 (0 또는 1..k)
    uint64_t code = 0;
    for (int c = 0; c < M; ++c) {
        code |= (uint64_t)(lab[c] & 0xF) << (4*c);
    }
    return code;
}
inline void decode_labels(uint64_t code, array<int, 9>& lab, int M) {
    for (int c = 0; c < M; ++c) {
        lab[c] = (int)((code >> (4*c)) & 0xF);
    }
}
inline void canonicalize(array<int,9>& lab, int M) {
    int mapv[16]; fill(begin(mapv), end(mapv), 0);
    int nxt = 1;
    for (int c = 0; c < M; ++c) if (lab[c]) {
        int v = lab[c];
        if (!mapv[v]) mapv[v] = nxt++;
        lab[c] = mapv[v];
    }
}

// curMask 의 연속 1-세그먼트 전처리
struct SegInfo {
    vector<int> segBits;      // 각 세그먼트의 비트마스크
    array<int,9> col2seg;     // 열 -> 포함 세그먼트 인덱스(없으면 -1)
};
vector<SegInfo> seginfo;      // size = (1<<M)

// 행별 마스크 합(가중치) 전처리
vector<ll> rowSum;

// 한 상태(이전 행의 열린 프론티어)에서 라벨별 열비트 집합을 얻는다.
struct PrevInfo {
    int k = 0;                // 라벨 개수
    int mask = 0;             // 열린 열 비트마스크
    int labBits[10];          // labBits[1..k]: 각 라벨이 차지하는 열 비트셋
};
inline PrevInfo buildPrev(uint64_t code, int M) {
    array<int,9> lab{};
    decode_labels(code, lab, M);
    PrevInfo P;
    for (int i=0;i<10;i++) P.labBits[i]=0;
    int kmax = 0, m = 0;
    for (int c = 0; c < M; ++c) {
        int v = lab[c];
        if (v) {
            P.labBits[v] |= (1<<c);
            m |= (1<<c);
            if (v > kmax) kmax = v;
        }
    }
    P.k = kmax;
    P.mask = m;
    return P;
}

// 세그먼트용 DSU
struct DSU {
    int p[10];
    void init(int n){ for(int i=0;i<n;i++) p[i]=i; }
    int find(int x){ return p[x]==x?x:p[x]=find(p[x]); }
    void uni(int a,int b){ a=find(a); b=find(b); if(a!=b) p[b]=a; }
};

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    if(!(cin>>N>>M)) return 0;
    for(int i=0;i<N;i++)
        for(int j=0;j<M;j++)
            cin>>A[i][j];

    // 1) 세그먼트 전처리
    seginfo.assign(1<<M, {});
    for (int mask = 0; mask < (1<<M); ++mask) {
        SegInfo si;
        si.col2seg.fill(-1);
        int c = 0, idx = 0;
        while (c < M) {
            if ( (mask>>c)&1 ) {
                int s = c;
                while (c < M && ((mask>>c)&1)) ++c;
                int e = c-1;
                int bits = ((1<<(e-s+1)) - 1) << s;
                si.segBits.push_back(bits);
                for (int x = s; x <= e; ++x) si.col2seg[x] = idx;
                ++idx;
            } else ++c;
        }
        seginfo[mask] = move(si);
    }

    // 2) DP: 열린 상태(used==0)만 맵에 유지
    unordered_map<uint64_t, ll> dp, nxt;
    dp.reserve(200000);
    nxt.reserve(200000);
    dp[0] = 0;                       // 처음엔 아무것도 안 고름

    ll best = 0;                     // 공집합 허용 → 0은 항상 후보

    for (int r = 0; r < N; ++r) {
        // 행 r 의 마스크 합 전처리
        rowSum.assign(1<<M, 0);
        for (int mask=0; mask<(1<<M); ++mask) {
            ll s = 0;
            for (int c=0; c<M; ++c) if ((mask>>c)&1) s += A[r][c];
            rowSum[mask] = s;
        }

        nxt.clear();
        // 각 이전 상태에서 모든 curMask 전이
        for (auto &kv : dp) {
            uint64_t code = kv.first;
            ll curCost = kv.second;

            PrevInfo P = buildPrev(code, M);
            int k = P.k;

            for (int curMask = 0; curMask < (1<<M); ++curMask) {
                // 닫힘 개수 계산: 이전 라벨 중 이번 curMask 와 한 열도 안 겹치는 라벨의 수
                int cont = 0;
                for (int lab = 1; lab <= k; ++lab)
                    if ( (P.labBits[lab] & curMask) != 0 ) ++cont;
                int closed = k - cont;

                if (k == 0) {
                    // 아직 아무것도 안 열린 상태 → 자유롭게 시작/유지 가능
                    // (닫힘은 0일 뿐)
                    // curMask로 새 프론티어 구성
                } else {
                    // 이미 열려 있는 컴포넌트들이 있음
                    if (closed == 0) {
                        // OK (모든 열린 컴포넌트가 아래 행과 맞닿아 이어짐)
                    } else {
                        // 일부만 닫힘은 금지(최종적으로 다중 컴포넌트)
                        // 단, "열린 것이 정확히 1개이고 curMask=0"이면
                        // 그 하나를 지금 완전히 닫아 정답 후보로 갱신 가능
                        if (k == 1 && curMask == 0) {
                            best = min(best, curCost); // 이번 행에서 끝냄
                        }
                        continue; // 다른 경우는 불가
                    }
                }

                // 여기까지 왔다면 closed==0 (혹은 k==0)
                // curMask 프론티어의 연결 분할을 만든다.
                const auto &SI = seginfo[curMask];
                int S = (int)SI.segBits.size();
                if (S == 0) {
                    // 다음 상태: 아무것도 안 열림
                    uint64_t ncode = 0;
                    ll ncost = curCost + rowSum[curMask]; // (= curCost)
                    auto it = nxt.find(ncode);
                    if (it == nxt.end() || ncost < it->second) nxt[ncode] = ncost;
                    continue;
                }

                DSU dsu; dsu.init(max(1,S));

                // 같은 이전 라벨을 공유해 아래로 내려온 세그먼트들은 하나로 합침
                for (int lab = 1; lab <= k; ++lab) {
                    int T = P.labBits[lab] & curMask; // 이번 행에서 이 라벨이 내려온 열들
                    if (T == 0) continue;
                    int first = -1;
                    for (int s = 0; s < S; ++s) {
                        if ( (SI.segBits[s] & T) != 0 ) {
                            if (first == -1) first = s;
                            else dsu.uni(first, s);
                        }
                    }
                }
                // 라벨 정규화: 좌→우 열 순으로 최초 등장한 루트에 1.. 부여
                array<int,9> nl{}; // 다음 상태의 열 라벨
                int rootId[10]; for(int i=0;i<10;i++) rootId[i]=0;
                int nxtId = 1;
                for (int c = 0; c < M; ++c) {
                    if ( ((curMask>>c)&1) == 0 ) { nl[c] = 0; continue; }
                    int seg = SI.col2seg[c];
                    int rt = dsu.find(seg);
                    if (!rootId[rt]) rootId[rt] = nxtId++;
                    nl[c] = rootId[rt];
                }
                uint64_t ncode = encode_labels(nl, M);
                ll ncost = curCost + rowSum[curMask];

                auto it = nxt.find(ncode);
                if (it == nxt.end() || ncost < it->second) nxt[ncode] = ncost;
            } // curMask
        } // states

        dp.swap(nxt);
    } // rows

    // 마지막으로, 남아있는 열린 상태들 중 "열린 게 정확히 1개"인 것들을
    // curMask=0으로 닫아 정답 후보 갱신
    for (auto &kv : dp) {
        PrevInfo P = buildPrev(kv.first, M);
        if (P.k == 1) best = min(best, kv.second);
    }

    // 공집합 허용 → 0 과 비교
    cout << min(0LL, best) << '\n';
    return 0;
}
