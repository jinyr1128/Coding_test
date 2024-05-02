def minimize_expression(expression):
    # '-'를 기준으로 식을 나눕니다.
    tokens = expression.split('-')
    initial_value = sum(map(int, tokens[0].split('+')))  # 첫 번째 토큰은 '+'만 계산하면 됩니다.
    
    # 나머지 토큰들을 처리하며, 각각의 토큰에서 '+'로 연결된 수들을 모두 더해서 최초 값에서 뺍니다.
    for token in tokens[1:]:
        # 각 부분을 '+'로 나눠서 합을 구한 후 전체 합에서 빼줍니다.
        sub_sum = sum(map(int, token.split('+')))
        initial_value -= sub_sum
        
    return initial_value

# 입력 받기
expression = input()
# 결과 출력
print(minimize_expression(expression))
