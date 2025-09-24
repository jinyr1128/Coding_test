#include <bits/stdc++.h>
using namespace std;
using ll = long long;

struct PairCmp {
    bool operator()(const pair<ll,ll>& a, const pair<ll,ll>& b) const {
        if (a.first != b.first) return a.first < b.first;
        return a.second < b.second;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;

    vector<ll> s(N+1), e(N+1);
    vector<pair<ll,ll>> lectures(N+1); // (start,end) in input order
    vector<pair<ll,ll>> arr; arr.reserve(N);

    for (int i = 1; i <= N; ++i) {
        cin >> s[i] >> e[i];
        lectures[i] = {s[i], e[i]};
        arr.push_back({s[i], e[i]});
    }

    // 시작 시각 좌표압축
    vector<ll> comp = {};
    comp.reserve(N);
    for (int i = 1; i <= N; ++i) comp.push_back(s[i]);
    sort(comp.begin(), comp.end());
    comp.erase(unique(comp.begin(), comp.end()), comp.end());
    int C = (int)comp.size();

    auto idxStart = [&](ll x)->int { // 1-based index of lower_bound(comp, x)
        return (int)(lower_bound(comp.begin(), comp.end(), x) - comp.begin()) + 1;
    };

    // 각 시작 인덱스별 "가장 빨리 끝나는" e의 최소값
    const ll INF = (ll)4e18;
    vector<ll> minEnd(C+2, INF); // 1..C, sentinel at C+1

    sort(arr.begin(), arr.end()); // by start asc
    for (int i = N-1; i >= 0; --i) {
        int pos = idxStart(arr[i].first);
        if (arr[i].second < minEnd[pos]) minEnd[pos] = arr[i].second;
    }

    // sparse[i][0]: 시작 인덱스 i 이상에서 고를 수 있는 "가장 빨리 끝나는" e의 최소값
    const int LOG = 20; // 2^19 > 200000
    vector<vector<ll>> sparse(C+2, vector<ll>(LOG, INF));
    // suffix min
    for (int i = C; i >= 1; --i) {
        sparse[i][0] = min(sparse[i+1][0], minEnd[i]);
    }
    // doubling: sparse[i][j] = sparse[next(i,j-1)][j-1]
    auto nextIndex = [&](ll last_end)->int {
        // 다음 시작 인덱스: start >= last_end + 1
        return (int)(lower_bound(comp.begin(), comp.end(), last_end + 1) - comp.begin()) + 1;
    };
    for (int i = C; i >= 1; --i) {
        for (int j = 1; j < LOG; ++j) {
            ll midEnd = sparse[i][j-1];
            if (midEnd >= INF) { sparse[i][j] = INF; continue; }
            int ni = nextIndex(midEnd);
            if (ni > C+1) { sparse[i][j] = INF; continue; }
            sparse[i][j] = sparse[ni][j-1];
        }
    }

    // get_res(S, E): 시작 시각 >= S, 끝 시각 < E 내에서 선택 가능한 최대 개수
    auto get_res = [&](ll S, ll E)->long long {
        long long res = 0;
        int i = idxStart(S);
        if (i > C) return 0;
        for (int j = LOG-1; j >= 0; --j) {
            ll lastEnd = sparse[i][j];
            if (lastEnd < E) {
                res += (1LL << j);
                i = nextIndex(lastEnd);
                if (i > C) break;
            }
        }
        return res;
    };

    // 전체 최대 개수
    long long M = get_res(1, INF);
    cout << M << '\n';

    // 사전식 복원: 선택된 구간들을 (start,end)로 관리
    set<pair<ll,ll>, PairCmp> chosen;

    vector<int> answer; answer.reserve((size_t)M);
    for (int i = 1; i <= N; ++i) {
        ll A = lectures[i].first, B = lectures[i].second;

        // 이미 선택된 구간들과 겹치면 제외
        auto it = chosen.lower_bound({A, LLONG_MIN});
        ll Y = INF;
        if (it != chosen.end()) {
            if (it->first <= B) continue; // next.start <= this.end => overlap
            Y = it->first;
        }
        ll X = 1;
        if (it != chosen.begin()) {
            auto it2 = it; --it2;
            if (it2->second >= A) continue; // prev.end >= this.start => overlap
            X = it2->second + 1;
        }

        // 이 구간을 (X,Y) 창에서 넣어도 전체 최댓값을 해치지 않는지 확인
        long long bestAll = get_res(X, Y);
        long long leftCnt = get_res(X, A);
        long long rightCnt = get_res(B+1, Y);
        if (leftCnt + 1 + rightCnt == bestAll) {
            chosen.insert({A, B});
            answer.push_back(i);
            if ((long long)answer.size() == M) break; // 다 채웠으면 중단
        }
    }

    // 출력 (오름차순: 이미 입력 순서대로 채웠으므로 정렬 불필요)
    for (int i = 0; i < (int)answer.size(); ++i) {
        if (i) cout << ' ';
        cout << answer[i];
    }
    cout << '\n';
    return 0;
}
