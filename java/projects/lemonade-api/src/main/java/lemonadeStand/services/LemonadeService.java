package lemonadeStand.services;

import lemonadeStand.entities.Lemonade;
import lemonadeStand.model.LemonadeRequestDto;
import lemonadeStand.model.LemonadeResponseDto;

import java.util.List;

public interface LemonadeService {

    Lemonade getLemonade(Long id);

    List<LemonadeResponseDto> getAllLemonades();

    LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto);

    LemonadeResponseDto getLemonadeById(Long id);

    LemonadeResponseDto updateLemonade(Long id, LemonadeRequestDto lemonadeRequestDto);

    LemonadeResponseDto deleteLemonade(Long id);

}
