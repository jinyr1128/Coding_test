#include<bits/stdc++.h>
using namespace std;

array<vector<int>, 100'001> treeAdj; // 트리의 인접 리스트
array<int, 100'001> entryTime, subTreeSize; // 방문 시간과 서브트리 크기

// DFS를 통해 각 노드의 방문 시간과 서브트리 크기 계산
void calculateSubtree(int parent, int current, int& clock) {
    entryTime[current] = clock++;
    subTreeSize[current] = 1;
    for (int neighbor : treeAdj[current]) {
        if (neighbor != parent) {
            calculateSubtree(current, neighbor, clock);
            subTreeSize[current] += subTreeSize[neighbor];
        }
    }
}

// 세그먼트 트리 클래스 정의
class SegmentTree {
    int size;
    vector<int> count, value;

    void updateRange(int node, int nodeStart, int nodeEnd, int rangeStart, int rangeEnd, int delta) {
        int nodeMid = (nodeStart + nodeEnd) >> 1;
        if (nodeStart > rangeEnd || nodeEnd < rangeStart) return;
        if (rangeStart <= nodeStart && nodeEnd <= rangeEnd) {
            count[node] += delta;
        } else {
            updateRange(node << 1, nodeStart, nodeMid, rangeStart, rangeEnd, delta);
            updateRange(node << 1 | 1, nodeMid + 1, nodeEnd, rangeStart, rangeEnd, delta);
        }
        if (count[node]) value[node] = nodeEnd - nodeStart + 1;
        else if (nodeStart != nodeEnd) value[node] = value[node << 1] + value[node << 1 | 1];
        else value[node] = 0;
    }

public:
    SegmentTree(int n) {
        size = 1;
        while (size < n) size *= 2;
        count.resize(size * 2);
        value.resize(size * 2);
    }

    void update(int rangeStart, int rangeEnd, int delta) {
        if (rangeStart > rangeEnd) return;
        updateRange(1, 0, size - 1, rangeStart, rangeEnd, delta);
    }

    int query() { return value[1]; }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int nodeCount;
    cin >> nodeCount;

    vector<pair<int, int>> directedEdges;
    for (int i = 1; i < nodeCount; ++i) {
        int u, v;
        string direction;
        cin >> u >> direction >> v;
        treeAdj[u].emplace_back(v);
        treeAdj[v].emplace_back(u);
        if (direction[0] == '<') directedEdges.emplace_back(v, u);
        if (direction[1] == '>') directedEdges.emplace_back(u, v);
    }

    int clock = 0;
    calculateSubtree(0, 1, clock);

    SegmentTree segmentTree(nodeCount);
    set<pair<int, int>> edgeSet;

    for (const auto& [x, y] : directedEdges) {
        if (subTreeSize[x] > subTreeSize[y]) segmentTree.update(entryTime[y], entryTime[y] + subTreeSize[y] - 1, 1);
        else {
            segmentTree.update(0, entryTime[x] - 1, 1);
            segmentTree.update(entryTime[x] + subTreeSize[x], nodeCount - 1, 1);
        }
        edgeSet.emplace(x, y);
    }

    int queryCount;
    cin >> queryCount;
    while (queryCount--) {
        int u, v;
        string direction;
        cin >> u >> direction >> v;

        if (edgeSet.find({u, v}) != edgeSet.end()) {
            if (subTreeSize[u] > subTreeSize[v]) segmentTree.update(entryTime[v], entryTime[v] + subTreeSize[v] - 1, -1);
            else {
                segmentTree.update(0, entryTime[u] - 1, -1);
                segmentTree.update(entryTime[u] + subTreeSize[u], nodeCount - 1, -1);
            }
            edgeSet.erase({u, v});
        } else if (edgeSet.find({v, u}) != edgeSet.end()) {
            if (subTreeSize[v] > subTreeSize[u]) segmentTree.update(entryTime[u], entryTime[u] + subTreeSize[u] - 1, -1);
            else {
                segmentTree.update(0, entryTime[v] - 1, -1);
                segmentTree.update(entryTime[v] + subTreeSize[v], nodeCount - 1, -1);
            }
            edgeSet.erase({v, u});
        }

        if (direction.compare("--")) {
            if (direction[0] == '<') swap(u, v);
            if (subTreeSize[u] > subTreeSize[v]) segmentTree.update(entryTime[v], entryTime[v] + subTreeSize[v] - 1, 1);
            else {
                segmentTree.update(0, entryTime[u] - 1, 1);
                segmentTree.update(entryTime[u] + subTreeSize[u], nodeCount - 1, 1);
            }
            edgeSet.emplace(u, v);
        }
        cout << nodeCount - segmentTree.query() << '\n';
    }

    return 0;
}
