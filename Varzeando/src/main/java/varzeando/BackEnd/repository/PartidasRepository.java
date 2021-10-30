package varzeando.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.Jogador;
import varzeando.BackEnd.models.Partida;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PartidasRepository extends JpaRepository<Partida, Long> {
    @Query(value = "select partidas.* from partidas inner join quadras on quadra_id=quadras.id order by sqrt((?1-quadras.latitude)^2+(?2-quadras.longitude)^2) desc limit 5", nativeQuery = true)
    List<Partida> partidasProximas(Double latitude, Double longitude);
    // inner join quadras on quadras.id=?3 order by sqrt((11-latitude)^2+(11-longitude)^2) asc limit 5

    @Query(value = "select partidas.* from  partidas inner join Jogadores on partida_id=partidas.id inner join usuarios on usuarios.id=usuario_id where usuarios.id=?1", nativeQuery = true)
    List<Partida> partidasUsuario(Long id);

    @Query(value = "select partidas.* from  partidas inner join quadras on quadra_id=quadras.id where quadras.id=?1",nativeQuery = true)
    List<Partida> partidasQuadra(Long id);

    @Query(value = "select partida.* from partidas", nativeQuery = true)
    List<Partida> partidas();

    @Query(value = "select partidas.* from  partidas inner join Jogadores on partida_id=partidas.id inner join usuarios on usuarios.id=usuario_id where usuarios.id=?1 and data_fim<?2", nativeQuery = true)
    List<Partida> partidasRealizadas(Long idUsuario, LocalDateTime agora);

    @Query(value = "select partidas.* from  partidas where ?1<=data_inicio and ?2>=data_inicio or ?1<=data_fim and ?2>=data_fim", nativeQuery = true)
    List<Partida> verificarHorario(Date dataInicio, Date dataFim);

    @Query(value = "select partidas.* from  partidas where (data_inicio between ?1 and ?2) and (data_fim between ?1 and ?2)", nativeQuery = true)
    List<Partida> partidasSemana(LocalDate hoje, LocalDate depois);

    @Query(value = "select jogadores.* from jogadores where partida_id=?1 and usuario_id=?2", nativeQuery = true)
    List<Jogador> verificarJogador(Long idPartida, Long idUsuario);
}
