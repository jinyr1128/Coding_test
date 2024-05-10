def spread_dust():
    temp = [[0] * C for _ in range(R)]
    for x in range(R):
        for y in range(C):
            if room[x][y] > 0:
                spread_amount = room[x][y] // 5
                count = 0
                for dx, dy in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
                    nx, ny = x + dx, y + dy
                    if 0 <= nx < R and 0 <= ny < C and room[nx][ny] != -1:
                        temp[nx][ny] += spread_amount
                        count += 1
                room[x][y] -= spread_amount * count
    for x in range(R):
        for y in range(C):
            room[x][y] += temp[x][y]

def activate_purifier():
    upper, lower = purifier
    # Upper part
    for i in range(upper - 1, 0, -1):
        room[i][0] = room[i - 1][0]
    for i in range(C - 1):
        room[0][i] = room[0][i + 1]
    for i in range(upper):
        room[i][C - 1] = room[i + 1][C - 1]
    for i in range(C - 1, 1, -1):
        room[upper][i] = room[upper][i - 1]
    room[upper][1] = 0
    # Lower part
    for i in range(lower + 1, R - 1):
        room[i][0] = room[i + 1][0]
    for i in range(C - 1):
        room[R - 1][i] = room[R - 1][i + 1]
    for i in range(R - 1, lower, -1):
        room[i][C - 1] = room[i - 1][C - 1]
    for i in range(C - 1, 1, -1):
        room[lower][i] = room[lower][i - 1]
    room[lower][1] = 0

def simulate():
    for _ in range(T):
        spread_dust()
        activate_purifier()
    total_dust = 0
    for i in range(R):
        for j in range(C):
            if room[i][j] > 0:
                total_dust += room[i][j]
    return total_dust

R, C, T = map(int, input().split())
room = [list(map(int, input().split())) for _ in range(R)]
purifier = [i for i in range(R) if room[i][0] == -1]

print(simulate())
