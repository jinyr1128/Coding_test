def dfs(graph, start, visited):
    stack = [start]
    count = 0
    
    while stack:
        node = stack.pop()
        if not visited[node]:
            visited[node] = True
            count += 1
            for neighbor in graph[node]:
                if not visited[neighbor]:
                    stack.append(neighbor)
    return count - 1 

n = int(input())  
m = int(input())  
graph = [[] for _ in range(n + 1)]  
visited = [False] * (n + 1)  

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

result = dfs(graph, 1, visited)
print(result)
