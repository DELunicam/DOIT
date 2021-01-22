package it.unicam.cs.ids.doit.progetto;

import java.util.HashMap;
import java.util.Map;

public class Progetto {

    private String id;
    private String nome;
    private String descrizione;
    private StatoProgetto stato;
    private String idProponente;
    private Map<Specializzazione, Integer> infoProgettistiRichiesti = new HashMap<>();

    public Progetto(String idProponente, String nome, String descrizione) {
        this.idProponente = idProponente;
        this.nome = nome;
        this.descrizione = descrizione;
        this.stato = StatoProgetto.NEUTRO;
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

    public String getIdProponente() {
        return this.idProponente;
    }

    public void setIdProponente(String idProponente) {
        this.idProponente = idProponente;
    }

    public Map<Specializzazione, Integer> getInfoProgettistiRichiesti() {
        return infoProgettistiRichiesti;
    }

    public void setInfoProgettistiRichiesti(Map<Specializzazione, Integer> infoProgettistiRichiesti) {
        this.infoProgettistiRichiesti = infoProgettistiRichiesti;
    }

    public Integer getNumeroProgettistiRichiesti() {
        Integer totale = 0;
        for (Specializzazione i : this.infoProgettistiRichiesti.keySet()) {
            totale += this.infoProgettistiRichiesti.get(i);
        }
        return totale;
    }

    public void addSingleInfoProgettistiRichiesti(Specializzazione tag, Integer numero) {
        this.infoProgettistiRichiesti.put(tag, numero);
    }

    public void addMultipleInfoProgettistiRichiesti(Map<Specializzazione, Integer> in) {
//        for (Specializzazione i : in.keySet()) {
//            addSingleInfoProgettistiRichiesti(i, in.get(i));
//        }
        this.infoProgettistiRichiesti.putAll(in);
    }

    public String getInfo() {
        return "ID: " + this.getId() + "\n"
                + "Nome: " + this.getNome() + "\n"
                + "Descrizione: " + this.getDescrizione() + "\n"
                + "Stato: " + this.getStatoProgetto() + "\n"
                + "Progettisti richiesti: \n" + this.progettistiString();
    }

    private String progettistiString() {
        StringBuilder output = new StringBuilder();
        output.append("Specializzazione, numero richiesti\n");
        this.infoProgettistiRichiesti.forEach((spec, num) -> {
            output.append(spec).append(", ").append(num).append("\n");
        });
        return output.toString();
    }
//        private String progettistiString() {
//        String out = new String();
//        for (String i : this.infoProgettistiRichiesti.keySet()) {
//            out += "Specializzazione: " + i + ", numero progettisti necessari: " + this.infoProgettistiRichiesti.get(i) + "\n";
//        }
//        return out;
//    }

//    // extra per comodità (usato in IProponente.selezionaProgetto())
//    public void setInfo(String id, String nome, String descrizione, StatoProgetto stato) {
//        this.id = id;
//        this.nome = nome;
//        this.descrizione = descrizione;
//        this.stato = stato;
//    }
}
