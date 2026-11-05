package lemonadeStand.repositories;

import lemonadeStand.entities.Lemonade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LemonadeRepository extends JpaRepository<Lemonade, Long> {

    Optional<Lemonade> findByIdAndDeletedFalse(Long id);

    List<Lemonade> findAllByDeletedFalse();

}
