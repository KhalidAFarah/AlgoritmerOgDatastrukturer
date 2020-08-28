package eksempler;

public class BinaryTreeNode {

    public static class Node{
        Node parent;
        Node left_child;
        Node right_child;
        char value;

        public Node(char value){
            this.value = value;
        }

        Node grandParent(){
            return this.parent.parent;
        }

    }
    public static void nodes(){
        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        Node root = new Node(characters[0]);
        root.left_child = new Node(characters[1]);
        root.right_child = new Node(characters[2]);

        root.left_child.parent = root;
        root.right_child.parent = root;

        root.left_child.left_child = new Node(characters[3]);
        root.left_child .right_child= new Node(characters[4]);
        root.right_child.right_child = new Node(characters[5]);
        root.right_child.left_child = new Node(characters[6]);

        root.left_child.left_child.parent = root.left_child;
        root.left_child.right_child = root.left_child;
        root.right_child.right_child = root.right_child;
        root.right_child.left_child = root.right_child;
    }
}
