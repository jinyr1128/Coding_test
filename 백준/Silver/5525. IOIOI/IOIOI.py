def find_PN_in_S(N, M, S):
    target_length = 2 * N + 1 
    count = 0
    i = 0
    
    while i <= M - target_length:
        if S[i] == 'I':
            valid = True
            for j in range(N):
                if i + 1 + 2*j >= M or S[i + 1 + 2*j] != 'O':
                    valid = False
                    break
                if i + 2 + 2*j >= M or S[i + 2 + 2*j] != 'I':
                    valid = False
                    break
            if valid:
                count += 1
                i += 2  
                continue
        i += 1
    
    return count

import sys
input = sys.stdin.read
data = input().split()

N = int(data[0])
M = int(data[1])
S = data[2]

print(find_PN_in_S(N, M, S))
