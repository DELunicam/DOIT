package it.unicam.cs.ids.doit.utils;

import it.unicam.cs.ids.doit.associazione.Associazione;
import it.unicam.cs.ids.doit.associazione.AssociazioneRepository;
import it.unicam.cs.ids.doit.associazione.StatoAssociazione;
import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.CandidaturaRepository;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.gestori_utenti.*;
import it.unicam.cs.ids.doit.notifica.Messaggio;
import it.unicam.cs.ids.doit.notifica.MessaggioRepository;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.ProgettoRepository;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.progetto.StatoProgetto;
import it.unicam.cs.ids.doit.utenti.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner loadProponenti(ProponenteRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Proponente("prop1", BCrypt.hashpw("prop1", BCrypt.gensalt()), "mail1", "mario", "alto")));
            log.info("Preloading " + repository.save(new Proponente("prop2", BCrypt.hashpw("prop2", BCrypt.gensalt()), "mail2", "dario", "basso")));
        };
    }

    @Bean
    CommandLineRunner loadProgetti(ProgettoRepository repository) {

        Progetto prog3 = new Progetto(1L, "nome3", "desc3");
        HashMap<Specializzazione, Integer> map = new HashMap<Specializzazione, Integer>();
        map.put(Specializzazione.CHIMICA, 2);
        map.put(Specializzazione.INFORMATICA, 5);
        map.put(Specializzazione.INGEGNERIA, 1);
        map.put(Specializzazione.MATEMATICA, 3);
        prog3.setInfoProgettistiRichiesti(map);
        prog3.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_CANDIDATURE);

        Progetto prog4 = new Progetto(1L, "nome4", "desc4");
        prog4.setInfoProgettistiRichiesti(map);
        prog4.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_PROGETTO);

        Progetto prog5 = new Progetto(1L, "nome2", "desc2");
        prog5.setInfoProgettistiRichiesti(map);
        prog5.setStatoProgetto(StatoProgetto.PUBBLICO);

        return args -> {
            log.info("Preloading " + repository.save(new Progetto(2L, "nome1", "desc1")));
            log.info("Preloading " + repository.save(new Progetto(1L, "nome2", "desc2")));
            log.info("Preloading" + repository.save(prog3));
            log.info("Preloading" + repository.save(prog4));
            log.info("Preloading " + repository.save(prog5));
        };
    }

    @Bean
    CommandLineRunner loadProgettisti(ProgettistaRepository repository) {
        Progettista luca = new Progettista();
        luca.setUsername("PROG1");
        luca.setPassword(BCrypt.hashpw("PROG1", BCrypt.gensalt()));
        luca.setNome("luca");

        Progettista enzo = new Progettista();
        enzo.setUsername("PROG2");
        enzo.setPassword(BCrypt.hashpw("PROG2", BCrypt.gensalt()));
        enzo.setNome("enzo");
        enzo.addSpecializzazione(Specializzazione.INGEGNERIA);

        Progettista daniele = new Progettista();
        daniele.setUsername("PROG3");
        daniele.setPassword(BCrypt.hashpw("PROG3", BCrypt.gensalt()));
        daniele.setNome("daniele");
        daniele.addSpecializzazione(Specializzazione.CHIMICA);
        daniele.addSpecializzazione(Specializzazione.INFORMATICA);

        Progettista marco = new Progettista();
        marco.setUsername("PROG4");
        marco.setPassword(BCrypt.hashpw("PROG4", BCrypt.gensalt()));
        marco.setNome("marco");

        Progettista paolo = new Progettista();
        paolo.setUsername("PROG5");
        paolo.setPassword(BCrypt.hashpw("PROG5", BCrypt.gensalt()));
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
        foo.setIdProgettista(8L);
        foo.setIdProgetto(7L);
        foo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura bar = new Candidatura();
        //bar.setId("CAND2");
        bar.setIdProgettista(8L);
        bar.setIdProgetto(7L);
        bar.setStatoCandidatura(StatoCandidatura.ACCETTATA);

        Candidatura sad = new Candidatura();
        //sad.setId("CAND3");
        sad.setIdProgettista(8L);
        sad.setIdProgetto(Long.valueOf(7));
        sad.setStatoCandidatura(StatoCandidatura.RIFIUTATA);

        Candidatura zoo = new Candidatura();
        //zoo.setId("CAND4");
        zoo.setIdProgettista(9L);
        zoo.setIdProgetto(Long.valueOf(5));
        zoo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura ciao = new Candidatura();
        //ciao.setId("CAND5");
        ciao.setIdProgettista(10L);
		ciao.setIdProgetto(5L);
        ciao.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura puffo = new Candidatura();
        //puffo.setId("CAND6");
        puffo.setIdProgettista(8L);
		puffo.setIdProgetto(5L);
        puffo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

        Candidatura bee = new Candidatura();
        bee.setIdProgettista(8L);
        bee.setIdProgetto(Long.valueOf(3));
        bee.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA);

        Candidatura poi = new Candidatura();
        poi.setIdProgettista(8L);
        poi.setIdProgetto(Long.valueOf(3));
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
            log.info("Preloading " + repository.save(new Esperto("esp1", BCrypt.hashpw("esp1", BCrypt.gensalt()), "mail", "nome", "cognome")));
            log.info("Preloading " + repository.save(new Esperto("esp2", BCrypt.hashpw("esp2", BCrypt.gensalt()), "mail", "nome", "cognome")));
        };
    }

    @Bean
    CommandLineRunner loadEnti(EnteRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Ente("apple", BCrypt.hashpw("apple", BCrypt.gensalt()), "mail", "nome")));
            log.info("Preloading " + repository.save(new Ente("microsoft", BCrypt.hashpw("microsoft", BCrypt.gensalt()), "mail", "nome")));
            log.info("Preloading " + repository.save(new Ente("governo", BCrypt.hashpw("governo", BCrypt.gensalt()), "mail", "nome")));
        };
    }

    @Bean
    CommandLineRunner loadAssociazioni(AssociazioneRepository repository) {
        Associazione a = new Associazione(23L, 8L, 3L);
        a.setStatoAssociazione(StatoAssociazione.PROPOSTA);

        Associazione b = new Associazione(23L, 8L, 3L);
        b.setStatoAssociazione(StatoAssociazione.PROPOSTA);
        return args -> {
            log.info("Preloading " + repository.save(new Associazione(23L, 8L, 3L)));
            log.info("Preloading " + repository.save(new Associazione(23L, 9L, 3L)));
            log.info("Preloading " + repository.save(new Associazione(24L, 10L, 4L)));
            log.info("Preloading " + repository.save(new Associazione(24L, 10L, 5L)));
            log.info("Preloading " + repository.save(new Associazione(25L, 11L, 5L)));
            log.info("Preloading " + repository.save(new Associazione(25L, 12L, 5L)));
            log.info("Preloading " + repository.save(a));
            log.info("Preloading " + repository.save(b));
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
            log.info("Preloading " + repository.save(new Lavoratore("Mario", "Rossi", 23L)));
            log.info("Preloading " + repository.save(new Lavoratore("Paolo", "Verdi", 23L)));
            log.info("Preloading " + repository.save(new Lavoratore("Giuseppe", "Bianchi", 23L)));
            log.info("Preloading " + repository.save(new Lavoratore("Franco", "Neri", set1, 24L)));
            log.info("Preloading " + repository.save(new Lavoratore("Augusto", "Viola", set2, 24L)));
            log.info("Preloading " + repository.save(new Lavoratore("Mauro", "Rosa", set3, 25L)));
        };
    }

    @Bean
    CommandLineRunner loadMessaggi(MessaggioRepository repository) {
        Messaggio msg1 = new Messaggio(1L, 8L, "Ciao, questo è un messaggio di prova");
        Messaggio msg2 = new Messaggio(1L, 8L, "Ciao, questo è un messaggio di prova letto");
        msg2.setLetto(true);
        return args -> {
            log.info("Preloading " + repository.save(msg1));
            log.info("Preloading " + repository.save(msg2));
            log.info("Preloading " + repository.save(new Messaggio(8L, 1L, "Ciao, questo è un messaggio di prova")));
            log.info("Preloading " + repository.save(new Messaggio(9L, 10L, "Ciao, questo è un messaggio di prova")));
            log.info("Preloading " + repository.save(new Messaggio(9L, 21L, "Ciao, questo è un messaggio di prova")));
            log.info("Preloading " + repository.save(new Messaggio(21L, 23L, "Ciao, questo è un messaggio di prova")));
        };
    }

}
