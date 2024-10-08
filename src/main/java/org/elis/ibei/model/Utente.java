package org.elis.ibei.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class Utente implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cognome;
	@Column(unique = true,nullable = false)
	private String email;
	private String password;
	private LocalDate dataNascita;
	private boolean disattivato;
	private Ruolo ruolo;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.toString()));
	}

	@Override
	public String getUsername() {
		return email;
	}
}
