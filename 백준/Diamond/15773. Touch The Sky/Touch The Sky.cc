#include <bits/stdc++.h>
using namespace std;

using int64 = long long;

struct Balloon {
    int64 limit;   // L_i
    int64 delta;   // D_i
    int64 deadline() const { return limit + delta; } // d_i = L_i + D_i
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;

    vector<Balloon> v(N);
    for (int i = 0; i < N; ++i) {
        cin >> v[i].limit >> v[i].delta;
    }

    // 마감 기한 d_i = L_i + D_i 기준 오름차순 정렬
    sort(v.begin(), v.end(),
         [](const Balloon& a, const Balloon& b) {
             return a.deadline() < b.deadline();
         });

    // 선택한 풍선들의 D만 최대 힙으로 보관, 누적 합 S 유지
    priority_queue<int64> chosen;
    int64 S = 0;

    for (const auto& b : v) {
        S += b.delta;
        chosen.push(b.delta);

        // 현재까지 누적 고도 S가 이 풍선의 마감 기한 d_i를 넘으면
        // 가장 큰 D 하나를 제거해 S를 줄인다 (필요하면 방금 넣은 것도 빠짐)
        while (!chosen.empty() && S > b.deadline()) {
            S -= chosen.top();
            chosen.pop();
        }
    }

    cout << chosen.size() << '\n';
    return 0;
}

