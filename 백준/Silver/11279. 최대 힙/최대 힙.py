import sys
import heapq

input = sys.stdin.read
data = input().split()

N = int(data[0])
operations = map(int, data[1:])

max_heap = []
results = []

for op in operations:
    if op == 0:
        if max_heap:
            # 최대값 출력 및 제거
            results.append(-heapq.heappop(max_heap))
        else:
            # 배열이 비어 있을 경우
            results.append(0)
    else:
        # 배열에 원소 추가 (최대 힙을 유지하기 위해 부호 반전)
        heapq.heappush(max_heap, -op)

print("\n".join(map(str, results)))
