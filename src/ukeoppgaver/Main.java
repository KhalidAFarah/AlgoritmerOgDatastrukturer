package ukeoppgaver;

import ukeoppgaver.uke12.Uke12Main;

class M{
    public int v;
}
class  M2{
    public int v;
    public M2(){
        M m = new M();
        v = m.v;
    }
}

public class Main {

    public static void main(String[] args){
        //Uke12Main.main();
        M m1 = new M();
        M2 m2 = new M2();

        m2.v = 2;
        m1.v = 2;
        System.out.println("1:" + m1.v + " 2: " + m2.v);
    }

}
