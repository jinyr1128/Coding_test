import sys
input = sys.stdin.read

def solve():
    data = input().split()
    N = int(data[0])
    M = int(data[1])
    
    unheard = set(data[2:N+2])
    unseen = set(data[N+2:N+2+M])
    
    result = sorted(unheard & unseen)
    
    print(len(result))
    for name in result:
        print(name)

solve()
