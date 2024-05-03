from collections import deque

def bfs(start, m, n, h):
    directions = [(1, 0, 0), (-1, 0, 0), (0, 1, 0), (0, -1, 0), (0, 0, 1), (0, 0, -1)]
    queue = deque(start)
    while queue:
        z, x, y = queue.popleft()
        for dz, dx, dy in directions:
            nz, nx, ny = z + dz, x + dx, y + dy
            if 0 <= nz < h and 0 <= nx < n and 0 <= ny < m and grid[nz][nx][ny] == 0:
                grid[nz][nx][ny] = grid[z][x][y] + 1
                queue.append((nz, nx, ny))

m, n, h = map(int, input().split())
grid = [[list(map(int, input().split())) for _ in range(n)] for _ in range(h)]
start = [(z, x, y) for z in range(h) for x in range(n) for y in range(m) if grid[z][x][y] == 1]

bfs(start, m, n, h)

max_days = 0
for layer in grid:
    for row in layer:
        for value in row:
            if value == 0:
                print(-1)
                exit()
            max_days = max(max_days, value)

print(max_days - 1)
