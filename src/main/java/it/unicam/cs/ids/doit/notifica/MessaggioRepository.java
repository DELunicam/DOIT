package it.unicam.cs.ids.doit.notifica;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MessaggioRepository extends JpaRepository<Messaggio, Long> {

    Set<Messaggio> findAllByLettoIsTrue();

    Set<Messaggio> findAllByLettoIsFalse();
}
