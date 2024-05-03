def find_kaing_year(M, N, x, y):
    k = x  # 시작하는 해, x는 1부터 M까지 순회
    while k <= M * N:  # M과 N의 최소공배수까지 순회 가능
        if (k - y) % N == 0:  # k가 x로 시작하고 y와 매칭되는지 체크
            return k
        k += M
    return -1  # 유효한 해를 찾지 못한 경우

# 입력 받기
import sys
input = sys.stdin.read
data = input().split()

T = int(data[0])
index = 1
results = []

for _ in range(T):
    M = int(data[index])
    N = int(data[index + 1])
    x = int(data[index + 2])
    y = int(data[index + 3])
    index += 4

    # 카잉 달력 해 찾기
    result = find_kaing_year(M, N, x, y)
    results.append(result)

# 결과 출력
for result in results:
    print(result)
