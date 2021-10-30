package varzeando.BackEnd.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import varzeando.BackEnd.dto.request.RequestNotificacao;
import varzeando.BackEnd.models.Notificacao;
import varzeando.BackEnd.services.NotificacoesService;

import java.util.List;

@RestController
@RequestMapping("notificacoes")
@Log4j2
@RequiredArgsConstructor
public class NotificacoesController {
    private final NotificacoesService notificacoesService;

    @PostMapping(path = "/criarNotificacao")
    public ResponseEntity<Notificacao> criar(@RequestBody RequestNotificacao requestNotificacao){
        return new ResponseEntity<Notificacao>(notificacoesService.criar(requestNotificacao), HttpStatus.CREATED);
    }

    @GetMapping(path = "notificacoesRecebidas/{id}")
    public ResponseEntity<List<Notificacao>> notificacoesRecebidas(@PathVariable Long id){
        return new ResponseEntity<List<Notificacao>>(notificacoesService.notificacoesRecebidas(id), HttpStatus.OK);
    }

    @GetMapping(path = "notificacoesEnviadas/{id}")
    public ResponseEntity<List<Notificacao>> notificacoesEnviadas(@PathVariable Long id){
        return new ResponseEntity<List<Notificacao>>(notificacoesService.notificacoesEnviadas(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/remover/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        notificacoesService.deletarNotificacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
