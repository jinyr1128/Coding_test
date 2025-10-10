#include <bits/stdc++.h>
using namespace std;

using pii = pair<int,int>;


static vector<pair<int, pii>> cols;       // size = N
static vector<pii> pairs_23;              // (row2, row3)만 뽑아낸 점열
static vector< set<pii> > layers;         // dp 레이어: 각 길이 L(+1)에 대한 frontier (set of (x,y))


static inline bool can_extend_from_layer(int p, const pii &q) {
    auto it = layers[p].upper_bound({q.first, INT_MAX}); // first > q.x 인 첫 위치
    if (it == layers[p].begin()) return false;           // x <= q.x 인 점이 없음
    --it;                                                // x <= q.x 중 최댓값
    return (it->second < q.second);                      // y도 엄격히 작아야 증가
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int M, N;
    cin >> M >> N;

    cols.resize(N);

    // 입력: 첫 행
    for (int j = 0; j < N; ++j) cin >> cols[j].first;       // row1
    // 입력: 둘째 행
    for (int j = 0; j < N; ++j) cin >> cols[j].second.first; // row2
    // 입력: 셋째 행(있을 때만)
    if (M == 3) {
        for (int j = 0; j < N; ++j) cin >> cols[j].second.second; // row3
    }

    // row1 오름차순 정렬
    sort(cols.begin(), cols.end());

    // (row2, row3)만 추출
    pairs_23.reserve(N);
    for (auto &col : cols) pairs_23.push_back(col.second);

    // M=2일 때는 (row2, index)로 만들어 x가 같아도 index로 엄격성 보장(여기선 값 전역 유일이라 사실 불필요)
    if (M == 2) {
        for (int i = 0; i < N; ++i) pairs_23[i].second = i;
    }

    // 2D LIS: layers를 유지하면서 점들을 순차 처리
    // layers[L]: 길이 L+1인 증가 사슬을 만들 때의 frontier 집합
    for (const auto &pt : pairs_23) {
        // 이분탐색으로 들어갈 레이어 찾기
        int lo = 0, hi = (int)layers.size();
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (can_extend_from_layer(mid, pt)) lo = mid + 1;
            else                                hi = mid;
        }
        // lo가 삽입할 레이어 인덱스 (길이 lo+1 사슬)
        if (lo == (int)layers.size()) {
            layers.emplace_back();
            layers.back().insert(pt);
        } else {
            // layers[lo]에 pt를 삽입하되,
            // (x가 더 크거나 같고, y가 pt.y 이상인 점)들은 지배되어 제거
            auto it = layers[lo].upper_bound({pt.first, INT_MAX}); // first > pt.x
            while (it != layers[lo].end() && it->second >= pt.second) {
                it = layers[lo].erase(it);
            }
            layers[lo].insert(it, pt);
        }
    }

    // layers의 수 = 최대 길이
    cout << layers.size() << '\n';
    return 0;
}
