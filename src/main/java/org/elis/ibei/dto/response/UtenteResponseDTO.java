package org.elis.ibei.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UtenteResponseDTO {
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String ruolo;
}
