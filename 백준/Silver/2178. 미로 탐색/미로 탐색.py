from collections import deque

def bfs(maze, N, M):
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    queue = deque([(0, 0)])
    visited = [[False] * M for _ in range(N)]
    visited[0][0] = True
    distance = [[1] * M for _ in range(N)]
    
    while queue:
        x, y = queue.popleft()
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < M and maze[nx][ny] == '1' and not visited[nx][ny]:
                queue.append((nx, ny))
                visited[nx][ny] = True
                distance[nx][ny] = distance[x][y] + 1
    
    return distance[N-1][M-1]

def main():
    import sys
    input = sys.stdin.read
    data = input().split()
    N, M = int(data[0]), int(data[1])
    maze = [data[i + 2] for i in range(N)]
    
    print(bfs(maze, N, M))

main()
