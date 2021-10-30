package varzeando.BackEnd.dto.request;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestLogin {

    @Size(max = 50)
    private String email;


    private String password;
}
