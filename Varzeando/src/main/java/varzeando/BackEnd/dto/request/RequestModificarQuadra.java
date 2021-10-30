package varzeando.BackEnd.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestModificarQuadra {
    private Long id;

    @Size(max = 50)
    private String name;

    @Size(max = 500)
    private String descricao;

    private Double latitude;

    private Double longitude;

    @Size(max = 80)
    private String endereco;

    private String url;
}
