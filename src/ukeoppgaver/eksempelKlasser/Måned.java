package ukeoppgaver.eksempelKlasser;

public enum Måned {

    JAN   ("Januar",1),
    FEB   ("Februar",2),
    MAR   ("Mars",3),
    APR   ("April",4),
    MAI   ("Mai",5),
    JUN   ("Juni",6),
    JUL   ("Juli",7),
    AUG   ("August",8),
    SEP   ("September",9),
    OKT   ("Oktober",10),
    NOV   ("November",11),
    DES   ("Desember",12);


    private String navn;
    private int dato;
    private Måned(String navn, int dato){
        this.navn = navn;
        this.dato = dato;
    }
    public String toString(){
        return getNavn() + " " + getMndNr();
    }

    public String getNavn(){
        return this.navn;
    }
    public int getMndNr(){
        return this.dato;
    }

    public static Måned[] vår(){
        Måned[] vår = new Måned[2];
        vår[0] = APR;
        vår[1] = MAI;
        return vår;
    }
    public static Måned[] sommer(){
        Måned[] sommer = new Måned[3];
        sommer[0] = JUN;
        sommer[1] = JUL;
        sommer[2] = AUG;
        return sommer;
    }
    public static Måned[] høst(){
        Måned[] høst = new Måned[2];
        høst[0] = SEP;
        høst[1] = OKT;
        return høst;
    }
    public static Måned[] vinter(){
        Måned[] vinter = new Måned[5];
        vinter[0] = NOV;
        vinter[1] = DES;
        vinter[2] = JAN;
        vinter[3] = FEB;
        vinter[4] = MAR;
        return vinter;
    }

}
