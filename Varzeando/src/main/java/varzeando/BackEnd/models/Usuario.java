//package varzeando.BackEnd.models;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonIgnoreType;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import javax.persistence.*;
//import javax.validation.constraints.*;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//@Data
//
//@NoArgsConstructor
//@Entity
//@AllArgsConstructor
//@Table(name = "usuarios")
//@Builder
//public class Usuario {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonIgnoreProperties(value={"usuario"})
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
//    private List<Jogador> jogadores;
//
//    @NotBlank
//    @Size(max = 50)
//    private String name;
//
//    @Size(max = 50)
//    private String email;
//
//    @NotBlank
//    @Size(max = 50)
//    private String password;
//
//    @NotNull
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private Date dataNascimento;
//
//    private Double latitude;
//
//    private Double longitude;
//
//    private String posicao;
//
//    private Integer resenha;
//
//    private Integer craque;
//
//    private Integer capita;
//
//    public Usuario(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }
//
//}
package varzeando.BackEnd.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "usuarios")
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties(value={"usuario"})
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Jogador> jogadores;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String email;

    @NotBlank
    private String password;

    private String posicao;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private Double latitude;

    private Double longitude;

    private Integer resenha;

    private Integer craque;

    private Integer capita;

    private String url;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;


}

