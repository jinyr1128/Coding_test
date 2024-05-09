import sys
input = sys.stdin.read
inf = float('inf')

def floyd_warshall(n, bus_info):
    # 비용 초기화
    cost = [[inf] * n for _ in range(n)]
    for i in range(n):
        cost[i][i] = 0
    
    # 버스 정보로 비용 행렬 초기화
    for a, b, c in bus_info:
        if cost[a-1][b-1] > c:
            cost[a-1][b-1] = c

    # 플로이드-워셜 알고리즘 적용
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if cost[i][j] > cost[i][k] + cost[k][j]:
                    cost[i][j] = cost[i][k] + cost[k][j]

    # 결과 출력
    for i in range(n):
        for j in range(n):
            if cost[i][j] == inf:
                cost[i][j] = 0
        print(" ".join(map(str, cost[i])))

# 입력 처리
data = input().split()
index = 0
n = int(data[index])
index += 1
m = int(data[index])
index += 1
bus_info = []

for _ in range(m):
    a = int(data[index])
    b = int(data[index + 1])
    c = int(data[index + 2])
    index += 3
    bus_info.append((a, b, c))

floyd_warshall(n, bus_info)
