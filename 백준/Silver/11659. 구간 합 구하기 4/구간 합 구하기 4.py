import sys
input = sys.stdin.read

def solve():
    data = input().split()
    N = int(data[0])
    M = int(data[1])
    numbers = list(map(int, data[2:N+2]))
    queries = data[N+2:]
    
    # 누적 합 계산
    prefix_sum = [0] * (N + 1)
    for i in range(1, N+1):
        prefix_sum[i] = prefix_sum[i-1] + numbers[i-1]
    
    results = []
    # 질의 처리
    for k in range(M):
        i = int(queries[2*k])   # 2k because each query has two numbers (i, j)
        j = int(queries[2*k+1]) # 2k+1 to get the second number in the query
        results.append(str(prefix_sum[j] - prefix_sum[i-1]))
    
    # 결과 출력
    print("\n".join(results))

solve()
