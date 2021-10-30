package varzeando.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "jogadores")
@Builder
@Getter
@Setter
@Data
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean moderador;

    private String posicao;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="partida_id")
    private Partida partida;
}
