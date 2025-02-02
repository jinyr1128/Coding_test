#include<bits/stdc++.h>
using namespace std;
const int MAX_SIZE = 1 << 19;

// 세그먼트 트리를 위한 노드 구조체
struct SegmentNode {
    int left, right, value;
    SegmentNode() : left(0), right(0), value(0) {}
    SegmentNode(int left_, int right_, int value_) : left(left_), right(right_), value(value_) {}
};

int elementCount, queryCount;
vector<SegmentNode> segmentTree(1 << 20);
int treeRoots[500005];

// 초기화: 트리를 기본 구조로 설정
void initializeTree() {
    for (int i = 1; i < MAX_SIZE; ++i) {
        segmentTree[i].left = i << 1;
        segmentTree[i].right = i << 1 | 1;
    }
}

// 세그먼트 트리 업데이트 함수
void updateTree(int node, int start, int end, int value, int index) {
    segmentTree[node].value += value;
    if (start != end) {  // 리프 노드가 아닌 경우
        int mid = (start + end) >> 1;
        int leftChild = segmentTree[node].left, rightChild = segmentTree[node].right;
        if (index <= mid) {
            segmentTree[node].left = segmentTree.size();
            segmentTree.push_back(segmentTree[leftChild]);
            updateTree(segmentTree[node].left, start, mid, value, index);
        }
        else {
            segmentTree[node].right = segmentTree.size();
            segmentTree.push_back(segmentTree[rightChild]);
            updateTree(segmentTree[node].right, mid + 1, end, value, index);
        }
    }
    else assert(start == index);
}

// XOR 연산을 활용한 쿼리
int xorQuery(int start, int end, int x) {
    int leftBound = 0, rightBound = MAX_SIZE - 1;
    int bitShift = 18;
    int result = 0;
    while (leftBound != rightBound) {
        int mid = (leftBound + rightBound) >> 1;
        int leftSize = segmentTree[segmentTree[end].left].value - segmentTree[segmentTree[start].left].value;
        int rightSize = segmentTree[segmentTree[end].right].value - segmentTree[segmentTree[start].right].value;
        if ((x & (1 << bitShift)) && leftSize || !rightSize) {
            start = segmentTree[start].left; 
            end = segmentTree[end].left;
            rightBound = mid;
        }
        else {
            result |= (1 << bitShift);
            start = segmentTree[start].right; 
            end = segmentTree[end].right;
            leftBound = mid + 1;
        }
        --bitShift;
    }
    assert(leftBound == rightBound);
    assert(bitShift == -1);
    return result;
}

// 특정 값 이하의 개수 구하기
int countLessThanOrEqual(int start, int end, int x) {
    int leftBound = 0, rightBound = MAX_SIZE - 1;
    int count = 0;
    while (leftBound != rightBound) {
        int mid = (leftBound + rightBound) >> 1;
        if (x <= mid) {
            start = segmentTree[start].left; 
            end = segmentTree[end].left;
            rightBound = mid;
        }
        else {
            count += segmentTree[segmentTree[end].left].value - segmentTree[segmentTree[start].left].value;
            end = segmentTree[end].right; 
            start = segmentTree[start].right;
            leftBound = mid + 1;
        }
    }
    assert(leftBound == rightBound);
    return count + (segmentTree[end].value - segmentTree[start].value);
}

// k번째 작은 원소 찾기
int findKthSmallest(int start, int end, int k) {
    int leftBound = 0, rightBound = MAX_SIZE - 1;
    while (leftBound != rightBound) {
        int mid = (leftBound + rightBound) >> 1;
        int leftSize = segmentTree[segmentTree[end].left].value - segmentTree[segmentTree[start].left].value;
        if (leftSize >= k) {
            start = segmentTree[start].left; 
            end = segmentTree[end].left;
            rightBound = mid;
        }
        else {
            start = segmentTree[start].right; 
            end = segmentTree[end].right;
            k -= leftSize;
            leftBound = mid + 1;
        }
    }
    assert(leftBound == rightBound);
    return leftBound;
}

int main() {
    cin.tie(nullptr); ios::sync_with_stdio(false);
    cin >> queryCount;
    initializeTree(); 
    treeRoots[0] = 1;
    for (int i = 0; i < queryCount; ++i) {
        int queryType; cin >> queryType;
        if (queryType == 1) {
            int value; cin >> value;
            treeRoots[++elementCount] = segmentTree.size();
            segmentTree.push_back(segmentTree[treeRoots[elementCount - 1]]);
            updateTree(treeRoots[elementCount], 0, MAX_SIZE - 1, 1, value);
        }
        else if (queryType == 2) {
            int left, right, x; cin >> left >> right >> x;
            cout << xorQuery(treeRoots[left - 1], treeRoots[right], x) << '\n';
        }
        else if (queryType == 3) {
            int x; cin >> x;
            elementCount -= x;
        }
        else if (queryType == 4) {
            int left, right, x; cin >> left >> right >> x;
            cout << countLessThanOrEqual(treeRoots[left - 1], treeRoots[right], x) << '\n';
        }
        else if (queryType == 5) {
            int left, right, k; cin >> left >> right >> k;
            cout << findKthSmallest(treeRoots[left - 1], treeRoots[right], k) << '\n';
        }
        else assert(false);
    }
    return 0;
}
