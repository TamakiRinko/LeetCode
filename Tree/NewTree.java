package Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Node{
    String value;
    Node left;
    Node right;
    public Node(String value){
        this.left = null;
        this.right = null;
        this.value = value;
    }
    public Node(String value, Node left, Node right){
        this.left = left;
        this.right = right;
        this.value = value;
    }
}

public class NewTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, Node> treeMap = new HashMap<>();
        String[] temp = new String[n];
        Map<String, Boolean> use = new HashMap<>();
        for(int i = 0; i < n; ++i){
            temp[i] = scanner.next();
            String value = temp[i].substring(0, 1);
            treeMap.put(value, new Node(value));
            use.put(value, false);
        }
        
        for(int i = 0; i < n; ++i){
            String value = temp[i].substring(0, 1);
            String left = temp[i].substring(1, 2);
            String right = temp[i].substring(2, 3);
            if(left != "*"){
                treeMap.get(value).left = treeMap.get(left);
                use.put(left, true);
            }
            if(right != "*"){
                treeMap.get(value).right = treeMap.get(right);
                use.put(right, true);
            }
        }
        String rootValue = null;
        for(Map.Entry<String, Boolean> entry: use.entrySet()){
            if(entry.getValue() == false){
                rootValue = entry.getKey();
                break;
            }
        }
        Node root = treeMap.get(rootValue);
        preOrder(root);
        scanner.close();
    }

    static void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }
}
