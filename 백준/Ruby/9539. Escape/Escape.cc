#include <bits/stdc++.h>
#define fi first
#define se second
#define em emplace
#define eb emplace_back
using namespace std;
typedef long long ll;
typedef vector<int> vi;
const ll INF = 1ll << 60;

// 노드 정보를 담는 구조체
struct Node {
    int parent, id;      // parent: 부모 노드, id: 노드 번호
    ll attack, defense;  // attack: 체력 감소량, defense: 체력 증가량
    bool operator <(const Node &other) const {
        // 체력 증가와 감소를 기반으로 정렬
        bool positiveSelf = (attack + defense >= 0);
        bool positiveOther = (other.attack + other.defense >= 0);
        if (positiveSelf && positiveOther) return tie(attack, id) < tie(other.attack, other.id);
        if (!positiveSelf && !positiveOther) return tie(defense, id) < tie(other.defense, other.id);
        return positiveOther;
    }
} nodes[200010];

// Union-Find 자료구조
struct UnionFind {
    int parent[200010];

    // 초기화
    void init() { memset(parent, 0, sizeof(parent)); }

    // 특정 노드의 루트 찾기
    int find(int x) { return parent[x] ? (parent[x] = find(parent[x])) : x; }

    // 두 노드 병합
    void unite(int x, int y) {
        x = find(x);
        y = find(y);
        parent[y] = x;
    }
} uf;

// 문제를 위한 전역 변수
int testCases, numNodes, targetNode;
vi graph[200010];

// 트리 구조를 구성하는 DFS
void buildTree(int node, int parent) {
    nodes[node].parent = parent;
    for (auto &neighbor : graph[node]) {
        if (neighbor != parent) buildTree(neighbor, node);
    }
}

// 문제 해결 함수
void solve() {
    uf.init();
    scanf("%d %d", &numNodes, &targetNode);

    // 노드 초기화
    for (int i = 1; i <= numNodes; i++) graph[i].clear(), nodes[i].id = i;

    // 체력 변화량 입력
    for (int i = 1, hpChange; i <= numNodes; i++) {
        scanf("%d", &hpChange);
        if (hpChange >= 0) {
            nodes[i].defense = hpChange;
            nodes[i].attack = 0;
        } else {
            nodes[i].attack = hpChange;
            nodes[i].defense = 0;
        }
    }

    // 그래프 간선 입력
    for (int i = 2, u, v; i <= numNodes; i++) {
        scanf("%d %d", &u, &v);
        graph[u].eb(v);
        graph[v].eb(u);
    }

    // 탈출 방 초기화
    nodes[targetNode].defense = INF;

    // 트리 구조 구성
    buildTree(1, 0);

    // 정렬 기반 탐색을 위한 집합
    set<Node> activeNodes;
    for (int i = 2; i <= numNodes; i++) activeNodes.em(nodes[i]);

    // 노드 병합 및 탐색
    while (!activeNodes.empty()) {
        auto currentNode = *activeNodes.rbegin();
        activeNodes.erase(currentNode);

        int parentNode = uf.find(currentNode.parent);
        uf.unite(parentNode, currentNode.id);
        activeNodes.erase(nodes[parentNode]);

        ll minAttack = min(nodes[parentNode].attack,
                           nodes[parentNode].attack + nodes[parentNode].defense + currentNode.attack);
        ll remainingDefense = nodes[parentNode].attack + nodes[parentNode].defense +
                              currentNode.attack + currentNode.defense - minAttack;
        tie(nodes[parentNode].attack, nodes[parentNode].defense) = tie(minAttack, remainingDefense);

        if (parentNode != 1) activeNodes.em(nodes[parentNode]);
    }

    // 탈출 가능 여부 출력
    printf("%s\n", nodes[1].attack < 0 ? "trapped" : "escaped");
}

int main() {
    scanf("%d", &testCases);
    while (testCases--) solve();
    return 0;
}
