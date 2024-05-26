import java.util.*;

class TrieNode {
    boolean isEnd;
    HashMap<Character, TrieNode> children;

    TrieNode() {
        isEnd = false;
        children = new HashMap<>();
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currentNode = root;
        for (char ch : word.toCharArray()) {
            currentNode.children.putIfAbsent(ch, new TrieNode());
            currentNode = currentNode.children.get(ch);
        }
        currentNode.isEnd = true;
    }

    public int countAutoComplete(String word) {
        int count = 1;  // 첫 글자는 반드시 입력해야 함
        TrieNode currentNode = root.children.get(word.charAt(0));

        for (int i = 1; i < word.length(); i++) {
            if (currentNode.isEnd || currentNode.children.size() > 1) {
                count++;
            }
            currentNode = currentNode.children.get(word.charAt(i));
        }

        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = scanner.next();
            }

            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }

            int sum = 0;
            for (String word : words) {
                sum += trie.countAutoComplete(word);
            }

            double result = (double) sum / n;
            System.out.printf("%.2f\n", result);
        }

        scanner.close();
    }
}
