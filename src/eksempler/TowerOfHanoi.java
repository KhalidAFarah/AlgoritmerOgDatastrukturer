package eksempler;

public class TowerOfHanoi {
    /**
     * Solves tower of hanoi puzzle with a recursive function
     * @param a tower 1
     * @param b tower 2
     * @param c tower 3
     */
    public static void Hanoi(char a, char b, char c, int brikkeNr){
        if(brikkeNr == 0)
            return;

        //Flytt alle untatt den nederste fra A til B
        Hanoi(a,c,b,brikkeNr-1);

        //Flytt nederste fra A til C
        System.out.println("Flytter brikke " + brikkeNr + " fra " + a + " til " + c);

        //Flytt alle fra B til C
        Hanoi(b,a,c,brikkeNr-1);
    }
}
