package eksempler;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class genericsortering {
    public static void main(String[] args){
        Integer[] ints = {1,0,23, 33,6,13,4};
        Character[] chars = {'F', 'B', 'C', 'A', 'P'};
        Person[] personer = {new Person("KD", "FA"), new Person("AH", "FA"), new Person("ÅO", "Pettersen"), new Person("Per", "Pettersen")};

        System.out.println("Største Integer: " + maksGeneric(ints));
        System.out.println("Største Character: " + maksGeneric(chars));
        System.out.println("Største Person: " + maksGeneric(personer));
    }

    static <T extends Comparable<? super T>>int maksGeneric(T[] a){
        int maks = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i].compareTo(a[maks]) > 0){
                maks = i;
            }
        }
        return maks;
    }
    static <T extends Comparable<? super T>>int maksGeneric(T[] a, int begin, int end){
        int maks = begin;
        for(int i = begin; i < end; i++){
            if(a[i].compareTo(a[maks]) > 0){
                maks = i;
            }
        }
        return maks;
    }

    static class Person implements Comparable<Person>{
        String Firstname;
        String LastName;

        public Person(String firstname, String lastName){
            Firstname = firstname;
            LastName = lastName;
        }

        @Override
        public int compareTo(Person o) {
            int bigger = LastName.compareTo(o.LastName);
            if(bigger == 0){
                return Firstname.compareTo(o.Firstname);
            }
            return bigger;
        }
    }

}

