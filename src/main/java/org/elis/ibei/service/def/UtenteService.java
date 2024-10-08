package org.elis.ibei.service.def;

import java.util.List;

import org.elis.ibei.model.Utente;

public interface UtenteService {
	public void registraUtente(Utente u);
	public Utente login(String email,String password);
	public void aggiornaUtente(Utente u);
	public Utente getById(long id);
	public List<Utente> getAll();
	public void disattivaUtente(long id);
	public Utente getbyEmail(String email);
}
