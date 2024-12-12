import java.util.*;

class Node {
    boolean valid;
    Node[] child;
    Node fail;

    Node() {
        valid = false;
        child = new Node[26];
        fail = null;
    }

    void insert(String s, int idx) {
        if (idx == s.length()) {
            valid = true;
            return;
        }
        int x = s.charAt(idx) - 'a';
        if (child[x] == null) {
            child[x] = new Node();
        }
        child[x].insert(s, idx + 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Node root = new Node();

        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            root.insert(s, 0);
        }

        // Build Aho-Corasick automaton
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        root.fail = root;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 26; i++) {
                Node next = current.child[i];
                if (next == null) continue;

                if (current == root) {
                    next.fail = root;
                } else {
                    Node dest = current.fail;
                    while (dest != root && dest.child[i] == null) {
                        dest = dest.fail;
                    }
                    if (dest.child[i] != null) {
                        dest = dest.child[i];
                    }
                    next.fail = dest;
                }

                if (next.fail.valid) {
                    next.valid = true;
                }
                queue.add(next);
            }
        }

        int m = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < m; i++) {
            String query = scanner.nextLine();

            Node current = root;
            boolean found = false;

            for (int j = 0; j < query.length(); j++) {
                int x = query.charAt(j) - 'a';
                while (current != root && current.child[x] == null) {
                    current = current.fail;
                }
                if (current.child[x] != null) {
                    current = current.child[x];
                }
                if (current.valid) {
                    found = true;
                    break;
                }
            }

            System.out.println(found ? "YES" : "NO");
        }

        scanner.close();
    }
}
