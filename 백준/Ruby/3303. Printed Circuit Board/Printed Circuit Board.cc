#include <iostream>
#include <algorithm>
#include <vector>

typedef long long ll;
const int MAX_NODES = 200'001;

// 구조체 정의: 좌표를 나타냄
struct Node {
    ll x, y; // 좌표 값
    int index; // 노드의 번호
    ll squaredDistance() const { return x * x + y * y; } // 원점에서의 제곱 거리 계산
    bool operator<(const Node& other) const { return y * other.x < other.y * x; } // 기울기 비교
    bool operator==(const Node& other) const { return y * other.x == other.y * x; } // 기울기 동일 여부
    friend std::istream& operator>>(std::istream& is, Node& p) { is >> p.x >> p.y; return is; } // 입력 연산자 오버로딩
};

// 전역 상수와 변수
Node origin = {0, 0}; // 원점 좌표
Node points[MAX_NODES]; // 노드들의 좌표 배열
int totalNodes, leftmostIdx, rightmostIdx; // 총 노드 수, 왼쪽 및 오른쪽 끝의 인덱스

// 외적 계산 함수
ll crossProduct(const Node& p1, const Node& p2, const Node& p3) {
    return (p2.x - p1.x) * (p3.y - p2.y) - (p2.y - p1.y) * (p3.x - p2.x);
}

// CCW 판단 함수
ll ccw(const Node& p1, const Node& p2, const Node& p3) {
    ll result = crossProduct(p1, p2, p3);
    return result < 0 ? -1 : !!result; // 음수면 시계 방향, 양수면 반시계 방향
}

// 가시성 확인 함수
bool isVisible(const Node& start, const Node& end, const Node& target) {
    return ccw(origin, start, target) <= 0 && ccw(origin, end, target) >= 0 && ccw(start, end, target) >= 0;
}

int main() {
    std::cin.tie(0)->sync_with_stdio(0);
    std::cin >> totalNodes >> points[0]; // 총 노드 수와 첫 번째 노드 입력

    points[0].index = 1;
    ll areaSum = 0;

    // 각 노드 입력 및 폴리곤 면적 계산
    for (int i = 1; i < totalNodes; ++i) {
        std::cin >> points[i];
        points[i].index = i + 1;
        areaSum += crossProduct(origin, points[i - 1], points[i]);
    }
    areaSum += crossProduct(origin, points[totalNodes - 1], points[0]);

    // 면적이 양수면 노드를 반대로 정렬
    if (areaSum > 0) std::reverse(points, points + totalNodes);

    // 왼쪽 및 오른쪽 끝점 찾기
    for (int i = 1; i < totalNodes; ++i) {
        if (points[leftmostIdx] < points[i] || (points[i] == points[leftmostIdx] && points[i].squaredDistance() < points[leftmostIdx].squaredDistance())) 
            leftmostIdx = i;
        if (points[i] < points[rightmostIdx] || (points[i] == points[rightmostIdx] && points[i].squaredDistance() < points[rightmostIdx].squaredDistance())) 
            rightmostIdx = i;
    }

    // 오른쪽 끝점을 시작점으로 정렬
    std::rotate(points, points + rightmostIdx, points + totalNodes);
    leftmostIdx = (leftmostIdx - rightmostIdx + totalNodes) % totalNodes;

    std::vector<int> monotonicStack; // 모노토닉 스택
    monotonicStack.push_back(0);
    if (leftmostIdx > 1) monotonicStack.push_back(1);

    for (int i = 2, forwardVisible = -1, backwardVisible = -1, direction = 0; i <= leftmostIdx; ++i) {
        ll dir = crossProduct(origin, points[i - 1], points[i]);
        ll orientation = crossProduct(points[i - 2], points[i - 1], points[i]);

        if (forwardVisible == -1 && backwardVisible == -1) {
            if (dir < 0) { // 시계 방향
                if (!direction && orientation < 0) {
                    direction = 1;
                    forwardVisible = i - 1;
                    continue;
                }
                direction = 1;
                while (!monotonicStack.empty() && isVisible(points[i - 1], points[i], points[monotonicStack.back()])) 
                    monotonicStack.pop_back();
            } else if (dir == 0) {
                if (points[i].squaredDistance() < points[i - 1].squaredDistance()) {
                    if (monotonicStack.back() == i - 1) 
                        monotonicStack.pop_back();
                }
            } else if (dir > 0) { // 반시계 방향
                if (direction && orientation > 0) {
                    direction = 0;
                    backwardVisible = i - 1;
                    continue;
                }
                direction = 0;
            }
        } else if (forwardVisible != -1) {
            if (!(points[forwardVisible] < points[i])) continue; 
            direction = 0;
            forwardVisible = -1;
        } else if (backwardVisible != -1) {
            if (!(points[i] < points[backwardVisible])) continue;
            direction = 1;
            backwardVisible = -1;
            while (!monotonicStack.empty() && isVisible(points[i - 1], points[i], points[monotonicStack.back()])) 
                monotonicStack.pop_back();
        }
        if (monotonicStack.empty() || points[monotonicStack.back()] < points[i]) 
            monotonicStack.push_back(i);
    }

    std::vector<int> resultNodes;
    for (const int& index : monotonicStack) 
        resultNodes.push_back(points[index].index);

    std::sort(resultNodes.begin(), resultNodes.end());
    std::cout << resultNodes.size() << '\n';
    for (const int& node : resultNodes) 
        std::cout << node << ' ';
}
