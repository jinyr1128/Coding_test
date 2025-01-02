#include <cstdio>
#include <vector>
using namespace std;

// 사이클을 처리하기 위한 구조체
struct Cycle {
    int start, mid, end;
};

// 노드 수와 간선 수
int numNodes, numEdges;
// 인접 리스트
vector<int> graph[100001];
// 방문 여부와 탐색 종료 여부
bool visited[100001];
bool completed[100001];
// 결과를 저장할 벡터
vector<Cycle> cycles;

// DFS를 수행하여 사이클을 처리하는 함수
bool performDFS(int currentNode, int parentNode) {
    vector<int> children;
    visited[currentNode] = true;

    // 인접 노드 탐색
    for (int neighbor : graph[currentNode]) {
        if (neighbor == parentNode) continue; // 부모 노드는 건너뜀
        if (visited[neighbor]) {
            if (!completed[neighbor]) {
                // 아직 완료되지 않은 노드는 사이클의 일부로 간주
                children.push_back(neighbor);
            }
        } else if (!performDFS(neighbor, currentNode)) {
            // DFS 결과를 기반으로 자식 노드 추가
            children.push_back(neighbor);
        }
    }

    completed[currentNode] = true;

    // 자식 노드 쌍을 처리
    for (int i = 1; i < children.size(); i += 2) {
        cycles.push_back({children[i - 1], currentNode, children[i]});
    }

    // 홀수 개의 자식 노드가 남으면 부모 노드와 함께 처리
    if ((children.size() & 1) && parentNode) {
        cycles.push_back({children.back(), currentNode, parentNode});
        return true;
    }
    return false;
}

int main() {
    // 입력 처리
    scanf("%d%d", &numNodes, &numEdges);
    for (int i = 0; i < numEdges; i++) {
        int node1, node2;
        scanf("%d%d", &node1, &node2);
        graph[node1].emplace_back(node2);
        graph[node2].emplace_back(node1);
    }

    // 모든 노드에 대해 DFS 수행
    for (int i = 1; i <= numNodes; i++) {
        if (!visited[i]) {
            performDFS(i, 0);
        }
    }

    // 결과 출력
    printf("%lu\n", cycles.size());
    for (auto &cycle : cycles) {
        printf("%d %d %d\n", cycle.start, cycle.mid, cycle.end);
    }
}
