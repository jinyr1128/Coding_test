#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

const int MAX_N = 200100;
int leftBound[MAX_N], rightBound[MAX_N];

// 재귀적으로 구간을 확인하는 함수
bool checkSequence(int start, int end) {
    if (start >= end) return true; // 기저 사례: 구간이 유효하지 않으면 참
    
    for (int i = 0; start + i <= end - i; i++) {
        // 왼쪽에서 탐색
        if (leftBound[start + i] <= start && rightBound[start + i] >= end)
            return checkSequence(start, start + i - 1) && checkSequence(start + i + 1, end);
        
        // 오른쪽에서 탐색
        if (leftBound[end - i] <= start && rightBound[end - i] >= end)
            return checkSequence(start, end - i - 1) && checkSequence(end - i + 1, end);
    }
    return false; // 모든 경우를 확인했지만 조건을 만족하지 않는 경우
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int testCases;
    cin >> testCases;
    
    while (testCases--) {
        int sequenceLength;
        cin >> sequenceLength;
        
        vector<pair<int, int>> values;
        for (int i = 0; i < sequenceLength; i++) {
            int num;
            cin >> num;
            values.emplace_back(num, i);
            leftBound[i] = 0;
            rightBound[i] = sequenceLength - 1;
        }
        
        sort(values.begin(), values.end()); // 값 기준 정렬하여 구간 경계를 설정
        
        for (int i = 1; i < sequenceLength; i++) {
            if (values[i].first == values[i - 1].first) {
                leftBound[values[i].second] = values[i - 1].second + 1;
                rightBound[values[i - 1].second] = values[i].second - 1;
            }
        }
        
        bool result = checkSequence(0, sequenceLength - 1);
        cout << (result ? "non-boring" : "boring") << '\n';
    }
    return 0;
}