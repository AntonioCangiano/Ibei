package org.elis.ibei.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String indirizzoRiga1;
    private String indirizzoRiga2;
    private String cap;
    private String comune;
    private String stato;
    private String numeroTelefono;
    private boolean isDefault;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_utente")
    private Utente utente;
}
