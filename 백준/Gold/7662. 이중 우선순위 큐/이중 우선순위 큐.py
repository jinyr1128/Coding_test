import sys
import heapq

input = sys.stdin.read
data = input().split()

index = 0
T = int(data[index])
index += 1

results = []

for _ in range(T):
    k = int(data[index])
    index += 1
    min_heap = []
    max_heap = []
    entry_finder = {}  # 추적: 항목이 힙에 있는지 여부
    counter = {}  # 각 요소의 개수 추적
    REMOVED = '<removed>'  # 제거된 표시자

    def add_task(task):
        if task in counter:
            counter[task] += 1
        else:
            counter[task] = 1
        heapq.heappush(min_heap, task)
        heapq.heappush(max_heap, -task)
        entry_finder[task] = True

    def remove_task(task):
        if task in counter:
            if counter[task] > 1:
                counter[task] -= 1
            else:
                del counter[task]
                entry_finder[task] = False

    for _ in range(k):
        operation = data[index]
        number = int(data[index+1])
        index += 2

        if operation == 'I':
            add_task(number)
        elif operation == 'D':
            if number == 1:
                # 최대값 삭제
                while max_heap and not entry_finder.get(-max_heap[0], False):
                    heapq.heappop(max_heap)
                if max_heap:
                    remove_task(-heapq.heappop(max_heap))
            elif number == -1:
                # 최소값 삭제
                while min_heap and not entry_finder.get(min_heap[0], False):
                    heapq.heappop(min_heap)
                if min_heap:
                    remove_task(heapq.heappop(min_heap))

    # 최종 유효 원소 확인
    while min_heap and not entry_finder.get(min_heap[0], False):
        heapq.heappop(min_heap)
    while max_heap and not entry_finder.get(-max_heap[0], False):
        heapq.heappop(max_heap)

    if min_heap and max_heap:
        results.append(f"{-max_heap[0]} {min_heap[0]}")
    else:
        results.append("EMPTY")

print("\n".join(results))
