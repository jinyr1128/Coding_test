#include <bits/stdc++.h>
using namespace std;

using ll = long long;
const ll INF = (ll)4e18;

struct Edge { int to; int w; };
struct UE { int u,v; int w; };

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, a, b;
    if (!(cin >> n >> m >> a >> b)) return 0;

    vector<vector<Edge>> g(n+1);
    vector<UE> edges; edges.reserve(m);
    for (int i=0;i<m;i++){
        int u,v,w; cin >> u >> v >> w;
        g[u].push_back({v,w});
        g[v].push_back({u,w});
        edges.push_back({u,v,w});
    }

    // 입력의 마지막 줄: k, v1=a, ..., vk=b (주어진 최단 경로)
    int k; cin >> k;
    vector<int> path(k+1);
    for (int i=1;i<=k;i++) cin >> path[i];

    // 경로 간선 집합(막아야 할 간선을 후보에서 제외)
    // key = min(u,v)*(n+1) + max(u,v)
    auto keyOf = [&](int x, int y)->long long {
        if (x>y) swap(x,y);
        return 1LL*x*(n+1) + y;
    };
    unordered_set<long long> onPath;
    onPath.reserve(k*2);
    for (int i=1;i<k;i++){
        onPath.insert(keyOf(path[i], path[i+1]));
    }

    auto dijkstra = [&](int src)->vector<ll> {
        vector<ll> dist(n+1, INF);
        priority_queue<pair<ll,int>, vector<pair<ll,int>>, greater<pair<ll,int>>> pq;
        dist[src]=0; pq.push({0,src});
        while(!pq.empty()){
            auto [d,u]=pq.top(); pq.pop();
            if (d!=dist[u]) continue;
            for (auto &e: g[u]){
                int v=e.to; ll nd=d+e.w;
                if (nd<dist[v]){ dist[v]=nd; pq.push({nd,v}); }
            }
        }
        return dist;
    };

    // 1) dA, dB
    vector<ll> dA = dijkstra(a);
    vector<ll> dB = dijkstra(b);
    ll S = dA[b];

    // 2) 경로 정점 인덱스
    vector<int> idx(n+1, -1);
    for (int i=1;i<=k;i++) idx[path[i]] = i;

    // 3) dpA: 타이트(dA) DAG에서 최대 인덱스 전파
    vector<int> ordA(n); iota(ordA.begin(), ordA.end(), 1);
    sort(ordA.begin(), ordA.end(), [&](int x, int y){
        if (dA[x]!=dA[y]) return dA[x] < dA[y];
        return x<y;
    });
    vector<int> dpA(n+1, 0); // 0 = (아직 P를 만나지 않음)
    for (int i=1;i<=k;i++) dpA[path[i]] = i;
    for (int u: ordA){
        if (dA[u]>=INF) continue;
        for (auto &e: g[u]){
            int v=e.to; if (dA[u] + e.w == dA[v]) {
                dpA[v] = max(dpA[v], dpA[u]);
            }
        }
    }

    // 4) dpB: 타이트(dB) DAG에서 최소 인덱스 전파
    vector<int> ordB(n); iota(ordB.begin(), ordB.end(), 1);
    sort(ordB.begin(), ordB.end(), [&](int x, int y){
        if (dB[x]!=dB[y]) return dB[x] < dB[y];
        return x<y;
    });
    vector<int> dpB(n+1, k+1); // k+1 = (끝까지 P를 만나지 않음)
    for (int i=1;i<=k;i++) dpB[path[i]] = i;
    for (int u: ordB){
        if (dB[u]>=INF) continue;
        for (auto &e: g[u]){
            int v=e.to; if (dB[u] + e.w == dB[v]) {
                dpB[v] = min(dpB[v], dpB[u]);
            }
        }
    }

    // 5) 모든 간선에 대해 구간 [i, j-1]에 값 L = dA[u]+w+dB[v] / (반대방향도)
    //    단, P 위의 간선은 제외
    //    구간-최솟값을 이벤트 스윕으로 처리
    vector<vector<ll>> add(k+2), rem(k+2); // 인덱스 1..k-1 사용, rem[t]는 t에서 제거
    auto push_interval = [&](int L, int R, ll val){
        if (L<1) L=1;
        if (R>k-1) R=k-1;
        if (L>R) return;
        add[L].push_back(val);
        rem[R].push_back(val);
    };

    for (const auto &E: edges){
        int u=E.u, v=E.v, w=E.w;
        if (onPath.count(keyOf(u,v))) continue; // 막는 간선은 후보에서 제외

        if (dA[u]<INF && dB[v]<INF){
            int i = dpA[u], j = dpB[v];
            if (i < j){
                ll cand = dA[u] + (ll)w + dB[v];
                push_interval(i, j-1, cand);
            }
        }
        if (dA[v]<INF && dB[u]<INF){
            int i = dpA[v], j = dpB[u];
            if (i < j){
                ll cand = dA[v] + (ll)w + dB[u];
                push_interval(i, j-1, cand);
            }
        }
    }

    // 6) 스윕하며 각 t의 최솟값 계산
    vector<ll> ans(k, INF); // 1..k-1
    multiset<ll> cur;
    for (int t=1; t<=k-1; ++t){
        for (ll x: add[t]) cur.insert(x);
        if (!cur.empty()) ans[t] = min(ans[t], *cur.begin());
        for (ll x: rem[t]) {
            auto it = cur.find(x);
            if (it != cur.end()) cur.erase(it);
        }
    }

    // 7) 출력
    for (int t=1; t<=k-1; ++t){
        if (ans[t] >= INF/2) cout << -1 << '\n';
        else                 cout << ans[t] << '\n';
    }
    return 0;
}
