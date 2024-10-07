#include <iostream>
#include <vector>
using namespace std;

// 큰 수 덧셈 함수
vector<int> addBigNumbers(const vector<int>& a, const vector<int>& b) {
    vector<int> result;
    int carry = 0;
    int maxSize = max(a.size(), b.size());

    for (int i = 0; i < maxSize || carry; i++) {
        int sum = carry;
        if (i < a.size()) sum += a[i];
        if (i < b.size()) sum += b[i];
        result.push_back(sum % 10);  // 자릿수 저장
        carry = sum / 10;            // 올림 처리
    }

    return result;
}

// 피보나치 수열 계산 함수
vector<int> fibonacci(int n) {
    if (n == 1 || n == 2) return {1};

    vector<int> a = {1};  // f(1) = 1
    vector<int> b = {1};  // f(2) = 1
    vector<int> fib;

    for (int i = 3; i <= n; i++) {
        fib = addBigNumbers(a, b);
        a = b;
        b = fib;
    }

    return fib;
}

int main() {
    int n;
    cin >> n;

    vector<int> result = fibonacci(n);

    // 큰 수 출력 (역순으로 저장되어 있음)
    for (auto it = result.rbegin(); it != result.rend(); ++it) {
        cout << *it;
    }
    cout << endl;

    return 0;
}
