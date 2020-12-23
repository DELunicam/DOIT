package it.unicam.cs.ids.progetto;

public enum StatoProgetto {
	NEUTRO, // Progetto creato ma visibile solo al Proponente
	PUBBLICO,	// Progetto aperto a candidature e visibile ai Progettisti
	INATTIVO,	// Progetto creato ma interrotto per varie cause
	IN_VALUTAZIONE_PROGETTO,	// Progetto creato e in valutazione, invisibile ai progettisti e chiuso a candidature
	IN_VALUTAZIONE_CANDIDATURE, // Progetto creato e visibile dagli esperti per valutare le candidature
	FINALIZZATO	// Progetto e Progettisti confermati
}
