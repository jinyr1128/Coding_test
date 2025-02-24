#include <bits/stdc++.h>
#define coordX first
#define coordY second
using namespace std;

typedef long long ll;
typedef pair<ll, ll> Point;

int totalSegments, holesToDrill;
ll horizontalX[1 << 18];
ll horizontalY[1 << 18];

// Segment tree to store minimum heights and their indices
Point segmentTree[1 << 19];
int segmentBias = 1 << 18;

// Segment tree initialization
void buildSegmentTree() {
    for (int i = 0; i < totalSegments; i++) {
        segmentTree[i | segmentBias] = {horizontalY[i], i};
    }
    for (int i = segmentBias - 1; i > 0; i--) {
        segmentTree[i] = min(segmentTree[i << 1], segmentTree[i << 1 | 1]);
    }
}

// Query function to find the minimum y-coordinate in a given range
int findMinHeightIndex(int left, int right) {
    left |= segmentBias;
    right |= segmentBias;
    Point result = {LLONG_MAX, LLONG_MAX};
    while (left <= right) {
        if (left & 1) result = min(result, segmentTree[left++]);
        if (~right & 1) result = min(result, segmentTree[right--]);
        left >>= 1;
        right >>= 1;
    }
    return result.coordY;
}

// Priority queue to store the area of removed water
priority_queue<ll> removedWaterVolumes;
int currentWaterHeight;

// Recursive function to calculate the maximum water drained using divide and conquer
ll calculateMaxDrain(int start, int end) {
    if (start >= end) return 0;
    
    int minHeightIndex = findMinHeightIndex(start, end - 1);
    int previousHeight = currentWaterHeight;
    currentWaterHeight = horizontalY[minHeightIndex];
    
    ll leftDrain = calculateMaxDrain(start, minHeightIndex);
    ll rightDrain = calculateMaxDrain(minHeightIndex + 1, end);
    
    // Store the minimum drained water area for possible hole placement
    removedWaterVolumes.push(min(leftDrain, rightDrain));
    
    currentWaterHeight = previousHeight;
    // Return the max of left and right drained area plus current area drained by the hole
    return max(leftDrain, rightDrain) + (horizontalX[end] - horizontalX[start]) * (horizontalY[minHeightIndex] - currentWaterHeight);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    cin >> totalSegments; 
    totalSegments >>= 1;  // Each horizontal segment uses two points
    
    // Input the aquarium boundary coordinates
    for (int i = 0; i < totalSegments; i++) {
        cin >> horizontalX[i] >> horizontalY[i];
        cin >> horizontalX[i] >> horizontalY[i];
    }
    
    cin >> holesToDrill;
    buildSegmentTree();
    
    // Initial full drain of the aquarium
    ll initialDrain = calculateMaxDrain(0, totalSegments - 1);
    removedWaterVolumes.push(initialDrain);
    
    // Collect the maximum drained water by placing k holes
    ll maxDrainedWater = 0;
    for (int i = 0; i < holesToDrill; i++) {
        if (removedWaterVolumes.empty()) break;
        maxDrainedWater += removedWaterVolumes.top();
        removedWaterVolumes.pop();
    }
    
    cout << maxDrainedWater << '\n';
    return 0;
}