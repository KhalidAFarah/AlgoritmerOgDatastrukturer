package eksempler;

import java.util.Arrays;

public class Heapsortering {
    public static void heapify(int[] values){
        for(int i = 1; i < values.length; i++){
            int current = i;
            int parent = i/2;

            while(parent > 0 && values[parent] > values[current]){
                System.out.println("Bytter " + current + " med " + parent);
                int temp = values[parent];
                values[parent] = values[current];
                values[current] = temp;
                current = parent;
                parent = current/2;
            }
        }
    }

    public static void heapsort(int[] values){
        System.out.println("Array før heapify: " + Arrays.toString(values));
        heapify(values);
        System.out.println("Array etter heapify: " + Arrays.toString(values));

        for(int i = 1; i < values.length; ++i){
            int first = 1;
            int last = values.length - i;

            System.out.println("Bytter " + first + " med " + last);

            int temp = values[first];
            values[first] = values[last];
            values[last] = temp;

            int current = first;
            int left_child = current*2;
            int right_child = current*2 + 1;
            while(true){
                if(left_child < last
                        && values[left_child] < values[right_child]
                        && values[left_child] < values[current]){
                    //venstre barn er minst av barna, og mindre enn parent
                    System.out.println("Bytter " + current + " med " + left_child);
                    int temp2 = values[left_child];
                    values[left_child] = values[current];
                    values[current] = temp2;

                    current = left_child;
                }else if(right_child < last &&
                        values[right_child] < values[left_child] &&
                        values[right_child] < values[current]){
                    //høyre barn er minst av barna, og mindre enn parent
                    System.out.println("Bytter " + current + " med " + right_child);
                    int temp2 = values[right_child];
                    values[right_child] = values[current];
                    values[current] = temp2;

                    current = right_child;
                }else{
                    //vi har funnet riktig plass for elementet.
                    break;
                }

                left_child = current * 2;
                right_child = current * 2 + 1;

            }
        }
        System.out.println("Array etter uttak: " + Arrays.toString(values));
    }
    public static void main(String[] args){
        int values[] = {-99, 5, 9, 8, 3, 1};
        //System.out.println("Array før heapsort: " + Arrays.toString(values));
        heapsort(values);
        //System.out.println("Array etter heapsort: " + Arrays.toString(values));
    }
}
