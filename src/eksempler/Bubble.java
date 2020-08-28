package eksempler;

public class Bubble {
    public static void bubble(int[] a){
        int begin = 0;
        int end = a.length - 1;
        for(int k = begin; k < end; k++) {
            for (int i = begin; i < end; i++) {
                if (a[i] > a[i + 1]) {
                    //Bytter mellom a[i] og a[i+1]
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                } else {
                    //ingenting
                }
                System.out.print(a[0]);
                for (int j = begin + 1; j < a.length; j++) {
                    System.out.print(", " + a[j]);
                }
                System.out.println();
            }
        }

    }
}
