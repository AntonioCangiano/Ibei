package org.elis.ibei.service.impl;

import org.elis.ibei.model.Ruolo;
import org.elis.ibei.model.Utente;
import org.elis.ibei.repository.UtenteRepository;
import org.elis.ibei.service.def.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityServiceJPA implements SecurityService {

	private final UtenteRepository repo;

	@Override
	public void isAdmin(String email) {
		checkByEmail(email, Ruolo.ADMIN);

	}

	@Override
	public void isAdmin(long id) {
		checkById(id, Ruolo.ADMIN);
	}

	@Override
	public void isCliente(String email) {
		checkByEmail(email, Ruolo.CLIENTE);
	}

	@Override
	public void isCliente(long id) {
		checkById(id, Ruolo.CLIENTE);
	}

	@Override
	public void isSuperAdmin(String email) {
		checkByEmail(email, Ruolo.SUPER_ADMIN);
	}

	@Override
	public void isSuperAdmin(long id) {
		checkById(id, Ruolo.SUPER_ADMIN);

	}

	private void checkDiritti(Utente u,Ruolo r) {
		if(u.isDisattivato())throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		if(u.getRuolo()!=r)throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

	}

	private void checkByEmail(String email,Ruolo r) {
		Utente u=repo.findByEmail(email)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		checkDiritti(u, r);
	}

	private void checkById(long id,Ruolo r) {
		Utente u=repo.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		checkDiritti(u, r);
	}



}