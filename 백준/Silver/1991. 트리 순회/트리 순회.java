import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        char left;
        char right;

        Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    static Node[] tree;
    static StringBuilder preorderResult;
    static StringBuilder inorderResult;
    static StringBuilder postorderResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new Node[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int index = st.nextToken().charAt(0) - 'A';
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree[index] = new Node(left, right);
        }

        preorderResult = new StringBuilder();
        inorderResult = new StringBuilder();
        postorderResult = new StringBuilder();

        preorder('A');
        inorder('A');
        postorder('A');

        System.out.println(preorderResult.toString());
        System.out.println(inorderResult.toString());
        System.out.println(postorderResult.toString());
    }

    static void preorder(char node) {
        if (node == '.') return;
        int index = node - 'A';
        preorderResult.append(node); // Visit node
        preorder(tree[index].left); // Visit left child
        preorder(tree[index].right); // Visit right child
    }

    static void inorder(char node) {
        if (node == '.') return;
        int index = node - 'A';
        inorder(tree[index].left); // Visit left child
        inorderResult.append(node); // Visit node
        inorder(tree[index].right); // Visit right child
    }

    static void postorder(char node) {
        if (node == '.') return;
        int index = node - 'A';
        postorder(tree[index].left); // Visit left child
        postorder(tree[index].right); // Visit right child
        postorderResult.append(node); // Visit node
    }
}
