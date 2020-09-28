package ukeoppgaver.eksempelKlasser;

import java.util.Objects;

public class Klokkeslett implements Comparable<Klokkeslett> {
    private int time;
    private int min;


    public Klokkeslett(String klokkeslett){
        String[] klokken = klokkeslett.split(":");
        this.time = Integer.parseInt(klokken[0]);
        this.min = Integer.parseInt(klokken[1]);
    }

    @Override
    public String toString(){
        return time + ":" + min;
    }

    @Override
    public boolean equals(Object k){
        if (k == this) return true;
        if (!(k instanceof Klokkeslett)) return false;
        return compareTo((Klokkeslett) k) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(time, min);
    }

    @Override
    public int compareTo(Klokkeslett o) {
        if(this.time > o.time){
            return 1;
        }else if(this.time < o.time){
            return -1;
        }else{
            if(this.min > o.min){
                return 1;
            }else if(this.min < o.min){
                return -1;
            }
        }
        return 0;
    }
}
