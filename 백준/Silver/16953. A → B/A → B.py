a, b = map(int, input().split())
operations = 0

while b > a:
    if b % 2 == 0:
        b //= 2
    elif b % 10 == 1:
        b //= 10
    else:
        break
    operations += 1

if b == a:
    print(operations + 1)  # +1을 하는 이유는 최종 단계를 포함하기 때문입니다.
else:
    print(-1)
