package varzeando.BackEnd.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestQuadras {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 300)
    private String descricao;

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;

    private String endereco;

    private String url;

    private Float media;

    private Integer quantidade;
}