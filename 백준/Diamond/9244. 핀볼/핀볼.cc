#include <cstdio>
#include <cassert>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

typedef long long ll;

// 방향 타입을 정의 (왼쪽, 오른쪽)
enum { LEFT_SIDE, RIGHT_SIDE };
// 이벤트 타입 (위쪽에서 내려오는 것, 아래쪽에서 올라가는 것 등)
enum { RIGHT_UP, RIGHT_DOWN, LEFT_DOWN, LEFT_UP };

// 2D 좌표를 나타내는 구조체
struct Coordinate {
    ll x, y;
    Coordinate() {}
    Coordinate(ll x, ll y) : x(x), y(y) {}
    void read() { scanf("%lld%lld", &x, &y); }
    Coordinate operator-(const Coordinate &p) const {
        return Coordinate(x - p.x, y - p.y);
    }
    ll crossProduct(const Coordinate &p) const {
        return x * p.y - y * p.x;
    }
};

// 선분을 나타내는 구조체
struct Segment {
    Coordinate left, right;
    int id;
    int side;
    
    void determineSide() { side = left.y < right.y ? LEFT_SIDE : RIGHT_SIDE; }
    void read(int index) {
        left.read();
        right.read();
        if (left.x > right.x) swap(left, right);
        id = index;
        determineSide();
    }
    ll getLowerX() { return side == LEFT_SIDE ? left.x : right.x; }
    bool isBetween(const Coordinate &p) const { return left.x <= p.x && p.x <= right.x; }
    bool isBelow(const Coordinate &p) const { return (right - left).crossProduct(p - left) < 0; }
    bool operator<(const Segment &b) const {
        if (id == b.id) return false;
        if (isBetween(b.left)) return isBelow(b.left);
        if (b.isBetween(left)) return !b.isBelow(left);
        assert(false);
        return id < b.id;
    }
    bool operator==(const Segment &b) const { return id == b.id; }
    Segment() {}
    Segment(int i, const Coordinate &l, const Coordinate &r) : left(l), right(r), id(i) {
        determineSide();
    }
};

// 이벤트를 나타내는 구조체
struct Event {
    ll x;
    int eventType;
    int segmentId;
    Event() {}
    void initialize(const Segment &segment, bool isLeft) {
        segmentId = segment.id;
        x = isLeft ? segment.left.x : segment.right.x;
        if (isLeft) {
            eventType = segment.side == LEFT_SIDE ? LEFT_DOWN : LEFT_UP;
        } else {
            eventType = segment.side == LEFT_SIDE ? RIGHT_UP : RIGHT_DOWN;
        }
    }
    bool operator<(const Event &e) const {
        if (x == e.x) return eventType > e.eventType;
        return x < e.x;
    }
};

int numSegments, numEvents;
Segment segments[100010];
Event events[200020];
int nextSegment[100010];

int main(void) {
    scanf("%d", &numSegments);
    for (int i = 0; i < numSegments; i++) segments[i].read(i);
    
    ll startX;
    scanf("%lld", &startX);
    
    segments[numSegments] = Segment(numSegments, Coordinate(startX - 1, 1000002), Coordinate(startX, 1000001));
    ++numSegments;
    numEvents = numSegments << 1;
    
    for (int i = 0; i < numSegments; i++) {
        events[i << 1].initialize(segments[i], true);
        events[(i << 1) | 1].initialize(segments[i], false);
    }
    
    sort(events, events + numEvents);
    set<Segment> activeSegments;
    
    for (int i = 0; i < numSegments; i++) nextSegment[i] = -2;
    for (int i = 0; i < numEvents; i++) {
        const Event &curEvent = events[i];
        Segment &curSegment = segments[curEvent.segmentId];
        
        if (curEvent.eventType == LEFT_UP) {
            activeSegments.insert(curSegment);
        } else if (curEvent.eventType == LEFT_DOWN) {
            activeSegments.insert(curSegment);
            auto it = activeSegments.find(curSegment); 
            it++;
            nextSegment[curSegment.id] = it == activeSegments.end() ? -1 : it->id;
        } else if (curEvent.eventType == RIGHT_UP) {
            activeSegments.erase(curSegment);
        } else { // RIGHT_DOWN
            auto it = activeSegments.find(curSegment); 
            it++;
            nextSegment[curSegment.id] = it == activeSegments.end() ? -1 : it->id;
            activeSegments.erase(curSegment);
        }
    }
    
    int currentSegment = numSegments - 1;
    while (nextSegment[currentSegment] >= 0) currentSegment = nextSegment[currentSegment];
    assert(nextSegment[currentSegment] != -2);
    printf("%lld\n", segments[currentSegment].getLowerX());
}