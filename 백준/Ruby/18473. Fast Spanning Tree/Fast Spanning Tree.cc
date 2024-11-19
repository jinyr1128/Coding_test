#include <bits/stdc++.h>
using namespace std;

// Union-Find 및 그래프 정보
long long parent[1010101]; // Union-Find의 부모 노드 배열
long long componentSize[1010101]; // 각 컴포넌트의 크기
long long componentWeight[1010101]; // 각 컴포넌트의 총 가중치

// 간선 정보를 저장하는 구조체
struct Edge {
    long long targetNode, weight, threshold, edgeIndex;
};

// 정렬 기준: Threshold 기반으로 우선순위 큐 정렬
struct CompareByThreshold {
    bool operator()(Edge &a, Edge &b) {
        return a.threshold > b.threshold;
    }
};

// 정렬 기준: Index 기반으로 우선순위 큐 정렬
struct CompareByIndex {
    bool operator()(Edge &a, Edge &b) {
        return a.edgeIndex > b.edgeIndex;
    }
};

// 결과를 저장할 큐
queue<long long> edgeIndices;

// 각 노드의 간선을 저장하는 우선순위 큐 배열
priority_queue<Edge, vector<Edge>, CompareByThreshold> nodePriorityQueue[303030];

// 전체 간선 정보를 저장하는 우선순위 큐
priority_queue<Edge, vector<Edge>, CompareByIndex> globalPriorityQueue;

// Union-Find의 루트 노드를 찾는 함수
long long findRoot(long long node) {
    if (parent[node] == node) return node;
    return parent[node] = findRoot(parent[node]);
}

// 두 컴포넌트를 병합하는 함수
long long mergeComponents(long long a, long long b) {
    a = findRoot(a);
    b = findRoot(b);

    // 작은 큐를 큰 큐로 병합하여 효율성을 유지
    if (nodePriorityQueue[a].size() <= nodePriorityQueue[b].size()) {
        parent[a] = b;
        componentWeight[b] += componentWeight[a];
        while (!nodePriorityQueue[a].empty()) {
            auto edge = nodePriorityQueue[a].top();
            nodePriorityQueue[a].pop();
            if (findRoot(edge.targetNode) == b) continue;
            nodePriorityQueue[b].push(edge);
        }
        return b;
    } else {
        parent[b] = a;
        componentWeight[a] += componentWeight[b];
        while (!nodePriorityQueue[b].empty()) {
            auto edge = nodePriorityQueue[b].top();
            nodePriorityQueue[b].pop();
            if (findRoot(edge.targetNode) == a) continue;
            nodePriorityQueue[a].push(edge);
        }
        return a;
    }
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);

    long long numVertices, numEdges;
    cin >> numVertices >> numEdges;

    // 간단한 종료 조건 처리
    if (numVertices == 1) {
        while (1);
    }

    // 각 노드의 초기 가중치 입력 및 Union-Find 초기화
    for (long long i = 1; i <= numVertices; i++) {
        cin >> componentWeight[i];
        parent[i] = i;
    }

    // 간선 정보 입력
    for (long long i = 1; i <= numEdges; i++) {
        long long nodeA, nodeB, edgeWeight;
        cin >> nodeA >> nodeB >> edgeWeight;

        // 두 노드의 현재 가중치 합이 조건을 만족하면 바로 처리
        if (componentWeight[nodeA] + componentWeight[nodeB] >= edgeWeight) {
            globalPriorityQueue.push({nodeA, nodeB, edgeWeight, i});
            continue;
        }

        // 그렇지 않으면 각 노드의 우선순위 큐에 간선 추가
        nodePriorityQueue[nodeA].push({
            nodeB,
            edgeWeight,
            componentWeight[nodeA] + (edgeWeight - componentWeight[nodeA] - componentWeight[nodeB] + 1) / 2,
            i
        });

        nodePriorityQueue[nodeB].push({
            nodeA,
            edgeWeight,
            componentWeight[nodeB] + (edgeWeight - componentWeight[nodeA] - componentWeight[nodeB] + 1) / 2,
            i
        });
    }

    // 간선 처리
    while (!globalPriorityQueue.empty()) {
        auto edge = globalPriorityQueue.top();
        globalPriorityQueue.pop();

        long long nodeA = edge.targetNode;
        long long nodeB = edge.weight;

        // 이미 연결된 컴포넌트는 무시
        if (findRoot(nodeA) == findRoot(nodeB)) continue;

        // 간선 추가
        edgeIndices.push(edge.edgeIndex);

        // 컴포넌트 병합
        long long mergedRoot = mergeComponents(nodeA, nodeB);

        // 병합된 컴포넌트의 간선 처리
        while (!nodePriorityQueue[mergedRoot].empty()) {
            auto candidateEdge = nodePriorityQueue[mergedRoot].top();
            candidateEdge.targetNode = findRoot(candidateEdge.targetNode);

            if (findRoot(mergedRoot) == findRoot(candidateEdge.targetNode)) {
                nodePriorityQueue[mergedRoot].pop();
                continue;
            }

            if (componentWeight[mergedRoot] < candidateEdge.threshold) break;

            nodePriorityQueue[mergedRoot].pop();

            if (candidateEdge.weight <= componentWeight[mergedRoot] + componentWeight[candidateEdge.targetNode]) {
                globalPriorityQueue.push({
                    mergedRoot,
                    candidateEdge.targetNode,
                    candidateEdge.weight,
                    candidateEdge.edgeIndex
                });
            } else {
                nodePriorityQueue[candidateEdge.targetNode].push({
                    mergedRoot,
                    candidateEdge.weight,
                    componentWeight[candidateEdge.targetNode] + (candidateEdge.weight - componentWeight[candidateEdge.targetNode] - componentWeight[mergedRoot] + 1) / 2,
                    candidateEdge.edgeIndex
                });

                nodePriorityQueue[mergedRoot].push({
                    candidateEdge.targetNode,
                    candidateEdge.weight,
                    componentWeight[mergedRoot] + (candidateEdge.weight - componentWeight[candidateEdge.targetNode] - componentWeight[mergedRoot] + 1) / 2,
                    candidateEdge.edgeIndex
                });
            }
        }
    }

    // 결과 출력
    cout << edgeIndices.size() << '\n';
    while (!edgeIndices.empty()) {
        cout << edgeIndices.front() << ' ';
        edgeIndices.pop();
    }
}
