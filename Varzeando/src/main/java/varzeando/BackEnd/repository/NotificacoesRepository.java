package varzeando.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.Jogador;
import varzeando.BackEnd.models.Notificacao;

import java.util.List;

@Repository
public interface NotificacoesRepository extends JpaRepository<Notificacao, Long> {

    @Query(value = "select notificacoes.* from notificacoes where destinatario_id=?1", nativeQuery = true)
    List<Notificacao> notificacoesRecebidas(Long id);

    @Query(value = "select notificacoes.* from notificacoes where remetente_id=?1", nativeQuery = true)
    List<Notificacao> notificacoesEnviadas(Long id);

}
