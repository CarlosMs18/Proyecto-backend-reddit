package proyectoreddit.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username es requerido")
    private String username;

    @NotBlank(message = "Passowrd es requerido")
    private String password;

    @Email
    @NotEmpty(message = "Email es requerido")
    private String email;

    private Instant created;

    private boolean enabled;


}
