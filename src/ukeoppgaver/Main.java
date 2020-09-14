package ukeoppgaver;

import ukeoppgaver.resources.Tabell;
import ukeoppgaver.uke2.*;
import ukeoppgaver.uke3.Oppgave134;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){

        String[] s = {"Per","Kari","Ole","Anne","Ali","Eva"};
        Tabell.innsettingssortering(s);
        System.out.println(Arrays.toString(s));  // [Ali, Anne, Eva, Kari, Ole, Per]

    }

}
