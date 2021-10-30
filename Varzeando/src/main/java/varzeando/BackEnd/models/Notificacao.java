package varzeando.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "notificacoes")
@Builder
@Getter
@Setter
@Data
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="remetente_id")
    private Usuario remetente;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="destinatario_id")
    private Usuario destinatario;

    @JsonIgnoreProperties(value={"jogadores"})
    @ManyToOne
    @JoinColumn(name="partida_id")
    private Partida partida;
}
