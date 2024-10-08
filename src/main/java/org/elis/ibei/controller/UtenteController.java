package org.elis.ibei.controller;

import org.elis.ibei.dto.request.CambiaPasswordRequestDTO;
import org.elis.ibei.dto.request.LoginRequestDTO;
import org.elis.ibei.dto.request.RegistrazioneRequestDTO;
import org.elis.ibei.dto.response.UtenteResponseDTO;
import org.elis.ibei.facede.UtenteFacade;
import org.elis.ibei.service.def.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UtenteController {

	private final UtenteFacade facade;
	private final SecurityService security;

	@PostMapping("/all/utente/registraCliente")
	public ResponseEntity<Void> registraCliente
			(@Valid @RequestBody RegistrazioneRequestDTO request){
		facade.registraUtente(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/superAdmin/utente/registraAdmin")
	public ResponseEntity<Void> registraAdmin
			(@Valid @RequestBody RegistrazioneRequestDTO request,
			 @RequestHeader("User") String emailSuperAdmin){
		security.isSuperAdmin(emailSuperAdmin);
		facade.registraAdmin(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/all/utente/login")
	public ResponseEntity<UtenteResponseDTO> login
			(@Valid @RequestBody LoginRequestDTO request){
		UtenteResponseDTO uDTO=facade.login(request);
		return ResponseEntity.ok(uDTO);
	}

	@PutMapping("/authorized/utente/cambiaPassword")
	public ResponseEntity<Void> cambiaPassword
			(@Valid @RequestBody CambiaPasswordRequestDTO request){
		facade.cambiaPassword(request);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/admin/utente/disattivaCliente/{id}")
	public ResponseEntity<Void> disattivaCliente(
			@PathVariable long id,@RequestHeader("User")String emailAdmin){
		security.isAdmin(emailAdmin);
		facade.disattivaUtente(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/superAdmin/utente/disattivaAdmin/{id}")
	public ResponseEntity<Void> disattivaAdmin(
			@PathVariable long id,@RequestHeader("User")String emailSuperAdmin){
		security.isSuperAdmin(emailSuperAdmin);
		facade.disattivaUtente(id);
		return ResponseEntity.ok().build();
	}
}