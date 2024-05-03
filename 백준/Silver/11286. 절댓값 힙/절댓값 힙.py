import sys
import heapq

input = sys.stdin.read
data = input().split()

N = int(data[0])
operations = map(int, data[1:])

min_heap = []
results = []

for op in operations:
    if op == 0:
        if min_heap:
            # 힙에서 가장 작은 원소 추출, (절댓값, 원본 값) 튜플 형태
            results.append(heapq.heappop(min_heap)[1])
        else:
            # 힙이 비어있으면 0 출력
            results.append(0)
    else:
        # (절댓값, 원본 값) 튜플로 힙에 추가
        heapq.heappush(min_heap, (abs(op), op))

print("\n".join(map(str, results)))
