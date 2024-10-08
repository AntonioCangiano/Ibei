package org.elis.ibei.repository;

import java.util.List;
import java.util.Optional;

import org.elis.ibei.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente,Long > {

	Optional<Utente> findByEmail(String email);
	Optional<Utente> findByEmailAndPasswordAndDisattivatoIsFalse
										(String email, String password);
	List<Utente> findAllByDisattivatoIsFalse();
}
