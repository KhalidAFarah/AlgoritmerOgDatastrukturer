package ukeoppgaver.resources;

import java.util.NoSuchElementException;

public class TabellKø<T> implements Kø<T>
{
    private T[] a;      // en tabell
    private int fra;    // posisjonen til den første i køen
    private int til;    // posisjonen til første ledige plass

    @SuppressWarnings("unchecked")      // pga. konverteringen: Object[] -> T[]
    public TabellKø(int lengde)
    {
        if (lengde < 1)
            throw new IllegalArgumentException("Må ha positiv lengde!");

        a = (T[])new Object[lengde];

        fra = 0;
        til = 0;    // a[fra:til> er tom
    }

    public TabellKø()   // standardkonstruktør
    {
        this(8);
    }

    @Override
    public boolean leggInn(T verdi)   // null-verdier skal være tillatt
    {

        a[til] = verdi;                                 // ny verdi bakerst
        til = til + 1;// øker til med 1
        if (til == a.length)
            til = 0;                   // hopper til 0
        if (fra == til)
            a = utvidTabell(2*a.length);    // sjekker og dobler
        return true;                                    // vellykket innlegging
    }
    private T[] utvidTabell(int lengde)
    {
        @SuppressWarnings("unchecked")      // pga. konverteringen: Object[] -> T[]
                T[] b = (T[])new Object[lengde];  // ny tabell

        // kopierer intervallet a[fra:a.length> over i b
        System.arraycopy(a,fra,b,0,a.length - fra);

        // kopierer intervallet a[0:fra> over i b
        System.arraycopy(a,0,b,a.length - fra, fra);

        fra = 0; til = a.length;
        return b;
    }

    @Override
    public T kikk() {
        return a[fra];
    }

    @Override
    public T taUt() {
        if (fra == til) throw new         // sjekker om køen er tom
                NoSuchElementException("Køen er tom!");

        T temp = a[fra];                  // tar vare på den første i køen
        a[fra] = null;                    // nuller innholdet
        fra++;                            // øker fra med 1
        if (fra == a.length) fra = 0;     // hopper til 0
        return temp;
    }

    @Override
    public int antall() {
        return fra <= til ? til - fra : a.length + til - fra;
    }

    @Override
    public boolean tom() {
        return antall() == 0;
    }

    @Override
    public void nullstill() {
        while (!tom())taUt();
    }

    @Override
    public String toString(){
        String ut = "[";

        if(!tom()){
            ut += a[fra];
            for(int i = fra+1; i < til ; i++){
                ut += ", " + a[i % a.length];
            }
        }

        ut += "]";
        return ut;
    }

    public int indeksTil(T verdi){
        int indeks = 0;
        for(int i = fra; i <= til; i++){
            if(a[i%a.length] == verdi)
                return indeks;

            indeks++;
        }

        return -1;
    }

    public static <T> void snu(Stakk<T> A){
        TabellKø<T> kø = new TabellKø<>(A.antall());
        while (!A.tom()) kø.leggInn(A.taUt());

        while(!kø.tom()) A.leggInn(kø.taUt());
    }

    public static <T> void snu(Kø<T> A){
        Stakk<T> B = new TabellStakk<>(A.antall());


        while (!A.tom()) B.leggInn(A.taUt());

        while(!B.tom()) A.leggInn(B.taUt());
    }

    public static <T> void snu2(Kø<T> A){
        Kø<T> B = new TabellKø<>();
        Kø<T> C = new TabellKø<>();
        int n = A.antall();
        while (n > 0) {
            while (A.antall() > 1) B.leggInn(A.taUt());
            C.leggInn(A.taUt());
            while (!B.tom()) A.leggInn(B.taUt());

            n--;
        }

        while (!C.tom()) A.leggInn(C.taUt());
    }

    public static <T> void byttPlass(Kø<T> kø, int indeks){
        if(kø.antall() <= indeks) return;
        TabellKø<T> hjelpekø = new TabellKø(kø.antall());

        for(int i = indeks; i > 0; i--){
            hjelpekø.leggInn(kø.taUt());
        }
        T temp = kø.taUt();
        hjelpekø.leggInn(kø.taUt());
        hjelpekø.leggInn(temp);
        while (!kø.tom()) hjelpekø.leggInn(kø.taUt());
        while (!hjelpekø.tom()){
            kø.leggInn(hjelpekø.taUt());
        }


    }

} // class TabellKø
