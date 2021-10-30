package varzeando.BackEnd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import varzeando.BackEnd.dto.request.RequestCadastro;
import varzeando.BackEnd.dto.request.RequestLogin;
import varzeando.BackEnd.dto.request.RequestModificarUsuario;
import varzeando.BackEnd.dto.request.RequestSegundoCadastro;
import varzeando.BackEnd.dto.response.ResponseCadastro;
import varzeando.BackEnd.dto.response.ResponseLogin;
import varzeando.BackEnd.dto.response.ResponseSegundoCadastro;
import varzeando.BackEnd.exception.BadRequestException;
import varzeando.BackEnd.exception.JWTException;
import varzeando.BackEnd.filters.JwtTokenProvider;
import varzeando.BackEnd.models.AvaliarUsuario;
import varzeando.BackEnd.models.Role;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.repository.AvaliarUsuarioRepository;
import varzeando.BackEnd.repository.UsuariosRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    private final PasswordEncoder passwordEncoder;
    private final UsuariosRepository usuariosRepository;
    private final AvaliarUsuarioRepository avaliarUsuarioRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseLogin login(RequestLogin requestLogin) {
        try {
            Usuario user=usuariosRepository.findByEmail(requestLogin.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("usuario não encontrado")));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));
            List<Role> roles= user.getRoles();
            String token= jwtTokenProvider.createToken(requestLogin.getEmail(),roles);
            return new ResponseLogin(user.getId(), token, user.getName(), user.getUrl(), user.getPosicao());
        } catch (JWTException e) {
            throw new JWTException("Usuario ou senha invalido", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public List<Usuario> listAll(){
        return usuariosRepository.findAll();
    }

    public ResponseCadastro cadastro(RequestCadastro requestCadastro) throws ParseException {
        verificaridade(requestCadastro.getDataNascimento());
        Usuario user=Usuario.builder()
                .name(requestCadastro.getName())
                .email(requestCadastro.getEmail())
                .password(requestCadastro.getPassword())
                .dataNascimento(requestCadastro.getDataNascimento())
                .latitude((double) 0)
                .longitude((double) 0)
                .url(requestCadastro.getPosicao())
                .posicao(requestCadastro.getPosicao())
                .roles(new ArrayList<>())
                .build();
        if (!usuariosRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usuariosRepository.save(user);
            String token=jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
            return new ResponseCadastro(user.getId(),user.getName(), token);
        } else {
            throw new JWTException("Email já registrado", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    public ResponseSegundoCadastro salvardois(RequestSegundoCadastro requestSegundoCadastro){
        Usuario user=usuariosRepository.findByEmail(requestSegundoCadastro.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ("valores ivalidos")));
//        user.setLatitude(requestSegundoCadastro.getLatitude());
//        user.setLongitude(requestSegundoCadastro.getLongitude());
//        user.setPosicao(requestSegundoCadastro.getPosicao());
        usuariosRepository.deleteById(user.getId());
        Usuario userFim= usuariosRepository.save(Usuario.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .dataNascimento(user.getDataNascimento())
                .latitude(requestSegundoCadastro.getLatitude())
                .longitude(requestSegundoCadastro.getLongitude())
                .url(requestSegundoCadastro.getUrl())
                .posicao(requestSegundoCadastro.getPosicao())
                .build());
        String token=jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        return new ResponseSegundoCadastro(userFim.getId(),userFim.getName(),token,userFim.getPosicao(),userFim.getUrl());
    }

    public Integer verificaridade(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String x= sdf.format(data);
        Date y=sdf.parse(x);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(y);
        int Anodata =calendar.get(calendar.YEAR)-1;
        int Mesdata =calendar.get(calendar.MONTH)+1;
        int Diadata =calendar.get(calendar.DATE);
      LocalDate localDate=LocalDate.of(Anodata,Mesdata,Diadata);
      LocalDate dataAtual=LocalDate.now();
        Period period=Period.between(localDate,dataAtual);
        if(period.getYears()>18){
            return period.getYears();
        }
        else
            throw new RuntimeException();
    }

    public String findUsuario(Long id){
        String primeiroNome=usuariosRepository.findById(id).orElseThrow(()->new BadRequestException("Usuario não encontrado")).getName();
        return primeiroNome.substring(0,primeiroNome.indexOf(" "));
    }

    public void deletarUsuario(Long id){
        usuariosRepository.deleteById(id);
    }

    public Usuario alterarUsuario(RequestModificarUsuario requestModificarUsuario){
        Usuario user=usuariosRepository.findById(requestModificarUsuario.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("usuario não encontrado")));
        String nome=null;String Password=null; String email=null; String posicao=null; Date dataNascimento=null;Double lat=0.0; Double log=0.0;
        if(!(requestModificarUsuario.getName()==null))
            nome=requestModificarUsuario.getName();
        else nome=user.getName();
        if(!(requestModificarUsuario.getDataNascimento()==null))
            dataNascimento=requestModificarUsuario.getDataNascimento();
        else dataNascimento=user.getDataNascimento();
        if(!(requestModificarUsuario.getLatitude()==0.0))
            lat=requestModificarUsuario.getLatitude();
        else lat=user.getLatitude();
        if(!(requestModificarUsuario.getLongitude()==0.0))
            log=requestModificarUsuario.getLongitude();
        else log=user.getLongitude();
        if(!(requestModificarUsuario.getPassword()==null))
            Password=requestModificarUsuario.getPassword();
        else Password=user.getPassword();
        if(!(requestModificarUsuario.getEmail()==null))
            email=requestModificarUsuario.getEmail();
        else email=user.getEmail();

        usuariosRepository.save(Usuario.builder().id(user.getId())
                .latitude(lat)
                .longitude(log)
                .dataNascimento(dataNascimento)
                .name(nome)
                .email(email)
                .password(Password)
                .build());

        return user;
    }

    public boolean avaliarUsuario(Long idUsuario, Integer avaliacao, Long idAvaliado){
        if(avaliarUsuarioRepository.buscarAvaliadoeUsuario(idUsuario, idAvaliado) != null){
            return false;
        }
        else{
            Usuario user=usuariosRepository.findById(idUsuario)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("Usuario não encontrado")));
            Usuario avaliado=usuariosRepository.findById(idAvaliado)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("Usuario não encontrado")));

            avaliarUsuarioRepository.save(AvaliarUsuario.builder()
                    .usuario(user)
                    .avaliado(avaliado)
                    .avaliacao(avaliacao)
                    .build());

            switch (avaliacao){
                case 1:
                    if(user.getResenha()==null){
                        usuariosRepository.save(Usuario.builder().id(user.getId()).latitude(user.getLatitude()).longitude(user.getLongitude()).dataNascimento(user.getDataNascimento()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).resenha(1).craque(user.getCraque()).capita(user.getCapita()).build());
                    }
                    else{
                        Integer resenha = user.getResenha()+1;
                        usuariosRepository.save(Usuario.builder().id(user.getId()).latitude(user.getLatitude()).longitude(user.getLongitude()).dataNascimento(user.getDataNascimento()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).resenha(resenha).craque(user.getCraque()).capita(user.getCapita()).build());
                    }
                    break;
                case 2:
                    if(user.getCraque()==null){
                        usuariosRepository.save(Usuario.builder().id(user.getId()).latitude(user.getLatitude()).longitude(user.getLongitude()).dataNascimento(user.getDataNascimento()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).resenha(user.getResenha()).craque(1).capita(user.getCapita()).build());
                    }
                    else{
                        Integer craque = user.getCraque()+1;
                        usuariosRepository.save(Usuario.builder().id(user.getId()).latitude(user.getLatitude()).longitude(user.getLongitude()).dataNascimento(user.getDataNascimento()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).resenha(user.getResenha()).craque(craque).capita(user.getCapita()).build());
                    }
                    break;
                case 3:
                    if(user.getCraque()==null){
                        usuariosRepository.save(Usuario.builder().id(user.getId()).latitude(user.getLatitude()).longitude(user.getLongitude()).dataNascimento(user.getDataNascimento()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).resenha(user.getResenha()).craque(user.getCraque()).capita(1).build());
                    }
                    else{
                        Integer capita = user.getCapita()+1;
                        usuariosRepository.save(Usuario.builder().id(user.getId()).latitude(user.getLatitude()).longitude(user.getLongitude()).dataNascimento(user.getDataNascimento()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).resenha(user.getResenha()).craque(user.getCraque()).capita(capita).build());
                    }
                    break;
            }

            return true;
        }
    }

    public List<Usuario> listAllMesmoNome(String nome){
        return usuariosRepository.todosMesmoNome(nome);
    }

}