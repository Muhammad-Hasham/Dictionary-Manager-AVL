public class AVL_Node {

    AVL_Node left;
    AVL_Node right;
    private int height;
    Database dataobj;

    public AVL_Node() {}

    public AVL_Node(Database dataobj) {
        this.left = left;
        this.right = right;
        this.height = height;
        this.dataobj = dataobj;
    }

    public AVL_Node getLeft() {
        return left;
    }

    public void setLeft(AVL_Node left) {
        this.left = left;
    }

    public AVL_Node getRight() {
        return right;
    }

    public void setRight(AVL_Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Database getDataobj() {
        return dataobj;
    }

    public void setDataobj(Database dataobj) {
        this.dataobj = dataobj;
    }
}
