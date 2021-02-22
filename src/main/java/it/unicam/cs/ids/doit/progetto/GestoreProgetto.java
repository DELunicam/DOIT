package it.unicam.cs.ids.doit.progetto;

import it.unicam.cs.ids.doit.candidatura.Candidatura;
import it.unicam.cs.ids.doit.candidatura.CandidaturaRepository;
import it.unicam.cs.ids.doit.candidatura.StatoCandidatura;
import it.unicam.cs.ids.doit.utenti.Progettista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GestoreProgetto {
    private static GestoreProgetto instance;
    @Autowired
    ProgettoRepository progettoRepository;
    // TODO candidatureRepository non dovrebbe essere qui
    @Autowired
    CandidaturaRepository candidatureRepository;

    private GestoreProgetto() {
    }

    // Singleton
    public static GestoreProgetto getInstance() {
        if (instance == null) {
            instance = new GestoreProgetto();
        }
        return instance;
    }


    public Progetto createProgetto(Long idProponente, String nome, String descrizione) {
        Progetto progettoNeutro = new Progetto(idProponente, nome, descrizione);
        progettoRepository.save(progettoNeutro);
        return progettoNeutro;
    }

    // TODO
    public void requestEsperto(Progetto progettoNeutro) {
        progettoNeutro.setStatoProgetto(StatoProgetto.IN_VALUTAZIONE_PROGETTO);
        progettoRepository.save(progettoNeutro);
    }

    public void insertInfoProgettisti(Progetto progetto, Map<Specializzazione, Integer> in) {
        progetto.setInfoProgettistiRichiesti(in);
        progettoRepository.save(progetto);
    }

    public void insertInfoProgettisti(Long idProgetto, Map<Specializzazione, Integer> in) {
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        this.insertInfoProgettisti(progetto, in);
    }  

    public boolean checkIdProgetto(Long idProgetto)
    {   
        return progettoRepository.existsById(idProgetto);

    }
    public boolean checkStatoProgetto(Long idProgetto, StatoProgetto stato)
    {  
            boolean checkStato;
            Progetto progetto = progettoRepository.findById(idProgetto).get();
            if(progetto.getStatoProgetto() == stato)
            {
                checkStato = true;
            }
            else
            {
                checkStato = false;
            }
            return checkStato;
    }

    /**
     * @param idProgetto
     * @return Progetto con id uguale a idProgetto, null se non esiste
     */
    public Progetto getProgetto(Long idProgetto) {
        return progettoRepository.findById(idProgetto).get();
    }

    // TODO
    public void pubblicaProgetto(Progetto progetto) {
        progetto.setStatoProgetto(StatoProgetto.PUBBLICO);
        progettoRepository.save(progetto);
    }

    public Set<Progetto> getListaProgetti() {
        return new HashSet<>(progettoRepository.findAll());
    }

    public Set<Progetto> getListaProgetti(Long idProponente) {
        return progettoRepository.findAllByIdProponente(idProponente);
    }

    public Set<Progetto> getListaProgetti(Long idProponente, StatoProgetto stato) {
        return progettoRepository.findAllByIdProponenteAndStatoProgetto(idProponente, stato);
    }

    // TODO check implementazione migliore
    // Restituisce tutti i progetti aventi stato che richiedono una delle specializzazioni passate
    public Set<Progetto> getListaProgetti(Set<Specializzazione> specializzazioni, StatoProgetto statoProgetto) {
        Set<Progetto> progettiConStato = progettoRepository.findAllByStatoProgetto(statoProgetto);
        Set<Progetto> progettiCercati = new HashSet<>();
        for (Specializzazione specializzazione : specializzazioni) {
            progettiCercati.addAll(progettiConStato.stream().
                    filter(p -> p.getInfoProgettistiRichiesti().containsKey(specializzazione))
                    .collect(Collectors.toSet()));
        }
//        for (Progetto p : progettiConStato) {
//            for (Specializzazione s : specializzazioni) {
//                if (p.getInfoProgettistiRichiesti().containsKey(s)) {
//                    progettiCercati.add(p);
//                }
//            }
//        }
        return progettiCercati;
    }

    // Restituisce tutti i progetti aventi stato che richiedono la specializzazione passata
    public List<Progetto> getListaProgetti(Specializzazione specializzazione, StatoProgetto statoProgetto) {

        Set<Progetto> progettiConStato = progettoRepository.findAllByStatoProgetto(statoProgetto);
        return progettiConStato.stream().filter(p -> p.getInfoProgettistiRichiesti().containsKey(specializzazione)).collect(Collectors.toList());
    }

    public Set<Progetto> getListaProgetti(StatoProgetto statoProgetto) {
        return progettoRepository.findAllByStatoProgetto(statoProgetto);
    }

    public Set<Progetto> getProgettiCandidati(Set<Long> id) {
        return progettoRepository.findProgettiByIdIn(id);
    }
    
    // TODO check
    public Set<Progetto> getListaProgettiSvolti(Progettista progettista) {
        return getListaProgettiSvolti(progettista.getId());
    }

    // TODO check
    public Set<Progetto> getListaProgettiSvolti(Long idProgettista) {
        Set<Progetto> progettiSvolti = new HashSet<>();
        Set<Candidatura> candidatureAccettate = candidatureRepository.findAllByIdProgettista(idProgettista);
        candidatureAccettate = candidatureAccettate.stream().filter(c -> c.getStatoCandidatura().equals(StatoCandidatura.ACCETTATA)).collect(Collectors.toSet());

        for (Candidatura candidatura : candidatureAccettate) {
            progettiSvolti.add(progettoRepository.getOne(candidatura.getIdProgetto()));
        }

        return progettiSvolti;
    }

    public void notificaEsito(String idProgettista) {
        //TODO notificaEsito
    }

    // TODO check se serve
//    public String getInfoProgetto(Long idProgetto) {
//        return this.getProgetto(idProgetto).getInfo();
//    }

    public void modificaStatoProgetto(Progetto progetto, StatoProgetto statoProgetto) {
        progetto.setStatoProgetto(statoProgetto);
        progettoRepository.save(progetto);
    }

    public void modificaStatoProgetto(Long idProgetto, StatoProgetto statoProgetto) {
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        this.modificaStatoProgetto(progetto, statoProgetto);
    }

}
