import math

def factorial(n):
    return math.factorial(n)

def combination(n, r):
    if r == 0 or n == r:
        return 1
    return factorial(n) // (factorial(r) * factorial(n - r))

n = []
m = []

number = int(input())

for i in range(number):
    n_var, m_var = map(int, input().split())
    n.append(n_var)
    m.append(m_var)

for i in range(number):
    print(combination(m[i], n[i]))