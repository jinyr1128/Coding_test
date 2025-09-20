#include <bits/stdc++.h>
using namespace std;
using ll = long long;

static const ll NEG_INF = (ll)-4e18;

int N, K;
struct Edge { int to; int w; };
vector<vector<Edge>> g;     // rooted at 1
vector<int> sz;

// dp[u] : vector<long long> of size up to min(K, sz[u]) + 1
vector<vector<ll>> dp;

void dfs(int u, int p) {
    sz[u] = 1;
    // base: u != 1 이면 자신을 고를 수도(0 or 1개), 루트면 0개만
    int baseCap = (u == 1 ? 0 : 1);
    dp[u].assign(min(K, baseCap) + 1, NEG_INF);
    dp[u][0] = 0;
    if (baseCap == 1) dp[u][1] = 0;

    int capU = baseCap; // 현재까지 u 서브트리에서 선택 가능한 최대 개수(상한)

    for (auto [v, w] : g[u]) {
        if (v == p) continue;
        dfs(v, u);

        int capV = min(K, sz[v]);                  // v 서브트리에서 최대 선택 수
        int newCap = min(K, capU + capV);
        vector<ll> ndp(newCap + 1, NEG_INF);

        // 간선 (u,v)의 기여 미리 계산
        vector<ll> gain(capV + 1, 0);
        for (int y = 0; y <= capV; ++y) {
            int m = min(y, K - y + 1);
            gain[y] = 2LL * w * (ll)m;
        }

        for (int t = 0; t <= capU; ++t) if (dp[u][t] > NEG_INF/2) {
            for (int y = 0; y <= capV && t + y <= K; ++y) {
                if (dp[v][y] <= NEG_INF/2) continue;
                ll cand = dp[u][t] + dp[v][y] + gain[y];
                ndp[t + y] = max(ndp[t + y], cand);
            }
        }

        dp[u].swap(ndp);
        sz[u] += sz[v];
        capU = newCap;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T; 
    if (!(cin >> T)) return 0;
    for (int tc = 1; tc <= T; ++tc) {
        cin >> N >> K;
        g.assign(N + 1, {});
        sz.assign(N + 1, 0);
        dp.assign(N + 1, {});

        for (int i = 2; i <= N; ++i) {
            int P, C; cin >> P >> C;
            g[P].push_back({i, C});
            g[i].push_back({P, C});
        }

        dfs(1, 0);

        // 루트는 선택할 수 없으므로, 정답은 dp[1][K]
        cout << "Case " << tc << ": " << dp[1][K] << '\n';
    }
    return 0;
}
