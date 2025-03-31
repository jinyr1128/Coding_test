#include <bits/stdc++.h>
using namespace std;
using i64 = int64_t;

// 각 위치별 선택 가능한 마이크로컨트롤러 비용 정보
vector<vector<int>> microcontrollerOptions;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int locationCount, robotCount;
    cin >> locationCount >> robotCount;

    i64 baseCost = 0; // 각 위치별 가장 저렴한 모델을 선택했을 때의 총 비용

    // 각 위치별 입력 처리
    while (locationCount--) {
        int modelCount;
        cin >> modelCount;
        vector<int> costs(modelCount);

        for (int& cost : costs) cin >> cost;

        sort(costs.begin(), costs.end()); // 비용 오름차순 정렬
        int minCost = costs[0];
        baseCost += minCost;

        // 최소값을 제외한 값만 보관, 이후 조합을 위해 필요함
        for (int& cost : costs) cost -= minCost;
        if (costs.size() > 1) microcontrollerOptions.push_back(costs);
    }

    // 비용 벡터 사전순 정렬 (다익스트라와 유사한 방식에 사용됨)
    sort(microcontrollerOptions.begin(), microcontrollerOptions.end());

    // 최소 비용 조합을 찾기 위한 우선순위 큐 (비용, 위치 인덱스, 해당 위치의 마이크로컨트롤러 인덱스)
    using State = tuple<i64, size_t, size_t>;
    priority_queue<State, vector<State>, greater<State>> pq;

    pq.emplace(0, 0, 0); // 초기 상태 (추가 비용 0, 첫 위치, 첫 인덱스)

    i64 totalCost = 0;

    // 로봇 K대 만들기
    while (robotCount--) {
        auto [addedCost, posIdx, optionIdx] = pq.top(); pq.pop();
        totalCost += baseCost + addedCost;

        // 현재 위치에서 다음 모델로 이동
        if (optionIdx + 1 < microcontrollerOptions[posIdx].size()) {
            pq.emplace(addedCost - microcontrollerOptions[posIdx][optionIdx] + microcontrollerOptions[posIdx][optionIdx + 1], posIdx, optionIdx + 1);
        }

        // 다음 위치의 모델을 처음 조합에 넣는 경우
        if (posIdx + 1 < microcontrollerOptions.size()) {
            // 현재 위치에서 0번째 이후 모델을 선택한 경우에만 다음 위치의 1번째 모델 추가
            if (optionIdx)
                pq.emplace(addedCost + microcontrollerOptions[posIdx + 1][1], posIdx + 1, 1);
            // 현재 위치에서 1번째 모델을 선택한 경우, 다음 위치로 교체 시뮬레이션
            if (optionIdx == 1)
                pq.emplace(addedCost - microcontrollerOptions[posIdx][1] + microcontrollerOptions[posIdx + 1][1], posIdx + 1, 1);
        }
    }

    cout << totalCost << '\n';
    return 0;
}