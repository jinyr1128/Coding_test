import sys
input = sys.stdin.read

def calc_cost(from_pos, to_pos):
    if from_pos == 0:
        return 2  # 중앙에서 다른 곳으로 이동
    if from_pos == to_pos:
        return 1  # 같은 곳을 다시 밟음
    if (from_pos == 1 and to_pos == 3) or (from_pos == 2 and to_pos == 4) or \
       (from_pos == 3 and to_pos == 1) or (from_pos == 4 and to_pos == 2):
        return 4  # 반대편으로 이동
    return 3  # 인접한 곳으로 이동

def solve(steps):
    inf = float('inf')
    # dp[left][right]는 왼쪽 발이 left에, 오른쪽 발이 right에 있을 때 최소 힘 사용량
    dp = {}
    dp[(0, 0)] = 0  # 초기 상태
    
    for step in steps:
        new_dp = {}
        for (left, right), cost in dp.items():
            # 왼쪽 발을 step으로 이동
            if step != right:  # 두 발이 같은 지점에 있을 수 없음
                next_cost = cost + calc_cost(left, step)
                if (step, right) not in new_dp:
                    new_dp[(step, right)] = next_cost
                else:
                    new_dp[(step, right)] = min(new_dp[(step, right)], next_cost)
            
            # 오른쪽 발을 step으로 이동
            if step != left:
                next_cost = cost + calc_cost(right, step)
                if (left, step) not in new_dp:
                    new_dp[(left, step)] = next_cost
                else:
                    new_dp[(left, step)] = min(new_dp[(left, step)], next_cost)
        
        dp = new_dp
    
    # 최소 힘 찾기
    return min(dp.values())

def main():
    data = list(map(int, input().strip().split()))
    steps = data[:-1]  # 마지막 0 제외
    print(solve(steps))

main()
