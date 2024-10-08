import math

# 첫 번째 분수의 분자와 분모 입력
a1, b1 = map(int, input().split())
# 두 번째 분수의 분자와 분모 입력
a2, b2 = map(int, input().split())

# 두 분수의 공통 분모를 구하기 위해 각 분모의 곱 사용
numerator = a1 * b2 + a2 * b1  # 분자 계산
denominator = b1 * b2          # 분모 계산

# 최대공약수를 사용해 기약분수로 변환
gcd = math.gcd(numerator, denominator)
numerator //= gcd
denominator //= gcd

# 결과 출력
print(numerator, denominator)
