#include <iostream>
#include <vector>
#include <algorithm>

const int MAX_NODES = 1000; // 최대 노드 수

std::vector<int> adjacencyList[MAX_NODES]; // 인접 리스트
int matched[MAX_NODES]; // 매칭된 노드
bool visited[MAX_NODES]; // 방문 여부
int nodeCount, edgeCount, fromNode, toNode; // 입력 변수

// DFS를 통해 최대 매칭을 찾는 함수
bool findMatch(int node) {
    for (const int& neighbor : adjacencyList[node]) {
        if (visited[neighbor]) continue; // 이미 방문한 노드는 스킵
        visited[neighbor] = true;

        // 매칭되지 않았거나, 재귀적으로 다른 매칭을 찾을 수 있다면 매칭
        if (matched[neighbor] == -1 || findMatch(matched[neighbor])) {
            matched[neighbor] = node;
            return true;
        }
    }
    return false;
}

int main() {
    int testCases; // 테스트 케이스 수
    std::cin >> testCases;

    while (testCases--) {
        // 초기화
        for (std::vector<int>& neighbors : adjacencyList) neighbors.clear();
        std::fill(matched, matched + MAX_NODES, -1);

        int matchCount = 0; // 최대 매칭 수
        std::cin >> nodeCount >> edgeCount;

        // 간선 입력 처리
        while (edgeCount--) {
            std::cin >> fromNode >> toNode;
            adjacencyList[fromNode].push_back(toNode);
        }

        // 각 노드에 대해 매칭 시도
        for (int node = 0; node < nodeCount; ++node) {
            std::fill(visited, visited + MAX_NODES, false);
            if (findMatch(node)) ++matchCount; // 매칭 성공 시 증가
        }

        std::cout << matchCount << '\n'; // 결과 출력
    }
}
