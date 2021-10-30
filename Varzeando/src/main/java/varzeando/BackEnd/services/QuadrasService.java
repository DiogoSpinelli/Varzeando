package varzeando.BackEnd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import varzeando.BackEnd.dto.request.RequestModificarQuadra;
import varzeando.BackEnd.dto.request.RequestQuadras;
import varzeando.BackEnd.models.AvaliarQuadra;
import varzeando.BackEnd.models.Quadra;
import varzeando.BackEnd.models.Usuario;
import varzeando.BackEnd.repository.AvaliarQuadraRepository;
import varzeando.BackEnd.repository.QuadrasRepository;
import varzeando.BackEnd.repository.UsuariosRepository;

import java.text.DecimalFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuadrasService {
    private final UsuariosRepository usuariosRepository;
    private final QuadrasRepository quadrasRepository;
    private final AvaliarQuadraRepository avaliarQuadraRepository;

    public Quadra salvar(RequestQuadras requestQuadras){
        verificarPalavras(requestQuadras.getDescricao());
        verificarPalavras(requestQuadras.getName());

        return quadrasRepository.save(Quadra.builder()
                .descricao(requestQuadras.getDescricao())
                .latitude(requestQuadras.getLatitude())
                .longitude(requestQuadras.getLongitude())
                .name(requestQuadras.getName())
                .endereco(requestQuadras.getEndereco())
                .url(requestQuadras.getUrl())
                .media(null)
                .quantidade(0)
                .build());
    }

    public List<Quadra> listAll(){
        return quadrasRepository.findAll();
    }

    public void deletarQuadra(Long id){
        quadrasRepository.deleteById(id);
    }

    public Quadra achar(Long id){
        Quadra quadra = quadrasRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("quadra não encontrado")));
        return quadra;
    }
    public List<Quadra> quadrasProximas(Double latitude,Double longitude){
        return quadrasRepository.quadrasProximas(latitude,longitude);
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
    public boolean avaliarQuadra(Long idUsuario,Float nota, Long idQuadra){
        if(avaliarQuadraRepository.buscarQuadraeUsuario(idUsuario,idQuadra) != null){
            return false;
        }
        else{
            Usuario user=usuariosRepository.findById(idUsuario)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("Usuario não encontrado")));
        Quadra quadra = quadrasRepository.findById(idQuadra)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("quadra não encontrado")));
        avaliarQuadraRepository.save(AvaliarQuadra.builder().usuario(user).nota(nota).quadra(quadra).build());

            if(Quantidade(quadra.getId())==0){

                quadrasRepository.save(Quadra.builder()
                        .id(quadra.getId())
                        .partidas(quadra.getPartidas())
                        .name(quadra.getName())
                        .descricao(quadra.getDescricao())
                        .latitude(quadra.getLatitude())
                        .longitude(quadra.getLongitude())
                        .endereco(quadra.getEndereco())
                        .url(quadra.getUrl())
                        .media(avaliarQuadraRepository.mediaDeNotas(quadra.getId()))
                        .quantidade(1)
                        .build());

                return true;
            }
            else {
                Integer quantidade = quadra.getQuantidade() + 1;

                quadrasRepository.save(Quadra.builder()
                        .id(quadra.getId())
                        .partidas(quadra.getPartidas())
                        .name(quadra.getName())
                        .descricao(quadra.getDescricao())
                        .latitude(quadra.getLatitude())
                        .longitude(quadra.getLongitude())
                        .endereco(quadra.getEndereco())
                        .url(quadra.getUrl())
                        .media(avaliarQuadraRepository.mediaDeNotas(quadra.getId()))
                        .quantidade(quantidade)
                        .build());

                return true;
            }
        }
    }

    public Quadra alterarQuadra(RequestModificarQuadra requestModificarQuadra){
        Quadra quadra = quadrasRepository.findById(requestModificarQuadra.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("partida não encontrado")));
        String name=null; String descricao=null; Double latitude=0.0; Double longitude=0.0; String endereco=null; String url=null; Float media=null; Integer quantidade=null;

        if(!(requestModificarQuadra.getName()==null))
            name = requestModificarQuadra.getName();
        else name=quadra.getName();
        if(!(requestModificarQuadra.getDescricao()==null))
            descricao=requestModificarQuadra.getDescricao();
        else descricao= quadra.getDescricao();
        if(!(requestModificarQuadra.getLatitude()==0.0))
            latitude=requestModificarQuadra.getLatitude();
        else latitude=quadra.getLatitude();
        if(!(requestModificarQuadra.getLongitude()==0.0))
            longitude=requestModificarQuadra.getLongitude();
        else longitude=quadra.getLongitude();
        if(!(requestModificarQuadra.getEndereco()==null))
            endereco=requestModificarQuadra.getEndereco();
        else endereco= quadra.getEndereco();
        if(!(requestModificarQuadra.getUrl()==null))
            url=requestModificarQuadra.getUrl();
        else url=quadra.getUrl();

        quadrasRepository.save(Quadra.builder()
                .id(quadra.getId())
                .name(name)
                .descricao(descricao)
                .latitude(latitude)
                .longitude(longitude)
                .endereco(endereco)
                .url(url)
                .media(quadra.getMedia())
                .quantidade(quadra.getQuantidade())
                .build());

        return quadra;
    }

    public Float mediaDeNotas(Long id){
       // DecimalFormat df= new DecimalFormat("#.000");
       // Float media = Float.valueOf(df.format(avaliarQuadraRepository.mediaDeNotas(id)));
        Quadra quadra = quadrasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("quadra não encontrado")));
        return avaliarQuadraRepository.mediaDeNotas(quadra.getId());
    }

    public int Quantidade(Long id){
        Quadra quadra = quadrasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("quadra não encontrado")));

        List<Long> quantidade = avaliarQuadraRepository.quantidade(quadra.getId());

        return quadra.getQuantidade();
    }
}