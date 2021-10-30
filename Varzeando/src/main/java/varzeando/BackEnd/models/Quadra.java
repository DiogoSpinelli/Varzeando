package varzeando.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "quadras")
@Builder
@Getter
@Setter
@Data
public class Quadra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties(value={"quadra"})
    @OneToMany(mappedBy = "quadra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partida> partidas;

    @JsonIgnoreProperties(value={"quadra", "id"})
    @OneToMany(mappedBy = "quadra", cascade = CascadeType.ALL)
    private List<AvaliarQuadra> avaliacoes;

    @Size(max = 50)
    private String name;

    @Size(max = 500)
//    @Min(value = 0)
//    @Max(value = 500)
    private String descricao;

    private Double latitude;

    private Double longitude;

    @Size(max = 80)
    private String endereco;

    private String url;

    private Float media;

    private Integer quantidade;
}