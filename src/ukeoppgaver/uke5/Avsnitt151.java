package ukeoppgaver.uke5;

public class Avsnitt151 {
    public static void oppgave1(int n){
        int sum = 1;
        for(int i = n; i > 1; i--){
            sum = sum*2;
        }
    }

    public static int oppgave3(int n){ //tverssum

        int sum = 0;
        while (n > 0){
            sum += n % 10;
            n = n/10;
        }
        return sum;
    }
    public static int oppgave4(int n){ //sifferrot

        int sum = 0;
        while (n > 0){
            sum += n % 10;
            n = n/10;
        }
        if(sum > 10)
            oppgave4(sum);

        return sum;
    }

    public static int oppgave7(int n){
        if(n == 1)
            return 1;
        else
            return (n*n) + oppgave7(n-1);
    }

    public static int oppgave8(int k, int n){
        if(n == k){
            return n;
        }else{
            int m = (k + n)/2;
            return oppgave8(k, m) + oppgave8(m+1, n);
        }
    }

    public static int oppgave9(int[] a, int k, int n){ // maks med splitt og hersk
        if(n == k){
            return n;
        }else{
            int m = (k + n)/2;
            int svar1 = oppgave9(a, k, m);
            int svar2 = oppgave9(a, m+1, n);
            return a[svar1] > a[svar2] ? svar1 : svar2;
        }
    }

    public static int oppgave10(int n){ //fakultet
        if(n == 1)
            return 1;
        else
            return n*oppgave10(n-1);
    }


}
