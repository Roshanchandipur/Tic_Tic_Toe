import java.util.*;

public class node{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int preorder[] = new int[n];
        int inorder[] = new int[n];
        int postorder[] = new int[n];
        for(int i=0;i<n;i++){
            preorder[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++) postorder[i] = sc.nextInt();
        for(int i=0;i<n;i++) inorder[i] = sc.nextInt();
        String ans = new Solution().find(inorder,preorder,postorder,n);
        System.out.println(ans);
    }
}
class Solution{
    public String find(int inorder[], int preorder[], int postorder[], int n){
        Map<Integer,Integer> map =  new HashMap<>();
        for(int i=0;i<n;i++) map.put(inorder[i],i);
        Node tree = make(inorder,preorder,0,n-1,0,n-1,map);
        ArrayList<Integer> arr = new ArrayList<>();
        findPost(tree,arr);
        for(int i=0;i<n;i++){
            if(postorder[i]!=arr.get(i)) return "NO";
        }
        return "YES";
    }
    Node make(int inorder[], int preorder[], int inS, int inE, int preS, int preE, Map<Integer,Integer> map){
        if(inS>inE||preS>preE) return null;
        Node tree = new Node(preorder[preS]);
        int ind = map.get(preorder[preS]);
        int len = ind - inS;
        tree.left = make(inorder,preorder, inS, ind-1, preS+1,preS+len,map);
        tree.right = make(inorder,preorder,ind+1,inE,preS+len+1,preE,map);
        return tree;
    }
    void findPost(Node tree, ArrayList<Integer> arr){
        if(tree == null) return;
        findPost(tree.left,arr);
        findPost(tree.right,arr);
        arr.add(tree.data);
    }
}
class Node{
    int data;
    Node left, right;
    public Node(int data){
        this.data = data;
        left = right = null;
    }
}