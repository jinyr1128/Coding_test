#include <iostream>

using namespace std;

// 변수 정의
int totalCards;                // 총 카드 수
int positionTracker[4];        // 각 기술에 따른 현재 위치 추적
int result[1000001] = {0};     // 초기 카드 상태를 저장할 배열
int isOccupied[1000001] = {0}; // 해당 위치에 카드가 놓였는지 여부를 기록

// 위치 업데이트 함수
void updatePosition(int technique) {
    if (technique == 1 || technique == 2) {
        // 기술 1과 2는 다음 빈 위치를 찾음
        do {
            positionTracker[technique]++;
        } while (isOccupied[positionTracker[technique]]);
    } else {
        // 기술 3은 이전 빈 위치를 찾음
        do {
            positionTracker[technique]--;
        } while (isOccupied[positionTracker[technique]]);
    }
}

int main() {
    cin >> totalCards;

    // 초기 기술 위치 설정
    positionTracker[1] = 1;            // 기술 1: 맨 위
    positionTracker[2] = 2;            // 기술 2: 두 번째
    positionTracker[3] = totalCards;   // 기술 3: 맨 아래

    // 기술을 역순으로 처리
    for (int i = totalCards; i >= 1; i--) {
        int technique;
        cin >> technique;

        // 현재 기술의 위치에 카드 배치
        isOccupied[positionTracker[technique]] = i;

        // 기술에 따라 위치 갱신
        switch (technique) {
            case 1:
                updatePosition(1); // 기술 1의 다음 위치 갱신
                updatePosition(2); // 기술 2의 다음 위치 갱신
                break;
            case 2:
                updatePosition(2); // 기술 2의 다음 위치 갱신
                break;
            case 3:
                updatePosition(3); // 기술 3의 이전 위치 갱신
                break;
        }
    }

    // 결과 출력
    for (int i = 1; i <= totalCards; i++) {
        cout << isOccupied[i] << " ";
    }

    return 0;
}
