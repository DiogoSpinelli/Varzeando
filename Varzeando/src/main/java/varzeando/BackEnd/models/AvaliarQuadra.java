package varzeando.BackEnd.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "AvaliarQuadra")
@Builder
@Getter
@Setter
@Data
public class AvaliarQuadra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float nota;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="quadra_id")
    private Quadra quadra;
}

