package lemonadeStand.mappers;

import lemonadeStand.entities.LemonadeStand;
import lemonadeStand.model.LemonadeStandDto;
import lemonadeStand.model.LemonadeStandRequestDto;
import lemonadeStand.model.LemonadeStandResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LemonadeStandMapper {

    LemonadeStand requestDtoToEntity(LemonadeStandRequestDto lemonadeStandRequestDto);

    LemonadeStand lemonadeStandDtoToEntity(LemonadeStandDto lemonadeStandDto);

    LemonadeStandResponseDto entityToResponseDto(LemonadeStand lemonadeStand);

    List<LemonadeStandResponseDto> entitiesToResponseDtos(List<LemonadeStand> lemonadeStands);

}
