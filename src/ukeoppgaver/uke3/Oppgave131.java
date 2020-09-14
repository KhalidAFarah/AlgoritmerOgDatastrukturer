package ukeoppgaver.uke3;

import ukeoppgaver.resources.Tabell;

public class Oppgave131 {
    public static void Oppgave2(){ //abcdef bacdef
        //a) 2 3 6 1 4 5
        System.out.println("Min løsning: 2, 3, 6, 1, 5, 4");  //1 2 3 4 5 6         2 1 3 4 5 6       2 3
        int[] a = new int[]{2, 3, 6, 1, 5, 4};
        System.out.println("neste permutasjon: " + Tabell.nestePermutasjon(a));
        //b) 2 3 6 1 5 4
        System.out.println("Min løsning: 2, 3, 6, 4, 1, 5");
        a = new int[]{2, 3, 6, 4, 1, 5};
        System.out.println("neste permutasjon: " + Tabell.nestePermutasjon(a));
        //c) 2 3 1 6 5 4
        System.out.println("Min løsning: 2, 3, 4, 1, 5, 6");
        a = new int[]{2, 3, 4, 1, 5, 6};
        System.out.println("neste permutasjon: " + Tabell.nestePermutasjon(a));
        //d) 2 3 6 5 4 1
        System.out.println("Min løsning: 2, 4, 1, 3, 5, 6");
        a = new int[]{2, 4, 1, 3, 5, 6};
        System.out.println("neste permutasjon: " + Tabell.nestePermutasjon(a));
        //e) 2 6 5 4 3 1
        System.out.println("Min løsning: 3, 1, 2, 4, 5, 6");
        a = new int[]{3, 1, 2, 4, 5, 6};
        System.out.println("neste permutasjon: " + Tabell.nestePermutasjon(a));
    }

    //Oppgave 3
    //3 1 4 9 7 10 8 6 5 2

    //3 1 4 9 8 2 5 6 7 10
    //3 1 4 9 8 2 5 6 10 7
    //3 1 4 9 8 2 5 7 6 10
    //3 1 4 9 8 2 5 7 10 6
    //3 1 4 9 8 2 5 10 6 7
    //3 1 4 9 8 2 5 10 7 6
    //3 1 4 9 8 2 6 5 7 10
    //3 1 4 9 8 2 6 5 10 7
    //3 1 4 9 8 2 6 7 5 10
    //3 1 4 9 8 2 6 7 10 5



}
