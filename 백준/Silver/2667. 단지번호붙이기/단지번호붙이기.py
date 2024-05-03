def dfs(x, y):
    global count
    visited[x][y] = True
    count += 1
    for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
        nx, ny = x + dx, y + dy
        if 0 <= nx < n and 0 <= ny < n:
            if not visited[nx][ny] and graph[nx][ny] == 1:
                dfs(nx, ny)

n = int(input())
graph = [list(map(int, list(input().strip()))) for _ in range(n)]
visited = [[False] * n for _ in range(n)]
complexes = []

for i in range(n):
    for j in range(n):
        if graph[i][j] == 1 and not visited[i][j]:
            count = 0
            dfs(i, j)
            complexes.append(count)

complexes.sort()
print(len(complexes))
for size in complexes:
    print(size)
