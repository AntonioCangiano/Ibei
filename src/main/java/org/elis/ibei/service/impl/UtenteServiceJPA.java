package org.elis.ibei.service.impl;

import java.util.List;

import org.elis.ibei.model.Utente;
import org.elis.ibei.repository.UtenteRepository;
import org.elis.ibei.service.def.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteServiceJPA implements UtenteService {

	private final UtenteRepository repo;

	@Override
	public void registraUtente(Utente u) {
		if(u.getId()!=0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		repo.save(u);

	}

	@Override
	public Utente login(String email, String password) {
		return repo.findByEmailAndPasswordAndDisattivatoIsFalse(email, password)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public void aggiornaUtente(Utente u) {
		if(u.getId()<1)throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		repo.save(u);

	}

	@Override
	public Utente getById(long id) {
		return repo.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public List<Utente> getAll() {
		return repo.findAllByDisattivatoIsFalse();
	}

	@Override
	public void disattivaUtente(long id) {
		Utente u=getById(id);
		u.setDisattivato(true);
		repo.save(u);

	}

	@Override
	public Utente getbyEmail(String email) {
		return repo.findByEmail(email)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}