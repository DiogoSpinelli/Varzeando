package varzeando.BackEnd.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestAvaliarUsuario {

    private Long idAvaliado;

    private Integer avaliacao;

    private Long idUsuario;
}
