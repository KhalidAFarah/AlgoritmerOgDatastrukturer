package ukeoppgaver.uke2;

public class Oppgave1213 {
    public static int[] nestMaks(int[] a)   // en turnering
    {
        int n = a.length;                // for å forenkle notasjonen

        if (n < 2) // må ha minst to verdier!
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");

        int[] b = new int[2*n];          // turneringstreet
        System.arraycopy(a,0,b,n,n);     // legger a bakerst i b

        for (int k = 2*n-2; k > 1; k -= 2)   // lager turneringstreet
            b[k/2] = Math.max(b[k],b[k+1]);

        int maksverdi = b[1], nestmaksverdi = Integer.MIN_VALUE;

        for (int m = 2*n - 1, k = 2; k < m; k *= 2)
        {
            int tempverdi = b[k+1];  // ok hvis maksverdi er b[k]
            if (maksverdi != b[k]) { tempverdi = b[k]; k++; }
            if (tempverdi > nestmaksverdi) nestmaksverdi = tempverdi;
        }

        return new int[] {maksverdi,nestmaksverdi}; // størst og nest størst

    } // nestMaks

    public static void kopier(int[] a, int[] b, int fra, int til, int ant){
        if(b == null || b.length == 0)
            throw new IllegalArgumentException("Arrayet er tom");

        int index = fra;
        for(int i = 0; i < ant; i++){
            b[index] = a[i];
            index++;
        }
    }

    public static void kopier2(int[] a, int[] b, int ant) throws IllegalArgumentException {
        //kopierer i midten
        System.out.println("1");
        kopier(a, b,  b.length/2 - a.length/2, b.length + a.length/2, a.length);
        //kopierer på slutten
        System.out.println("2");
        kopier(a, b, b.length - ant, b.length, ant);
        //kopierer på starten
        System.out.println("3");
        kopier(a, b, 0, a.length, a.length);
    }
}
