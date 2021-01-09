package it.unicam.cs.ids.progetto;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgettoTest {

    @Test
    void progettistiString() {
        Progetto progetto = new Progetto("id1", "nome", "descrizione");
        Map<Specializzazione, Integer> infoProgettistiRichiesti = new HashMap<>();
        infoProgettistiRichiesti.put(Specializzazione.CHIMICA, 3);
        infoProgettistiRichiesti.put(Specializzazione.INFORMATICA, 1);
        progetto.setInfoProgettistiRichiesti(infoProgettistiRichiesti);
        String toOutput = "Specializzazione, numero richiesti\n"+
                "CHIMICA, 3\n"+
                "INFORMATICA, 1\n";
        System.out.println(progetto.getInfo());
        assertEquals(toOutput, progetto.getInfo());
    }
}