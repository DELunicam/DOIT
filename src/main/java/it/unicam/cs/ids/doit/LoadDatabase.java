package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Progettista;
import it.unicam.cs.ids.doit.utenti.Proponente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    Proponente mario = new Proponente();
    Proponente dario = new Proponente();

    @Bean
    CommandLineRunner loadProponenti(ProponenteRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(mario));
            log.info("Preloading " + repository.save(dario));

        };
    }

    @Bean
    CommandLineRunner loadProgetti(ProgettoRepository repository) {

        Progetto prog3 = new Progetto(mario.getId(), "nome3", "desc3");
        HashMap<Specializzazione, Integer> map = new HashMap<Specializzazione, Integer>();
        map.put(Specializzazione.CHIMICA, 2);
        map.put(Specializzazione.INFORMATICA, 5);
        map.put(Specializzazione.INGEGNERIA, 1);
        map.put(Specializzazione.MATEMATICA, 3);
        prog3.setInfoProgettistiRichiesti(map);

        return args -> {
            log.info("Preloading " + repository.save(new Progetto(mario.getId(), "nome1", "desc1")));
            log.info("Preloading " + repository.save(new Progetto(dario.getId(), "nome2", "desc2")));
            log.info("Preloading" + repository.save(prog3));
        };
    }

    @Bean
    CommandLineRunner loadProgettisti(ProgettistaRepository repository) {
        Progettista luca = new Progettista();
        luca.setUsername("PROG1");
        luca.setNome("luca");

        Progettista enzo = new Progettista();
        enzo.setUsername("PROG2");
        enzo.setNome("enzo");
        enzo.addSpecializzazione(Specializzazione.INGEGNERIA);

        Progettista daniele = new Progettista();
        daniele.setUsername("PROG3");
        daniele.setNome("daniele");
        daniele.addSpecializzazione(Specializzazione.CHIMICA);
        daniele.addSpecializzazione(Specializzazione.INFORMATICA);

        Progettista marco = new Progettista();
        marco.setUsername("PROG4");
        marco.setNome("marco");

        Progettista paolo = new Progettista();
        paolo.setUsername("PROG5");
        paolo.setNome("paolo");

        return args -> {
            log.info("Preloading " + repository.save(luca));
            log.info("Preloading " + repository.save(enzo));
            log.info("Preloading " + repository.save(daniele));
            log.info("Preloading " + repository.save(marco));
            log.info("Preloading " + repository.save(paolo));

        };
    }


}
