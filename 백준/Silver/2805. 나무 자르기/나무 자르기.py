def canCut(trees, height, M):
    total = 0
    for tree in trees:
        if tree > height:
            total += tree - height
        if total >= M:
            return True
    return False

def binarySearch(trees, M):
    low, high = 0, max(trees)
    result = 0
    while low <= high:
        mid = (low + high) // 2
        if canCut(trees, mid, M):
            result = mid
            low = mid + 1
        else:
            high = mid - 1
    return result

N, M = map(int, input().split())
trees = list(map(int, input().split()))

print(binarySearch(trees, M))
