package varzeando.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import varzeando.BackEnd.models.AvaliarQuadra;
import varzeando.BackEnd.models.AvaliarUsuario;

public interface AvaliarUsuarioRepository extends JpaRepository<AvaliarUsuario, Long> {

    @Query(value = "select id from Avaliar_Usuario where avaliado_id=?1 and usuario_id=?2", nativeQuery = true)
    Long buscarAvaliadoeUsuario(Long idAvaliado, Long idUsuario);
}
