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
public class RequestQuadrasProximas {

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;
}
