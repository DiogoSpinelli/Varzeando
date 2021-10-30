package varzeando.BackEnd.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import varzeando.BackEnd.dto.request.*;
import varzeando.BackEnd.dto.response.ResponseAvaliacao;
import varzeando.BackEnd.models.Jogador;
import varzeando.BackEnd.models.Partida;
import varzeando.BackEnd.services.PartidasService;

import java.util.List;

@RestController
@RequestMapping("partidas")
@Log4j2
@RequiredArgsConstructor
public class PartidasController {
    private final PartidasService partidasService;

    @GetMapping(path = "/partidas")
    public ResponseEntity<List<Partida>> listPartidas(){
        return ResponseEntity.ok(partidasService.listAllPartidas());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Partida> partida(@PathVariable("id") Long id){
        return ResponseEntity.ok(partidasService.partida(id));
    }

    @GetMapping(path = "/jogadores")
    public ResponseEntity<List<Jogador>> listJogadores(){
        return ResponseEntity.ok(partidasService.listAllJogadores());
    }

    @PostMapping(path = "/criarPartida")
    public ResponseEntity<Partida> criar(@RequestBody RequestPartida requestPartidas){
        return new ResponseEntity<Partida>(partidasService.salvar(requestPartidas), HttpStatus.CREATED);
    }

    @PostMapping(path = "/adicionarJogador")
    public ResponseEntity<ResponseAvaliacao> adicionar(@RequestBody RequestJogador requestJogador){
        ResponseAvaliacao responseAvaliacao = new ResponseAvaliacao(partidasService.adicionar(requestJogador));
        return ResponseEntity.ok(responseAvaliacao);
    }

    @DeleteMapping(path = "/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id){
        ResponseAvaliacao responseAvaliacao = new ResponseAvaliacao(partidasService.removerJogador(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        ResponseAvaliacao responseAvaliacao = new ResponseAvaliacao(partidasService.deletarPartida(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/partidasSemana")
    public ResponseEntity<List<Partida>> partidasSemana(){
        return new ResponseEntity<List<Partida>>(partidasService.partidasSemana(), HttpStatus.OK);
    }

    @GetMapping(path = "/partidasProximas/{latitude}/{longitude}")
    public ResponseEntity<List<Partida>> partidasProximas(@PathVariable Double latitude,@PathVariable Double longitude){
        return new ResponseEntity<List<Partida>>(partidasService.partidasProximas(latitude,longitude), HttpStatus.OK);
    }

    @GetMapping(path = "/jogadoresPartida/{id}")
    public ResponseEntity<List<Jogador>> jogadoresPartida(@PathVariable Long id) {
        return new ResponseEntity<List<Jogador>>(partidasService.jogadoresPartida(id), HttpStatus.OK);
    }

    @GetMapping(path="/partidasQuadra/{id}")
    public ResponseEntity<List<Partida>> partidasQuadra(@PathVariable Long id){
        return new ResponseEntity<List<Partida>>(partidasService.partidasQuadra(id), HttpStatus.OK);
    }

    @PutMapping(path = "/alterar")
    public ResponseEntity<Partida> alterar(@RequestBody RequestModificarPartida requestModificarPartida){
        return new ResponseEntity<Partida>(partidasService.alterarPartida(requestModificarPartida), HttpStatus.OK);
    }

    @PutMapping(path = "/tornarModerador")
    public ResponseEntity<Jogador> tornarModerador(@RequestBody RequestModerador requestModerador){
        return new ResponseEntity<Jogador>(partidasService.tornarModerador(requestModerador), HttpStatus.OK);
    }

    @GetMapping(path = "/moderador")
    public Boolean moderador(@RequestBody RequestModerador requestModerador){
        return partidasService.moderador(requestModerador);
    }
}
