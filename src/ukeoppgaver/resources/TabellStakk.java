package ukeoppgaver.resources;

public class TabellStakk<T> implements Stakk<T>
{
    private T[] a;                     // en T-tabell
    private int antall;                // antall verdier på stakken

    public TabellStakk()               // konstruktør - tabellengde 8
    {
        this(8);
    }

    @SuppressWarnings("unchecked")     // pga. konverteringen: Object[] -> T[]
    public TabellStakk(int lengde)     // valgfri tabellengde
    {
        if (lengde < 0)
            throw new IllegalArgumentException("Negativ tabellengde!");

        a = (T[])new Object[lengde];     // oppretter tabellen
        antall = 0;                      // stakken er tom
    }

    @Override
    public void leggInn(T verdi) {
        a[antall] = verdi;
        antall++;
    }

    @Override
    public T kikk() {
        return null;
    }

    @Override
    public T taUt() {
        T temp = a[antall-1];
        a[antall-1] = null;
        antall--;
        return temp;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {
        for(int i = 0; i < antall; i++){
            a[i] = null;
        }
        antall = 0;
    }

    public String toString(){
        String ut = "[";
        if(antall > 0){
            ut += a[0];
            for(int i = 1; i < antall; i++){
                ut += ", " + a[i];
            }
        }
        ut += "]";
        return ut;
    }

    public static <T> void snu(Stakk<T> A){
        Stakk<T> B = new TabellStakk<>();
        Stakk<T> C = new TabellStakk<>();

        while (!A.tom()) B.leggInn(A.taUt());
        while (!B.tom()) C.leggInn(B.taUt());
        while (!C.tom()) A.leggInn(C.taUt());
    }

    public static <T> void snu2(Stakk<T> A){
        Stakk<T> B = new TabellStakk<>();
        int antall = A.antall();

        while (antall > 0){
            T start = A.taUt();
            while (!A.tom())
                B.leggInn(A.taUt());
            T slutt = B.taUt();
            A.leggInn(start);
            while (!B.tom())
                A.leggInn(B.taUt());
            A.leggInn(slutt);
            antall--;
        }
    }

    public static <T> void kopier(Stakk<T> A, Stakk<T> B){
        Stakk<T> C = new TabellStakk<>();
        while (!A.tom()) C.leggInn(A.taUt());

        while (!C.tom()){
            T temp = C.taUt();
            A.leggInn(temp);
            B.leggInn(temp);
        }
    }

    public static <T> void kopier2(Stakk<T> A, Stakk<T> B){
        int n = A.antall();

        for(int i = 1; i < n; i++) {
            while (A.antall() > i)
            { B.leggInn(A.taUt());}
            T temp = A.taUt();
            A.leggInn(temp);

            int teller = i;
                while (n -  teller != 0){
                    A.leggInn(B.taUt());
                    teller++;
                }

            B.leggInn(temp);

        }

        T temp = A.taUt();
        A.leggInn(temp);
        B.leggInn(temp);

    }

}  // class TabellStakk