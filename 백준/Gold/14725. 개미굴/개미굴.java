import java.util.*;

public class Main {

    static class TrieNode {
        Map<String, TrieNode> children = new TreeMap<>();
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String[] foods) {
            TrieNode current = root;
            for (String food : foods) {
                current.children.putIfAbsent(food, new TrieNode());
                current = current.children.get(food);
            }
        }

        void display(TrieNode node, int depth) {
            for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
                for (int i = 0; i < depth; i++) {
                    System.out.print("--");
                }
                System.out.println(entry.getKey());
                display(entry.getValue(), depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine(); // consume the remaining newline

        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            String[] foods = line.split(" ");
            int K = Integer.parseInt(foods[0]);
            trie.insert(Arrays.copyOfRange(foods, 1, K + 1));
        }

        trie.display(trie.root, 0);
    }
}
