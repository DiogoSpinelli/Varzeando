package varzeando.BackEnd.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestModerador {

    private Long idPartida;

    private Long idUsuario;
}
