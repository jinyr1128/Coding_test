import java.io.*;
import java.util.*;

public class Main {
    static int[] inorder, postorder;
    static Map<Integer, Integer> indexMap;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        inorder = new int[n];
        postorder = new int[n];
        indexMap = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            indexMap.put(inorder[i], i);
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }
        
        StringBuilder sb = new StringBuilder();
        findPreorder(0, n-1, 0, n-1, sb);
        System.out.println(sb.toString().trim());
    }
    
    static void findPreorder(int inStart, int inEnd, int postStart, int postEnd, StringBuilder sb) {
        if (inStart > inEnd || postStart > postEnd) return;
        int root = postorder[postEnd];
        sb.append(root).append(" ");
        int idx = indexMap.get(root);
        int leftSize = idx - inStart;
        findPreorder(inStart, idx - 1, postStart, postStart + leftSize - 1, sb);
        findPreorder(idx + 1, inEnd, postStart + leftSize, postEnd - 1, sb);
    }
}
