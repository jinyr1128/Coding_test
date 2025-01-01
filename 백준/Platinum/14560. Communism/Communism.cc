#include <bits/stdc++.h>
#define fastio cin.tie(0)->sync_with_stdio(0)
using namespace std;

#define int int64_t

int32_t main() {
    fastio;
    
    // 입력 크기 및 초기화
    int size; 
    cin >> size;
    vector<int> numbers(size);
    for (int i = 0; i < size; i++) cin >> numbers[i];
    int threshold; 
    cin >> threshold;

    // 크기가 1인 경우 즉시 종료
    if (size == 1) 
        return !(cout << (numbers[0] <= threshold ? 3 : 1) << '\n');

    // 부분 배열의 가능한 합을 계산하는 함수
    auto calculateSubsets = [&](int left, int right) -> vector<int> {
        auto dfs = [&](int index, auto&& dfs) -> vector<int> {
            if (index > right) return { 0 }; // 끝까지 탐색했으면 0 반환
            auto subset1 = dfs(index + 1, dfs); // 현재 숫자를 포함하지 않는 경우
            auto subset2 = subset1, subset3 = subset2;
            for (auto& val : subset2) val -= numbers[index]; // 현재 숫자를 빼는 경우
            for (auto& val : subset3) val += numbers[index]; // 현재 숫자를 더하는 경우
            vector<int> result; 
            result.reserve(subset1.size() + subset2.size() + subset3.size());
            
            // 세 개의 배열을 병합하여 정렬 상태 유지
            for (int i1 = 0, i2 = 0, i3 = 0; 
                 i1 < subset1.size() || i2 < subset2.size() || i3 < subset3.size();) {
                if (i1 < subset1.size()
                    && (i2 == subset2.size() || subset1[i1] <= subset2[i2])
                    && (i3 == subset3.size() || subset1[i1] <= subset3[i3])) result.push_back(subset1[i1++]);
                if (i2 < subset2.size()
                    && (i1 == subset1.size() || subset2[i2] <= subset1[i1])
                    && (i3 == subset3.size() || subset2[i2] <= subset3[i3])) result.push_back(subset2[i2++]);
                if (i3 < subset3.size()
                    && (i1 == subset1.size() || subset3[i3] <= subset1[i1])
                    && (i2 == subset2.size() || subset3[i3] <= subset2[i2])) result.push_back(subset3[i3++]);
            }
            return result;
        };
        return dfs(left, dfs);
    };

    // 배열을 두 부분으로 나누어 각각 처리
    auto leftSums = calculateSubsets(0, size / 2 - 1);
    auto rightSums = calculateSubsets(size / 2, size - 1);

    // 정답 계산
    int count = 0;
    for (int i = 0, leftIndex = 0, rightIndex = 0; i < leftSums.size(); i++) {
        while (rightIndex < rightSums.size() && rightSums[rightIndex] <= leftSums[i] + threshold) rightIndex++;
        while (leftIndex < rightSums.size() && rightSums[leftIndex] < leftSums[i] - threshold) leftIndex++;
        count += rightIndex - leftIndex;
    }

    // 결과 출력
    cout << count << '\n';
}
