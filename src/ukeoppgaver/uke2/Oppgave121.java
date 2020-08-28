package ukeoppgaver.uke2;

public class Oppgave121 {
    /*------------------------1.2.1------------------------*/
    //Oppgave 1
    public static int min(int[] a, int fra, int til)
    {
        if (fra < 0 || til > a.length || fra >= til)
        {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        int m = fra;              // indeks til største verdi i a[fra:til>
        int minimumverdi = a[fra];   // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
        {
            if (a[i] < minimumverdi)
            {
                m = i;
                minimumverdi = a[m];
            }
        }

        return m;  // posisjonen til største verdi i a[fra:til>
    }
    public static int min(int[] a)  // bruker hele tabellen
    {
        return min(a,0,a.length);     // kaller metoden over
    }

    //Array for-løkke
    public void arrayLøkke(int[] a){
        for(int integer : a){
            System.out.println(integer);
        }
    }
}
