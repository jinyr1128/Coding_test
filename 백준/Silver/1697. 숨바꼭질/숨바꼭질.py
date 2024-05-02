from collections import deque

def find_min_time(N, K):
    if N >= K:
        return N - K  # 수빈이가 동생보다 뒤에 있거나 같은 위치에 있을 경우, 걸어가야 하는 최소 거리
    
    # BFS를 위한 준비
    queue = deque([N])
    visited = [False] * 100001
    visited[N] = True
    time = [0] * 100001
    
    # BFS 실행
    while queue:
        current = queue.popleft()
        
        # 가능한 이동 옵션 체크
        for next_position in (current - 1, current + 1, current * 2):
            if 0 <= next_position <= 100000 and not visited[next_position]:
                visited[next_position] = True
                time[next_position] = time[current] + 1
                queue.append(next_position)
                if next_position == K:
                    return time[next_position]

    return time[K]

# 입력
N, K = map(int, input().split())
# 결과 출력
print(find_min_time(N, K))
