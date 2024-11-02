#include <iostream>
#include <algorithm>
#include <string>

typedef unsigned long long ULL;
const int MAX_SIZE = 1001;

std::string strA, strB;

ULL dp_matrix[2][MAX_SIZE], *current_dp, *previous_dp;
ULL bitset_cache[MAX_SIZE], char_bitsets[26][MAX_SIZE];

// 두 값의 차이를 계산하고 borrow 비트를 반환하는 함수
ULL calculate_borrow(ULL& a, ULL b) {
    ULL temp = a;
    return (a = temp - b) > temp;
}

int main() {
    // 두 문자열 입력
    std::cin >> strA >> strB;

    // bitset 사전 처리: 각 문자가 나타나는 위치를 비트로 표시하여 저장
    for (int j = 0; j < strB.length(); ++j)
        char_bitsets[strB[j] - 'A'][j >> 6] |= 1llu << (j & 63);

    // 초기화: 첫 번째 문자가 일치하는 경우 그 이후 비트들은 모두 1로 표시하여 종료
    for (int j = 0; j < strB.length(); ++j)
        if (strB[j] == strA[0]) {
            dp_matrix[0][j >> 6] |= 1llu << (j & 63);
            break;
        }

    previous_dp = dp_matrix[0];

    // 각 문자를 비교하며 dp 테이블 갱신
    for (int i = 1; i < strA.length(); ++i) {
        current_dp = dp_matrix[~i & 1];
        previous_dp = dp_matrix[i & 1];

        ULL carry = 1;
        ULL borrow = 0;
        ULL x, y, z;

        // x = 현재 캐릭터 비트셋 | 이전 dp
        // y = 이전 dp를 왼쪽으로 쉬프트하고 carry를 OR 연산
        // 최종적으로 x와 (x - y)의 차이를 계산해 새로운 dp에 저장
        for (int j = 0; j <= (strB.length() >> 6); ++j) {
            x = char_bitsets[strA[i] - 'A'][j] | current_dp[j];
            y = current_dp[j] << 1 | carry;
            carry = current_dp[j] >> 63;

            // 차이 계산
            z = x;
            borrow = calculate_borrow(z, borrow);
            borrow += calculate_borrow(z, y);

            previous_dp[j] = x & (x ^ z);
        }
        previous_dp[strB.length() >> 6] &= (1llu << (strB.length() & 63)) - 1;
    }

    int lcs_length = 0;
    // 최종 결과 계산: dp 배열을 통해 최장 공통 부분 수열 길이를 계산
    for (int j = 0; j < strB.length(); ++j)
        lcs_length += !!(previous_dp[j >> 6] & (1llu << (j & 63)));

    // 결과 출력
    std::cout << lcs_length;
}
