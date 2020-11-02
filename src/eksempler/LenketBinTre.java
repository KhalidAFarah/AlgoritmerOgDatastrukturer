package eksempler;

import java.util.Comparator;

public class LenketBinTre<T> {
    static class Node <T>{
        T value;
        Node Left_child;
        Node Right_child;
        Node Parent;

        public  Node(T value, Node Parent){
            this.value = value;
            this.Parent = Parent;
            this.Left_child = null;
            this.Right_child = null;
        }

        static <T> void insert(Node<T> current, T value){
            Comparator c = Comparator.naturalOrder();

            int cmp = c.compare(current.value, value);
            if(cmp <= 0){
                if(current.Right_child == null){
                    current.Right_child = new Node(value, current);
                }else
                    insert(current.Right_child, value);
            }else {
                if(current.Left_child == null){
                    current.Left_child = new Node(value, current);
                }else
                    insert(current.Left_child, value);
            }
        }
        void printPreorder(){
            //skriv ut
            System.out.print(this.value + ", ");
            //rekursivt går til venstre
            if(this.Left_child != null)
                this.Left_child.printPreorder();
            //rekursivt går til høyre
            if(this.Right_child != null)
                this.Right_child.printPreorder();
        }
        void printInorder(){
            //rekursivt går til venstre
            if(this.Left_child != null)
                this.Left_child.printPreorder();
            //skriv ut
            System.out.print(this.value + ", ");
            //rekursivt går til høyre
            if(this.Right_child != null)
                this.Right_child.printPreorder();
        }
        void printPostorder(){
            //rekursivt går til venstre
            if(this.Left_child != null)
                this.Left_child.printPreorder();
            //rekursivt går til høyre
            if(this.Right_child != null)
                this.Right_child.printPreorder();
            //skriv ut
            System.out.print(this.value + ", ");
        }
        static <T> Node nestePreorder(Node p){
            //venstre barn finnes
            if(p.Left_child != null) return p.Left_child;
            //venstre barn finnes ikke men høyre gjør
            else if(p.Right_child != null) return p.Right_child;
            //ingen barn eksisterer
            else{
                Node<T> current = p.Parent;
                while (current != null && current.Right_child == p){
                    current = current.Parent;
                    p = p.Parent;
                }
                //vi har et barn som er søsken barn av p, det neste i preorden
                if(current != null){
                    return current.Right_child;
                }
                //vi kommer hele tiden fra høyre, dvs vi er siste i preorden
                else {
                    return null;
                }
            }
        }
    }


    public static void main(String[] args){
        Node<Character> root = new Node<Character>('M', null);

        Node.insert(root,'D');
        Node.insert(root,'P');

        Node.insert(root, 'C');
        Node.insert(root, 'E');
        Node.insert(root, 'N');
        Node.insert(root, 'Q');

        Node.insert(root, 'F');
        Node.insert(root, 'O');

        root.printPreorder();
        System.out.println();
        Node current = root;
        while(current != null){
            System.out.print(current.value + ", ");
            current = Node.nestePreorder(current);
        }
    }
}
