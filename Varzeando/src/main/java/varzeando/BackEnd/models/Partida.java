package varzeando.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "partidas")
@Builder
@Getter
@Setter
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @JsonIgnoreProperties("partidas")
    @ManyToOne
    @JoinColumn(name = "quadra_id")
    private Quadra quadra;

    @JsonIgnoreProperties(value={"partida"})
    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<Jogador> jogadores;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataInicio;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataFim;

    @Size(max = 300)
    private String descricao;

    @NotNull
    private int numMax;

    @NotNull
    private int numPessoas;

    @NotBlank
    private String url;
}
