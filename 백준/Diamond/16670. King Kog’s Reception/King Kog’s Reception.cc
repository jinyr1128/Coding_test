#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

typedef long long ll;

// 세그먼트 트리 구조체
struct SegmentTree {
    ll endTime[4000000]; // 각 노드의 종료 시간
    ll count[4000000];   // 각 노드의 방문 횟수
    
    // 부모 노드의 값을 업데이트하는 함수
    void push(int node, int left, int right) {
        count[node] = count[left] + count[right];
        endTime[node] = max(endTime[right], endTime[left] + count[right]);
    }
    
    // 세그먼트 트리 초기화
    void init(int node, int start, int end) {
        if (start == end) {
            endTime[node] = start;
            count[node] = 0;
        } else {
            int mid = (start + end) >> 1;
            int leftChild = node << 1, rightChild = leftChild | 1;
            init(leftChild, start, mid);
            init(rightChild, mid + 1, end);
            push(node, leftChild, rightChild);
        }
    }
    
    // 특정 노드 업데이트 (방문 시간 추가/제거)
    void update(int node, int start, int end, int index, ll value) {
        if (start == end) {
            endTime[node] += value;
            count[node] += value;
        } else {
            int mid = (start + end) >> 1;
            int leftChild = node << 1, rightChild = leftChild | 1;
            if (index <= mid) update(leftChild, start, mid, index, value);
            else update(rightChild, mid + 1, end, index, value);
            push(node, leftChild, rightChild);
        }
    }
    
    void update(int index, ll value) {
        update(1, 1, 1000000, index, value);
    }
    
    ll answer;
    
    // 특정 시간에 대한 대기 시간 계산
    void query(int node, int start, int end, int time) {
        if (end <= time) {
            answer = max(answer + count[node], endTime[node]);
        } else {
            int mid = (start + end) >> 1;
            int leftChild = node << 1, rightChild = leftChild | 1;
            query(leftChild, start, mid, time);
            if (mid < time) query(rightChild, mid + 1, end, time);
        }
    }
    
    ll query(int time) {
        answer = 0;
        query(1, 1, 1000000, time);
        return answer;
    }
} segmentTree;

int main() {
    int queryCount;
    scanf("%d", &queryCount);
    vector<pair<int, int>> eventList(300001);
    segmentTree.init(1, 1, 1000000);
    
    for (int i = 1; i <= queryCount; i++) {
        char command[2];
        scanf("%s", command);
        
        if (command[0] == '?') { // 질의 (대기 시간 확인)
            ll arrivalTime;
            scanf("%lld", &arrivalTime);
            printf("%lld\n", max(segmentTree.query(arrivalTime) - arrivalTime, 0LL));
        } else if (command[0] == '+') { // 새로운 기사가 대기열에 추가됨
            int arrivalTime, duration;
            scanf("%d%d", &arrivalTime, &duration);
            eventList[i] = {arrivalTime, duration};
            segmentTree.update(arrivalTime, duration);
        } else { // 방문 취소
            int eventIndex;
            scanf("%d", &eventIndex);
            segmentTree.update(eventList[eventIndex].first, -eventList[eventIndex].second);
        }
    }
    return 0;
}
