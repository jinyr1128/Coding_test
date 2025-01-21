#include <bits/stdc++.h>
using namespace std;
const int MAX_SIZE = 500009;
const int NEG_INF = 0x3f3f3f3f;
typedef pair<int, int> Pair;
typedef vector<pair<int, int>> VectorPair;

// 각 위치별 데이터 저장
VectorPair markers[MAX_SIZE];
int numPoints, upCost, downCost, startPoint;
int upTree[2 * MAX_SIZE], downTree[2 * MAX_SIZE];

// 범위 쿼리 함수
int rangeQuery(int* tree, int left, int right) {
    int maxVal = -NEG_INF;
    for (left += MAX_SIZE, right += MAX_SIZE; left <= right; left >>= 1, right >>= 1) {
        if (left & 1) maxVal = max(maxVal, tree[left++]);
        if (~right & 1) maxVal = max(maxVal, tree[right--]);
    }
    return maxVal;
}

// 단일 업데이트 함수
void updateTree(int* tree, int index, int value) {
    index += MAX_SIZE;
    tree[index] = max(tree[index], value);
    for (; index > 1; index >>= 1) {
        tree[index >> 1] = max(tree[index], tree[index ^ 1]);
    }
}

// upTree와 downTree를 동시에 업데이트
void updateCombined(int position, int value) {
    updateTree(upTree, position, value - upCost * position);
    updateTree(downTree, position, value + downCost * position);
}

// 특정 위치에서 쿼리 수행
int calculateMax(int position) {
    return max(
        rangeQuery(downTree, 0, position) - downCost * position,
        rangeQuery(upTree, position, MAX_SIZE - 1) + upCost * position
    );
}

// 주어진 위치의 데이터를 처리
void processMarkers(VectorPair& currentMarkers) {
    if (currentMarkers.empty()) return;

    sort(currentMarkers.begin(), currentMarkers.end()); // 위치 기준 정렬
    vector<int> upperValues, lowerValues;

    int markerSize = currentMarkers.size();
    for (int i = 0; i < markerSize; i++) {
        int currentMax = calculateMax(currentMarkers[i].first);
        upperValues.push_back(currentMax);
        lowerValues.push_back(currentMax);
    }

    // 아래에서 위로 갱신
    for (int i = 0; i < markerSize; i++) {
        if (i != 0) {
            lowerValues[i] = max(lowerValues[i], lowerValues[i - 1] - downCost * (currentMarkers[i].first - currentMarkers[i - 1].first));
        }
        lowerValues[i] += currentMarkers[i].second;
    }

    // 위에서 아래로 갱신
    for (int i = markerSize - 1; i >= 0; i--) {
        if (i != markerSize - 1) {
            upperValues[i] = max(upperValues[i], upperValues[i + 1] - upCost * (currentMarkers[i + 1].first - currentMarkers[i].first));
        }
        upperValues[i] += currentMarkers[i].second;
    }

    // 최종 업데이트
    for (int i = 0; i < markerSize; i++) {
        updateCombined(currentMarkers[i].first, max(upperValues[i], lowerValues[i]));
    }
}

int main() {
    scanf("%d %d %d %d", &numPoints, &upCost, &downCost, &startPoint);

    // 입력 처리
    for (int i = 0, x, y, z; i < numPoints; i++) {
        scanf("%d %d %d", &x, &y, &z);
        markers[x].emplace_back(y, z);
    }

    // 트리 초기화
    memset(upTree, 0xc0, sizeof(upTree));
    memset(downTree, 0xc0, sizeof(downTree));
    updateCombined(startPoint, 0);

    // 마커 처리
    for (int i = 1; i <= 500001; i++) {
        processMarkers(markers[i]);
    }

    // 결과 출력
    printf("%d", calculateMax(startPoint));
    return 0;
}
