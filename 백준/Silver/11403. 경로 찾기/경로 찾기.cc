#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<vector<int>> graph(n, vector<int>(n));

    // 인접 행렬 입력
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }

    // 플로이드-워셜 알고리즘 적용
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][k] == 1 && graph[k][j] == 1) {
                    graph[i][j] = 1;
                }
            }
        }
    }

    // 결과 인접 행렬 출력
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << graph[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}
