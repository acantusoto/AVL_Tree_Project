import java.io.*;

class AVLTree {
    AVLNode root;

    // A utility function to get the height of the tree
    int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(AVLNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    AVLNode insert(AVLNode AVLNode, Book input) {

        /* 1. Perform the normal BST insertion */
        if (AVLNode == null)
            return (new AVLNode(input));

        if (input.isbnNum < AVLNode.key)
            AVLNode.left = insert(AVLNode.left, input);
        else if (input.isbnNum > AVLNode.key)
            AVLNode.right = insert(AVLNode.right, input);
        else // Duplicate keys not allowed
            return AVLNode;

        /* 2. Update height of this ancestor node */
        AVLNode.height = 1 + max(height(AVLNode.left),
                height(AVLNode.right));

		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
        int balance = getBalance(AVLNode);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && input.isbnNum < AVLNode.left.key){
            System.out.println("Imbalance occurred at inserting ISBN "+input.isbnNum+"; fixed in LeftLeft Rotation");
            return rightRotate(AVLNode);
        }

        // Right Right Case
        if (balance < -1 && input.isbnNum > AVLNode.right.key){
            System.out.println("Imbalance occurred at inserting ISBN "+input.isbnNum+"; fixed in RightRight Rotation");
            return leftRotate(AVLNode);
        }

        // Left Right Case
        if (balance > 1 && input.isbnNum > AVLNode.left.key) {
            AVLNode.left = leftRotate(AVLNode.left);
            System.out.println("Imbalance occurred at inserting ISBN "+input.isbnNum+"; fixed in LeftRight Rotation");
            return rightRotate(AVLNode);
        }

        // Right Left Case
        if (balance < -1 && input.isbnNum < AVLNode.right.key) {
            AVLNode.right = rightRotate(AVLNode.right);
            System.out.println("Imbalance occurred at inserting ISBN "+input.isbnNum+"; fixed in RightLeft Rotation");
            return leftRotate(AVLNode);
        }

        /* return the (unchanged) node pointer */
        return AVLNode;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    void preOrder(AVLNode AVLNode) {
        if (AVLNode != null) {
            System.out.print(AVLNode.key + " ");
            preOrder(AVLNode.left);
            preOrder(AVLNode.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree;
        tree = importFromFile();



		/* The constructed AVL Tree would be
			30
			/ \
		20 40
		/ \	 \
		10 25 50
		*/

    }
    static AVLTree importFromFile(){
        AVLTree tree = new AVLTree();
        try{
        InputStream is = AVLTree.class.getResourceAsStream("BookList.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
            // reads each line
            String data;
            while((data = r.readLine()) != null) {
                String[] separateCat = data.split("\\s+");
                Book temp = new Book(Long.parseLong(separateCat[0]),separateCat[1],separateCat[2]);
                tree.root = tree.insert(tree.root, temp);
            }
            is.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return tree;
    }
}
