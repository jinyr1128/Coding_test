import sys
import heapq

input = sys.stdin.read
def solve():
    data = input().split()
    N = int(data[0])
    
    min_heap = []
    result = []
    
    for i in range(1, N + 1):
        x = int(data[i])
        
        if x == 0:
            if min_heap:
                result.append(heapq.heappop(min_heap))
            else:
                result.append(0)
        else:
            heapq.heappush(min_heap, x)
    
    print('\n'.join(map(str, result)))

solve()
