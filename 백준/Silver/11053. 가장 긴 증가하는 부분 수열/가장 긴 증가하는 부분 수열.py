n = int(input())
A = list(map(int, input().split()))
dp = [1] * n  # 모든 요소가 최소한 길이 1의 LIS를 이루므로 1로 초기화

for i in range(n):
    for j in range(i):
        if A[j] < A[i]:
            dp[i] = max(dp[i], dp[j] + 1)

print(max(dp))  # dp 배열의 최댓값이 최장 증가 부분 수열의 길이
