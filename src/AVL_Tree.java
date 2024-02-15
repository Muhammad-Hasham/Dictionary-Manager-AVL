import java.io.*;
import java.util.Scanner;

public class AVL_Tree {

    AVL_Node root;

    public AVL_Tree() {
        root = null;
    }

    AVL_Node Insert(Database mydata, AVL_Node newnode) {
        if (newnode == null) {
            newnode = new AVL_Node();
            newnode.dataobj = mydata;
            newnode.left = newnode.right = null;
        } else if (mydata.getWord().compareTo(newnode.dataobj.getWord()) < 0) {

            newnode.left = Insert(mydata, newnode.left);
            if (getHeight(newnode.left) - getHeight(newnode.right) == 2)
                if (mydata.getWord().compareTo(newnode.left.dataobj.getWord()) < 0)
                    newnode = SingleRotationRight(newnode);
                else
                    newnode = LeftRightRotation(newnode);

        } else if (mydata.getWord().compareTo(newnode.dataobj.getWord()) > 0) {

            newnode.right = Insert(mydata, newnode.right);
            if (getHeight(newnode.right) - getHeight(newnode.left) == 2)
                if (mydata.getWord().compareTo(newnode.dataobj.getWord()) > 0)
                    newnode = SingleRotationLeft(newnode);
                else
                    newnode = RightLefttRotation(newnode);
        }
        newnode.setHeight(Integer.max(getHeight(newnode.left), getHeight(newnode.right)) + 1);
        return newnode;
    }

    AVL_Node SingleRotationLeft(AVL_Node itrnode1) {

        AVL_Node itrnode2;
        itrnode2 = itrnode1.right;
        itrnode1.right = itrnode2.left;
        itrnode2.left = itrnode1;
        itrnode1.setHeight(Integer.max(getHeight(itrnode1.left), getHeight(itrnode1.right)) + 1);
        itrnode2.setHeight(Integer.max(getHeight(itrnode2.right), itrnode1.getHeight()) + 1);
        return itrnode2;
    }

    AVL_Node SingleRotationRight(AVL_Node itrnode1) {

        AVL_Node itrnode2;

        itrnode2 = itrnode1.left;
        itrnode1.left = itrnode2.right;
        itrnode2.right = itrnode1;
        itrnode1.setHeight(Integer.max(getHeight(itrnode1.left), getHeight(itrnode1.right)) + 1);
        itrnode2.setHeight(Integer.max(getHeight(itrnode2.left), itrnode1.getHeight()) + 1);
        return itrnode2;
    }

    AVL_Node LeftRightRotation(AVL_Node itrnode3) {

        itrnode3.left = SingleRotationLeft(itrnode3.left);
        return SingleRotationRight(itrnode3);
    }

    AVL_Node RightLefttRotation(AVL_Node itrnode1) {

        itrnode1.right = SingleRotationRight(itrnode1.right);
        return SingleRotationLeft(itrnode1);
    }

    public int getHeight(AVL_Node ptrNode) {
        if (ptrNode == null) {
            return -1;
        } else {
            int leftHeight = getHeight(ptrNode.left);
            int rightHeight = getHeight(ptrNode.right);

            if (ptrNode.left != null) {
                return (leftHeight + 1);
            } else {
                return (rightHeight + 1);
            }
        }
    }

    public void InOrder(AVL_Node root) {
        if (root == null)
            return;
        InOrder(root.left);
        System.out.println(root.dataobj);
        InOrder(root.right);
    }

    public void PreOrder(AVL_Node root) {
        if (root == null)
            return;
        System.out.println(root.dataobj);
        PreOrder(root.left);
        PreOrder(root.right);
    }

    public void PostOrder(AVL_Node root) {
        if (root == null)
            return;
        PostOrder(root.left);
        PostOrder(root.right);
        System.out.println(root.dataobj);
    }

    public void Start() {
        try {
            File file = new File("Dictionary.txt");
            Scanner fileReader;
            fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                String word = line.substring(0, line.indexOf(' ', 0));
                line = line.substring(line.indexOf(' ', 0) + 1, line.length());

                String meaning = "";

                while (line.indexOf(' ', 0) != -1) {
                    meaning += line.substring(0, line.indexOf(' ', 0)) + " ";
                    line = line.substring(line.indexOf(' ', 0) + 1, line.length());
                }
                meaning += " " + line;

                Database node = new Database(word, meaning);
                root = Insert(node, root);
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public void remove(String dataobj) {
        root = deleteNode(dataobj, root);
        System.out.println("The Word Has Been Successfully Deleted");
    }

    AVL_Node deleteNode(String dataobj, AVL_Node nodePtr) {

        if (nodePtr == null)
            System.out.println(dataobj + "Not found");
        else if (dataobj.compareTo(nodePtr.dataobj.getWord()) < 0)
            nodePtr.left = deleteNode(dataobj, nodePtr.left);
        else if (dataobj.compareTo(nodePtr.dataobj.getWord()) > 0)
            nodePtr.right = deleteNode(dataobj, nodePtr.right);
        else
            nodePtr = makeDeletion(nodePtr);

        return nodePtr;
    }

    AVL_Node makeDeletion(AVL_Node nodePtr) {
        if (nodePtr.right == null) {
            nodePtr = nodePtr.left;
            return nodePtr;
        } else if (nodePtr.left == null) {
            nodePtr = nodePtr.right;
            return nodePtr;
        } else {
            AVL_Node tempNodePtr;
            tempNodePtr = nodePtr.right;
            while (tempNodePtr.left != null)
                tempNodePtr = tempNodePtr.left;
            tempNodePtr.left = nodePtr.left;
            nodePtr = nodePtr.right;
            return nodePtr;
        }
    }

    AVL_Node Insert(AVL_Node mydata, AVL_Node newnode) {
        if (newnode == null) {
            newnode = mydata;
        } else if (mydata.dataobj.getWord().compareTo(newnode.dataobj.getWord()) < 0) {

            newnode.left = Insert(mydata, newnode.left);
            if (getHeight(newnode.left) - getHeight(newnode.right) == 2)
                if (mydata.dataobj.getWord().compareTo(newnode.left.dataobj.getWord()) < 0)
                    newnode = SingleRotationRight(newnode);
                else
                    newnode = LeftRightRotation(newnode);

        } else if (mydata.dataobj.getWord().compareTo(newnode.dataobj.getWord()) > 0) {

            newnode.right = Insert(mydata, newnode.right);
            if (getHeight(newnode.right) - getHeight(newnode.left) == 2)
                if (mydata.dataobj.getWord().compareTo(newnode.dataobj.getWord()) > 0)
                    newnode = SingleRotationLeft(newnode);
                else
                    newnode = RightLefttRotation(newnode);
        }
        newnode.setHeight(Integer.max(getHeight(newnode.left), getHeight(newnode.right)) + 1);
        return newnode;
    }

    public void Search(String Searchobj) {
        boolean flag = false;
        AVL_Node nodePtr = root;
        while (nodePtr != null) {
            if (nodePtr.dataobj.getWord().equals(Searchobj)) {
                System.out.println(nodePtr.dataobj.getWord() + ": " + nodePtr.dataobj.getMeaning());
                flag = true;
                break;
            } else if (Searchobj.compareTo(nodePtr.dataobj.getWord()) < 0)
                nodePtr = nodePtr.left;
            else
                nodePtr = nodePtr.right;
        }
        if (!flag)
            System.out.println("This word does not exist in this dictionary");
    }

    public void WriteInOrderToFile(AVL_Node node) {
        try {
            FileWriter fw = new FileWriter("Dictionary in Inorder.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            if (node == null)
                return;
            WriteInOrderToFile(node.left);
            pw.println(node.dataobj.toFileLine());
            pw.close();
            WriteInOrderToFile(node.right);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WritePreOrderToFile(AVL_Node node) {
        try {
            FileWriter fw = new FileWriter("Dictionary in PreOrder.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            if (node == null)
                return;
            pw.println(node.dataobj.toFileLine());
            pw.close();
            WritePreOrderToFile(node.left);
            WritePreOrderToFile(node.right);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WritePostOrderToFile(AVL_Node node) {
        try {
            FileWriter fw = new FileWriter("Dictionary in PostOrder.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            if (node == null)
                return;
            WritePostOrderToFile(node.left);
            WritePostOrderToFile(node.right);
            pw.println(node.dataobj.toFileLine());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Menu() {
        while (true) {
            System.out.println();
            System.out.println("Press 1 To Print Inorder");
            System.out.println("Press 2 To Print Preorder");
            System.out.println("Press 3 To Print Postorder");
            System.out.println("Press 4 To Search For a Word");
            System.out.println("Press 5 To Delete a Word");
            System.out.println("Press 0 To Exit Program");
            System.out.println();

            Scanner Ascan = new Scanner(System.in);

            System.out.println("Enter Choice: ");
            int choice = Ascan.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("-----Inorder traversal-----");
                    InOrder(root);
                    break;

                case 2:
                    System.out.println("-----Preorder traversal-----");
                    PreOrder(root);
                    break;

                case 3:
                    System.out.println("-----Postorder traversal-----");
                    PostOrder(root);
                    break;

                case 4:
                    System.out.println("-----Searching For a Word-----");
                    System.out.println();
                    System.out.println("Enter a word");
                    Scanner scan1 = new Scanner(System.in);
                    String word = scan1.nextLine();
                    Search(word);
                    break;

                case 5:
                    System.out.println("-----Deleting A Word-----");
                    System.out.println();
                    System.out.println("Enter a word to delete it");
                    Scanner scan2 = new Scanner(System.in);
                    String word1 = scan2.nextLine();
                    remove(word1);
                    break;

                case 0:
                    WriteInOrderToFile(root);
                    WritePreOrderToFile(root);
                    WritePostOrderToFile(root);
                    System.out.println("You have exited the program");
                    java.lang.System.exit(0);

                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        }
    }
}
