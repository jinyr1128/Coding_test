#include <bits/stdc++.h>
using namespace std;

using ll = long long;

// Ai < 2^30
static constexpr int MAX_BIT = 29;

// ------------------------------------------------------------
// 동적(implicit) 세그먼트 트리: [0 .. (1<<30)-1] 구간에 값들을 삽입해두고,
// 주어진 x와 XOR 값이 최소가 되는 집합 원소 y를 O(트리높이)로 찾아준다.
// - 노드에는 해당 구간에 포함된 원소 개수(cnt)만 저장한다.
// - 자식이 아직 없으면 인덱스 0(더미)의 cnt=0을 이용해 "비어있음"을 표현.
// ------------------------------------------------------------
struct DynSeg {
    struct Node {
        int left, right; // 자식 노드 인덱스
        int cnt;         // 이 노드 구간에 있는 원소 개수
        Node(int l=0, int r=0, int c=0) : left(l), right(r), cnt(c) {}
    };
    vector<Node> tr; // tr[0]은 더미(빈 노드)

    // 초기화: 0번 더미 + 1번 실제 루트 노드
    void init() {
        tr.clear();
        tr.emplace_back(); // index 0: dummy
        tr.emplace_back(); // index 1: root
    }

    // 값 key 삽입
    void insert(int node, int L, int R, int key) {
        tr[node].cnt++;
        if (L == R) return;
        int mid = (L + R) >> 1;
        if (key <= mid) {
            if (tr[node].left == 0) {
                tr[node].left = (int)tr.size();
                tr.emplace_back();
            }
            insert(tr[node].left, L, mid, key);
        } else {
            if (tr[node].right == 0) {
                tr[node].right = (int)tr.size();
                tr.emplace_back();
            }
            insert(tr[node].right, mid+1, R, key);
        }
    }

    // 집합 내 원소 중에서 x와 XOR이 최소가 되도록 y를 탐색해서 "값 그 자체"를 반환
    // bit 파라미터는 현재 내려가는 트리 깊이에 대응(최상위 비트부터).
    int bestMatch(int node, int L, int R, int x, int bit) const {
        if (L == R) return L;
        int mid = (L + R) >> 1;

        // 왼/오른 자식의 원소 존재 여부 확인 (인덱스 0은 cnt=0이므로 비어있음을 뜻함)
        const Node &cur = tr[node];
        int lc = cur.left, rc = cur.right;
        bool hasL = (lc != 0) && (tr[lc].cnt > 0);
        bool hasR = (rc != 0) && (tr[rc].cnt > 0);

        // 한쪽만 있으면 그쪽으로 내려간다.
        if (!hasL) return bestMatch(rc, mid+1, R, x, bit-1);
        if (!hasR) return bestMatch(lc, L, mid, x, bit-1);

        // 둘 다 있으면, x의 bit와 같은 쪽으로 내려가야 XOR이 최소가 됨
        if ((x >> bit) & 1) return bestMatch(rc, mid+1, R, x, bit-1);
        else                return bestMatch(lc, L, mid, x, bit-1);
    }
};

// ------------------------------------------------------------
// 분할 정복 on bits:
//  - 현재 비트 t에서 값을 0-그룹/1-그룹으로 나눈다.
//  - 두 그룹이 모두 비어있지 않으면, 두 그룹 사이를 연결하는 최소 간선 가중치
//    = min_{x in 0-그룹} min_{y in 1-그룹} (x xor y) 를 더해준다.
//    (이 값이 MST에서 이 비트 레벨에서의 "cross-edge 비용"이 됨)
//  - 이후 각각의 그룹에 대해 t-1 비트로 재귀.
// ------------------------------------------------------------
ll totalCost = 0;

void solveByBit(vector<int>& vals, int bit) {
    if (vals.empty() || bit < 0) return;

    // bit 위치로 0-그룹, 1-그룹 분할
    vector<int> group0, group1;
    group0.reserve(vals.size());
    group1.reserve(vals.size());
    for (int v : vals) {
        if ((v >> bit) & 1) group1.push_back(v);
        else                group0.push_back(v);
    }

    // 두 그룹 모두 존재하면 "교차 간선"의 최소 비용을 구해서 누적
    if (!group0.empty() && !group1.empty()) {
        // 더 큰 쪽을 기준으로 세그트리를 만들어두고,
        // 작은 쪽 원소에 대해 최소 xor 매칭을 찾으면 약간 더 효율적이다.
        vector<int> &big = (group0.size() >= group1.size()) ? group0 : group1;
        vector<int> &sml = (group0.size() >= group1.size()) ? group1 : group0;

        DynSeg seg;
        seg.init();
        // 값의 전체 범위 [0, 2^30 - 1]
        const int FULL_L = 0;
        const int FULL_R = (1 << 30) - 1;

        for (int v : big) seg.insert(1, FULL_L, FULL_R, v);

        int bestCross = INT_MAX;
        for (int v : sml) {
            int partner = seg.bestMatch(1, FULL_L, FULL_R, v, MAX_BIT);
            bestCross = min(bestCross, v ^ partner);
        }
        totalCost += bestCross;
    }

    // 하위 비트로 재귀
    solveByBit(group0, bit - 1);
    solveByBit(group1, bit - 1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N; 
    cin >> N;
    vector<int> values(N);
    for (int i = 0; i < N; ++i) cin >> values[i];

    solveByBit(values, MAX_BIT);
    cout << totalCost << '\n';
    return 0;
}
