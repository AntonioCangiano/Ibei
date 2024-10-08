package org.elis.ibei.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CambiaPasswordRequestDTO {

    @Min(value = 1,message = "non esistono id negativi")
    private long id;
    @Pattern(regexp =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message="la password deve avere almeno 8 caratteri, una maiuscola, "
                    + "una minuscola, un numero e un carattere speciale tra \"@#$%^&+=\"")
    private String vecchiaPassword;
    @Pattern(regexp =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message="la password deve avere almeno 8 caratteri, una maiuscola, "
                    + "una minuscola, un numero e un carattere speciale tra \"@#$%^&+=\"")
    private String nuovaPassword;
    private String nuovaPasswordRipetuta;
}