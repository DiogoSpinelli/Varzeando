package varzeando.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "AvaliarUsuario")
@Builder
@Getter
@Setter
@Data
public class AvaliarUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer avaliacao;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="avaliado_id")
    private Usuario avaliado;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
}
