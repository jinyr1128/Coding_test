#include <cstdio>
#include <deque>
#include <utility>
#include <algorithm>
using namespace std;

const int INF = 1e9;
using pii = pair<int,int>;

int main() {
    // 입력 파라미터
    int numAcademies, numCourses, minRun, maxRun, switchCost;

    // fee[a][j]  : 학원 a에서 j번째 강좌의 수강비
    // prefix[a][k]: 학원 a에서 처음 k개 강좌의 누적합 (k=0..M+S), M 이후는 값 고정(패딩)
    // bannedFrom[a]: 학원 a가 직전 강좌를 수강한 학원으로 **허용하지 않는** 학원 번호
    // 주의: dp의 칼럼을 M+S까지 두는 이유는 "마지막 학원은 S개 미만을 연속 수강해도 됨"을
    //       간단히 표현하기 위해 M 이후 구간을 비용 증가 없이 패딩하기 위함.
    static int fee[3000][6000] = {0};
    static int bannedFrom[3000];
    static int prefix[3000][6001] = {0};

    scanf("%d %d %d %d %d", &numAcademies, &numCourses, &minRun, &maxRun, &switchCost);

    // 각 학원별 강좌 비용과 prefix 누적합 계산
    for (int a = 0; a < numAcademies; ++a) {
        for (int j = 0; j < numCourses; ++j) {
            scanf("%d", &fee[a][j]);
            prefix[a][j+1] = prefix[a][j] + fee[a][j];
        }
        // 마지막 학원에서는 S개 미만도 허용 → M 이후 구간도 동일 값으로 패딩
        for (int j = numCourses; j < numCourses + minRun; ++j)
            prefix[a][j+1] = prefix[a][j];
    }

    // 각 학원의 불허용(직전 학원) 입력 (1-indexed → 0-indexed 변환)
    for (int a = 0; a < numAcademies; ++a) {
        scanf("%d", &bannedFrom[a]);
        --bannedFrom[a];
    }

    // dp[a][j]: j(0-based) 시점까지의(패딩 포함) 최소 비용, 마지막 구간이 학원 a에서 끝남
    static int dp[3000][6000];
    // dq[a]: 학원 a를 마지막으로 하는 전이 후보를 위한 deque (모노톤 큐)
    deque<pii> dq[3000];

    // 초기값 INF로 채움
    for (int a = 0; a < numAcademies; ++a)
        fill(dp[a], dp[a] + (numCourses + minRun), INF);

    // j는 "마지막으로 수강한 강좌의 인덱스"를 의미하되,
    // 마지막 학원 완화 조건을 편히 처리하기 위해 M..M+S-1 까지 패딩
    for (int j = 0; j < numCourses + minRun; ++j) {

        // j0 = 이전 블록의 끝 위치(새 블록 시작 가능 지점 직전) = j - S
        int j0 = j - minRun;

        if (j0 >= 0) {
            // 이전 시점 j0에서의 dp를 (값, 학원)으로 모아 오름차순 정렬
            // 각 타겟 학원 a에 대해, a와 bannedFrom[a]를 제외한 **최소 dp 학원**을 선택해야 함
            static pii candidates[3000];
            for (int a = 0; a < numAcademies; ++a)
                candidates[a] = pii(dp[a][j0], a);
            sort(candidates, candidates + numAcademies);

            // 모든 학원 a에 대해 전이 후보를 deque에 갱신
            for (int a = 0; a < numAcademies; ++a) {
                for (int k = 0; ; ++k) {
                    int prevCost = candidates[k].first;
                    int prevAcad = candidates[k].second;
                    if (prevCost == INF) break;               // 더 이상 유효 후보 없음
                    if (prevAcad != a && prevAcad != bannedFrom[a]) {
                        // deque에 넣을 값:
                        // prevCost - prefix[a][j0+1]  (a에서 j0+1 이후부터 비용이 붙을 것이므로 미리 빼 두기)
                        int val = prevCost - prefix[a][j0+1];
                        while (!dq[a].empty() && dq[a].back().first > val) dq[a].pop_back();
                        dq[a].push_back(pii(val, j0));  // (값, 시점)
                        break;
                    }
                }
            }
        }

        // dp 갱신
        for (int a = 0; a < numAcademies; ++a) {
            // 첫 블록으로 시작하는 경우: 길이가 [S..E] 에 해당하면 가능
            // j == length-1 → length ∈ [S..E]  ⇒  j ∈ [S-1 .. E-1]
            if (j >= minRun - 1 && j < maxRun)
                dp[a][j] = prefix[a][j+1];  // 처음부터 a에서 (j+1)개 수강 비용

            // deque에서 유효 구간 유지: 블록 길이 ≤ E ⇒ j - j0 ≤ E  ⇒  j0 ≥ j - E
            while (!dq[a].empty() && dq[a].front().second < j - maxRun)
                dq[a].pop_front();

            // deque의 최솟값으로 전이 (블록 사이 전환비용 T 포함), prefix 더해 복원
            if (!dq[a].empty())
                dp[a][j] = min(dp[a][j], dq[a].front().first + prefix[a][j+1] + switchCost);
        }
    }

    // 정답: 마지막 학원은 S개 미만도 허용 → j ∈ [M-1 .. M+S-1] 범위에서 최소
    int answer = INF;
    for (int a = 0; a < numAcademies; ++a)
        for (int j = numCourses - 1; j < numCourses + minRun; ++j)
            answer = min(answer, dp[a][j]);

    printf("%d\n", answer);
    return 0;
}
