package varzeando.BackEnd.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import varzeando.BackEnd.dto.request.*;
import varzeando.BackEnd.dto.response.*;
import varzeando.BackEnd.models.Jogador;
import varzeando.BackEnd.models.Partida;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.repository.QuadrasRepository;
import varzeando.BackEnd.repository.UsuariosRepository;
import varzeando.BackEnd.services.PartidasService;
import varzeando.BackEnd.services.QuadrasService;
//import varzeando.BackEnd.services.UsuariosDetailsService;
import varzeando.BackEnd.services.UsuariosService;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@Log4j2
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuariosService usuariosService;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private UsuariosDetailsService userDetailsService;


    private final UsuariosRepository usuarioRepository;
    private final PartidasService partidasService;
    private final QuadrasService quadrasService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) throws Exception {

        return ResponseEntity.ok(usuariosService.login(requestLogin));
    }

    @PostMapping(path = "/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody RequestCadastro requestCadastro) throws Exception {

        return new ResponseEntity<>( usuariosService.cadastro(requestCadastro), HttpStatus.CREATED);
    }
 
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        return ResponseEntity.ok(usuariosService.listAll());
    }

    @PutMapping(path = "/segundocadastro")
    public ResponseEntity<ResponseSegundoCadastro> segundocadastro(@RequestBody RequestSegundoCadastro requestSegundoCadastro){
        return new ResponseEntity<>(usuariosService.salvardois(requestSegundoCadastro), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{Id}")
    public ResponseEntity<ResponseNome> getNome(@PathVariable ("Id") Long Id){
        ResponseNome responseNome = new ResponseNome(usuariosService.findUsuario(Id));
        return ResponseEntity.ok(responseNome);
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<Void>deletar(@PathVariable("id") Long id){
        usuariosService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path="/partidasUsuario/{id}")
    public ResponseEntity<List<Partida>> partidasUsuario(@PathVariable Long id){
        return new ResponseEntity<List<Partida>>(partidasService.partidasUsuario(id), HttpStatus.OK);
    }

    @GetMapping(path="/partidasRealizadas/{id}")
    public ResponseEntity<List<Partida>> partidasRealizadas(@PathVariable Long id){
        return new ResponseEntity<List<Partida>>(partidasService.partidasRealizadas(id), HttpStatus.OK);
    }

    @PostMapping(path = "/avaliarUsuario")
    public ResponseEntity<ResponseAvaliacao> avaliarUsuario(@RequestBody RequestAvaliarUsuario requestAvaliarUsuario){
        ResponseAvaliacao responseAvaliacao = new ResponseAvaliacao(usuariosService.avaliarUsuario(requestAvaliarUsuario.getIdUsuario(), requestAvaliarUsuario.getAvaliacao(), requestAvaliarUsuario.getIdAvaliado()));
        return ResponseEntity.ok(responseAvaliacao);
//        return new ResponseEntity<Boolean>(usuariosService.avaliarUsuario(requestAvaliarUsuario.getIdUsuario(), requestAvaliarUsuario.getAvaliacao(), requestAvaliarUsuario.getIdAvaliado()), HttpStatus.CREATED);
    }

    @PutMapping(path = "/alterar")
    public ResponseEntity<Usuario>alterar(@RequestBody RequestModificarUsuario requestModificarUsuario){
        return new ResponseEntity<Usuario>(usuariosService.alterarUsuario(requestModificarUsuario), HttpStatus.OK);
    }

    @PutMapping(path = "/avaliarUsuario")
    public Boolean avaliarQuadra(@RequestBody RequestAvaliarUsuario requestAvaliarUsuario){
        return usuariosService.avaliarUsuario(requestAvaliarUsuario.getIdUsuario(), requestAvaliarUsuario.getAvaliacao(), requestAvaliarUsuario.getIdAvaliado());
    }

    @GetMapping(path="/usuariosMesmoNome/{nome}")
    public ResponseEntity<List<Usuario>> listarMesmoNome(@PathVariable String nome){
        return ResponseEntity.ok(usuariosService.listAllMesmoNome(nome));
    }
}