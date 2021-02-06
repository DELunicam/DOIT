package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner loadProgetti(ProgettoRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Progetto("PROPONENTE1", "nome1", "desc1")));
            log.info("Preloading " + repository.save(new Progetto("PROPONENTE2", "nome2", "desc2")));
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
