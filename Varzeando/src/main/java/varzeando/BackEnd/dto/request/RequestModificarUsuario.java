package varzeando.BackEnd.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestModificarUsuario {

    private Long id;


    @Size(max = 50)
    private String name;


    @Email
    @Size(max = 50)
    private String email;

    private String password;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;


    private Double latitude;


    private Double longitude;


    private String posicao;
}
