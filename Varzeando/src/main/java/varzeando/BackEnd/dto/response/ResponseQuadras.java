package varzeando.BackEnd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import varzeando.BackEnd.models.Partida;
import varzeando.BackEnd.models.Quadra;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ResponseQuadras {

    private Quadra quadra;

    private ResponseNotaQuadra responseNotaQuadra;

}
