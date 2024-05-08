import sys
input = sys.stdin.read

def main():
    data = input().splitlines()
    n, m = map(int, data[0].split())
    
    # 사이트 주소와 비밀번호를 저장할 딕셔너리 생성
    password_dict = {}
    
    # 사이트와 비밀번호를 딕셔너리에 저장
    for i in range(1, n + 1):
        site, password = data[i].split()
        password_dict[site] = password
    
    # 비밀번호 찾기
    result = []
    for j in range(n + 1, n + m + 1):
        result.append(password_dict[data[j]])
    
    # 결과 출력
    sys.stdout.write("\n".join(result) + "\n")

if __name__ == '__main__':
    main()
