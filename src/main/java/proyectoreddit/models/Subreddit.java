package proyectoreddit.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subreddit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre de la comunidad es requerido")
    private String name;

    @NotBlank(message = "La descripcion es requerido")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
