public class AVLNode {
    long key;
    int height;
    Book data;
    AVLNode left, right;

    AVLNode(Book d) {
        data = d;
        key = d.isbnNum;
        height = 1;
    }
}
