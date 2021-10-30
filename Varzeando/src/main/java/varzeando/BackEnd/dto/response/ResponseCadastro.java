package varzeando.BackEnd.dto.response;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Builder
@Getter
public class ResponseCadastro {

    private Long id;

    private String name;

    private final String token;

    public ResponseCadastro(Long id, String name, String token) {
        this.id = id;
        this.name = name;
        this.token = token;
    }
}