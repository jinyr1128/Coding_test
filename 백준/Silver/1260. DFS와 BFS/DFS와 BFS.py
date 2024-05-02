from collections import deque
import sys
input = sys.stdin.read

def dfs(graph, start, visited):
    visited[start] = True
    dfs_result.append(start)
    for neighbor in sorted(graph[start]):
        if not visited[neighbor]:
            dfs(graph, neighbor, visited)

def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True
    while queue:
        vertex = queue.popleft()
        bfs_result.append(vertex)
        for neighbor in sorted(graph[vertex]):
            if not visited[neighbor]:
                visited[neighbor] = True
                queue.append(neighbor)

# 입력 받기
data = input().split()
n = int(data[0])  # 정점의 개수
m = int(data[1])  # 간선의 개수
v = int(data[2])  # 시작 정점

graph = [[] for _ in range(n + 1)]

index = 3
for _ in range(m):
    u = int(data[index])
    v2 = int(data[index + 1])
    graph[u].append(v2)
    graph[v2].append(u)
    index += 2

dfs_result = []
bfs_result = []

visited_dfs = [False] * (n + 1)
visited_bfs = [False] * (n + 1)

dfs(graph, v, visited_dfs)
bfs(graph, v, visited_bfs)

print(" ".join(map(str, dfs_result)))
print(" ".join(map(str, bfs_result)))
