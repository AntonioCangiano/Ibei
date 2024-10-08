package org.elis.ibei.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_utente","id_prodotto"})

})
public class Recensione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Column(length = 512)
    private String testo;
    private int voto;
    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_utente")
    private Utente utente;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_canzone")
    private Prodotto prodotto;

}