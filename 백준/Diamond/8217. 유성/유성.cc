#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX = 300000; // 최대 입력 크기 제한

// 펜윅 트리 (Binary Indexed Tree) 구조체 정의
struct BITree {
    int treeSize; // 트리 크기
    long long bitArr[MAX + 1]; // 트리 배열
    
    // 기본 생성자
    BITree() : BITree(1) {}

    // 크기 초기화 생성자
    BITree(int size) : treeSize(size) {
        fill(bitArr, bitArr + MAX + 1, 0);
    }

    // 특정 위치에 값을 추가하는 함수
    void addValue(int index, int value) {
        while (index <= treeSize) {
            bitArr[index] += value;
            index += index & -index;
        }
    }

    // 특정 위치까지의 합을 구하는 함수
    long long getPrefixSum(int index) {
        long long sum = 0;
        while (index > 0) {
            sum += bitArr[index];
            index -= index & -index;
        }
        return sum;
    }
};

int main() {
    int nations, sectors, requiredSamples[MAX], meteorEvents[MAX][3];
    vector<int> ownership[MAX]; // 각 국가가 소유한 구역 정보

    // 입력: 국가 수 N, 구역 수 M
    scanf("%d %d", &nations, &sectors);

    // 각 구역이 소속된 국가 정보 입력
    for (int i = 0; i < sectors; ++i) {
        int owner;
        scanf("%d", &owner);
        ownership[owner - 1].push_back(i); // 국가별로 구역 저장
    }

    // 각 국가의 목표 샘플 수 입력
    for (int i = 0; i < nations; ++i) {
        scanf("%d", &requiredSamples[i]);
    }

    // 유성우 이벤트 입력
    int eventCount;
    scanf("%d", &eventCount);
    for (int i = 0; i < eventCount; ++i) {
        scanf("%d %d %d", &meteorEvents[i][0], &meteorEvents[i][1], &meteorEvents[i][2]);
    }

    // 이분 탐색을 위한 하한, 상한 초기화
    int low[MAX] = {0}, high[MAX];
    fill(high, high + nations, eventCount + 1);

    // 이분 탐색 (Parallel Binary Search)
    while (true) {
        bool isUpdated = false;
        vector<int> nationGroups[eventCount + 1]; // 미드 값 기준 그룹화

        // 이분 탐색 범위 조정
        for (int i = 0; i < nations; ++i) {
            if (low[i] + 1 < high[i]) {
                isUpdated = true;
                nationGroups[(low[i] + high[i]) / 2].push_back(i);
            }
        }

        if (!isUpdated) break; // 모든 국가가 수렴한 경우 반복 종료

        BITree bit(sectors); // 새로운 펜윅 트리 초기화

        // 각 날짜별로 이벤트 처리
        for (int day = 0; day < eventCount; ++day) {
            // 유성우 이벤트 적용
            if (meteorEvents[day][0] <= meteorEvents[day][1]) {
                bit.addValue(meteorEvents[day][0], meteorEvents[day][2]);
                bit.addValue(meteorEvents[day][1] + 1, -meteorEvents[day][2]);
            } else { // 원형 순환 처리
                bit.addValue(1, meteorEvents[day][2]);
                bit.addValue(meteorEvents[day][1] + 1, -meteorEvents[day][2]);
                bit.addValue(meteorEvents[day][0], meteorEvents[day][2]);
            }

            // 해당 날짜 기준으로 국가들의 목표 샘플 수 달성 여부 확인
            for (int nation : nationGroups[day + 1]) {
                long long collectedSamples = 0;
                for (int sector : ownership[nation]) {
                    collectedSamples += bit.getPrefixSum(sector + 1);
                    if (collectedSamples >= requiredSamples[nation]) break;
                }

                // 목표 샘플 수량 달성 여부에 따라 이분 탐색 범위 조정
                if (collectedSamples >= requiredSamples[nation]) {
                    high[nation] = day + 1;
                } else {
                    low[nation] = day + 1;
                }
            }
        }
    }

    // 결과 출력
    for (int i = 0; i < nations; ++i) {
        if (low[i] == eventCount) {
            puts("NIE"); // 목표 수량 달성 불가
        } else {
            printf("%d\n", high[i]); // 달성 가능한 최소 날짜 출력
        }
    }

    return 0;
}