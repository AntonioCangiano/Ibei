package org.elis.ibei.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistrazioneRequestDTO {

	@NotBlank(message = "tutte le persone hanno un nome")
	private String nome;
	@NotBlank(message = "tutte le persone hanno un cognome")
	private String cognome;
	@NotNull(message = "devi inserire una mail")
	@Email(message = "deve essere una email")
	private String email;
	@Pattern(regexp =
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
			message="la password deve avere almeno 8 caratteri, una maiuscola, "
					+ "una minuscola, un numero e un carattere speciale tra \"@#$%^&+=\"")
	private String password;
	private String passwordRipetuta;
	@Past(message = "non puoi essere nato nel futuro")
	private LocalDate dataDiNascita;

}