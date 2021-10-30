package varzeando.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import varzeando.BackEnd.models.Quadra;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuadrasRepository extends JpaRepository<Quadra,Long> {
    @Query(value = "select * from quadras order by sqrt((?1-latitude)^2+(?2-longitude)^2) asc limit 5", nativeQuery = true)
    List<Quadra> quadrasProximas(Double latitude, Double longitude);
}