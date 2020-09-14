package Oblig.Oblig1;

////// Løsningsforslag Oblig 1 ////////////////////////

import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Random;

public class Oblig1 {

    public static void main(String[] args){
        char[] a = {'A','B','C','D','E','F','G','H','I','J'};

        rotasjon(a, 3);

        System.out.println("--------------");
        for(char i : a){
            System.out.print(i+" ");
        }
        System.out.println("\n--------------");

    }
    private Oblig1() {}

    ///// Oppgave 1 //////////////////////////////////////
    public static int maks(int[] a) {
        if(a.length <= 0)
            throw new NoSuchElementException("Arrayet er tomt");

        //int fokus = a.length;
        //for(int i = 0; i > a.length; i++){
            for(int j = 0; j < a.length; j++){
                if(j+1 != a.length) {
                    if (a[j] > a[j + 1]) {
                        int temp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = temp;
                    }
                }
            }
        //}
        return a[a.length-1];
    }



    public static int ombyttinger(int[] a) {
        if(a.length <= 0)
            throw new NoSuchElementException("Arrayet er tomt");

        int ombyttinger = 0;


        int fokus = a.length;
        //for(int i = 0; i > a.length; i++){
        for(int j = 0; j < a.length; j++){
            if(j+1 != a.length) {
                if (a[j] > a[j + 1]) {
                    ombyttinger++;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        //}
        return ombyttinger;
    }

    ///// Oppgave 2 //////////////////////////////////////
    public static int antallUlikeSortert(int[] a) {
        if(a.length == 0)
            return 0;
        if(a.length == 1)
            return 1;

        int ulike = 0;

        for(int i = 0; i < a.length; i++){
            boolean brukt = true;
            for(int j = 0; j < a.length; j++){
                if(i+1 != a.length) {
                    if (a[i] <= a[i + 1]) {
                        if (a[i] == a[j] && j != i && i > j) {
                            brukt = false;
                        }
                    } else {
                        throw new IllegalStateException("Arrayet er ikke sortert");
                    }
                }else{
                    if (a[i] >= a[i - 1]) {
                        if (a[i] == a[j] && j != i && i > j) {
                            brukt = false;
                        }
                    } else {
                        throw new IllegalStateException("Arrayet er ikke sortert");
                    }
                }
            }
            if(brukt){
                ulike++;
            }
        }
        return ulike;
    }

    ///// Oppgave 3 //////////////////////////////////////
    public static int antallUlikeUsortert(int[] a) {
        if(a.length == 0)
            return 0;

        int ulike = 0;

        for(int i = 0; i < a.length; i++){
            boolean brukt = true;
            for(int j = 0; j < a.length; j++){
                if(a[i] == a[j] && i > j && i != j)
                    brukt = false;
            }
            if(brukt)
                ulike++;
        }
        return ulike;
    }

    ///// Oppgave 4 //////////////////////////////////////
    public static void quicksort(int[] a, int fra, int til){
        if(til-fra <= 0)
            return;

        int p = til;
        int placement = fra;
        for(int i = fra; i < til; i++){
            if(a[i] < a[p]){

                int temp = a[placement];
                a[placement] = a[i];
                a[i] = temp;

                placement++;
            }
        }

        int temp = a[p];
        a[p] = a[placement];
        a[placement] = temp;

        //int left = fra;
        //int right = til-2;


        //int temp = a[p];
        //a[p] = a[right];
        //a[right] = temp;
        //p = right;

        //right--;

        //boolean foundL = false;
        //boolean foundH = false;

        /*while(left != right){
            if(a[left] < a[p] && !foundH){
                left++;
            }else{
                foundH = true;
            }

            if(a[right] > a[p] && !foundL){
                right--;
            }else{
                foundL = true;
            }

            if(foundH && foundL){
                temp = a[left];
                a[left] = a[right];
                a[right] = temp;

                left++;
                right--;

                foundH = false;
                foundL = false;
            }


        }*/

        /*temp = a[p];
        a[p] = a[(fra+til)/2];
        a[(fra+til)/2] = temp;*/


        quicksort(a, fra, placement-1);
        quicksort(a, placement+1, til);


    }

    public static void delsortering(int[] a) {
        if(a.length == 0)
            return;


        int oddetall = 0;


        int i = 0;
        for(int j = 0; j < a.length; j++){
            if(a[j] % 2 != 0){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                oddetall++;
            }
        }


        quicksort(a, 0, oddetall-1);
        quicksort(a, oddetall, a.length-1);









        /*int oddetall = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] % 2 != 0){
                int temp = a[i];
                a[i] = a[oddetall];
                a[oddetall] = temp;
                oddetall++;
            }
        }
        for(int i = 0; i < a.length ; i++){
            if(i < oddetall){
                int min = i;
                for(int j = i; j < oddetall; j++){
                    if(a[min] > a[j])
                        min = j;
                }
                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }else {
                int min = i;
                for(int j = i; j < a.length; j++){
                    if(a[min] > a[j])
                        min = j;
                }

                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }*/

    }

    ///// Oppgave 5 //////////////////////////////////////
    public static void rotasjon(char[] a) {
        if(a.length > 1) {
            char sist = a[a.length-1];
            char next = a[0];
            char save = a[1];

            for (int i = 1; i < a.length; i++) {
                if(i+1 != a.length) {
                    a[i] = next;
                    next = save;
                    save = a[i + 1];
                }else{
                    a[i] = next;
                    next = save;
                }
            }

            a[0] = sist;
        }
    }

    ///// Oppgave 6 //////////////////////////////////////
    public static void rotasjon(char[] a, int k) {
        if(a.length > 1) {
            if (k > 0) {

                char sist;
                int reset = k;
                char next = a[0];
                char save = a[1];
                k--;
                for (int i = 1; i < a.length; i++) {
                    if(reset != 0) {
                        if (i + k < a.length) {
                            a[i+k] = next;
                            next = save;
                            save = a[i];

                            reset--;
                        }
                    }else {
                        reset = k;
                        i += reset;
                    }
                }

                //a[0] = sist;
            }else{
                for(; k < 0; k++){
                    char sist = a[0];
                    char next = a[a.length-1];
                    char save = a[a.length-2];

                    for (int i = a.length-2; i >= 0; i--) {
                        if(i-1 > 0) {
                            a[i] = next;
                            next = save;
                            save = a[i - 1];
                        }else{
                            a[i] = next;
                            next = save;
                        }
                    }

                    a[a.length-1] = sist;
                }
           }
        }
    }

    ///// Oppgave 7 //////////////////////////////////////
    /// 7a)
    public static String flett(String s, String t) {
        if(s.isEmpty() && t.isEmpty())
            return "";

        String[] sSplit = s.split("");
        String[] tSplit = t.split("");

        String utskrift = "";
        for(int i = 0; ; i++){
            if(tSplit.length > i || sSplit.length > i){
                if(sSplit.length > i)
                    utskrift += sSplit[i];
                if(tSplit.length > i)
                    utskrift += tSplit[i];
            }else {
                return utskrift;
            }
        }
    }

    /// 7b)
    public static String flett(String... s) {
        String utskrift = "";
        boolean ferdig = false;

        for(int i = 0; !ferdig; i++){
            ferdig = true;
            for(int j = 0; j < s.length; j++){
                String[] chars = s[j].split("");
                if(chars.length > i) {
                    ferdig = false;
                    utskrift += chars[i];
                }
            }
        }

        return utskrift;
    }

    ///// Oppgave 8 //////////////////////////////////////
    public static int[] indekssortering(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 9 //////////////////////////////////////
    public static int[] tredjeMin(int[] a) {
        throw new UnsupportedOperationException();
    }

    ///// Oppgave 10 //////////////////////////////////////
    public static int bokstavNr(char bokstav) {
        throw new UnsupportedOperationException();
    }

    public static boolean inneholdt(String a, String b) {
        throw new UnsupportedOperationException();
    }

}  // Oblig1
