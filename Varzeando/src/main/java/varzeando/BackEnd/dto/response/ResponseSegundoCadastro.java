package varzeando.BackEnd.dto.response;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Builder
@Getter
public class ResponseSegundoCadastro {

    private Long id;

    private String name;

    private final String token;

    private String url;

    private String posicao;

    public ResponseSegundoCadastro(Long id, String name, String token,String posicao,String url) {
        this.id = id;
        this.name = name;
        this.url =url;
        this.posicao = posicao;
        this.token = token;
    }
}