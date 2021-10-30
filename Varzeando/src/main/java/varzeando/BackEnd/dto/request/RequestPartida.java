package varzeando.BackEnd.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import varzeando.BackEnd.models.Quadra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestPartida {

    private Long idQuadra;

    @NotBlank
    @Size(max = 50)
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataFim;

    @Size(max = 300)
    private String descricao;

    private int numMax;

    @NotBlank
    private String url;
}