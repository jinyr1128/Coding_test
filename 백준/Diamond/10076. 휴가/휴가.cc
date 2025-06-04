#include <bits/stdc++.h>
using namespace std;

/*
 * 두 개의 멀티셋(혹은 우선순위 큐)을 이용해
 * "현재 원소 집합에서 상위 k개의 합"을 유지하는 자료구조 예시
 */
struct TopK {
    multiset<long long> topSet;    // 상위 k개의 원소들
    multiset<long long> botSet;    // 나머지 원소들
    long long sumTop;             // topSet에 들어있는 원소들의 합
    int curK;                     // 현재 k값

    TopK() {
        sumTop = 0;
        curK = 0;
    }

    // 크기가 0인 상태에서 시작한다고 가정
    // val 추가
    void add(long long val) {
        // topSet이 비어있다면 우선 botSet에 넣고 나중에 재조정
        if(topSet.empty()) {
            botSet.insert(val);
        } else {
            // topSet의 최소값보다 val가 크다면 topSet에 넣어볼 여지가 있음
            long long minTop = *topSet.begin(); // topSet은 오름차순 정렬이라고 가정
            if(val > minTop) {
                topSet.insert(val);
                sumTop += val;
            } else {
                botSet.insert(val);
            }
        }
        // 사이즈 조정
        rebalance();
    }

    // val 제거 (항상 존재한다고 가정)
    void remove(long long val) {
        // topSet에 있는지, botSet에 있는지 확인
        auto itTop = topSet.find(val);
        if(itTop != topSet.end()) {
            // topSet에서 제거
            topSet.erase(itTop);
            sumTop -= val;
        } else {
            // botSet에서 제거
            auto itBot = botSet.find(val);
            if(itBot != botSet.end()) {
                botSet.erase(itBot);
            } else {
                // 이 문제에선 remove가 잘못 호출되지 않도록 주의
                // (에러 처리)
            }
        }
        // 사이즈 조정
        rebalance();
    }

    // 현재 topSet.size()가 curK가 되도록 조정
    void rebalance() {
        // topSet 크기가 curK보다 크면 초과분을 botSet으로
        while((int)topSet.size() > curK) {
            auto it = topSet.begin(); // topSet에서 가장 작은 값
            long long val = *it;
            topSet.erase(it);
            sumTop -= val;
            botSet.insert(val);
        }
        // topSet 크기가 curK보다 작으면 botSet에서 옮겨옴
        while((int)topSet.size() < curK && !botSet.empty()) {
            // botSet 중 가장 큰 값을 옮기는 것이 합을 최대로 하는 데 유리
            auto it = prev(botSet.end());
            long long val = *it;
            botSet.erase(it);
            topSet.insert(val);
            sumTop += val;
        }
    }

    // 외부에서 k를 변경하고 싶을 때
    void setK(int k) {
        curK = max(k, 0); // 음수가 되지 않도록
        rebalance();
    }

    // 현재 topSet(상위 k개) 원소들의 합
    long long getTopSum() {
        return sumTop;
    }
};


static const long long INF = 1e15;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, start;
    long long d; // d가 매우 클 수 있으므로 long long
    cin >> n >> start >> d;
    vector<long long> A(n);
    for(int i=0; i<n; i++){
        cin >> A[i];
    }

    // --- 1. 단일 방향(왼쪽) / (오른쪽)으로만 이동하는 경우 ---
    // 예) "오른쪽"만
    //    이동 비용 = (현재도시 ~ 끝도시 거리)
    //    day_budget = d - (이동일수)
    //    가능한 k = min( day_budget, 구간길이 )
    //    구간 [start..R]에서 상위 k개의 합
    //    R를 1씩 증가시키며(슬라이딩), 자료구조로 관리
    auto solveOneDirection = [&](bool goRight){
        long long ans = 0LL;
        TopK topk;
        // 시작점을 포함(처음에 add)
        // pointer: pos = start ~ 끝(혹은 0)
        if(goRight){
            // pos: start -> n-1
            int leftPos = start;  // 구간의 시작
            topk.add(A[start]);
            for(int pos = start; pos < n; pos++){
                if(pos > start){
                    // 새 도시 추가
                    topk.add(A[pos]);
                }
                // 이동 비용 = pos - start
                long long moveCost = (pos - start);
                if(moveCost > d) break; // 더 이상 불가능
                long long dayBudget = d - moveCost;
                // 구간 크기 = (pos - start + 1)
                int sz = (pos - start + 1);
                int k = min<long long>(sz, dayBudget);
                topk.setK(k);
                ans = max(ans, topk.getTopSum());
            }
            // 끝
        } else {
            // 왼쪽만
            int rightPos = start;
            topk.add(A[start]);
            for(int pos = start; pos >= 0; pos--){
                if(pos < start){
                    topk.add(A[pos]);
                }
                long long moveCost = (start - pos);
                if(moveCost > d) break;
                long long dayBudget = d - moveCost;
                int sz = (start - pos + 1);
                int k = min<long long>(sz, dayBudget);
                topk.setK(k);
                long long val = topk.getTopSum();
                if(val > ans) ans = val;
            }
        }
        return ans;
    };

    long long answer = 0;

    // (1) 왼쪽만 이동
    answer = max(answer, solveOneDirection(false));
    // (2) 오른쪽만 이동
    answer = max(answer, solveOneDirection(true));

    // --- 2. 한 번 방향 전환 (왼->오 or 오->왼) ---
    //    x = start에서 왼쪽으로 이동한 양
    //    y = start에서 오른쪽으로 이동한 양
    //    cost(x,y) = (x + y) + min(x,y)
    //    -> subarray [start-x..start+y]
    //    2포인터 기법 + 슬라이딩 윈도우(TopK)로 구현
    //    왼->오만 하면 되는가? 오른->왼도 따로 해야 함.
    //    하지만 cost(x,y)는 x,y 양쪽에서의 이동이므로, 실제로는
    //    "start - x" ~ "start + y" 구간 커버
    //    "오->왼"은 "start+x" ~ "start-y" 비슷하게 처리(좌우 뒤집기).

    // A를 편의상 분리해서, start 기준 왼쪽 배열 L, 오른쪽 배열 R 생성
    // L[i] = A[start - i], i=1..leftLen
    // R[i] = A[start + i], i=1..rightLen
    // 실제 구현 시, 슬라이딩에 맞추어 add/remove 해줄 수 있도록 한다.
    // 먼저 "왼->오"를 코드로 작성해 보고, 그 뒤 "오->왼"을 거의 유사하게 처리.

    // 왼쪽, 오른쪽의 길이
    int leftLen = start;      // start에서 0까지 갈 수 있는 최대 거리
    int rightLen = (n-1) - start; // start에서 n-1까지 갈 수 있는 최대 거리

    // L[], R[] 따로 구성
    vector<long long> L(leftLen), R(rightLen);
    for(int i=0; i<leftLen; i++){
        L[i] = A[start - (i+1)]; // i=0 -> A[start-1], i=1 -> A[start-2], ...
    }
    for(int i=0; i<rightLen; i++){
        R[i] = A[start + (i+1)];
    }

    // --- (a) 왼->오 ---
    {
        TopK topk;
        topk.add(A[start]); // 처음엔 start 도시 포함

        long long ans2 = A[start]; // 최소 start 도시 관광지는 방문할 수 있다고 가정(0이 아닐 수도)
        // 2포인터: i=왼쪽 사용량, j=오른쪽 사용량
        // i는 0..leftLen, j는 0..rightLen
        int i = 0, j = 0;
        // 초기 cost(0,0) = 0이므로 dayBudget = d, k=1 (start 도시만)
        topk.setK(1);
        ans2 = max(ans2, topk.getTopSum());

        while(i <= leftLen && j <= rightLen) {
            // 다음으로 j를 늘려보고, 가능하면 j를 늘린다.
            // 불가능하면 i를 늘린다.
            // 1) j를 늘릴 수 있는지 확인
            bool canMoveJ = false;
            if(j < rightLen) {
                long long nextJ = j+1;
                long long cost = (i + nextJ) + min((long long)i, (long long)nextJ); 
                // = i + j+1 + min(i, j+1)
                if(cost <= d) {
                    // j를 하나 늘려본다
                    canMoveJ = true;
                }
            }
            if(canMoveJ) {
                // 실제로 R[j]를 자료구조에 add
                j++;
                topk.add(R[j-1]); 
            } else {
                // j를 못 늘리면 i를 늘려본다
                if(i == leftLen) {
                    // 더 이상 i를 못 늘리면 종료
                    break;
                }
                long long nextI = i+1;
                long long cost = (nextI + j) + min((long long)nextI, (long long)j);
                if(cost <= d) {
                    i++;
                    topk.add(L[i-1]);
                } else {
                    // i도 못 늘리면 종료
                    break;
                }
            }
            // 이제 (i,j)에 대한 cost 검사 후, dayBudget, k 계산
            long long costVal = (i + j) + min((long long)i, (long long)j);
            if(costVal <= d) {
                long long dayBudget = d - costVal;
                // subarray 크기 = i + j + 1 (start 도시 포함)
                int sz = i + j + 1;
                int k = (int)min((long long)sz, dayBudget);
                topk.setK(k);
                long long got = topk.getTopSum();
                ans2 = max(ans2, got);
            }
        }

        answer = max(answer, ans2);
    }

    // --- (b) 오->왼 ---
    //    아이디어는 비슷. 이번에는
    //    x = 오른쪽으로 이동한 양, y = 왼쪽으로 이동한 양
    //    cost(x,y) = x + y + min(x,y)
    //    => 구간 [start - y.. start + x]
    //    구현 편의를 위해서는 R[]가 "오른쪽 후보", L[]가 "왼쪽 후보"지만,
    //    이번엔 먼저 오른쪽부터 소비(i), 그다음 왼쪽 소비(j) 식으로 2포인터.

    {
        TopK topk;
        topk.add(A[start]);
        long long ans2 = A[start];
        int i = 0, j = 0; // i: 오른쪽 사용량, j: 왼쪽 사용량
        topk.setK(1);
        ans2 = max(ans2, topk.getTopSum());

        while(i <= rightLen && j <= leftLen) {
            bool canMoveI = false;
            if(i < rightLen) {
                long long nextI = i+1;
                long long cost = (nextI + j) + min((long long)nextI, (long long)j);
                if(cost <= d) {
                    canMoveI = true;
                }
            }
            if(canMoveI) {
                i++;
                topk.add(R[i-1]);
            } else {
                if(j == leftLen) {
                    break;
                }
                long long nextJ = j+1;
                long long cost = (i + nextJ) + min((long long)i, (long long)nextJ);
                if(cost <= d) {
                    j++;
                    topk.add(L[j-1]);
                } else {
                    break;
                }
            }
            long long costVal = (i + j) + min((long long)i, (long long)j);
            if(costVal <= d) {
                long long dayBudget = d - costVal;
                int sz = i + j + 1;
                int k = (int)min((long long)sz, dayBudget);
                topk.setK(k);
                long long got = topk.getTopSum();
                ans2 = max(ans2, got);
            }
        }
        answer = max(answer, ans2);
    }

    cout << answer << "\n";
    return 0;
}
