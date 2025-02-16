#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;
#define ff first
#define ss second
#define pb push_back

const ll INF = 1e9 + 7;
const int MAX_N = 210101;

struct Query {
    ll start, end, from, to;
};

vector<ll> distVec;
ll cityCount, roadCount;
ll distanceArr[MAX_N], resultArr[MAX_N], parent[MAX_N], dist[MAX_N];
Query queries[MAX_N];
ll visitStatus[MAX_N];

vector<ll> cityClusters[MAX_N];
vector<pll> roads[MAX_N];
vector<ll> queryBuckets[MAX_N];
priority_queue<pll, vector<pll>, greater<pll>> pq;

ll findRoot(ll node) {
    if (parent[node] == node) return node;
    return parent[node] = findRoot(parent[node]);
}

void mergeClusters(ll a, ll b) {
    a = findRoot(a);
    b = findRoot(b);
    if (visitStatus[a] + visitStatus[b] != 2) return;
    parent[a] = b;
}

void dijkstra() {
    while (!pq.empty()) {
        auto current = pq.top(); pq.pop();
        if (dist[current.ss] < current.ff) continue;
        for (auto next : roads[current.ss]) {
            if (dist[current.ss] + next.ff < dist[next.ss]) {
                dist[next.ss] = dist[current.ss] + next.ff;
                pq.emplace(dist[next.ss], next.ss);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    ll i, j, k, a, b, c, festivalCount, queryCount;

    cin >> cityCount >> roadCount >> festivalCount >> queryCount;

    for (i = 1; i <= roadCount; i++) {
        cin >> a >> b >> c;
        roads[a].pb({c, b});
        roads[b].pb({c, a});
    }

    fill(dist, dist + cityCount + 1, INF);
    
    for (i = 1; i <= festivalCount; i++) {
        cin >> a;
        dist[a] = 0;
        pq.push({0, a});
    }

    dijkstra();

    distVec.pb(-1);
    for (i = 1; i <= cityCount; i++) {
        distVec.pb(dist[i]);
    }
    sort(distVec.begin(), distVec.end());
    distVec.erase(unique(distVec.begin(), distVec.end()), distVec.end());

    ll distSize = distVec.size();
    for (i = 1; i <= cityCount; i++) {
        ll pos = lower_bound(distVec.begin(), distVec.end(), dist[i]) - distVec.begin();
        cityClusters[pos].pb(i);
    }
    
    for (i = 1; i <= queryCount; i++) {
        cin >> queries[i].from >> queries[i].to;
        queries[i].start = 0;
        queries[i].end = distSize;
        ll mid = (queries[i].start + queries[i].end) / 2;
        queryBuckets[mid].pb(i);
    }
    
    for (ll iteration = 1; iteration <= 20; iteration++) {
        for (i = 1; i <= cityCount; i++) {
            visitStatus[i] = 0;
            parent[i] = i;
        }

        for (i = distSize - 1; i >= 1; i--) {
            for (auto node : cityClusters[i]) {
                visitStatus[node] = 1;
                for (auto adj : roads[node]) {
                    mergeClusters(node, adj.ss);
                }
            }

            for (auto queryIndex : queryBuckets[i]) {
                if (findRoot(queries[queryIndex].from) == findRoot(queries[queryIndex].to)) {
                    resultArr[queryIndex] = max(resultArr[queryIndex], i);
                    queries[queryIndex].start = i + 1;
                } else {
                    queries[queryIndex].end = i - 1;
                }
            }
            queryBuckets[i].clear();
        }

        for (i = 1; i <= queryCount; i++) {
            if (queries[i].start <= queries[i].end) {
                ll mid = (queries[i].start + queries[i].end) / 2;
                queryBuckets[mid].pb(i);
            }
        }
    }

    for (i = 1; i <= queryCount; i++) {
        cout << max(distVec[resultArr[i]], 0LL) << '\n';
    }
}
