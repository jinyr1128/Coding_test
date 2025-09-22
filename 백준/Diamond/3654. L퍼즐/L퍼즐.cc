#include <bits/stdc++.h>
using namespace std;

struct Dinic {
    struct Edge { int to, cap, rev; };
    int N;
    vector<vector<Edge>> G;
    vector<int> level, it;

    Dinic(int n=0){ init(n); }
    void init(int n){
        N = n;
        G.assign(N, {});
        level.assign(N, 0);
        it.assign(N, 0);
    }
    void addEdge(int u, int v, int c){
        Edge a{v, c, (int)G[v].size()};
        Edge b{u, 0, (int)G[u].size()};
        G[u].push_back(a); G[v].push_back(b);
    }
    bool bfs(int s, int t){
        fill(level.begin(), level.end(), -1);
        queue<int> q; level[s]=0; q.push(s);
        while(!q.empty()){
            int u=q.front(); q.pop();
            for(const auto &e:G[u]) if(e.cap>0 && level[e.to]==-1){
                level[e.to]=level[u]+1; q.push(e.to);
            }
        }
        return level[t]!=-1;
    }
    int dfs(int u, int t, int f){
        if(!f) return 0;
        if(u==t) return f;
        for(int &i=it[u]; i<(int)G[u].size(); ++i){
            Edge &e=G[u][i];
            if(e.cap>0 && level[e.to]==level[u]+1){
                int ret=dfs(e.to, t, min(f, e.cap));
                if(ret){
                    e.cap-=ret;
                    G[e.to][e.rev].cap+=ret;
                    return ret;
                }
            }
        }
        return 0;
    }
    long long maxflow(int s, int t){
        long long flow=0;
        while(bfs(s,t)){
            fill(it.begin(), it.end(), 0);
            while(int pushed=dfs(s,t,INT_MAX)) flow+=pushed;
        }
        return flow;
    }
};

int dr[4] = { -1, 1, 0, 0 };
int dc[4] = { 0, 0, -1, 1 };

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T; 
    if(!(cin >> T)) return 0;
    while(T--){
        int n, m;
        cin >> n >> m;
        vector<string> g(n);
        for(int i=0;i<n;i++) cin >> g[i];

        // ID 부여: 검정(B)와 흰(W)
        vector<vector<int>> idB(n, vector<int>(m, -1));
        vector<vector<int>> idW(n, vector<int>(m, -1));
        int cntB=0, cntW=0;
        for(int i=0;i<n;i++) for(int j=0;j<m;j++){
            if(g[i][j]=='B') idB[i][j]=cntB++;
            else if(g[i][j]=='W') idW[i][j]=cntW++;
        }

        // 빠른 불가능 체크
        if(cntW != 2*cntB){
            cout << "NO\n";
            continue;
        }
        if(cntW==0 && cntB==0){
            // 패턴에 유색 칸이 최소 하나 있다고 했으므로 여기 오진 않음.
            cout << "YES\n";
            continue;
        }

        // 노드 인덱스 구성
        // S, Bh[0..cntB-1], Bv[0..cntB-1], W[0..cntW-1], T
        int S = 0;
        int offBh = 1;
        int offBv = offBh + cntB;
        int offW  = offBv + cntB;
        int Tt    = offW + cntW;
        Dinic din(Tt+1);

        auto Bh = [&](int b){ return offBh + b; };
        auto Bv = [&](int b){ return offBv + b; };
        auto Wn = [&](int w){ return offW + w; };

        // S -> Bh, S -> Bv
        for(int b=0;b<cntB;b++){
            din.addEdge(S, Bh(b), 1);
            din.addEdge(S, Bv(b), 1);
        }
        // W -> T
        for(int w=0; w<cntW; w++){
            din.addEdge(Wn(w), Tt, 1);
        }

        // 인접한 B-W 연결: 방향에 따라 Bh 또는 Bv에서 W로
        for(int i=0;i<n;i++) for(int j=0;j<m;j++){
            if(g[i][j] != 'B') continue;
            int b = idB[i][j];
            // 네 방향 이웃이 W이면 간선 추가
            for(int dir=0; dir<4; dir++){
                int ni = i + dr[dir];
                int nj = j + dc[dir];
                if(ni<0||ni>=n||nj<0||nj>=m) continue;
                if(g[ni][nj] != 'W') continue;
                int w = idW[ni][nj];
                if(dir < 2) {
                    // dir 0/1 : 위/아래 -> 세로 이웃 -> Bv -> W
                    din.addEdge(Bv(b), Wn(w), 1);
                } else {
                    // dir 2/3 : 좌/우 -> 가로 이웃 -> Bh -> W
                    din.addEdge(Bh(b), Wn(w), 1);
                }
            }
        }

        long long flow = din.maxflow(S, Tt);
        cout << (flow == cntW ? "YES" : "NO") << '\n';
    }
    return 0;
}
