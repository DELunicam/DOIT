package it.unicam.cs.ids.doit;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.CandidatureRepository;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.progetto.Progetto;
import it.unicam.cs.ids.doit.progetto.Specializzazione;
import it.unicam.cs.ids.doit.utenti.Esperto;
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


    @Bean
    CommandLineRunner loadCandidature(CandidatureRepository repository) {
        Candidatura foo = new Candidatura();
		//foo.setId("CAND1");
		foo.setIdProgettista("PROG1");
		foo.setIdProgetto(Long.valueOf(1));
		foo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

		Candidatura bar = new Candidatura();
		//bar.setId("CAND2");
		bar.setIdProgettista("PROG4");
		bar.setIdProgetto(Long.valueOf(1));
		bar.setStatoCandidatura(StatoCandidatura.ACCETTATA);

		Candidatura sad = new Candidatura();
		//sad.setId("CAND3");
		sad.setIdProgettista("PROG5");
		sad.setIdProgetto(Long.valueOf(1));
		sad.setStatoCandidatura(StatoCandidatura.RIFIUTATA);

		Candidatura zoo = new Candidatura();
		//zoo.setId("CAND4");
		zoo.setIdProgettista("PROG3");
		zoo.setIdProgetto(Long.valueOf(2));
		zoo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

		Candidatura ciao = new Candidatura();
		//ciao.setId("CAND5");
		ciao.setIdProgettista("PROG3");
//		ciao.setIdProgetto(tre.getId());
		ciao.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

		Candidatura puffo = new Candidatura();
		//puffo.setId("CAND6");
		puffo.setIdProgettista("PROG2");
//		puffo.setIdProgetto(tre.getId());
		puffo.setStatoCandidatura(StatoCandidatura.DA_VALUTARE);

		Candidatura bee = new Candidatura();
		//bee.setId("CAND7");
		bee.setIdProgettista("PROG3");
		bee.setIdProgetto(Long.valueOf(1));
		bee.setStatoCandidatura(StatoCandidatura.PRESELEZIONATA);

		Candidatura poi = new Candidatura();
		//poi.setId("CAND8");
		poi.setIdProgettista("PROG2");
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

}
