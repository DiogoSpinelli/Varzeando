package varzeando.BackEnd.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestModificarPartida {
    private Long id;

    @Size(max = 50)
    private String name;

    private Long idQuadra;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataFim;

    @Size(max = 300)
    private String descricao;

    private int numMax;

    private int numPessoas;

    private String url;
}
