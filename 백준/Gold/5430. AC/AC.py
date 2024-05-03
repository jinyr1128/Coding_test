import sys
from collections import deque

input = sys.stdin.read
data = input().split()

T = int(data[0])
index = 1
results = []

for _ in range(T):
    p = data[index]
    n = int(data[index + 1])
    if n == 0:
        arr = deque()
    else:
        arr = deque(map(int, data[index + 2].strip('[]').split(',')))
    
    index += 3
    is_reversed = False
    error = False
    
    for command in p:
        if command == 'R':
            is_reversed = not is_reversed
        elif command == 'D':
            if not arr:
                results.append("error")
                error = True
                break
            if is_reversed:
                arr.pop()
            else:
                arr.popleft()
    
    if not error:
        if is_reversed:
            arr.reverse()
        results.append("[" + ",".join(map(str, arr)) + "]")

for result in results:
    print(result)
