#include <bits/stdc++.h>
using namespace std;

static const int MAXN = 1010101;
typedef long long ll;

int parent[MAXN];
ll sz[MAXN];  // Union-Find 내 각 루트의 크기(원소 개수)

pair<ll,ll> arrPlate[MAXN];
vector<pair<ll,int>> poi;    // (값, 철판인덱스)
vector<int> gp[MAXN];        // 루트별 소속 판 인덱스 모음

int findUF(int x){
    if(parent[x] == x) return x;
    return parent[x] = findUF(parent[x]);
}

void unionUF(int a, int b){
    a = findUF(a); b = findUF(b);
    if(a == b) return;
    // 크기가 큰 쪽에 작은 쪽을 붙이기
    if(sz[a] < sz[b]) swap(a,b);
    parent[b] = a;
    sz[a] += sz[b];
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    // 입력 받고, ans에 (s_i + t_i) 합산
    long long ans = 0;
    for(int i = 1; i <= n; i++){
        cin >> arrPlate[i].first >> arrPlate[i].second;
        ans += arrPlate[i].first;
        ans += arrPlate[i].second;

        // poi에 (s_i, i), (t_i, i) 추가
        poi.push_back({arrPlate[i].first, i});
        poi.push_back({arrPlate[i].second, i});
    }

    // 초기 Union-Find 세팅
    for(int i = 1; i <= n; i++){
        parent[i] = i;
        sz[i] = 1; 
    }

    // poi 정렬: 값 오름차순
    sort(poi.begin(), poi.end(), 
         [](auto &a, auto &b){
            if(a.first == b.first) return a.second < b.second;
            return a.first < b.first;
         });

    // 인접한 두 poi가 같은 값이면 해당 철판들 union
    for(int i = 1; i < (int)poi.size(); i++){
        if(poi[i].first == poi[i-1].first){
            int idxA = poi[i].second;
            int idxB = poi[i-1].second;
            unionUF(idxA, idxB);
        }
    }

    // union-find 루트별로 철판 인덱스 모음
    for(int i = 1; i <= n; i++){
        int r = findUF(i);
        gp[r].push_back(i);
    }

    // 각 루트에 대해 처리
    for(int r = 1; r <= n; r++){
        if(gp[r].empty()) continue; // 해당 루트에 소속된 철판 없음
        int cnt = (int)gp[r].size();

        // 컴포넌트 r에 속한 모든 철판들이 가진 (s_i, t_i)를 전부 loc에 모으기
        vector<ll> loc;
        loc.reserve(cnt * 2);
        for(auto idx : gp[r]){
            ll s = arrPlate[idx].first;
            ll t = arrPlate[idx].second;
            loc.push_back(s);
            loc.push_back(t);
        }

        // 중복 제거 위해 정렬 후 unique
        sort(loc.begin(), loc.end());
        loc.erase(unique(loc.begin(), loc.end()), loc.end());

        // cnt개 판 -> 너비도 cnt개 필요
        // -> loc에서 가장 작은 값 cnt개를 ans에서 빼주면 됨
        //   (문제에서 반드시 가능하다고 했으므로 loc.size() >= cnt 보장)
        for(int i = 0; i < cnt; i++){
            ans -= loc[i];
        }
    }

    cout << ans << "\n";
    return 0;
}
