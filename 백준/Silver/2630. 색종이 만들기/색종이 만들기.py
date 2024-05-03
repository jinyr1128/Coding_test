def count_papers(x, y, size):
    if size == 1:
        if paper[x][y] == 0:
            return (1, 0)  
        else:
            return (0, 1)  

    initial_color = paper[x][y]
    same_color = True
    for i in range(x, x + size):
        if not same_color:
            break
        for j in range(y, y + size):
            if paper[i][j] != initial_color:
                same_color = False
                break

    if same_color:
        if initial_color == 0:
            return (1, 0) 
        else:
            return (0, 1)  

    next_size = size // 2
    results = [(0, 0)] * 4  
    results[0] = count_papers(x, y, next_size)
    results[1] = count_papers(x, y + next_size, next_size)
    results[2] = count_papers(x + next_size, y, next_size)
    results[3] = count_papers(x + next_size, y + next_size, next_size)
    
    white = sum(result[0] for result in results)
    blue = sum(result[1] for result in results)
    return (white, blue)

n = int(input())
paper = [list(map(int, input().split())) for _ in range(n)]

white_count, blue_count = count_papers(0, 0, n)

print(white_count)
print(blue_count)
