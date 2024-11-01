#include <iostream>
#include <vector>
#include <queue>

typedef long long ll;
const int SIZE = 2001;
const ll INFINITY_COST = 1e9;

struct Path {
    ll time, cost;
    bool operator==(const Path& other) const { return time == other.time && cost == other.cost; }
    bool operator<(const Path& other) const { return time < other.time; }
    Path operator+(const Path& other) const { return { time + other.time, cost + other.cost }; }
};

ll compute_cross(const Path& p1, const Path& p2, const Path& p3) {
    return (p2.time - p1.time) * (p3.cost - p2.cost) - (p2.cost - p1.cost) * (p3.time - p2.time);
}

int compute_ccw(const Path& p1, const Path& p2, const Path& p3) {
    ll cross = compute_cross(p1, p2, p3);
    return cross > 0 ? 1 : cross < 0 ? -1 : 0;
}

class ConvexHullTree {
    struct TreeNode {
        TreeNode* left;
        TreeNode* right;
        TreeNode* parent;
        Path value;
        int node_count;
        TreeNode(const Path& p) : left(nullptr), right(nullptr), parent(nullptr), value(p), node_count(1) {}
        ~TreeNode() { if (left) delete left; if (right) delete right; }
        void recalculate() {
            node_count = 1;
            if (left) node_count += left->node_count;
            if (right) node_count += right->node_count;
        }
    } *root;

    void perform_rotate(TreeNode* x) {
        TreeNode* parent = x->parent;
        if (!parent) return;
        TreeNode* sibling = nullptr;
        if (x == parent->left) {
            parent->left = sibling = x->right;
            x->right = parent;
        } else {
            parent->right = sibling = x->left;
            x->left = parent;
        }
        x->parent = parent->parent;
        parent->parent = x;
        if (sibling) sibling->parent = parent;
        (x->parent ? parent == x->parent->left ? x->parent->left : x->parent->right : root) = x;
        parent->recalculate();
        x->recalculate();
    }

    void perform_splay(TreeNode* x) {
        while (x->parent) {
            TreeNode* parent = x->parent;
            TreeNode* grandparent = parent->parent;
            if (grandparent) {
                if ((x == parent->left) == (parent == grandparent->left)) perform_rotate(parent);
                else perform_rotate(x);
            }
            perform_rotate(x);
        }
    }

    bool adjust_tree(TreeNode* x) {
        perform_splay(x);

        TreeNode* left_node = x->left;
        while (left_node && left_node->right) left_node = left_node->right;
        if (left_node) perform_splay(left_node);

        TreeNode* right_node = x->right;
        while (right_node && right_node->left) right_node = right_node->left;
        if (right_node) perform_splay(right_node);

        if (compute_ccw(right_node->value, left_node->value, x->value) <= 0) {
            remove_path(x->value);
            return false;
        }

        while (left_node && left_node->left) {
            TreeNode* next_left = left_node->left;
            while (next_left && next_left->right) next_left = next_left->right;
            if (compute_ccw(x->value, next_left->value, left_node->value) <= 0) remove_path(left_node->value);
            else break;
            left_node = next_left;
        }

        while (right_node && right_node->right) {
            TreeNode* next_right = right_node->right;
            while (next_right && next_right->left) next_right = next_right->left;
            if (compute_ccw(next_right->value, x->value, right_node->value) <= 0) remove_path(right_node->value);
            else break;
            right_node = next_right;
        }
        return true;
    }

public:
    ConvexHullTree() {
        root = new TreeNode({ -1, INFINITY_COST });
        root->right = new TreeNode({ INFINITY_COST, -1 });
        root->right->parent = root; perform_splay(root->right);
    }
    ~ConvexHullTree() { if (root) delete root; root = nullptr; }
    int count_nodes() const { return root ? root->node_count : 0; }

    bool add_path(const Path& val) {
        if (!root) {
            root = new TreeNode(val);
            return true;
        }
        TreeNode* node = root;
        TreeNode** node_ptr;
        while (1) {
            if (node->value.time == val.time) {
                if (val.cost >= node->value.cost) return false;
                node->value.cost = val.cost;
                return adjust_tree(node);
            }
            if (val < node->value) {
                if (!node->left) {
                    node_ptr = &node->left;
                    break;
                }
                node = node->left;
            } else {
                if (!node->right) {
                    node_ptr = &node->right;
                    break;
                }
                node = node->right;
            }
        }
        TreeNode* new_node = new TreeNode(val);
        new_node->parent = node;
        *node_ptr = new_node;
        return adjust_tree(new_node);
    }

    bool find_path(const Path& val) {
        if (!root) return false;
        TreeNode* node = root;
        while (1) {
            if (node->value.time == val.time) break;
            if (val < node->value) {
                if (!node->left) break;
                node = node->left;
            } else {
                if (!node->right) break;
                node = node->right;
            }
        }
        perform_splay(node);
        return node->value == val;
    }

    void remove_path(const Path& val) {
        if (!find_path(val)) return;
        TreeNode* node = root;

        if (node->left && node->right) {
            root = node->left;
            root->parent = nullptr;
            TreeNode* left_sub = root;
            while (left_sub->right) left_sub = left_sub->right;
            left_sub->right = node->right;
            node->right->parent = left_sub;
            perform_splay(node->right);
        } else if (node->left) root = node->left, root->parent = nullptr;
        else if (node->right) root = node->right, root->parent = nullptr;
        else root = nullptr;

        node->left = node->right = nullptr;
        delete node;
    }

    ll calculate_product(size_t i) {
        TreeNode* node = root;
        if (!node) return INFINITY_COST;
        while (1) {
            while (node->left && node->left->node_count > i) node = node->left;
            if (node->left) i -= node->left->node_count;
            if (!i--) break;
            node = node->right;
        }
        perform_splay(node);
        return node->value.time * node->value.cost;
    }
} optimal_path[SIZE];

struct CityInfo {
    int city;
    Path path_info;
    bool operator<(const CityInfo& other) const { return path_info.time * path_info.cost > other.path_info.time * other.path_info.cost; }
};

std::priority_queue<CityInfo> cities_queue;
std::vector<CityInfo> road_map[SIZE];
int city_count, road_count;

int main() {
    std::cin.tie(0)->sync_with_stdio(0);
    std::cin >> city_count >> road_count;
    for (int i = 0, src, dest, travel_time, travel_cost; i < road_count; ++i) {
        std::cin >> src >> dest >> travel_time >> travel_cost;
        road_map[src].push_back({ dest, { travel_time, travel_cost } });
        road_map[dest].push_back({ src, { travel_time, travel_cost } });
    }

    cities_queue.push({ 1, { 0, 0 } });
    optimal_path[1].add_path({ 0, 0 });
    while (!cities_queue.empty()) {
        int curr_city = cities_queue.top().city;
        Path curr_path = cities_queue.top().path_info;
        cities_queue.pop();
        if (!optimal_path[curr_city].find_path(curr_path)) continue;
        for (const CityInfo& next : road_map[curr_city]) {
            int next_city = next.city;
            Path next_path = curr_path + next.path_info;
            if (optimal_path[next_city].add_path(next_path)) cities_queue.push({ next_city, next_path });
        }
    }

    for (int i = 2; i <= city_count; ++i) {
        ll min_cost = INFINITY_COST * INFINITY_COST;
        for (int j = 1; j < optimal_path[i].count_nodes() - 1; ++j) {
            ll cost_product = optimal_path[i].calculate_product(j);
            min_cost = std::min(min_cost, cost_product);
        }
        std::cout << (min_cost < INFINITY_COST * INFINITY_COST ? min_cost : -1) << '\n';
    }
}
