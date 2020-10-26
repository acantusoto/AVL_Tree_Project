import java.util.Random;

// A Java program to introduce Binary Tree
class BinaryTree
{
    // Root of Binary Tree
    Node root;

    // Constructors
    BinaryTree(int key)
    {
        root = new Node(key);
    }

    BinaryTree()
    {
        root = null;
    }

    static Node insert( Node node, long key){
        if(node == null){
            node = new Node(key);
        }
        else if(node.left == null){
            node.left = new Node(key);
        }
        else if(node.right == null){
            node.right = new Node(key);
        }
        else if ((node.left.left == null)||(node.left.right == null)){
            node.left = insert(node.left,key);
        }
        else if ((node.right.left == null)||(node.right.right == null)){
            insert(node.right, key);
        }
        else{
            node.left = insert(node.left,key);
        }

        return node;
    }
    static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();

        for (int i =0; i <25; i++){
            long n = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L + 9_780_000_000_000L ;
            System.out.println(n);
            tree.root = insert(tree.root,n);
        }
        preOrder(tree.root);
    }
}