package varzeando.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.AvaliarQuadra;

import java.util.List;

@Repository
public interface AvaliarQuadraRepository extends JpaRepository<AvaliarQuadra, Long> {
    @Query(value = "select id from Avaliar_Quadra where quadra_id=?2 and usuario_id=?1",nativeQuery = true)
    Long buscarQuadraeUsuario(Long idUsuario,Long idQuadra);
    @Query(value = "select round( cast( avg(nota) as numeric), 2  ) from avaliar_quadra where quadra_id=?1", nativeQuery = true)
    Float mediaDeNotas(Long id);
    @Query(value = "select Avaliar_Quadra.* from Avaliar_Quadra where quadra_id=?1", nativeQuery = true)
    List<Long> quantidade(Long id);
}