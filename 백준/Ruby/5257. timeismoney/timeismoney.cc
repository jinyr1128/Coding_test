#include <iostream>
#include <algorithm>
#include <cstring>

typedef long long int64; // Define int64 for readability
const int MAX_EDGES = 10000;
const int MAX_VERTICES = 201;
const int64 LARGE_VAL = 1e9;

int city_count, road_count;

// Position structure with two values x and y, and a custom function for product
struct Position { 
    int64 time_sum, cost_sum; 
    int64 compute_value() const { return time_sum * cost_sum; } 
};

// Calculate the cross product for three positions (used for convex hull checks)
int64 calculate_cross(const Position& a, const Position& b, const Position& c) { 
    return (b.time_sum - a.time_sum) * (c.cost_sum - b.cost_sum) - 
           (b.cost_sum - a.cost_sum) * (c.time_sum - b.time_sum); 
}

// ccw function to find the orientation of triplet points (clockwise, counter-clockwise, collinear)
int calculate_ccw(const Position& a, const Position& b, const Position& c) {
    int64 cross_product = calculate_cross(a, b, c);
    return cross_product > 0 ? 1 : cross_product < 0 ? -1 : 0;
}

int parent[MAX_VERTICES]; // Disjoint-set parent array

// Find with path compression
int find_parent(int idx) { 
    return parent[idx] < 0 ? idx : parent[idx] = find_parent(parent[idx]); 
}

// Union of two sets; returns true if union was successful
int union_sets(int u, int v) { 
    u = find_parent(u), v = find_parent(v);
    if (u == v) return 0;
    if (parent[u] < parent[v]) parent[u] += parent[v], parent[v] = u;
    else parent[v] += parent[u], parent[u] = v;
    return 1;
}

int64 time_weight, cost_weight;

struct Road {
    int64 time, cost;
    int start, end;
    int64 compute_priority() const { return time_weight * time + cost_weight * cost; }
    bool operator<(const Road& other) const { return compute_priority() < other.compute_priority(); }
} connections[MAX_EDGES];

Position calculate_mst(int64 time_w, int64 cost_w) {
    time_weight = time_w, cost_weight = cost_w;
    Position result = {0, 0};
    std::sort(connections, connections + road_count);
    std::memset(parent, -1, sizeof parent);
    for (int i = 0, selected = 0; i < road_count && selected < city_count - 1; ++i) {
        if (union_sets(connections[i].start, connections[i].end)) {
            ++selected;
            result.time_sum += connections[i].time;
            result.cost_sum += connections[i].cost;
        }
    }
    return result;
}

Position optimal_mst;
int64 optimal_time_weight, optimal_cost_weight;

// Recursive function to adjust weights and find the optimal MST configuration
void find_optimal(Position s, Position e) {
    int64 time_diff = e.time_sum - s.time_sum;
    int64 cost_diff = s.cost_sum - e.cost_sum;
    Position mid = calculate_mst(cost_diff, time_diff);
    if (mid.compute_value() < optimal_mst.compute_value()) {
        optimal_mst = mid;
        optimal_time_weight = cost_diff, optimal_cost_weight = time_diff;
    }
    if (calculate_ccw(s, e, mid) >= 0) return;
    find_optimal(s, mid);
    find_optimal(mid, e);
}

int main() {
    std::cin.tie(0)->sync_with_stdio(0);
    std::cin >> city_count >> road_count;
    
    // Reading input connections between towns with time and cost
    for (int i = 0; i < road_count; ++i) 
        std::cin >> connections[i].start >> connections[i].end >> connections[i].time >> connections[i].cost;

    Position start_mst = calculate_mst(256, 1);
    Position end_mst = calculate_mst(1, 256);

    if (start_mst.compute_value() < end_mst.compute_value()) 
        optimal_mst = start_mst, optimal_time_weight = 256, optimal_cost_weight = 1;
    else 
        optimal_mst = end_mst, optimal_time_weight = 1, optimal_cost_weight = 256;

    // Recursively find the optimal MST path by adjusting time and cost weights
    find_optimal(start_mst, end_mst);
    
    // Output the optimal time and cost sums
    std::cout << optimal_mst.time_sum << ' ' << optimal_mst.cost_sum << '\n';

    // Recalculate the MST with optimal weights and output the connections
    time_weight = optimal_time_weight, cost_weight = optimal_cost_weight;
    std::sort(connections, connections + road_count);
    std::memset(parent, -1, sizeof parent);
    for (int i = 0, selected = 0; i < road_count && selected < city_count - 1; ++i) {
        if (union_sets(connections[i].start, connections[i].end)) {
            ++selected;
            std::cout << connections[i].start << ' ' << connections[i].end << '\n';
        }
    }
}
