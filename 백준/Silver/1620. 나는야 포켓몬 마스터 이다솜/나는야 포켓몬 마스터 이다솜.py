import sys
input = sys.stdin.read

def main():
    data = input().splitlines()
    N, M = map(int, data[0].split())
    pokemon_to_number = {}
    number_to_pokemon = {}
    
    # 포켓몬 도감 정보 입력
    for i in range(1, N + 1):
        pokemon_name = data[i]
        pokemon_to_number[pokemon_name] = i
        number_to_pokemon[i] = pokemon_name
    
    # 문제 해결
    results = []
    for j in range(N + 1, N + 1 + M):
        query = data[j]
        if query.isdigit():
            results.append(number_to_pokemon[int(query)])
        else:
            results.append(str(pokemon_to_number[query]))
    
    # 결과 출력
    for result in results:
        print(result)

if __name__ == '__main__':
    main()
