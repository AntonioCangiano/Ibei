package org.elis.ibei.mapper;

import org.elis.ibei.dto.request.RegistrazioneRequestDTO;
import org.elis.ibei.dto.response.UtenteResponseDTO;
import org.elis.ibei.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {
    public Utente fromRegistrazioneRequestDTO(RegistrazioneRequestDTO uDTO) {
        Utente u=new Utente();
        u.setNome(uDTO.getNome());
        u.setCognome(uDTO.getCognome());
        u.setDataNascita(uDTO.getDataDiNascita());
        u.setEmail(uDTO.getEmail());
        u.setPassword(uDTO.getPassword());
        return u;
    }

    public UtenteResponseDTO toUtenteResponseDTO(Utente u) {
        UtenteResponseDTO uDTO =new UtenteResponseDTO();
        uDTO.setCognome(u.getCognome());
        uDTO.setNome(u.getNome());
        uDTO.setEmail(u.getEmail());
        uDTO.setDataNascita(u.getDataNascita());
        uDTO.setId(u.getId());
        uDTO.setRuolo(u.getRuolo().toString());
        return uDTO;
    }
}
