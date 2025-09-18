#include <bits/stdc++.h>
using namespace std;

using ll = long long;

static const int MAXN = 2005;          // m,n <= 2000
static const int INF  = 0x3f3f3f3f;

ll  K;
int W, H;                              // W = columns(m), H = rows(n)

// 행별/열별 누적합 (1-based)
static ll rsum[MAXN][MAXN];            // rsum[row][col] : 그 행의 1..col 합
static ll csum[MAXN][MAXN];            // csum[col][row] : 그 열의 1..row 합

// DP[y1][y2] : 열 구간 [y1,y2]만 남았을 때,
//  (행은 가능한 만큼 위/아래에서 모두 제거해 둔 상태라고 가정하고)
//  남은 부분을 전부 지우는 데 필요한 "열 제거 횟수"의 최소값
static int dp[MAXN][MAXN];

inline bool row_ok(int x, int y1, int y2) { // x행 [y1..y2] 합 <= K ?
    return (rsum[x][y2] - rsum[x][y1-1] <= K);
}
inline bool col_ok(int y, int x1, int x2) { // y열 [x1..x2] 합 <= K ?
    return (csum[y][x2] - csum[y][x1-1] <= K);
}

// 2D 배열 rsum과 csum을 통째로 교환 + H<->W 전치
void transpose_all() {
    for (int i = 1; i < MAXN; ++i)
        for (int j = 1; j < MAXN; ++j)
            swap(rsum[i][j], csum[i][j]);
    swap(H, W);
}

// 재귀 + 메모이제이션
// 현재 경계 [x1..x2] x [y1..y2]
ll solve_interval(int x1, int y1, int x2, int y2) {
    if (x1 > x2 || y1 > y2) return 0;

    // 1) 가능한 모든 행을 위/아래에서 제거 (무조건 지금 제거)
    ll removed_rows = 0;
    while (x1 <= x2 && row_ok(x1, y1, y2)) { ++x1; ++removed_rows; }
    while (x1 <= x2 && row_ok(x2, y1, y2)) { --x2; ++removed_rows; }

    if (x1 > x2) return removed_rows; // 행 다 지움 → 종료

    // 2) 더 이상 행을 못 지우면, 열 하나만 제거 (왼/오) 선택 → DP
    int &memo = dp[y1][y2];
    if (memo != INF) return removed_rows + memo;

    ll best = (ll)4e18;

    if (col_ok(y1, x1, x2)) {
        best = min(best, solve_interval(x1, y1+1, x2, y2) + 1);
    }
    if (col_ok(y2, x1, x2)) {
        best = min(best, solve_interval(x1, y1, x2, y2-1) + 1);
    }
    // 문제에서 "항상 가능" 보장 → 위 둘 중 하나는 반드시 가능
    memo = (best >= (ll)INF ? INF : (int)best);
    return removed_rows + memo;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    // 입력: k m n (문제 원문 기준), 이어서 n줄에 m개씩 값
    // 여기서는 W=m(열), H=n(행)
    if (!(cin >> K >> W >> H)) return 0;

    // 누적합 구성
    // rsum[row][col] : 그 행의 1..col 합
    // csum[col][row] : 그 열의 1..row 합
    for (int x = 1; x <= H; ++x) {
        ll running_row = 0;
        for (int y = 1; y <= W; ++y) {
            ll v; cin >> v;
            running_row += v;
            rsum[x][y] = running_row;
            csum[y][x] = csum[y][x-1] + v;
        }
    }

    // 첫 번째: (행을 먼저 모두 빼고 → 열 하나) 전략의 최적화
    memset(dp, 0x3f, sizeof(dp)); // INF로 초기화
    ll ans1 = solve_interval(1, 1, H, W);

    // 두 번째: 전치해서 (열↔행) 뒤집어 같은 로직을 한 번 더
    transpose_all();
    memset(dp, 0x3f, sizeof(dp));
    ll ans2 = solve_interval(1, 1, H, W);

    cout << min(ans1, ans2) << '\n';
    return 0;
}
