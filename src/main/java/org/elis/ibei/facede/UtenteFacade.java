package org.elis.ibei.facede;

import org.elis.ibei.dto.request.CambiaPasswordRequestDTO;
import org.elis.ibei.dto.request.LoginRequestDTO;
import org.elis.ibei.dto.request.RegistrazioneRequestDTO;
import org.elis.ibei.dto.response.UtenteResponseDTO;
import org.elis.ibei.mapper.UtenteMapper;
import org.elis.ibei.model.Ruolo;
import org.elis.ibei.model.Utente;
import org.elis.ibei.service.def.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteFacade {

    private final UtenteService utenteService;
    private final UtenteMapper mapper;

    public void registraUtente(RegistrazioneRequestDTO request) {
        if(!request.getPassword().equals(request.getPasswordRipetuta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Utente u=mapper.fromRegistrazioneRequestDTO(request);
        u.setRuolo(Ruolo.CLIENTE);
        utenteService.registraUtente(u);
    }

    public void registraAdmin(RegistrazioneRequestDTO request) {
        if(!request.getPassword().equals(request.getPasswordRipetuta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Utente u=mapper.fromRegistrazioneRequestDTO(request);
        u.setRuolo(Ruolo.ADMIN);
        utenteService.registraUtente(u);
    }

    public UtenteResponseDTO login(LoginRequestDTO request) {
        Utente u=utenteService.login(request.getEmail(), request.getPassword());
        UtenteResponseDTO uDTO=mapper.toUtenteResponseDTO(u);
        return uDTO;
    }

    public void cambiaPassword(CambiaPasswordRequestDTO request) {
        Utente u=utenteService.getById(request.getId());
        if(u.isDisattivato()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if(!u.getPassword().equals(request.getVecchiaPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if(!request.getNuovaPassword().equals(request.getNuovaPasswordRipetuta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        u.setPassword(request.getNuovaPassword());
        utenteService.aggiornaUtente(u);
    }

    public void disattivaUtente(long id) {
        utenteService.disattivaUtente(id);
    }


}