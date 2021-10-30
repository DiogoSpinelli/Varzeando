package varzeando.BackEnd.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import varzeando.BackEnd.dto.request.*;
import varzeando.BackEnd.dto.response.ResponseAvaliacao;
import varzeando.BackEnd.dto.response.ResponseNotaQuadra;
import varzeando.BackEnd.dto.response.ResponseQuadras;
import varzeando.BackEnd.dto.response.ResponseNotaQuadra;
import varzeando.BackEnd.models.AvaliarQuadra;
import varzeando.BackEnd.models.Partida;
import varzeando.BackEnd.models.Quadra;
import varzeando.BackEnd.services.QuadrasService;

import java.util.List;

@RestController
@RequestMapping("quadras")
@Log4j2
@RequiredArgsConstructor
public class QuadrasController {
    private final QuadrasService quadrasService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(path = "/quadras")
    public ResponseEntity<List<Quadra>> list(){
        return ResponseEntity.ok(quadrasService.listAll());
    }


    @PostMapping(path = "/cadastro")
    public ResponseEntity<Quadra> cadastro(@RequestBody RequestQuadras requestQuadras) {
        return new ResponseEntity<>(quadrasService.salvar(requestQuadras), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<Void>deletar(@PathVariable("id") Long id){
        quadrasService.deletarQuadra(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/achar/{id}")
    public ResponseEntity<ResponseQuadras> achar(@PathVariable("id") Long id){
        ResponseNotaQuadra responseNotaQuadra = new ResponseNotaQuadra(quadrasService.mediaDeNotas(id), quadrasService.Quantidade(id));
        ResponseQuadras responseQuadras = new ResponseQuadras(quadrasService.achar(id), responseNotaQuadra);
        return ResponseEntity.ok(responseQuadras);
    }

    @GetMapping(path = "/quadrasProximas/{latitude}/{longitude}")
    public ResponseEntity<List<Quadra>> acharProximas(@PathVariable Double latitude,@PathVariable Double longitude){
        return new ResponseEntity<List<Quadra>>(quadrasService.quadrasProximas(latitude,longitude), HttpStatus.OK);
    }

    @PutMapping(path = "/avaliarQuadra")
    public ResponseEntity<ResponseAvaliacao> avaliarQuadra(@RequestBody RequestAvaliarQuadra requestAvaliarQuadra){
        ResponseAvaliacao responseAvaliacao = new ResponseAvaliacao(quadrasService.avaliarQuadra(requestAvaliarQuadra.getIdUsuario(), requestAvaliarQuadra.getNota(), requestAvaliarQuadra.getIdQuadra()));
        return ResponseEntity.ok(responseAvaliacao);
//        return new ResponseEntity<Boolean>(quadrasService.avaliarQuadra(requestAvaliarQuadra.getIdUsuario(), requestAvaliarQuadra.getNota(), requestAvaliarQuadra.getIdQuadra()), HttpStatus.CREATED);
    }

    @GetMapping(path = "/notas/{id}")
    public ResponseEntity<ResponseNotaQuadra> notas(@PathVariable("id") Long id){
        ResponseNotaQuadra responseNotaQuadra = new ResponseNotaQuadra(quadrasService.mediaDeNotas(id), quadrasService.Quantidade(id));
        return ResponseEntity.ok(responseNotaQuadra);
    }

    public ResponseEntity<Quadra> alterarQuadra(@RequestBody RequestModificarQuadra requestModificarQuadra){
        return new ResponseEntity<Quadra>(quadrasService.alterarQuadra(requestModificarQuadra), HttpStatus.OK);
    }
}