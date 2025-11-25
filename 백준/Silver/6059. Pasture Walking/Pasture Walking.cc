#include <iostream>
#include <vector>
#include <queue>
#include <cstring> // memset을 사용하기 위함

using namespace std;

// 간선 정보를 저장할 구조체
struct Edge {
    int to;     // 연결된 목초지 번호
    int weight; // 길의 길이
};

// 최대 N이 1000이므로 넉넉하게 선언
const int MAX_N = 1001;
vector<Edge> adj[MAX_N]; // 인접 리스트
bool visited[MAX_N];     // 방문 체크 배열

// BFS를 통해 start에서 end까지의 거리를 구하는 함수
int getDistance(int start, int end) {
    // 방문 배열 초기화
    memset(visited, 0, sizeof(visited));
    
    // 큐에는 {현재 노드, 현재까지의 거리}를 저장
    queue<pair<int, int>> q;
    
    q.push({start, 0});
    visited[start] = true;

    while (!q.empty()) {
        int curr = q.front().first;
        int dist = q.front().second;
        q.pop();

        // 목표 목초지에 도착했다면 거리 반환
        if (curr == end) {
            return dist;
        }

        // 연결된 모든 목초지 확인
        for (Edge& e : adj[curr]) {
            if (!visited[e.to]) {
                visited[e.to] = true;
                q.push({e.to, dist + e.weight});
            }
        }
    }
    return 0; // 트리 구조상 도달 못할 일은 없음
}

int main() {
    // 입출력 속도 향상
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, Q;
    if (cin >> N >> Q) {
        // 트리의 간선 정보 입력 (N-1개)
        for (int i = 0; i < N - 1; i++) {
            int u, v, w;
            cin >> u >> v >> w;
            // 양방향 연결
            adj[u].push_back({v, w});
            adj[v].push_back({u, w});
        }

        // 쿼리 처리 (Q개)
        for (int i = 0; i < Q; i++) {
            int p1, p2;
            cin >> p1 >> p2;
            // p1에서 p2까지의 거리 출력
            cout << getDistance(p1, p2) << "\n";
        }
    }

    return 0;
}