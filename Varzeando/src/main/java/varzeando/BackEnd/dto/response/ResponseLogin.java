package varzeando.BackEnd.dto.response;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Getter
public class ResponseLogin {

    private Long id;

    private final String token;

    private String name;

    private String url;

    private String posicao;

    public ResponseLogin(Long id, String token, String name, String url, String posicao) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.url = url;
        this.posicao = posicao;
    }
}