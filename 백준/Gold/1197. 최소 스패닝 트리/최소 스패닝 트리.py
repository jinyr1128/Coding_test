import sys
input = sys.stdin.read
from heapq import heappop, heappush

class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.rank = [0] * n

    def find(self, u):
        if u != self.parent[u]:
            self.parent[u] = self.find(self.parent[u])
        return self.parent[u]

    def union(self, u, v):
        root_u = self.find(u)
        root_v = self.find(v)
        if root_u != root_v:
            if self.rank[root_u] > self.rank[root_v]:
                self.parent[root_v] = root_u
            elif self.rank[root_u] < self.rank[root_v]:
                self.parent[root_u] = root_v
            else:
                self.parent[root_v] = root_u
                self.rank[root_u] += 1
            return True
        return False

def kruskal(n, edges):
    uf = UnionFind(n)
    mst_cost = 0
    edges.sort(key=lambda x: x[2])  # 간선을 가중치에 따라 정렬

    for u, v, weight in edges:
        if uf.union(u - 1, v - 1):  # 1번 부터 V번까지가 입력으로 주어지므로 0-indexed로 조정
            mst_cost += weight

    return mst_cost

data = input().split()
V, E = int(data[0]), int(data[1])
edges = []

index = 2
for _ in range(E):
    A, B, C = int(data[index]), int(data[index + 1]), int(data[index + 2])
    edges.append((A, B, C))
    index += 3

print(kruskal(V, edges))
