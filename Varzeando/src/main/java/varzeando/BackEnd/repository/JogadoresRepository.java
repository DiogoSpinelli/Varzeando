package varzeando.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.Jogador;
import varzeando.BackEnd.models.Partida;

import java.util.List;

@Repository
public interface JogadoresRepository extends JpaRepository<Jogador, Long> {
    @Query(value = "select jogadores.* from jogadores where partida_id=?1",nativeQuery = true)
    List<Jogador> jogadoresPartida(Long id);

    @Query(value = "select * from  jogadores where usuario_id=?1 and partida_id=?2",nativeQuery = true)
    Jogador buscarJogador(Long idUsuario, Long idPartida);

//    @Query(value = "select jogadores.* from  partidas inner join jogadores on partida_id=partidas.id inner join usuarios on jogadores.usuario_id=usuarios.id where (partidas.id=?2 and usuarios.id=?1) limit 1",nativeQuery = true)
//    Jogador buscarJogador(Long idUsuario, Long idPartida);

}
