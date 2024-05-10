import sys
import heapq

input = sys.stdin.read
data = input().split()

N = int(data[0])
K = int(data[1])

jewels = []
bags = []

index = 2
for _ in range(N):
    M, V = int(data[index]), int(data[index+1])
    jewels.append((M, V))
    index += 2

for _ in range(K):
    C = int(data[index])
    bags.append(C)
    index += 1

# 보석을 무게에 따라 오름차순으로 정렬
jewels.sort()
# 가방을 용량에 따라 오름차순으로 정렬
bags.sort()

total_value = 0
valid_jewels = []
j = 0

# 각 가방에 대하여 처리
for bag_capacity in bags:
    # 현재 가방에 들어갈 수 있는 보석들을 힙에 넣음
    while j < len(jewels) and jewels[j][0] <= bag_capacity:
        heapq.heappush(valid_jewels, -jewels[j][1])  # 최대 힙을 사용하기 위해 -를 사용
        j += 1
    
    # 가방에 넣을 수 있는 보석 중 가장 가치가 높은 것을 선택
    if valid_jewels:
        total_value += -heapq.heappop(valid_jewels)

print(total_value)
