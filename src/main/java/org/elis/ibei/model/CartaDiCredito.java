package org.elis.ibei.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class CartaDiCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numeroCarta;
    private LocalDate scedenza;
    private String cvv;
    private boolean isDefault;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_utente")
    private Utente utente;
}
