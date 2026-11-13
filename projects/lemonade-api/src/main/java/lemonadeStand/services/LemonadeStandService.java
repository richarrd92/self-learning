package lemonadeStand.services;

import lemonadeStand.entities.LemonadeStand;
import lemonadeStand.model.LemonadeStandRequestDto;
import lemonadeStand.model.LemonadeStandResponseDto;

import java.util.List;

public interface LemonadeStandService {

    LemonadeStand getLemonadeStand(Long id);

    LemonadeStandResponseDto createLemonadeStand(LemonadeStandRequestDto lemonadeStandRequestDto);

    List<LemonadeStandResponseDto> getAllLemonadeStands();

    LemonadeStandResponseDto getLemonadeStandById(Long id);

    LemonadeStandResponseDto updateLemonadeStand(Long id, LemonadeStandRequestDto lemonadeStandRequestDto);

    LemonadeStandResponseDto deleteLemonadeStand(Long id);

}
