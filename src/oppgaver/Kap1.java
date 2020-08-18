package oppgaver;

public class Kap1 {
    /*------------------------1.1.2-----------------------*/
    //1. det ville ha returnert tallet 1 som har posisjon 6
    //oppgave  del 2
    public static int min(int[] a)  // a er en heltallstabell
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        int m = 0;  // indeks til foreløpig minste verdi
        for (int i = 1; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] < a[m]) m = i;  // indeksen oppdateres
        }
        return m;  // returnerer indeksen/posisjonen til minste verdi
    }
    public static int maks(int[] a)  // deloppgave 3
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");

        int m = 0;  // indeks til foreløpig største verdi

        for (int i = 1; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] >= a[m]) m = i;  // endrer til >= istedet for > slik at hvis en ny verdi er det
            // samme som den største oppdateres m til den nyligste
        }

        return m;  // returnerer indeksen/posisjonen til største verdi

    }
    /*------------------------1.1.3-----------------------*/
    //deloppgave 5
    public int[] minmaks(int[] a) {
        int[] b = new int[2];
        if(a.length == 2){
            if(a[0] > a[1]){
                b = new int[]{a[1], a[0]};
            }else if(a[1] > a[0]) {
                b = new int[]{a[0], a[1]};
            }
        }
        return b;
    }

    //deloppgave 6
    public long fak(int n){
        /*if(n > 0)
        return n*fak(n-1);
        else
            return 1;*/
        int teller = 0;
        int sum = n;
        for(int i = n-1; i > 0; i--){
            teller++;
            sum = sum * i;
        }
        System.out.println("antall multiplikasjoner: " + teller + " " + n + "! er: " + sum);
        return sum;
    }
    /*------------------------1.1.4-----------------------*/
    //i)
    /*
    public static int maks(int[] a) [10, 5, 7, 2, 9, 1, 3, 8, 4, 6]
    {
        int m = 0;               1 operasjon
        int maksverdi = a[0];    2 operasjoner

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi) 9 + 
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdateres
        }
        return m;   // returnerer indeks/posisjonen til største verdi

    } // maks
    */
}
