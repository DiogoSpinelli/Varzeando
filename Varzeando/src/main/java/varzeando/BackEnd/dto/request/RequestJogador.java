package varzeando.BackEnd.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestJogador {

    private Boolean moderador;

    private Long idUsuario;

    private Long idPartida;

    private String posicao;

    public Boolean isModerador() {
        return moderador;
    }
}
