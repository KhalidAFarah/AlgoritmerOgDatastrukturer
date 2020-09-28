package ukeoppgaver.eksempelKlasser;

import java.util.Objects;

public class Tid implements Comparable<Tid>{
    private Klokkeslett klokkeslett;
    private Dato dato;

    public Tid(Klokkeslett klokkeslett, Dato dato){
        this.klokkeslett = klokkeslett;
        this.dato = dato;
    }
    public Tid(String klokkeslett, Dato dato){
        this.klokkeslett = new Klokkeslett(klokkeslett);
        this.dato = dato;
    }
    public Tid(int dag, int mnd, int år, Klokkeslett klokkeslett){
        this.klokkeslett = klokkeslett;
        this.dato = new Dato(dag, mnd, år);
    }
    public Tid(int dag, Måned mnd, int år, Klokkeslett klokkeslett){
        this.klokkeslett = klokkeslett;
        this.dato = new Dato(dag, mnd, år);
    }
    public Tid(int dag, int mnd, int år, String klokkeslett){
        this.klokkeslett = new Klokkeslett(klokkeslett);
        this.dato = new Dato(dag, mnd, år);
    }
    public Tid(int dag, Måned mnd, int år, String klokkeslett){
        this.klokkeslett = new Klokkeslett(klokkeslett);
        this.dato = new Dato(dag, mnd, år);
    }

    @Override
    public String toString(){
        return dato.toString() + " , " + klokkeslett.toString();
    }

    @Override
    public boolean equals(Object k){
        if (k == this) return true;
        if (!(k instanceof Tid)) return false;
        return compareTo((Tid) k) == 0;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 97 * hash + dato.hashCode();
        hash = 97 * hash + klokkeslett.hashCode();
        return hash;
    }

    @Override
    public int compareTo(Tid o) {
        if(this.dato.compareTo(o.dato) > 0){
            return 1;
        }else if(this.dato.compareTo(o.dato) < 0){
            return -1;
        }else{
            if(this.klokkeslett.compareTo(o.klokkeslett) > 0){
                return 1;
            }else if(this.klokkeslett.compareTo(o.klokkeslett) < 0){
                return -1;
            }
        }

        return 0;
    }
}
