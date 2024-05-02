def can_press(channel, broken):
    for ch in str(channel):
        if ch in broken:
            return False
    return True

def find_min_press(N, broken):
    # 최대 이동할 수 있는 채널 범위를 임의로 설정 (채널 0 ~ 999999)
    max_channel = 1000000
    # 직접 채널 번호를 눌러 이동하는 경우와 +/- 버튼을 사용하는 경우 중 최소값을 찾아야 함
    min_press = abs(N - 100)  # 현재 채널 100에서 시작, + or -만 사용하는 경우
    
    for channel in range(max_channel):
        if can_press(channel, broken):
            # 숫자 버튼을 사용해서 해당 채널로 이동하고, + 또는 -를 눌러 N으로 이동
            press_count = len(str(channel)) + abs(N - channel)
            min_press = min(min_press, press_count)
    
    return min_press

# 입력 받기
import sys
input = sys.stdin.read
data = input().split()
N = int(data[0])
M = int(data[1])
broken = set(data[2:]) if M > 0 else set()

# 최소 횟수 계산
result = find_min_press(N, broken)
print(result)
