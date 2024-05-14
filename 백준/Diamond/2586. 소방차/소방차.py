import sys
input = sys.stdin.read
from itertools import groupby

def main():
    data = input().split()
    
    n = int(data[0])
    m = int(data[1])
    
    pumps = [(int(data[i + 2]), 0) for i in range(n)]
    fire_trucks = [(int(data[i + 2 + n]), 1) for i in range(m)]
    
    positions = sorted(pumps + fire_trucks, key=lambda x: x[0])
    
    total_positions = n + m
    v = [[] for _ in range(2 * total_positions)]
    
    now = total_positions
    
    for pos in positions:
        if pos[1] == 0:
            v[now].append(pos)
            now += 1
        else:
            now -= 1
            v[now].append(pos)
    
    ans = 0
    
    for group in v:
        if not group:
            continue
        t = 0
        for j in range(1, len(group), 2):
            t += abs(group[j][0] - group[j - 1][0])
        
        if len(group) % 2 == 0:
            ans += t
            continue
        
        mn = t
        for j in range(len(group) - 1, 1, -2):
            t += abs(group[j][0] - group[j - 1][0]) - abs(group[j - 1][0] - group[j - 2][0])
            mn = min(mn, t)
        ans += mn
    
    print(ans)

if __name__ == "__main__":
    main()

