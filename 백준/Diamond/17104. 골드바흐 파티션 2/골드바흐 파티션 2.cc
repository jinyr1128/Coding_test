#define _USE_MATH_DEFINES
#include <bits/stdc++.h>
using namespace std;

// 복소수를 사용하는 타입 정의
typedef complex<double> Complex;
const int SIZE = 1 << 21;  // FFT 배열 크기 (2의 제곱수)

// 고속 푸리에 변환(FFT) 함수
void FFT(Complex arr[], bool inverse = false) {
    int n = SIZE;
    // 비트 반전 순서로 배열 재배치
    for (int i = 1, j = 0; i < n; ++i) {
        int bit = n / 2;
        while (!((j ^= bit) & bit)) bit /= 2;
        if (i < j) swap(arr[i], arr[j]);
    }
    // FFT 수행
    for (int len = 1; len < n; len *= 2) {
        double angle = (inverse ? M_PI / len : -M_PI / len);
        Complex w(cos(angle), sin(angle));
        for (int i = 0; i < n; i += len * 2) {
            Complex wp(1, 0);
            for (int j = 0; j < len; ++j) {
                Complex u = arr[i + j];
                Complex v = arr[i + j + len] * wp;
                arr[i + j] = u + v;
                arr[i + j + len] = u - v;
                wp *= w;
            }
        }
    }
    // 역변환 시 모든 값 나누기
    if (inverse) {
        for (int i = 0; i < n; ++i)
            arr[i] /= n;
    }
}

// FFT 제곱 함수: 자기 자신과의 곱셈을 수행
void SquareFFT(Complex arr[]) {
    FFT(arr);
    for (int i = 0; i < SIZE; i++) arr[i] *= arr[i];
    FFT(arr, true);
}

int isNotPrime[SIZE >> 1] = {1, 1};  // 소수 판별 배열 (0: 소수, 1: 합성수)
Complex primeArray[SIZE];  // 소수 마킹 배열

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    isNotPrime[0] = isNotPrime[1] = 1;

    // 에라토스테네스의 체로 소수 판별 및 마킹
    for (long long i = 2; i < 1000000; i++) {
        if (isNotPrime[i]) continue;
        primeArray[i] = Complex(1, 0);  // 소수를 1로 마킹

        for (long long j = i * i; j < 1000000; j += i) isNotPrime[j] = 1;  // 배수 지우기
    }

    // FFT 제곱으로 두 소수의 합 계산
    SquareFFT(primeArray);

    int testCaseCount; cin >> testCaseCount;
    while (testCaseCount--) {
        int n; cin >> n;
        int partitionCount = round(primeArray[n].real());  // n을 만들 수 있는 두 소수의 조합 수
        int middlePrimeFlag = !isNotPrime[n / 2];  // n/2가 소수인지 판별

        // 중복 제거 (두 수가 같은 경우)
        if (n / 2 % 2 == 0 && n / 2 > 2) middlePrimeFlag = 0;
        if (middlePrimeFlag) partitionCount--;

        cout << partitionCount / 2 + middlePrimeFlag << "\n";
    }
}
