package varzeando.BackEnd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import varzeando.BackEnd.dto.request.RequestNotificacao;
import varzeando.BackEnd.models.Notificacao;
import varzeando.BackEnd.models.Partida;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.repository.NotificacoesRepository;
import varzeando.BackEnd.repository.PartidasRepository;
import varzeando.BackEnd.repository.UsuariosRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacoesService {
    private final NotificacoesRepository notificacoesRepository;
    private final UsuariosRepository usuariosRepository;
    private final PartidasRepository partidasRepository;

    public Notificacao criar(RequestNotificacao requestNotificacao){
        Usuario remetente = usuariosRepository.findById(requestNotificacao.getIdRemetente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("remetente não encontrado")));
        Usuario destinario = usuariosRepository.findById(requestNotificacao.getIdDestinatario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("destinatario não encontrado")));
        Partida partida = partidasRepository.findById(requestNotificacao.getIdPartida())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("partida não encontrado")));
        LocalDateTime agora = LocalDateTime.now();

        return notificacoesRepository.save(Notificacao.builder()
                .data(agora)
                .remetente(remetente)
                .destinatario(destinario)
                .partida(partida)
                .build());
    }

    public List<Notificacao> notificacoesRecebidas(Long id){
        return notificacoesRepository.notificacoesRecebidas(id);
    }

    public List<Notificacao> notificacoesEnviadas(Long id){
        return notificacoesRepository.notificacoesEnviadas(id);
    }

    public void deletarNotificacao(Long id){
        notificacoesRepository.deleteById(id);
    }
}
