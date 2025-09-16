#include <bits/stdc++.h>
using namespace std;

/*
  별자리(트리)의 위상 정규형(AHU)으로 서로 다른 개수를 센다.
  - 각 트리에 대해 지름 경로를 구하고, 중심(1개 또는 2개)을 뿌리로 하여
    AHU 정규형 문자열을 계산한 뒤 사전순 최소를 채택.
  - 모든 트리의 정규형 문자열을 unordered_set에 넣고 size를 출력.
*/

struct FastScanner {
    static const int S = 1 << 20;
    int idx = 0, sz = 0; char buf[S];
    inline char read() {
        if (idx >= sz) {
            sz = (int)fread(buf, 1, S, stdin);
            idx = 0;
            if (!sz) return 0;
        }
        return buf[idx++];
    }
    template<typename T>
    bool nextInt(T &out) {
        char c = read(); if (!c) return false;
        T sign = 1, x = 0;
        while (c!='-' && (c<'0'||c>'9')) { c = read(); if (!c) return false; }
        if (c=='-') { sign = -1; c = read(); }
        for (; c>='0' && c<='9'; c = read()) x = x*10 + (c-'0');
        out = x * sign; return true;
    }
} In;

// 한 트리에서 루트 root를 기준으로 AHU 정규형을 만든다.
string ahu_dfs(int u, int p, const vector<vector<int>>& g) {
    vector<string> childs;
    childs.reserve(g[u].size());
    for (int v : g[u]) if (v != p) {
        childs.push_back(ahu_dfs(v, u, g));
    }
    sort(childs.begin(), childs.end());
    size_t len = 2; // '(' + ')'
    for (auto &s : childs) len += s.size();
    string res; res.reserve(len);
    res.push_back('(');
    for (auto &s : childs) res += s;
    res.push_back(')');
    return res;
}

// BFS로 가장 먼 정점을 찾고 parent를 기록한다.
pair<int, vector<int>> bfs_far(int s, const vector<vector<int>>& g) {
    int n = (int)g.size();
    vector<int> dist(n, -1), par(n, -1);
    queue<int> q;
    dist[s] = 0; par[s] = -1; q.push(s);
    int last = s;
    while (!q.empty()) {
        int u = q.front(); q.pop();
        last = u;
        for (int v : g[u]) if (dist[v] == -1) {
            dist[v] = dist[u] + 1;
            par[v] = u;
            q.push(v);
        }
    }
    return {last, par};
}

// 트리의 중심(들)을 구한다: 지름의 중앙 1개 또는 2개.
vector<int> centers_of_tree(const vector<vector<int>>& g) {
    int n = (int)g.size();
    if (n == 1) return {0};
    auto [u, par1] = bfs_far(0, g);          // 0에서 가장 먼 u
    auto [v, par2] = bfs_far(u, g);          // u에서 가장 먼 v (지름 끝)
    // u->v 경로 복원
    vector<int> path;
    for (int x = v; x != -1; x = par2[x]) path.push_back(x);
    reverse(path.begin(), path.end()); // u..v
    int L = (int)path.size() - 1;      // 간선 기준 길이
    if (L % 2 == 0) {
        return { path[L/2] };
    } else {
        return { path[L/2], path[L/2 + 1] };
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n; 
    if (!In.nextInt(n)) return 0;

    // 최악을 대비해 해시 버킷 여유 확보
    unordered_set<string> shapes;
    shapes.reserve(n * 2);

    for (int i = 0; i < n; ++i) {
        int s; In.nextInt(s);
        vector<vector<int>> g(s);
        for (int e = 0; e < s - 1; ++e) {
            int u, v; In.nextInt(u); In.nextInt(v);
            g[u].push_back(v);
            g[v].push_back(u);
        }
        // 중심(들) 계산
        vector<int> ctr = centers_of_tree(g);
        // 중심마다 AHU 정규형을 구해 사전순 최소를 채택
        string best;
        for (int c : ctr) {
            string cur = ahu_dfs(c, -1, g);
            if (best.empty() || cur < best) best = std::move(cur);
        }
        shapes.insert(std::move(best));
    }

    cout << shapes.size() << '\n';
    return 0;
}
