#include <bits/stdc++.h>
using namespace std;

struct Dinic {
    struct Edge { int to, rev, cap; };
    int N;
    vector<vector<Edge>> G;
    vector<int> level, it;

    Dinic(int n=0): N(n), G(n), level(n), it(n) {}

    int addEdge(int u, int v, int cap) {
        Edge a{v, (int)G[v].size(), cap};
        Edge b{u, (int)G[u].size(), 0};
        G[u].push_back(a); G[v].push_back(b);
        return (int)G[u].size() - 1; // index of forward edge at u
    }

    bool bfs(int s, int t) {
        fill(level.begin(), level.end(), -1);
        queue<int> q; level[s]=0; q.push(s);
        while(!q.empty()){
            int u=q.front(); q.pop();
            for(const auto &e:G[u]) if(e.cap>0 && level[e.to]==-1){
                level[e.to]=level[u]+1;
                q.push(e.to);
            }
        }
        return level[t]!=-1;
    }

    int dfs(int u, int t, int f){
        if(!f || u==t) return f;
        for(int &i=it[u]; i<(int)G[u].size(); ++i){
            Edge &e=G[u][i];
            if(e.cap>0 && level[e.to]==level[u]+1){
                int ret=dfs(e.to,t,min(f,e.cap));
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

// 안전 파서: "x.y"(한 자리 소수) 또는 "x" 모두 처리
int parseTenths(const string &s) {
    int n = (int)s.size();
    int pos = -1;
    for (int i=0;i<n;i++) if (s[i]=='.'){ pos=i; break; }
    if (pos == -1) return stoi(s) * 10;
    int a = (pos? stoi(s.substr(0,pos)) : 0);
    int b = (pos+1<n ? s[pos+1]-'0' : 0);
    return a*10 + b;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int M,N; 
    if(!(cin>>M>>N)) return 0;

    // 입력: M줄, 각 줄 N개 값 + 행합, 마지막 줄 N개 열합
    vector<vector<int>> t(M, vector<int>(N));         // 10*a_ij
    vector<int> rowT(M), colT(N);                     // 10*rowSum / 10*colSum

    for (int i=0;i<M;i++){
        for (int j=0;j<N;j++){
            string s; cin>>s; t[i][j] = parseTenths(s);
        }
        string sr; cin>>sr; rowT[i] = parseTenths(sr);
    }
    for (int j=0;j<N;j++){ string s; cin>>s; colT[j]=parseTenths(s); }

    // 각 셀: t = 10*b + f
    vector<vector<int>> b(M, vector<int>(N));
    vector<vector<int>> f(M, vector<int>(N));
    vector<int> sumBrow(M,0), sumFrow(M,0);
    vector<int> sumBcol(N,0), sumFcol(N,0);
    vector<char> fracExistsRow(M,0), fracExistsCol(N,0);

    for (int i=0;i<M;i++){
        for (int j=0;j<N;j++){
            b[i][j] = t[i][j]/10;
            f[i][j] = t[i][j]%10;
            sumBrow[i] += b[i][j];
            sumFrow[i] += f[i][j];
            sumBcol[j] += b[i][j];
            sumFcol[j] += f[i][j];
            if (f[i][j]>0){ fracExistsRow[i]=1; fracExistsCol[j]=1; }
        }
    }

    // 각 행/열의 하한-상한 계산
    // L_i = (sum f_ij - (rowT_i % 10)) / 10,  U_i = L_i + (rowT_i % 10 > 0 ? 1 : 0)
    vector<int> Lrow(M), Urow(M), Lcol(N), Ucol(N);
    for (int i=0;i<M;i++){
        int rem = rowT[i] % 10;
        Lrow[i] = (sumFrow[i] - rem)/10;
        Urow[i] = Lrow[i] + (rem>0 ? 1 : 0);
        // 안전상한: 0 <= Lrow <= Urow <= (행에서 f>0 인 칸 수)
        // (문제 보장과 구조상 항상 성립)
    }
    for (int j=0;j<N;j++){
        int rem = colT[j] % 10;
        Lcol[j] = (sumFcol[j] - rem)/10;
        Ucol[j] = Lcol[j] + (rem>0 ? 1 : 0);
    }

    // 노드 구성: S, rows, cols, T, SS, TT
    int S = 0;
    int baseRows = 1;
    int baseCols = baseRows + M;
    int T = baseCols + N;
    int SS = T + 1;
    int TT = SS + 1;
    int V = TT + 1;

    Dinic din(V);
    const int INF = 1e9;

    // 하한 있는 간선 추가 함수: cap=(upper-lower), demand[u]-=lower, demand[v]+=lower
    vector<int> demand(V, 0);
    auto addEdgeLB = [&](int u, int v, int low, int up, int &storeIdx, bool keepIdx=false){
        // up >= low
        int idx = din.addEdge(u, v, up - low);
        demand[u] -= low;
        demand[v] += low;
        if (keepIdx) storeIdx = idx;
        return idx;
    };

    // S->row (하한 Lrow, 상한 Urow)
    for (int i=0;i<M;i++){
        addEdgeLB(S, baseRows+i, Lrow[i], Urow[i], *(new int), false);
    }
    // row->col (f_ij>0 일 때만, 하한 0 상한 1). 이후 흐른 양(=x_ij)을 읽기 위해 인덱스 저장
    vector<vector<int>> edgeIdx(M, vector<int>(N, -1)); // forward edge index at row-node
    for (int i=0;i<M;i++){
        for (int j=0;j<N;j++){
            if (f[i][j] > 0){
                int dummy = -1;
                int idx = din.addEdge(baseRows+i, baseCols+j, 1); // lower=0
                edgeIdx[i][j] = idx;
            }
        }
    }
    // col->T (하한 Lcol, 상한 Ucol)
    for (int j=0;j<N;j++){
        addEdgeLB(baseCols+j, T, Lcol[j], Ucol[j], *(new int), false);
    }
    // T->S 무한 용량(하한 0)
    din.addEdge(T, S, INF);

    // SS/TT 추가: demand>0 => SS->v, demand<0 => v->TT
    long long need = 0;
    for (int v=0; v<V; v++){
        if (demand[v] > 0) { din.addEdge(SS, v, demand[v]); need += demand[v]; }
        else if (demand[v] < 0) { din.addEdge(v, TT, -demand[v]); }
    }

    // SS->TT 최대 유량
    long long got = din.maxflow(SS, TT);
    // 문제에서 항상 가능이라 했으므로 체크 생략 가능하지만, 안전을 위해:
    // if (got != need) { /* 불가능한 경우 */ }

    // x_ij = (row->col 간선의 역방향 용량) = 흘린 유량
    vector<vector<int>> x(M, vector<int>(N, 0));
    for (int i=0;i<M;i++){
        for (int j=0;j<N;j++){
            int idx = edgeIdx[i][j];
            if (idx == -1) { x[i][j]=0; continue; }
            auto &e = din.G[baseRows+i][idx];
            // e.to == baseCols+j, e.rev 는 그 반대 간선의 인덱스
            int flow = din.G[e.to][e.rev].cap; // 역방향 간선의 cap == 흘린 유량
            x[i][j] = (flow>0 ? 1 : 0);
        }
    }

    // 최종 정수 테이블 구성 및 출력
    vector<int> rowInt(M,0), colInt(N,0);
    for (int i=0;i<M;i++){
        for (int j=0;j<N;j++){
            int cij = b[i][j] + x[i][j];
            rowInt[i] += cij;
            colInt[j] += cij;
            cout << cij << (j+1==N ? ' ' : ' ');
        }
        cout << rowInt[i] << "\n";
    }
    for (int j=0;j<N;j++){
        cout << colInt[j] << (j+1==N?'\n':' ');
    }
    return 0;
}
