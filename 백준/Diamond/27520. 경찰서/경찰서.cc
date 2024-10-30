#include <iostream>
#include <algorithm>
#include <cmath>

typedef long long ll;
typedef long double ld;
const ld TOL = 1e-7;
const ll LEN = 20000;

int N;
ld D;
bool z(ld x) { return std::abs(x) < TOL; }
int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

struct Vector {
    ld x, y;
    Vector operator-() const { return {-x, -y}; }
    ld operator*(const Vector& other) const { return y * other.x - x * other.y; }
    bool operator<(const Vector& other) const { return z(y - other.y) ? x < other.x : y < other.y; }
} hull[LEN * 2];

const Vector Zero = {0, 0};

ld dist(const Vector& p1, const Vector& p2) {
    return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
}

ld cross(const Vector& p1, const Vector& p2, const Vector& p3) {
    return (p2.x - p1.x) * (p3.y - p2.y) - (p2.y - p1.y) * (p3.x - p2.x);
}

void normalize(Vector& d1, Vector& d2) { 
    if (cross(Zero, d2, d1) > 0) std::swap(d1, d2); 
}

struct Line {
    Vector slope;
    ld c;
    ld operator*(const Line& other) const { return slope * other.slope; }
    Line operator-() const { return {-slope, -c}; }
    Line operator+(const ld& scalar) const { return {slope, c + hypot(slope.x, slope.y) * scalar}; }
    bool operator<(const Line& other) const {
        bool leftIsLess = Zero < slope;
        bool rightIsLess = Zero < other.slope;
        if (leftIsLess ^ rightIsLess) return leftIsLess;
        ld ccw = slope * other.slope;
        return z(ccw) ? c * hypot(other.slope.x, other.slope.y) < other.c * hypot(slope.x, slope.y) : ccw > 0;
    }
} planes[LEN], dq[LEN * 2];

Line createLine(const Vector& s, const Vector& p) { 
    return {s, s.y * p.x + s.x * p.y}; 
}

Vector intersect(const Line& l1, const Line& l2) {
    ld det = l1.slope * l2.slope;
    return {(l1.c * l2.slope.x - l1.slope.x * l2.c) / det, (l1.slope.y * l2.c - l1.c * l2.slope.y) / det};
}

bool clockwise(const Line& l1, const Line& l2, const Line& target) {
    if (l1.slope * l2.slope < 0 + TOL) return false;
    Vector p = intersect(l1, l2);
    return target.slope.y * p.x + target.slope.x * p.y > target.c - TOL;
}

int hpi_len;
int half_plane_intersection(ld m) {
    hpi_len = 0;
    int head = 0, tail = 0;
    for (int i = 0; i < N; ++i) {
        Line l = planes[i] + m;
        if (head - tail && z(dq[head - 1] * l)) continue;
        while (head - tail >= 2 && clockwise(dq[head - 2], dq[head - 1], l)) --head;
        while (head - tail >= 2 && clockwise(l, dq[tail], dq[tail + 1])) ++tail;
        dq[head++] = l;
    }
    for (int i = 0; i < N; ++i) {
        Line l = -planes[i] + m;
        if (head - tail && z(dq[head - 1] * l)) continue;
        while (head - tail >= 2 && clockwise(dq[head - 2], dq[head - 1], l)) --head;
        while (head - tail >= 2 && clockwise(l, dq[tail], dq[tail + 1])) ++tail;
        dq[head++] = l;
    }

    while (head - tail >= 3 && clockwise(dq[head - 2], dq[head - 1], dq[tail])) --head;
    while (head - tail >= 3 && clockwise(dq[head - 1], dq[tail], dq[tail + 1])) ++tail;

    for (int i = tail; i < head; ++i) {
        Line cur = dq[i], nxt = dq[i == head - 1 ? tail : i + 1];
        if (cur * nxt < TOL) {
            return hpi_len = 0;
        }
        hull[hpi_len++] = intersect(cur, nxt);
    }
    return hpi_len;
}

ld binary_search() {
    ld l = 0, r = 1e17, m, ret = r;
    while (l < r) {
        m = (l + r) / 2;
        if (half_plane_intersection(m)) {
            ret = std::min(ret, m);
            r = m - TOL;
        }
        else l = m + TOL;
    }
    return ret;
}

int main() {
    std::cin.tie(0)->sync_with_stdio(0);
    std::cin >> N;
    for (int i = 0, a, b, c, d; i < N; ++i) {
        std::cin >> a >> b >> c; c *= -1;
        d = gcd(std::abs(a), std::abs(b));
        ld dx = b / d;
        ld dy = a / d;
        if (dy > 0 || (a == 0 && dx > 0)) { dx *= -1; dy *= -1; c *= -1; }
        planes[i] = { {dx, dy}, (ld)c / d };
    }
    std::sort(planes, planes + N);
    ld ret = N <= 2 ? 0 : binary_search();
    std::cout << std::fixed;
    std::cout.precision(7);
    std::cout << ret;
}
