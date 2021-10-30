package varzeando.BackEnd.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import varzeando.BackEnd.models.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestSegundoCadastro {

    @Email
    @Size(max = 50)
    private String email;

    @NotEmpty
    private Double latitude;

    @NotEmpty
    private Double longitude;

    private String posicao;

    private  String url;
}
