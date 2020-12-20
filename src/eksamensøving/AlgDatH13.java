package eksamensøving;

import ukeoppgaver.resources.Tabell;

public class AlgDatH13 {
    public static void main(String[] args){
        oppgave1();
    }
    private static void oppgave1(){
        //A
        String s = "Kari";
        String s2 = "Petter";
        String result = "KPaertiter";

        System.out.println("returnete svar: " + Tabell.hash(s, s2) + "riktige svar: " + result.hashCode());

        //B
        char[] a = "ABBA".toCharArray();
        char[] b = "BARBARER".toCharArray();
        char[] c = "BARBERER".toCharArray();

        System.out.println(Tabell.inneholdti(a, b));  // Utskrift: true
        System.out.println(Tabell.inneholdti(a, c));  // Utskrift: false


    }
}