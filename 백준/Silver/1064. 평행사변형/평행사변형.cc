#include <iostream>
#include <cmath>
#include <iomanip>
#include <algorithm>

using namespace std;

struct Point {
    double x, y;
};

double getLength(Point a, Point b) {
    return hypot(a.x - b.x, a.y - b.y);
}

// 두 벡터 (a -> b)와 (b -> c)가 평행한지 여부 확인
bool isCollinear(Point a, Point b, Point c) {
    return (b.x - a.x) * (c.y - a.y) == (b.y - a.y) * (c.x - a.x);
}

int main() {
    Point a, b, c;
    cin >> a.x >> a.y >> b.x >> b.y >> c.x >> c.y;

    // 세 점이 일직선 상에 있는지 확인
    if (isCollinear(a, b, c)) {
        cout << "-1.0" << endl;
        return 0;
    }

    // 각각의 변 길이 계산
    double abLen = getLength(a, b);
    double bcLen = getLength(b, c);
    double acLen = getLength(a, c);

    // 세 개의 평행사변형 둘레 계산
    double len1 = 2 * (abLen + bcLen);
    double len2 = 2 * (bcLen + acLen);
    double len3 = 2 * (abLen + acLen);

    // 최대 둘레와 최소 둘레의 차이 계산 및 출력
    double result = max({len1, len2, len3}) - min({len1, len2, len3});
    cout << fixed << setprecision(16) << result << endl;

    return 0;
}
