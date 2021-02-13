package it.unicam.cs.ids.doit.utils;

import it.unicam.cs.ids.doit.associazione.Associazione;
import it.unicam.cs.ids.doit.associazione.AssociazioneRepository;
import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.CandidaturaRepository;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.gestori_utenti.EnteRepository;
import it.unicam.cs.ids.doit.gestori_utenti.EspertoRepository;
import it.unicam.cs.ids.doit.gestori_utenti.LavoratoreRepository;
import it.unicam.cs.ids.doit.gestori_utenti.ProgettistaRepository;
import it.unicam.cs.ids.doit.gestori_utenti.ProponenteRepository;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.ProgettoRepository;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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


    @Bean
    CommandLineRunner loadCandidature(CandidaturaRepository repository) {
        Candidatura foo = new Candidatura();
        //foo.setId("CAND1");
        foo.setIdProgettista(7L);
        foo.setIdProgetto(5L);
        foo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura bar = new Candidatura();
        //bar.setId("CAND2");
        bar.setIdProgettista(7L);
        bar.setIdProgetto(4L);
        bar.setStatoCandidatura(StatoCandidatura.ACCETTATA);

        Candidatura sad = new Candidatura();
        //sad.setId("CAND3");
        sad.setIdProgettista(7L);
        sad.setIdProgetto(Long.valueOf(1));
        sad.setStatoCandidatura(StatoCandidatura.RIFIUTATA);

        Candidatura zoo = new Candidatura();
        //zoo.setId("CAND4");
        zoo.setIdProgettista(7L);
        zoo.setIdProgetto(Long.valueOf(2));
        zoo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura ciao = new Candidatura();
        //ciao.setId("CAND5");
        ciao.setIdProgettista(7L);
//		ciao.setIdProgetto(tre.getId());
        ciao.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura puffo = new Candidatura();
        //puffo.setId("CAND6");
        puffo.setIdProgettista(7L);
//		puffo.setIdProgetto(tre.getId());
        puffo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura bee = new Candidatura();
        //bee.setId("CAND7");
        bee.setIdProgettista(7L);
        bee.setIdProgetto(Long.valueOf(1));
        bee.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA);

        Candidatura poi = new Candidatura();
        //poi.setId("CAND8");
        poi.setIdProgettista(7L);
        poi.setIdProgetto(Long.valueOf(1));
        poi.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);


        return args -> {
            log.info("Preloading " + repository.save(foo));
            log.info("Preloading " + repository.save(bar));
            log.info("Preloading " + repository.save(sad));
            log.info("Preloading " + repository.save(zoo));
            log.info("Preloading " + repository.save(ciao));
            log.info("Preloading " + repository.save(puffo));
            log.info("Preloading " + repository.save(bee));
            log.info("Preloading " + repository.save(poi));
        };
    }

    @Bean
    CommandLineRunner loadEsperto(EspertoRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Esperto()));
            log.info("Preloading " + repository.save(new Esperto()));
        };
    }

    @Bean
    CommandLineRunner loadEnti(EnteRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Ente("Apple")));
            log.info("Preloading " + repository.save(new Ente("Microsoft")));
            log.info("Preloading " + repository.save(new Ente("Governo")));
        };
    }

    @Bean
    CommandLineRunner loadAssociazioni(AssociazioneRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Associazione(21L,6L,3L)));
            log.info("Preloading " + repository.save(new Associazione(21L,7L,3L)));
            log.info("Preloading " + repository.save(new Associazione(22L,8L,4L)));
            log.info("Preloading " + repository.save(new Associazione(22L,9L,5L)));
            log.info("Preloading " + repository.save(new Associazione(23L,10L,5L)));
            log.info("Preloading " + repository.save(new Associazione(23L,6L,5L)));
        };
    }

    @Bean
    CommandLineRunner loadLavoratori(LavoratoreRepository repository) {
        Set<Specializzazione> set1 = new HashSet<Specializzazione>();
        set1.add(Specializzazione.CHIMICA);
        set1.add(Specializzazione.INGEGNERIA);
        Set<Specializzazione> set2 = new HashSet<Specializzazione>();
        set2.add(Specializzazione.CHIMICA);
        set2.add(Specializzazione.MATEMATICA);
        Set<Specializzazione> set3 = new HashSet<Specializzazione>();
        set3.add(Specializzazione.MATEMATICA);
        set3.add(Specializzazione.INGEGNERIA);
        set3.add(Specializzazione.INFORMATICA);
        return args -> {
            log.info("Preloading " + repository.save(new Lavoratore("Mario", "Rossi", 21L)));
            log.info("Preloading " + repository.save(new Lavoratore("Paolo", "Verdi", 21L)));
            log.info("Preloading " + repository.save(new Lavoratore("Giuseppe", "Bianchi", 21L)));
            log.info("Preloading " + repository.save(new Lavoratore("Franco", "Neri", set1, 22L)));
            log.info("Preloading " + repository.save(new Lavoratore("Augusto", "Viola", set2, 22L)));
            log.info("Preloading " + repository.save(new Lavoratore("Mauro", "Rosa", set3, 21L)));
        };
    }

}
