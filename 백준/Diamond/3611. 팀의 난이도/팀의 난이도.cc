#include <bits/stdc++.h>
using namespace std;

/*
    -------------------------------------
     Dinic for double-capacity flows
    -------------------------------------
*/
struct Dinic {
    using FlowT = double;
    struct Edge {
        int to;
        FlowT cap;
        int rev; // reverse edge index
    };
    int n;
    vector<vector<Edge>> g;
    vector<int> level, work;

    Dinic(int n): n(n), g(n), level(n), work(n) {}

    void add_edge(int u, int v, FlowT c) {
        g[u].push_back({v, c, (int)g[v].size()});
        g[v].push_back({u, 0, (int)g[u].size()-1});
    }

    bool bfs(int s, int t) {
        fill(level.begin(), level.end(), -1);
        queue<int>q;
        level[s] = 0;
        q.push(s);
        while(!q.empty()){
            int u=q.front(); q.pop();
            for(auto &e: g[u]){
                if(e.cap>1e-14 && level[e.to]<0){
                    level[e.to]=level[u]+1;
                    q.push(e.to);
                }
            }
        }
        return level[t]>=0;
    }

    FlowT send_flow(int u, FlowT f, int t) {
        if(!f) return 0;
        if(u==t) return f;
        for(int &i=work[u]; i<(int)g[u].size(); i++){
            auto &e = g[u][i];
            if(e.cap>1e-14 && level[e.to]==level[u]+1){
                FlowT cur = send_flow(e.to, min(f,e.cap), t);
                if(cur>1e-14){
                    e.cap -= cur;
                    g[e.to][e.rev].cap += cur;
                    return cur;
                }
            }
        }
        return 0;
    }

    FlowT max_flow(int s, int t) {
        FlowT total=0;
        while(bfs(s,t)){
            fill(work.begin(), work.end(), 0);
            while(true){
                FlowT flow=send_flow(s,1e9,t);
                if(flow<1e-14) break;
                total+=flow;
            }
        }
        return total;
    }

    // 소스 정점 s에서 나가는 간선에 조금이라도 여유 용량이 남아 있으면 false
    bool sourceCut(int s) const {
        for(auto &e: g[s]){
            if(e.cap>1e-14) return false;
        }
        return true;
    }

    // s에서 용량>0인 간선을 따라 도달 가능한 정점 목록
    vector<int> reachable(int s) const {
        vector<int> used(n,0), result;
        queue<int>q;
        q.push(s); used[s]=1;
        while(!q.empty()){
            int u=q.front(); q.pop();
            result.push_back(u);
            for(auto &e: g[u]){
                if(e.cap>1e-14 && !used[e.to]){
                    used[e.to]=1;
                    q.push(e.to);
                }
            }
        }
        return result;
    }
};


/*
    ------------------------------------------------------------
     Solve the "maximum density subgraph" = max(|E(S)| / |S|)
     Using parametric min-cut with binary search
    ------------------------------------------------------------
*/
int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n,m; // number of vertices(employees), edges
    cin >> n >> m;

    // 간선이 0개면, 난이도=0이고 아무 정점 1개 골라도 동일
    // 문제에서 "아무거나" 라 했으므로, 1개 출력
    if(m==0){
        cout << 1 << "\n" << 1 << "\n";
        return 0;
    }

    // Dinic: 정점 = n+2, 소스=0, 싱크=n+1
    int SRC=0, SINK=n+1;
    Dinic baseFlow(n+2);

    vector<int> deg(n+1,0);
    // 무방향 -> 각 방향으로 cap=1
    for(int i=0;i<m;i++){
        int a,b; 
        cin >> a >> b;
        baseFlow.add_edge(a,b,1);
        baseFlow.add_edge(b,a,1);
        deg[a]++; deg[b]++;
    }

    // SRC-> i 의 용량 = m
    for(int i=1;i<=n;i++){
        baseFlow.add_edge(SRC,i,(double)m);
    }

    // 이분탐색 범위 [0..m]
    double L=0.0, R=(double)m;
    double bestVal=0.0;
    // 오차 범위: n최대 100 => 1/(n*(n-1)) 정도
    double eps=1.0/double(n)/max(1.0,double(n-1));

    // "mid 이상의 밀도 부분집합 존재여부" 판단 함수
    auto canAchieve = [&](double mid){
        // baseFlow 복사
        Dinic test(baseFlow);
        // i-> SINK 용량 = (m + 2*mid - deg[i])
        // (음수면 0으로 취급)
        for(int i=1;i<=n;i++){
            double cap2 = (double)m + 2.0*mid - (double)deg[i];
            if(cap2<0) cap2=0;
            test.add_edge(i,SINK,cap2);
        }
        test.max_flow(SRC,SINK);
        // 소스 정점에 잔여 용량이 0이면 가능
        return test.sourceCut(SRC);
    };

    // Binary Search
    while(R-L>eps){
        double mid=(L+R)*0.5;
        if(canAchieve(mid)){
            R=mid; 
        } else {
            L=mid; 
            bestVal=mid;
        }
    }

    // bestVal 근방에서 최종 플로우 -> 부분집합 복원
    {
        Dinic finalFlow(baseFlow);
        for(int i=1;i<=n;i++){
            double cap2 = (double)m + 2.0*bestVal - (double)deg[i];
            if(cap2<0) cap2=0;
            finalFlow.add_edge(i,SINK,cap2);
        }
        finalFlow.max_flow(SRC,SINK);

        vector<int> visited=finalFlow.reachable(SRC);
        sort(visited.begin(), visited.end());

        // visited에는 SRC(0)도 포함 -> 제외 후 출력
        vector<int> team;
        for(int v: visited){
            if(v==SRC) continue;
            team.push_back(v);
        }

        // 결과 출력
        cout << (int)team.size() << "\n";
        for(int v: team){
            cout << v << "\n";
        }
    }
    return 0;
}
