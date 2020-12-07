package it.unicam.cs.ids.progetto;

import it.unicam.cs.ids.utenti.Progettista;

//import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Progetto {
    
    private String id;
    private String nome;
    private String descrizione;
    private StatoProgetto stato;
    private Set<Progettista> progettisti = new HashSet<Progettista>();
    //HashMap<String, Integer> infoProgettistiRichiesti = new HashMap<String, Integer>(); // tag e numero necessario
    private Map<Specializzazione, Integer> infoProgettistiRichiesti;
    private Set<Candidatura> candidature = new HashSet<Candidatura>();
    
    public Progetto(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public StatoProgetto getStatoProgetto() {
        return this.stato;
    }

    public void setStatoProgetto(StatoProgetto statoProgetto) {
        this.stato = statoProgetto;
    }

    public void setProgettista(Progettista tizio) {
        this.progettisti.add(tizio);
    }

    public void setProgettisti(HashSet<Progettista> set) {
        for (Progettista tizio : set) {
            setProgettista(tizio);
        }
    }

    // credo non serva, fatto con id tante volte servisse
    public Progettista getProgettista(String givenId) {
        Progettista cercato = new Progettista();
        for (Progettista tizio : this.progettisti) {
            if (tizio.getId().equals(givenId))
                cercato = tizio;
        }
        return cercato;
    }

    public Set<Progettista> getProgettisti() {
        return this.progettisti;
    }

    public String getStringProgettisti() {
        String out = new String();
        for (Progettista tizio : this.progettisti) {
            out += "ID: " + tizio.getId() + "\n";
            // System.out.println("ID: " + tizio.id + ", nome: " + tizio.nome);
        }
        return out;
    }

    public void setSingleInfoProgettistiRichiesti(Specializzazione tag, Integer numero) {
        this.infoProgettistiRichiesti.put(tag, numero);
    }

    public void setMultipleInfoProgettistiRichiesti(Map<Specializzazione, Integer> in) {
//        for (Specializzazione i : in.keySet()) {
//            setSingleInfoProgettistiRichiesti(i, in.get(i));
//        }
        this.infoProgettistiRichiesti.putAll(in);
    }

//    public String getInfoProgettistiRichiesti() {
//        String out = new String();
//        for (String i : this.infoProgettistiRichiesti.keySet()) {
//            out += "Specializzazione: " + i + ", numero progettisti necessari: " + this.infoProgettistiRichiesti.get(i) + "\n";
//        }
//        return out;
//    }
    public Map<Specializzazione, Integer> getInfoProgettistiRichiesti() {
        return infoProgettistiRichiesti;
    }


//    public Integer getNumeroProgettistiRichiesti() {
//        Integer out = 0;
//        for (String i : this.infoProgettistiRichiesti.keySet()) {
//            out += this.infoProgettistiRichiesti.get(i);
//        }
//        return out;
//    }

    public void setInfoProgettistiRichiesti(Map<Specializzazione, Integer> infoProgettistiRichiesti) {
        this.infoProgettistiRichiesti = infoProgettistiRichiesti;
    }

    // candidature???

    // extra per comodit�
    public void setInfo(String id, String nome, String descrizione, StatoProgetto stato) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.stato = stato;
    }

    public String getInfo() {
        String info = "ID: " + this.getId() + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Descrizione: " + this.getDescrizione() + "\n"
                + "Stato: " + this.getStatoProgetto() + "\n"
                + "Progettisti: \n" + this.getProgettisti() + "\n"
                + "Progettisti richiesti: \n" + this.getInfoProgettistiRichiesti() + "\n";
        return info;
    }

    public void printInfo() {
        System.out.println(this.getInfo());
    }

}
