package eksempler;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Visitorpattern {
    @FunctionalInterface
    public interface Komparator<T>{
        public int compare(T a, T b);
    }

    public class AscendingPersonComparator implements Komparator<genericsortering.Person>{

        @Override
        public int compare(genericsortering.Person a, genericsortering.Person b) {
            int lastCompare = a.LastName.compareTo(b.LastName);
            if(lastCompare == 0){
                return a.Firstname.compareTo(b.Firstname);
            }
            return lastCompare;
        }
    }
    public class DescendingPersonComparator implements Komparator<genericsortering.Person>{

        @Override
        public int compare(genericsortering.Person a, genericsortering.Person b) {
            int lastCompare = a.LastName.compareTo(b.LastName);
            if(lastCompare == 0){
                return a.Firstname.compareTo(b.Firstname);
            }
            return lastCompare;
        }
    }
    public static class OddePartallKomparator implements Komparator<Integer>{

        @Override
        public int compare(Integer a, Integer b) {
            //begge er partall
            if(a % 2 == 0 && b % 2 == 0){
                return a.compareTo(b);
            }
            //a er partall og b er oddetall
            else if(a % 2 > b % 2){
                return 1;
            }
            //b er oddetall og a er partall
            else if(b % 2 > a % 2){
                return -1;
            }
            //begge er oddetall
            else if(a % 2 == 1 && b % 2 == 1){
                return a.compareTo(b);
            }

            //not a number , infinity, etc. kaster exceptions
            return 0;
        }
    }
    static <T extends Comparable<? super T>>int maksGeneric(T[] a, int begin, int end, Komparator<T> comp){
        int maks = begin;
        for(int i = begin; i < end; i++){
            if(comp.compare(a[i], a[maks]) > 0){
                maks = i;
            }
        }
        return maks;
    }
    static <T extends Comparable<? super T>>int maksGeneric(T[] a, Komparator<T> comp){
        int maks = 0;
        for(int i = 0; i < a.length; i++){
            if(comp.compare(a[i], a[maks]) > 0){
                maks = i;
            }
        }
        return maks;
    }
    public static void main(String[] args){
        genericsortering.Person[] personer = {new genericsortering.Person("KD", "FA"),
                new genericsortering.Person("AH", "FA"),
                new genericsortering.Person("ÅO", "Pettersen"),
                new genericsortering.Person("Per", "Pettersen")};

        Integer[] a = {3, 8, 10, 12, 14, 16, 21, 24, 27, 30, 32, 33, 34, 37, 40};
        Komparator<Integer> compOddPar = new OddePartallKomparator();
        maksGeneric(a, compOddPar);

        System.out.println(Arrays.toString(a));



    }

    public <T> void sort(T[] s, Komparator<T> comp){

    }
}
