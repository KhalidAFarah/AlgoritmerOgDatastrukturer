package ukeoppgaver.uke3;

public class Oppgave134 {

    //Oppgave 9
    public static void utvalgssortering(int[] a, int fra, int til){
        if(fra >= 0 || a.length >= til || til > fra){
            for(int i = fra; i < til; i++){
                int min = i;
                for(int j = i; j < til; j++){
                    if(a[min] > a[j])
                        min = j;
                }

                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }
}
