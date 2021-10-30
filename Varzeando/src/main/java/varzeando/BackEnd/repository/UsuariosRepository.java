package varzeando.BackEnd.repository;



import org.assertj.core.util.Lists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.Usuario;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    Usuario findByName(String name);
    boolean existsByEmail(String email);

    @Query(value = "select * from usuarios where name like %?1% ", nativeQuery = true)
    List<Usuario> todosMesmoNome(String nome);
}



