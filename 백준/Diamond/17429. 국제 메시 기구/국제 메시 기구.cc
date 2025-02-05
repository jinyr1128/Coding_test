#include<bits/stdc++.h>
#define RANGE int node, int start, int end
#define ll unsigned int
#define MAX_NODES 500001
using namespace std;
 
vector<int> treeGraph[MAX_NODES], heavyLightTree[MAX_NODES];
ll segmentTree[1 << 20], lazy[1 << 20][2];
int chainHead[MAX_NODES], entryIndex[MAX_NODES], exitIndex[MAX_NODES];
int subtreeSize[MAX_NODES], parent[MAX_NODES], depth[MAX_NODES];
int totalNodes, totalQueries, nodeCounter;
 
void initialize() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    cin >> totalNodes >> totalQueries;
    for (int i = 0; i < totalNodes - 1; i++) {
        int u, v; cin >> u >> v;
        treeGraph[u].push_back(v);
        treeGraph[v].push_back(u);
    }
}

void dfsSubtreeSize(int node) {
    subtreeSize[node] = 1;
    for (int &child : treeGraph[node]) {
        if (!subtreeSize[child]) {
            depth[child] = depth[node] + 1;
            parent[child] = node;
            dfsSubtreeSize(child);
            subtreeSize[node] += subtreeSize[child];
            heavyLightTree[node].push_back(child);
            if (subtreeSize[child] > subtreeSize[heavyLightTree[node][0]]) 
                swap(heavyLightTree[node][0], heavyLightTree[node].back());
        }
    }
}

void dfsHLD(int node) {
    entryIndex[node] = ++nodeCounter;
    for (int &child : heavyLightTree[node]) {
        chainHead[child] = (child == heavyLightTree[node][0]) ? chainHead[node] : child;
        dfsHLD(child);
    }
    exitIndex[node] = nodeCounter;
}

void propagateLazy(RANGE) {
    if (lazy[node][0] == 1 && !lazy[node][1]) return;
    segmentTree[node] *= lazy[node][0];
    segmentTree[node] += (end - start + 1) * lazy[node][1];
    if (start != end) {
        lazy[node << 1][0] *= lazy[node][0];
        lazy[node << 1][1] *= lazy[node][0];
        lazy[node << 1][1] += lazy[node][1];
        
        lazy[node << 1 | 1][0] *= lazy[node][0];
        lazy[node << 1 | 1][1] *= lazy[node][0];
        lazy[node << 1 | 1][1] += lazy[node][1];
    }
    lazy[node][0] = 1, lazy[node][1] = 0;
}

ll updateSegmentTree(RANGE, int left, int right, ll multiplier, ll increment) {
    propagateLazy(node, start, end);
    if (start > right || end < left) return segmentTree[node];
    if (start >= left && end <= right) {
        lazy[node][0] = multiplier;
        lazy[node][1] = increment;
        propagateLazy(node, start, end);
        return segmentTree[node];
    }
    int mid = (start + end) >> 1;
    return segmentTree[node] = updateSegmentTree(node << 1, start, mid, left, right, multiplier, increment) +
                               updateSegmentTree(node << 1 | 1, mid + 1, end, left, right, multiplier, increment);
}

ll querySegmentTree(RANGE, int left, int right) {
    propagateLazy(node, start, end);
    if (start > right || end < left) return 0;
    if (start >= left && end <= right) return segmentTree[node];
    int mid = (start + end) >> 1;
    return querySegmentTree(node << 1, start, mid, left, right) +
           querySegmentTree(node << 1 | 1, mid + 1, end, left, right);
}

void updateHLD(int u, int v, int multiplier, int increment) {
    while (chainHead[u] != chainHead[v]) {
        if (depth[chainHead[u]] < depth[chainHead[v]]) swap(u, v);
        updateSegmentTree(1, 1, totalNodes, entryIndex[chainHead[u]], entryIndex[u], multiplier, increment);
        u = parent[chainHead[u]];
    }
    if (depth[u] > depth[v]) swap(u, v);
    updateSegmentTree(1, 1, totalNodes, entryIndex[u], entryIndex[v], multiplier, increment);
}

ll queryHLD(int u, int v) {
    ll result = 0;
    while (chainHead[u] != chainHead[v]) {
        if (depth[chainHead[u]] < depth[chainHead[v]]) swap(u, v);
        result += querySegmentTree(1, 1, totalNodes, entryIndex[chainHead[u]], entryIndex[u]);
        u = parent[chainHead[u]];
    }
    if (depth[u] > depth[v]) swap(u, v);
    return result + querySegmentTree(1, 1, totalNodes, entryIndex[u], entryIndex[v]);
}

void processQueries() {
    while (totalQueries--) {
        int type, u, v, multiplier, increment;
        cin >> type >> u;
        if (type == 1) {
            cin >> increment;
            updateSegmentTree(1, 1, totalNodes, entryIndex[u], exitIndex[u], 1, increment);
        } else if (type == 2) {
            cin >> v >> increment;
            updateHLD(u, v, 1, increment);
        } else if (type == 3) {
            cin >> multiplier;
            updateSegmentTree(1, 1, totalNodes, entryIndex[u], exitIndex[u], multiplier, 0);
        } else if (type == 4) {
            cin >> v >> multiplier;
            updateHLD(u, v, multiplier, 0);
        } else if (type == 5) {
            cout << querySegmentTree(1, 1, totalNodes, entryIndex[u], exitIndex[u]) << '\n';
        } else {
            cin >> v;
            cout << queryHLD(u, v) << '\n';
        }
    }
}

int main() {
    initialize();
    dfsSubtreeSize(1);
    dfsHLD(1);
    processQueries();
}
