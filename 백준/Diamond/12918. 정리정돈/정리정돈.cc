#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define loop(i, start, end) for(int i = (start); i < (end); i++)
using namespace std;
typedef pair<int, int> Point;

void setupFastIO(){
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(false);
}

const int MAX_SIZE = 202;
int objectCount;
Point objects[100];

// Minimum Cost Maximum Flow 구조체
struct MinCostMaxFlow{
    struct Edge{
        int destination, capacity, reverseIndex;
        double cost;

        Edge(int dest, int cap, int rev, double cost): destination(dest), capacity(cap), reverseIndex(rev), cost(cost) {}
    };

    vector<Edge> edges[MAX_SIZE];
    int source, sink;

    void initialize(){
        loop(i, 0, MAX_SIZE) edges[i].clear();
    }

    void addEdge(int from, int to, int cap, double cost){
        edges[from].emplace_back(to, cap, edges[to].size(), cost);
        edges[to].emplace_back(from, 0, edges[from].size() - 1, -cost);
    }

    void setSourceSink(int src, int snk){
        source = src;
        sink = snk;
    }

    double distances[MAX_SIZE];
    int parentNode[MAX_SIZE], parentEdge[MAX_SIZE]; 
    bool inQueue[MAX_SIZE];

    bool shortestPath(){
        fill(distances, distances + MAX_SIZE, 1e9);
        fill(inQueue, inQueue + MAX_SIZE, false);

        queue<int> q;
        q.push(source);
        distances[source] = 0;
        inQueue[source] = true;
        while(!q.empty()){
            int current = q.front(); q.pop();
            inQueue[current] = false;
            for(int i = 0; i < edges[current].size(); i++){
                int next = edges[current][i].destination;
                int cap = edges[current][i].capacity;
                double cost = edges[current][i].cost;
                if(cap > 0 && distances[next] > distances[current] + cost + 1e-9){
                    distances[next] = distances[current] + cost;
                    parentNode[next] = current;
                    parentEdge[next] = i;
                    if(!inQueue[next]){
                        q.push(next);
                        inQueue[next] = true;
                    }
                }
            }
        }
        return distances[sink] != 1e9;
    }

    double findMatching(){
        double result = 0;
        loop(i, 0, objectCount){
            shortestPath();
            result += distances[sink];
            for(int j = sink; j != source; j = parentNode[j]){
                int reverseIdx = parentEdge[j];
                edges[parentNode[j]][reverseIdx].capacity--;
                edges[j][edges[parentNode[j]][reverseIdx].reverseIndex].capacity++;
            }
        }
        return result;
    }
}FlowSolver;

// 두 점 사이의 거리 계산
double calculateDistance(Point a, Point b){
    double dx = (double)a.first + b.first;
    double dy = (double)a.second - b.second;
    return sqrt(dx * dx + dy * dy);
}

void solve(){
    cin >> objectCount;
    loop(i, 0, objectCount) cin >> objects[i].first >> objects[i].second;

    FlowSolver.initialize();
    FlowSolver.setSourceSink(objectCount * 2, objectCount * 2 + 1);

    loop(i, 0, objectCount){
        FlowSolver.addEdge(FlowSolver.source, i * 2, 1, 0);
        FlowSolver.addEdge(i * 2 + 1, FlowSolver.sink, 1, 0);
    }

    loop(i, 0, objectCount){
        loop(j, 0, objectCount){
            if(i == j) FlowSolver.addEdge(i * 2, j * 2 + 1, 1, abs(objects[i].first));
            else FlowSolver.addEdge(i * 2, j * 2 + 1, 1, calculateDistance(objects[i], objects[j]) / 2);
        }
    }

    cout << fixed << setprecision(3) << FlowSolver.findMatching() << '\n';
}

int main(){
    setupFastIO();
    int testCases = 1;
    loop(testCase, 1, testCases + 1){
        solve();
    }
    return 0;
}
