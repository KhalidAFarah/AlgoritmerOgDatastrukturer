package eksamensøving;

import ukeoppgaver.resources.*;

public class AlgDatV16 {
    public static void main(String[] args){
        oppgave1();
        oppgave3();
    }
    private static void oppgave1(){
        //A
        Stakk<Character> A = new TabellStakk<>();    // en tabellstakk
        Stakk<Character> B = new TabellStakk<>();    // en lenket stakk

        char[] bokstaver = "ABCDEFG".toCharArray();  // en bokstavtabell
        for (char c : bokstaver) A.leggInn(c);       // legger inn i A
        System.out.println(A + " " + B);             // skriver ut innholdet i A og B

        while (!A.tom()) B.leggInn(A.taUt());        // flytter fra A til B
        System.out.println(A + " " + B);             // skriver ut innholdet i A og B

        //B
        Stakk<String> s = new TabellStakk<>();    // en lenket stakk
        String[] navn = {"Ole","Kari","Ali","Eli","Per","Pia"};
        for (String n : navn) s.leggInn(n);       // legger inn i s

        System.out.println(s);                    // skriver ut innholdet
        System.out.println("Pia har indeks " + indeks(s, "Pia"));
        System.out.println("Kari har indeks " + indeks(s, "Kari"));
        System.out.println("Petter har indeks " + indeks(s, "Petter"));

        // Utskrift:
        // [Pia, Per, Eli, Ali, Kari, Ole]
        // Pia har indeks 0
        // Kari har indeks 4
        // Petter har indeks -1

        //C
        Kø<String> A2 = new TabellKø<>();      // oppretter kø A
        Kø<String> B2 = new TabellKø<>();      // oppretter kø B

        String[] navn1 = {"Per","Kari","Elin","Ali","Jens"};
        String[] navn2 = {"Åse","Ole","Kjersti"};

        for (String n : navn1) A2.leggInn(n);  // legger inn i kø A
        for (String n : navn2) B2.leggInn(n);  // legger inn i kø B

        System.out.println(A2 + "  " + B2);     // skriver ut A og B
        flytt(A2,B2);                           // B flyttes over i A
        System.out.println(A2 + "  " + B2);     // skriver ut A og B

        // Utskrift: [Per, Kari, Elin, Ali, Jens]  [Åse, Ole, Kjersti]
        //           [Per, Åse, Kari, Ole, Elin, Kjersti, Ali, Jens]  []
    }
    private static void oppgave3(){
        //A
        Integer[] ints = {7, 4, 18, 1, 6, 14, 30, 12, 15, 9, 25, 22, 27, 10, 20};
        SBinTre<Integer> tre = SBinTre.sbintre();
        for(Integer i : ints) tre.leggInn(i);
        //C
        System.out.println("Dybden til 14 er: " + tre.dybde(14));
        //D
        System.out.println("Avstanden mellom 1 og 14 er: " + tre.avstand(1, 14));

        System.out.println("Diameteren til treet er: " + tre.diameter());
    }

    public static <T> int indeks(Stakk<T> s, T verdi){
        int indeks = 0;
        Stakk<T> B = new TabellStakk<>(s.antall());
        boolean funnet = false;
        while (!s.tom()){
            if(s.kikk().equals(verdi)){
                funnet = true;
            }
            if(!funnet) indeks++;

            B.leggInn(s.taUt());
        }

        while (!B.tom()) s.leggInn(B.taUt());
        if(!funnet) return -1;
        else return indeks;
    }
    public static <T> void flytt(Kø<T> A, Kø<T> B){
        Kø<T> C = new TabellKø<>();

        while (!A.tom() || !B.tom()){
            if(A.tom()) C.leggInn(B.taUt());
            else if(B.tom()) C.leggInn(A.taUt());
            else{
                C.leggInn(A.taUt());
                C.leggInn(B.taUt());
            }
        }

        while (!C.tom()) A.leggInn(C.taUt());
    }
    public static <T> SBinTre<T> kopi(SBinTre<T> tre){

        SBinTre<T> copyOfTree;
        copyOfTree = (SBinTre<T>) SBinTre.sbintre();
        /*for(T verdi: tre){
            copyOfTree.leggInn(verdi);
        }*/
        return copyOfTree;
    }
}
