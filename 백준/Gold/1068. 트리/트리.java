import java.util.*;

public class Main {
    static List<Integer>[] tree;
    static boolean[] visited;
    static int leafCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        tree = new ArrayList[N];
        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        int root = -1;
        for (int i = 0; i < N; i++) {
            int parent = sc.nextInt();
            if (parent == -1) {
                root = i;
            } else {
                tree[parent].add(i);
            }
        }
        
        int deleteNode = sc.nextInt();
        
        if (deleteNode == root) {
            System.out.println(0);
            return;
        }

        deleteNodeAndChildren(deleteNode);

        leafCount = 0;
        countLeafNodes(root);

        System.out.println(leafCount);
    }
    
    public static void deleteNodeAndChildren(int node) {
        visited[node] = true;
        for (int child : tree[node]) {
            deleteNodeAndChildren(child);
        }
    }
    
    public static void countLeafNodes(int node) {
        visited[node] = true;
        if (tree[node].isEmpty() || (tree[node].stream().allMatch(child -> visited[child]))) {
            leafCount++;
            return;
        }
        for (int child : tree[node]) {
            if (!visited[child]) {
                countLeafNodes(child);
            }
        }
    }
}
