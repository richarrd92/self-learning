package lemonadeStand.repositories;

import lemonadeStand.entities.LemonadeStand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LemonadeStandRepository extends JpaRepository<LemonadeStand, Long> {

}
