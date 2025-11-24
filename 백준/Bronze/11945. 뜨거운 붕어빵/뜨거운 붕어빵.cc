#include <iostream>
#include <string>
#include <algorithm> // reverse 함수를 사용하기 위해 필요

using namespace std;

int main() {
    // 입출력 속도 향상을 위한 설정 (필수는 아니지만 권장)
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M;
    // N과 M 입력
    if (cin >> N >> M) {
        // N번 반복
        for (int i = 0; i < N; ++i) {
            string line;
            cin >> line; // 한 줄 입력 받기

            // 문자열 뒤집기 (begin부터 end까지)
            reverse(line.begin(), line.end());

            // 뒤집힌 문자열 출력
            cout << line << "\n";
        }
    }

    return 0;
}