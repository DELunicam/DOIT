package it.unicam.cs.ids.doit.utils.printers;
import it.unicam.cs.ids.doit.valutazione.Valutazione;
import it.unicam.cs.ids.doit.valutazione.ValutazioneController;
import it.unicam.cs.ids.doit.utils.SpringContext;
import java.util.Set;
public abstract class PrinterValutazioni {
    private static ValutazioneController getValutazioneController()
    {
        return SpringContext.getBean(ValutazioneController.class);
    }

    public static void printListaValutazioni(Long idProgetto)

    {
        Set<Valutazione> valutazioni = getValutazioneController().getValutzioniByIdProgetto(idProgetto);
        printBasicValutazioni(valutazioni);
    }

    public static void printBasicValutazioni(Set<Valutazione> valutazioni)
    
    {
        if (valutazioni.size() == 0) {
            System.out.println("Non sono stati trovate valutazioni");
        } else {
            System.out.println("ID_VALUTAZIONE, ID_PROGETTO, ID_ESPERTO, FATTIBILITA'\n");
            for (Valutazione valutazione : valutazioni) {
                System.out.println(valutazione.getId()+ ""+valutazione.getIdProgetto() +""+valutazione.getIdEsperto()+ ""+valutazione.getFattibilità()+ "\n");
            }
        }
    }
    
        public static void printInfoValutazione(Long idValutazione)
        {
            Valutazione valutazione = getValutazioneController().oneById(idValutazione);
            if (valutazione != null) {
                printInfoValutazione(valutazione);
            }
            else {
                System.out.println("Impossibile trovare il progetto desiderato");
            }
        }
        public static void printInfoValutazione(Valutazione valutazione) {
            System.out.println("----------------------------");
            System.out.println("IdVautazione: " + valutazione.getId());
            System.out.println("IdEsperto: " + valutazione.getIdEsperto());
            System.out.println("IdProgetto: " + valutazione.getIdProgetto());
            System.out.println("Fattibilità: " + valutazione.getFattibilità());
            System.out.println("Progettisti richiesti: \n" +
                    "SPECIALIZZAZONE | NUMERO");
            valutazione.getLavoratoriConsigliati().forEach((key, value) -> System.out.println(key + " : " + value));
            System.out.println("Parere: " + valutazione.getParere());
            System.out.println("----------------------------");
        }
    
}   
