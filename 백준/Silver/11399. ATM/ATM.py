n = int(input())
times = list(map(int, input().split()))

# 인출 시간이 짧은 사람부터 정렬
times.sort()

# 최소 대기 시간 계산
total_wait_time = 0
cumulative_time = 0

for time in times:
    cumulative_time += time
    total_wait_time += cumulative_time

print(total_wait_time)
