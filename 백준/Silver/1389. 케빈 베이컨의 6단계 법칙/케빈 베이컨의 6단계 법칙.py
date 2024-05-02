from collections import deque
import sys
input = sys.stdin.read

def bfs(start):
    distances = [-1] * (n + 1)
    queue = deque([start])
    distances[start] = 0
    while queue:
        current = queue.popleft()
        for neighbor in graph[current]:
            if distances[neighbor] == -1:
                distances[neighbor] = distances[current] + 1
                queue.append(neighbor)
    return distances

# 입력 받기
data = input().split()
n = int(data[0])  # 유저의 수
m = int(data[1])  # 친구 관계의 수

# 그래프 초기화
graph = [[] for _ in range(n + 1)]
index = 2
for _ in range(m):
    a = int(data[index])
    b = int(data[index + 1])
    graph[a].append(b)
    graph[b].append(a)
    index += 2

# 각 사용자의 케빈 베이컨 수 계산
min_kevin_bacon = float('inf')
min_person = -1

for i in range(1, n + 1):
    distances = bfs(i)
    kevin_bacon = sum(filter(lambda x: x != -1, distances))
    if kevin_bacon < min_kevin_bacon:
        min_kevin_bacon = kevin_bacon
        min_person = i

# 결과 출력
print(min_person)
