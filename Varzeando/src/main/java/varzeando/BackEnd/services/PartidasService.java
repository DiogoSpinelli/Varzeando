package varzeando.BackEnd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import varzeando.BackEnd.dto.request.RequestJogador;
import varzeando.BackEnd.dto.request.RequestModificarPartida;
import varzeando.BackEnd.dto.request.RequestModerador;
import varzeando.BackEnd.exception.BadRequestException;
import varzeando.BackEnd.models.Jogador;
import varzeando.BackEnd.models.Partida;
import varzeando.BackEnd.dto.request.RequestPartida;
import varzeando.BackEnd.models.Quadra;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.repository.JogadoresRepository;
import varzeando.BackEnd.repository.PartidasRepository;
import varzeando.BackEnd.repository.QuadrasRepository;
import varzeando.BackEnd.repository.UsuariosRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidasService {
    private final PartidasRepository partidasRepository;
    private final QuadrasRepository quadrasRepository;
    private final UsuariosRepository usuariosRepository;
    private final JogadoresRepository jogadoresRepository;

    public Partida salvar(RequestPartida requestPartidas){
        verificarPalavras(requestPartidas.getDescricao());
        verificarPalavras(requestPartidas.getName());

        List<Partida> partidas = partidasRepository.verificarHorario(requestPartidas.getDataInicio(), requestPartidas.getDataFim());

        if(partidas.size()==0){
            Quadra quadra = quadrasRepository.findById(requestPartidas.getIdQuadra())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("quadra não encontrado")));

            return partidasRepository.save(Partida.builder()
                    .name(requestPartidas.getName())
                    .quadra(quadra)
                    .dataInicio(requestPartidas.getDataInicio())
                    .dataFim(requestPartidas.getDataFim())
                    .descricao(requestPartidas.getDescricao())
                    .numMax(requestPartidas.getNumMax())
                    .numPessoas(0)
                    .url(requestPartidas.getUrl())
                    .build());
        }
        else{
            throw new BadRequestException("Esta quadra esta ocupada neste horario");
        }

    }

    public Boolean adicionar(RequestJogador requestJogador){
        Partida partida = partidasRepository.findById(requestJogador.getIdPartida())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("partida não encontrado")));
        Usuario usuario = usuariosRepository.findById(requestJogador.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("usuario não encontrado")));

        if(partida.getJogadores().size()<partida.getNumMax()){
            jogadoresRepository.save(Jogador.builder()
                    .moderador(requestJogador.getModerador())
                    .usuario(usuario)
                    .partida(partida)
                    .posicao(requestJogador.getPosicao())
                    .build());
            }
            else{
                throw new BadRequestException("A partida está cheia");
            }

        return true;
    }

    public Jogador tornarModerador(RequestModerador requestModerador){
        Long idUsuario = requestModerador.getIdUsuario();
        Long idPartida = requestModerador.getIdPartida();
        Jogador jogador = jogadoresRepository.buscarJogador(idUsuario, idPartida);

        return jogadoresRepository.save(Jogador.builder()
                .id(jogador.getId())
                .moderador(true)
                .usuario(jogador.getUsuario())
                .partida(jogador.getPartida())
                .build());
    }

    public Boolean moderador(RequestModerador requestModerador){

        Long idUsuario = requestModerador.getIdUsuario();
        Long idPartida = requestModerador.getIdPartida();
        Jogador jogador = jogadoresRepository.buscarJogador(idUsuario, idPartida);

//        List<Jogador> jogadores=new ArrayList<>();

        return jogador.getModerador();
    }





    public Partida alterarPartida(RequestModificarPartida requestModificarPartida){

        if(partidasRepository.verificarHorario(requestModificarPartida.getDataInicio(), requestModificarPartida.getDataFim())==null){

            Partida partida=partidasRepository.findById(requestModificarPartida.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("partida não encontrado")));
            String name=null; Quadra quadra=null; Date dataInicio=null; Date dataFim=null; String descricao=null; int numPessoas=0; String url=null; int numMax=0;

            if(!(requestModificarPartida.getName()==null))
                name= requestModificarPartida.getName();
            else name =partida.getName();

            if(!(requestModificarPartida.getIdQuadra()==0)){
                Quadra local = quadrasRepository.findById(requestModificarPartida.getIdQuadra())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("quadra não encontrado")));
                quadra = local;
            }
            else{
                quadra=partida.getQuadra();
            }

            if(!(requestModificarPartida.getDataInicio()==null))
                dataInicio = requestModificarPartida.getDataInicio();
            else dataInicio=partida.getDataInicio();
            if(!(requestModificarPartida.getDataFim()==null))
                dataFim = requestModificarPartida.getDataFim();
            else dataFim=partida.getDataFim();
            if(!(requestModificarPartida.getDescricao()==null))
                descricao = requestModificarPartida.getDescricao();
            else descricao=partida.getDescricao();
            if(!(requestModificarPartida.getNumPessoas()==0))
                numPessoas = requestModificarPartida.getNumPessoas();
            else numPessoas=partida.getNumPessoas();
            if(!(requestModificarPartida.getUrl()==null))
                url = requestModificarPartida.getUrl();
            else url=partida.getUrl();
            if(!(requestModificarPartida.getNumMax()==0))
                numMax = requestModificarPartida.getNumMax();
            else numMax = partida.getNumMax();

            partidasRepository.save(Partida.builder()
                    .id(partida.getId())
                    .name(name)
                    .quadra(quadra)
                    .dataInicio(dataInicio)
                    .dataFim((dataFim))
                    .descricao(descricao)
                    .numPessoas(numPessoas)
                    .numMax(numMax)
                    .url(url)
                    .build());

            return partida;
        }
        else{
            throw new BadRequestException("Esta quadra esta ocupada neste horario");
        }

    }

    public List<Partida> partidasUsuario(Long id){
        return partidasRepository.partidasUsuario(id);
    }

    public List<Partida> partidasRealizadas(Long idUsuario){
        LocalDateTime agora = LocalDateTime.now();
        return partidasRepository.partidasRealizadas(idUsuario, agora);
    }

    public List<Jogador> jogadoresPartida(Long id){
        Partida partida = partidasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("partida não encontrado")));

        return partida.getJogadores();
    }

    public List<Partida> partidasQuadra(Long id){
        Quadra quadra = quadrasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("partida não encontrado")));
        return quadra.getPartidas();
    }

    public boolean removerJogador(Long id){
        jogadoresRepository.deleteById(id);
        return true;
    }

    public Boolean deletarPartida(Long id){
        partidasRepository.deleteById(id);
        return true;
    }

    public List<Partida> partidasProximas(Double latitude,Double longitude){
        return partidasRepository.partidasProximas(latitude,longitude);
    }

    public List<Partida> listAllPartidas(){
        return partidasRepository.findAll();
    }

    public Partida partida(Long id){return partidasRepository.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("quadra não encontrado")));
    }

    public List<Jogador> listAllJogadores(){
        return jogadoresRepository.findAll();
    }

    public List<Partida> partidasSemana(){
        LocalDate hoje = LocalDate.now();
        LocalDate depois = hoje.plusDays(7);

        return partidasRepository.partidasSemana(hoje, depois);
    }

    public void verificarPalavras(String frase){
        String[] palavrasRuins={"piroca","buceta","poha","porra","caralho","krl","fdp","filha da puta","vadia","arrombado","cuzao","cuzão","cu","foda","merda","pirocao","pinto","rola","bucetão"};
        String x=frase.toLowerCase().replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a")
                .replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");
        for (int i=0;i<palavrasRuins.length;i++){
            if (x.contains(palavrasRuins[i]))
                throw new RuntimeException();
        }
    }
}
