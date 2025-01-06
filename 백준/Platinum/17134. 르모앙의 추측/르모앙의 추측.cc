#include <iostream>
#include <complex>
#include <vector>
#include <cstring>
#include <string>
#include <algorithm>

// FFT 기반 소수와 세미소수 계산 프로그램
using namespace std;
typedef complex<double> Complex;
typedef long long ll;
const double PI = acos(-1);

// FFT 구현 함수
void performFFT(vector<Complex>& data, bool inverse = false) {
    int size = data.size(), index = 0;
    vector<Complex> roots(size / 2);

    // 데이터 재배치 (비트 반전)
    for (int i = 1; i < size; i++) {
        int bit = size >> 1;
        while (index >= bit) {
            index -= bit;
            bit >>= 1;
        }
        index += bit;
        if (i < index) swap(data[i], data[index]);
    }

    // 복소수 각도 계산
    double angle = 2 * PI / size * (inverse ? -1 : 1);
    for (int i = 0; i < size / 2; i++) {
        roots[i] = Complex(cos(angle * i), sin(angle * i));
    }

    // FFT 수행 (분할 정복)
    for (int len = 2; len <= size; len <<= 1) {
        int step = size / len;
        for (int i = 0; i < size; i += len) {
            for (int j = 0; j < len / 2; j++) {
                Complex u = data[i + j];
                Complex v = data[i + j + len / 2] * roots[step * j];
                data[i + j] = u + v;
                data[i + j + len / 2] = u - v;
            }
        }
    }

    // 역변환 시 정규화
    if (inverse) {
        for (int i = 0; i < size; i++) data[i] /= size;
    }
}

// 두 벡터의 곱셈 연산 (FFT 이용)
vector<ll> multiplyPolynomials(vector<ll>& poly1, vector<ll>& poly2) {
    vector<Complex> comp1(poly1.begin(), poly1.end()), comp2(poly2.begin(), poly2.end());

    // 벡터 크기를 2의 제곱수로 조정
    int n = 2;
    while (n < poly1.size() + poly2.size()) n <<= 1;
    comp1.resize(n);
    comp2.resize(n);

    // FFT 적용
    performFFT(comp1, false);
    performFFT(comp2, false);

    // 요소별 곱
    for (int i = 0; i < n; i++) comp1[i] *= comp2[i];

    // 역 FFT
    performFFT(comp1, true);

    // 결과 저장
    vector<ll> result(n);
    for (int i = 0; i < n; i++) result[i] = (ll)round(comp1[i].real());
    return result;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    // 소수 판별 배열 초기화
    bool isPrime[1000004];
    memset(isPrime, true, sizeof(isPrime));

    // 에라토스테네스의 체로 소수 계산
    for (int i = 2; i <= 1000000; i++) {
        if (isPrime[i]) {
            for (int j = 2; i * j <= 1000000; j++) {
                isPrime[i * j] = false;
            }
        }
    }

    // 홀수 소수 및 세미소수 배열 생성
    vector<ll> oddPrimes(1000004), semiPrimes(1000004);
    for (int i = 2; i <= 1000000; i++) {
        if (isPrime[i]) {
            if (i * 2 <= 1000000) semiPrimes[i * 2] = 1; // 세미소수 저장
            if (i % 2 == 1) oddPrimes[i] = 1; // 홀수 소수 저장
        }
    }

    // FFT를 이용한 다항식 곱셈 수행
    vector<ll> result = multiplyPolynomials(oddPrimes, semiPrimes);

    // 결과값 출력
    int testCases, query;
    cin >> testCases;
    while (testCases--) {
        cin >> query;
        cout << result[query] << "\n";
    }

    return 0;
}
