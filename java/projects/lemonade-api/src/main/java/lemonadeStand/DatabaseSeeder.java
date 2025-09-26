package lemonadeStand;

import lemonadeStand.entities.Lemonade;
import lemonadeStand.repositories.LemonadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private LemonadeRepository lemonadeRepository;

    @Override
    public void run(String... args) throws Exception {
        Lemonade lemonade = new Lemonade();
        lemonade.setLemonJuice(3.25);
        lemonade.setWater(2.5);
        lemonade.setSugar(1.25);
        lemonade.setIceCubes(5);
        lemonade.setPrice(4.50);

        lemonadeRepository.saveAndFlush(lemonade);

        System.out.println(lemonadeRepository.findAll());
    }

}
