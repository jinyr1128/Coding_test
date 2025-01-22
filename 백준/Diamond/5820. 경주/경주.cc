#include<bits/stdc++.h>
using namespace std;
using ll = long long;
const int MAX_NODES = 200000;
const int MAX_DIST = 1000000;
const int INF = 1e9;

int subtree_size[MAX_NODES + 5];
bool is_visited[MAX_NODES + 5];
int min_depth[MAX_DIST + 5];
vector<int> active_distances;
vector<vector<pair<int, int>>> adjacency_list(MAX_NODES + 5);
int node_count, target_length;

// 서브트리의 크기를 계산
int compute_subtree_size(int node, int parent) {
    subtree_size[node] = 1;
    for (const auto& edge : adjacency_list[node]) {
        int neighbor = edge.first;
        if (neighbor == parent || is_visited[neighbor]) continue;
        subtree_size[node] += compute_subtree_size(neighbor, node);
    }
    return subtree_size[node];
}

// 서브트리의 중심 노드를 찾음
int find_centroid(int node, int parent, int threshold) {
    for (const auto& edge : adjacency_list[node]) {
        int neighbor = edge.first;
        if (neighbor == parent || is_visited[neighbor]) continue;
        if (subtree_size[neighbor] > threshold) 
            return find_centroid(neighbor, node, threshold);
    }
    return node;
}

// 특정 경로에 대한 최소 깊이 계산
int compute_min_depth(int node, int parent, int distance, int depth) {
    int result = INF;
    if (distance > target_length) return result;
    result = min(result, min_depth[target_length - distance] + depth);
    for (const auto& edge : adjacency_list[node]) {
        int neighbor = edge.first;
        int weight = edge.second;
        if (neighbor == parent || is_visited[neighbor]) continue;
        result = min(result, compute_min_depth(neighbor, node, distance + weight, depth + 1));
    }
    return result;
}

// 경로 정보를 업데이트
void update_paths(int node, int parent, int distance, int depth) {
    if (distance > target_length) return;
    min_depth[distance] = min(min_depth[distance], depth);
    active_distances.push_back(distance);
    for (const auto& edge : adjacency_list[node]) {
        int neighbor = edge.first;
        int weight = edge.second;
        if (neighbor == parent || is_visited[neighbor]) continue;
        update_paths(neighbor, node, distance + weight, depth + 1);
    }
}

// 분할 정복 알고리즘
int decompose_and_conquer(int node) {
    int threshold = compute_subtree_size(node, -1);
    int centroid = find_centroid(node, -1, threshold / 2);
    is_visited[centroid] = true;
    int result = INF;

    for (const auto& distance : active_distances) 
        min_depth[distance] = INF;

    active_distances.clear();
    min_depth[0] = 0;

    for (const auto& edge : adjacency_list[centroid]) {
        int neighbor = edge.first;
        int weight = edge.second;
        if (!is_visited[neighbor]) {
            result = min(result, compute_min_depth(neighbor, centroid, weight, 1));
            update_paths(neighbor, centroid, weight, 1);
        }
    }

    for (const auto& edge : adjacency_list[centroid]) {
        int neighbor = edge.first;
        if (!is_visited[neighbor]) {
            result = min(result, decompose_and_conquer(neighbor));
        }
    }

    return result;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> node_count >> target_length;
    for (int i = 0; i < node_count - 1; ++i) {
        int u, v, w;
        cin >> u >> v >> w;
        adjacency_list[u].emplace_back(v, w);
        adjacency_list[v].emplace_back(u, w);
    }

    fill(begin(min_depth), end(min_depth), INF);
    int answer = decompose_and_conquer(0);
    cout << (answer == INF ? -1 : answer) << '\n';

    return 0;
}
