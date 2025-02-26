#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int main() {
    int cityCount, bridgeCount;
    cin >> cityCount >> bridgeCount;

    struct Connection { int destination, index; };
    vector<vector<Connection>> roadNetwork(cityCount);
    
    for (int i = 0; i < bridgeCount; ++i) {
        int cityA, cityB;
        cin >> cityA >> cityB;
        cityA--, cityB--; // 0-based index
        roadNetwork[cityA].push_back({cityB, i});
        roadNetwork[cityB].push_back({cityA, i});
    }
    
    int discoveryTime = 0;
    vector<int> stackStorage;
    vector<int> visitOrder(cityCount), lowLink(cityCount), component(cityCount);
    
    // DFS를 이용한 컴포넌트 분리
    auto findComponents = [&](auto self, int currentCity, int parentEdge) -> void {
        visitOrder[currentCity] = lowLink[currentCity] = ++discoveryTime;
        stackStorage.push_back(currentCity);
        
        for (auto [nextCity, edgeID] : roadNetwork[currentCity]) {
            if (edgeID == parentEdge) continue;
            if (visitOrder[nextCity]) {
                lowLink[currentCity] = min(lowLink[currentCity], visitOrder[nextCity]);
            } else {
                self(self, nextCity, edgeID);
                lowLink[currentCity] = min(lowLink[currentCity], lowLink[nextCity]);
            }
        }
        
        if (visitOrder[currentCity] == lowLink[currentCity]) {
            while (true) {
                int topCity = stackStorage.back();
                stackStorage.pop_back();
                component[topCity] = currentCity;
                if (topCity == currentCity) break;
            }
        }
    };
    findComponents(findComponents, 0, -1);
    
    vector<set<int>> compressedGraph(cityCount);
    for (int i = 0; i < cityCount; ++i) {
        for (auto [neighbor, edgeID] : roadNetwork[i]) {
            if (component[neighbor] == component[i]) continue;
            compressedGraph[component[i]].insert(component[neighbor]);
        }
    }
    
    vector<int> leafCount(cityCount);
    auto countLeafNodes = [&](auto self, int node, int parent) -> int {
        leafCount[node] = (compressedGraph[node].size() == 1);
        for (int neighbor : compressedGraph[node]) {
            if (neighbor == parent) continue;
            leafCount[node] += self(self, neighbor, node);
        }
        return leafCount[node];
    };
    countLeafNodes(countLeafNodes, 0, 0);
    
    auto findCentroid = [&](auto self, int node, int parent, int rootLeafCount) -> int {
        for (int neighbor : compressedGraph[node]) {
            if (neighbor == parent) continue;
            if (leafCount[neighbor] * 2 > rootLeafCount)
                return self(self, neighbor, node, rootLeafCount);
        }
        return node;
    };
    int centralNode = findCentroid(findCentroid, 0, 0, leafCount[0]);
    
    vector<vector<int>> leafStorage(cityCount);
    auto collectLeaves = [&](auto self, int node, int parent, int root) -> void {
        if (compressedGraph[node].size() == 1) leafStorage[root].push_back(node);
        for (int neighbor : compressedGraph[node]) {
            if (neighbor == parent) continue;
            self(self, neighbor, node, root);
        }
    };
    for (int neighbor : compressedGraph[centralNode]) {
        collectLeaves(collectLeaves, neighbor, centralNode, neighbor);
    }
    
    using PII = pair<int, int>;
    priority_queue<PII> maxHeap;
    for (int neighbor : compressedGraph[centralNode]) {
        maxHeap.push({leafStorage[neighbor].size(), neighbor});
    }
    
    vector<PII> bridgesToBuild;
    while (!maxHeap.empty()) {
        if (maxHeap.size() == 1) {
            auto [_, single] = maxHeap.top(); maxHeap.pop();
            bridgesToBuild.push_back({leafStorage[single][0], centralNode});
        } else {
            auto [_, first] = maxHeap.top(); maxHeap.pop();
            auto [__, second] = maxHeap.top(); maxHeap.pop();
            bridgesToBuild.push_back({leafStorage[first].back(), leafStorage[second].back()});
            leafStorage[first].pop_back();
            leafStorage[second].pop_back();
            if (!leafStorage[first].empty())
                maxHeap.push({leafStorage[first].size(), first});
            if (!leafStorage[second].empty())
                maxHeap.push({leafStorage[second].size(), second});
        }
    }
    
    cout << bridgesToBuild.size() << '\n';
    for (auto [cityA, cityB] : bridgesToBuild) {
        cityA++, cityB++;
        cout << cityA << " " << cityB << "\n";
    }
}
