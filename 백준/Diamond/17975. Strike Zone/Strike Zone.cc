#include <bits/stdc++.h>
using namespace std;

// 세그먼트 트리의 노드 구조체 정의
struct TreeNode {
    long long sum, maxVal, leftMax, rightMax;
    TreeNode() : sum(0), maxVal(0), leftMax(0), rightMax(0) { }
    
    // 두 노드를 합치는 연산 정의
    TreeNode operator+(TreeNode &other) {
        TreeNode result;
        result.sum = sum + other.sum;
        result.leftMax = max(leftMax, sum + other.leftMax);
        result.rightMax = max(other.rightMax, rightMax + other.sum);
        result.maxVal = max(rightMax + other.leftMax, max(maxVal, other.maxVal));
        return result;
    }
};

// 세그먼트 트리 클래스 정의
struct RangeSegmentTree {
    vector<TreeNode> nodes;
    int size;

    RangeSegmentTree(int n) {
        int power = 1;
        while (power < n) power *= 2;
        nodes.resize(power * 2);
        size = power;
    }

    void reset() {
        nodes.clear();
        nodes.resize(size * 2);
    }

    void assign(int index, long long value) {
        index += size - 1;
        nodes[index].sum = value;
        nodes[index].leftMax = value;
        nodes[index].rightMax = value;
        nodes[index].maxVal = value;
    }

    void build() {
        for (int i = size - 2; i >= 0; i--) {
            nodes[i] = nodes[i * 2 + 1] + nodes[i * 2 + 2];
        }
    }

    void update(int index, long long value) {
        int current = index + size - 1;
        nodes[current].sum += value;
        nodes[current].leftMax += value;
        nodes[current].rightMax += value;
        nodes[current].maxVal += value;
        while (current > 0) {
            current = (current - 1) / 2;
            nodes[current] = nodes[current * 2 + 1] + nodes[current * 2 + 2];
        }
    }

    long long queryMax(int left, int right) {
        left += size;
        right += size + 1;
        TreeNode leftResult, rightResult;
        while (left < right) {
            if (right & 1) {
                --right;
                rightResult = nodes[right - 1] + rightResult;
            }
            if (left & 1) {
                leftResult = leftResult + nodes[left - 1];
                ++left;
            }
            left >>= 1;
            right >>= 1;
        }
        return (leftResult + rightResult).maxVal;
    }
};

// 좌표와 가중치를 저장하는 구조체 정의
struct Coordinate {
    int x, y; long long weight;
    Coordinate(int x, int y, long long weight) : x(x), y(y), weight(weight) { }
    bool operator<(Coordinate &other) {
        return y < other.y;
    }
};

int main() {
    cin.tie(nullptr);
    cout.tie(nullptr);
    ios::sync_with_stdio(false);

    vector<Coordinate> coordinates;
    set<int> xCoords, yCoords;
    unordered_map<int, int> xIndex, yIndex;

    int positiveCount;
    cin >> positiveCount;
    for (int i = 0; i < positiveCount; i++) {
        int x, y;
        cin >> x >> y;
        xCoords.insert(x);
        yCoords.insert(y);
        coordinates.emplace_back(x, y, 1);
    }

    int negativeCount;
    cin >> negativeCount;
    for (int i = 0; i < negativeCount; i++) {
        int x, y;
        cin >> x >> y;
        xCoords.insert(x);
        yCoords.insert(y);
        coordinates.emplace_back(x, y, -1);
    }

    sort(coordinates.begin(), coordinates.end());
    long long weightPositive, weightNegative;
    cin >> weightPositive >> weightNegative;

    int index = 0;
    for (const auto& x : xCoords) {
        xIndex[x] = index++;
    }
    index = 0;
    for (const auto& y : yCoords) {
        yIndex[y] = index++;
    }

    int xSize = xCoords.size();
    int ySize = yCoords.size();
    int totalPoints = positiveCount + negativeCount;
    RangeSegmentTree segmentTree(xSize);

    int leftPointer = 0, rightPointer;
    long long maximumValue = LLONG_MIN;
    for (int i = 0; i < ySize; i++) {
        segmentTree.reset();
        rightPointer = leftPointer;
        for (int j = i; j < ySize; j++) {
            while (rightPointer < totalPoints && yIndex[coordinates[rightPointer].y] == j) {
                if (coordinates[rightPointer].weight == 1) {
                    segmentTree.update(xIndex[coordinates[rightPointer].x], weightPositive);
                } else {
                    segmentTree.update(xIndex[coordinates[rightPointer].x], -weightNegative);
                }
                ++rightPointer;
            }
            maximumValue = max(maximumValue, segmentTree.queryMax(0, xSize - 1));
        }
        while (leftPointer < totalPoints && yIndex[coordinates[leftPointer].y] == i) {
            leftPointer++;
        }
    }
    cout << maximumValue << '\n';
}
