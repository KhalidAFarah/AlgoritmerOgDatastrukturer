package ukeoppgaver.uke12;

import ukeoppgaver.resources.Iterator;
import ukeoppgaver.resources.LenketHashTabell;
import ukeoppgaver.resources.Tabell;

import java.util.Arrays;

public class Uke12Main {
    public static void main(){
        avsnitt611();
        avsnitt613();
        avsnitt614();
    }

    private static void avsnitt611(){String[] s = {"A"};
        //Oppgave 1
        // a*31 -b == c*31 - d;
        //Oppgave 2
        String a = "A", b = "A", c = new String("A"), d = s[0];

        boolean lik1 = (a == b), lik2 = (a == c), lik3 = (a == d);
        boolean eq1 = a.equals(b), eq2 = a.equals(c), eq3 = a.equals(d);
        int h1 = a.hashCode(), h2 = b.hashCode(), h3 = c.hashCode(), h4 = d.hashCode();
        System.out.println("lik1: " + lik1);
        System.out.println("lik2: " + lik2);
        System.out.println("lik3: " + lik3);

        System.out.println("eq1: " + eq1);
        System.out.println("eq2: " + eq2);
        System.out.println("eq3: " + eq3);

        System.out.println("h1: " + h1);
        System.out.println("h2: " + h2);
        System.out.println("h3: " + h3);
        System.out.println("h4: " + h4);

        //Oppgave 2
        int[] a2 = {1,2,3}, b2 = a2, c2 = {1,2,3};
        boolean lik12 = (a2 == b2), lik22 = (a2 == c2), lik32 = a2.equals(c2);

        System.out.println("lik12: " + lik12);
        System.out.println("lik22: " + lik22);
        System.out.println("lik32: " + lik32);
    }

    private static void avsnitt613(){
        System.out.println("-------------------------------------------");
        //Oppgave 2
        int n = 197;
        int[] hash = new int[n];

        for(int i = 0; i < 400; i++){
            String s = "A";
            if(i < 100) s += 0;
            if(i < 10) s += 0;
            s += i;
            hash[s.hashCode() % n]++;
        }

        int maksindex = Tabell.maks(hash);
        int maks = hash[maksindex];

        int[] igjen = new int[maks+1]; //spør om dette
        for(int i = 0; i < hash.length; i++){ // dette
            igjen[hash[i]]++; // dette ...]++ ?
        }

        System.out.println(Arrays.toString(igjen));

        System.out.println("indeksen til den største verdiern: " + maksindex);
        System.out.println("Hash sin største verdier: " + maks);

        //Oppgave 3
        System.out.println("---------------");
        //n = 197
        hash = new int[n];

        for(int i = 0; i < 400; i++){
            String s = "A";
            if(i < 100) s += 0;
            if(i < 10) s += 0;
            s += i;
            int hashcode = hash1(s);
            hash[hashcode % n]++;
        }

        maksindex = Tabell.maks(hash);
        maks = hash[maksindex];

        igjen = new int[maks+1];
        for(int i = 0; i < hash.length; i++){
            igjen[hash[i]]++;
        }

        System.out.println(Arrays.toString(igjen));

        System.out.println("indeksen til den største verdiern: " + maksindex);
        System.out.println("Hash sin største verdier: " + maks);

        //Oppgave 4
        System.out.println("---------------");
        //n = 197
        hash = new int[n];

        for(int i = 0; i < 400; i++){
            String s = "A";
            if(i < 100) s += 0;
            if(i < 10) s += 0;
            s += i;
            int hashcode = hash2(s);
            hash[hashcode % n]++;
        }

        maksindex = Tabell.maks(hash);
        maks = hash[maksindex];

        igjen = new int[maks+1];
        for(int i = 0; i < hash.length; i++){
            igjen[hash[i]]++;
        }

        System.out.println(Arrays.toString(igjen));

        System.out.println("indeksen til den største verdiern: " + maksindex);
        System.out.println("Hash sin største verdier: " + maks);

        //Oppgave 6
        System.out.println("-1 % 3 = " + (-1 % 3));
        System.out.println("Integer.remainderUnsigned(-1, 3) = " + Integer.remainderUnsigned(-1, 3));

        int h = hash("ABC", 10, 3.14);
        System.out.println(h);


    }

    public static int hash1(String s)
    {
        int h = 0;
        for (int i = 0; i < s.length(); i++)
        {
            h = (h << 5) ^ s.charAt(i) ^ h;
        }
        return h;
    }
    public static int hash2(String s)
    {
        int h = 0;
        for (int i = 0; i < s.length(); i++)
        {
            h = (h << 4) + s.charAt(i);
            int g = h & 0xf0000000;
            if (g != 0) h ^= (g >>> 24);
            h &= ~g;
        }
        return h;
    }

    public static void komplement(int k){
        if(k == 1) k = ~k;
    }

    public static int hash(Object... verdier)
    {
        if (verdier == null) return 0;
        int h = 1;
        for (Object o : verdier)
        {
            h = h*31 + (o == null ? 0 : o.hashCode());
        }
        return h;
    }

    public static void avsnitt614(){
        System.out.println("----------------------------------------------");
        String[] navn = {"Olga","Basir","Ali","Per","Elin","Siri",
                "Ole","Mette","Anne","Åse","Leif","Mona","Lise"};

        LenketHashTabell<String> hashtabell = new LenketHashTabell<>();

        for (String n : navn) hashtabell.leggInn(n);

        System.out.println(hashtabell);
        Iterator iterator = hashtabell.iterator();
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(hashtabell);
        // [Elin, Basir, Leif, Ole, Olga, Per, Mette, Mona, Anne, Ali, Lise, Åse, Siri]
    }
}
