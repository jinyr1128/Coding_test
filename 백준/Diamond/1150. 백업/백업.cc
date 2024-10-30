#include <iostream>
#include <queue>

const int MAX_LEN = 100'001;

struct CableSegment {
    int cost, left, right;
    bool operator<(const CableSegment& other) const { return cost > other.cost; }
} segments[MAX_LEN];
int numCompanies, numCables, prevDistance, currDistance, result = 0;
std::priority_queue<CableSegment> segmentQueue;

int main() {
    std::cin >> numCompanies >> numCables >> prevDistance;
    
    for (int i = 1; i < numCompanies; ++i, prevDistance = currDistance) {
        std::cin >> currDistance;
        segments[i].cost = currDistance - prevDistance;
        segments[i].left = i - 1;
        segments[i].right = i + 1;
        segmentQueue.push({segments[i].cost, i, i + 1});
    }
    segments[numCompanies] = {0, numCompanies - 1, numCompanies + 1};

    for (int count = 0; count < numCables;) {
        CableSegment current = segmentQueue.top(); 
        segmentQueue.pop();
        
        if (current.left >= 1 && current.right <= numCompanies && 
            current.left == segments[current.right].left && 
            current.right == segments[current.left].right) { // 유효한 연결 경로 확인
            
            result += current.cost;
            ++count;

            int newLeft = segments[current.left].left;
            int newRight = segments[current.right].right;
            segments[newLeft].cost = segments[newLeft].cost + segments[current.right].cost - current.cost;
            current.cost = segments[newLeft].cost;
            current.left = newLeft;
            current.right = newRight;
            segments[newLeft].right = newRight;
            segments[newRight].left = newLeft;
            segmentQueue.push(current);
        }
    }
    
    std::cout << result;
}
