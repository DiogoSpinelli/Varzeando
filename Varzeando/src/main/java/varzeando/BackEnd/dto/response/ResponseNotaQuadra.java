package varzeando.BackEnd.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseNotaQuadra {
    private Float media;

    private int quantidade;

    public ResponseNotaQuadra(Float media, int quantidade) {
        this.media = media;
        this.quantidade = quantidade;
    }
}
